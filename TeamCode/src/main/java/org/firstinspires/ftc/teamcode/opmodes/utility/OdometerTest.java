package org.firstinspires.ftc.teamcode.opmodes.utility;

import com.qualcomm.ftccommon.ViewLogsActivity;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.westtorrancerobotics.lib.Angle;
import org.westtorrancerobotics.lib.Location;

@TeleOp(name = "Odometry", group = "none")
public class OdometerTest extends MecanumTeleop {

    private Location myLocation;
    private Wheel leftY;
    private Wheel rightY;
    private Wheel x;

    private final double TICKS_TO_INCHES = (2 * Math.PI) / 4096;

    @Override
    public void init() {
        super.init();
        myLocation = new Location(0, 0, new Angle(0, Angle.AngleUnit.DEGREES, Angle.AngleOrientation.COMPASS_HEADING));
        leftY = new Wheel(new Location(0,0,
                new Angle(0, Angle.AngleUnit.DEGREES, Angle.AngleOrientation.COMPASS_HEADING)),"");
        rightY = new Wheel(new Location(0,0,
                new Angle(0, Angle.AngleUnit.DEGREES, Angle.AngleOrientation.COMPASS_HEADING)),"");
        x = new Wheel(new Location(0,0,
                new Angle(0, Angle.AngleUnit.DEGREES, Angle.AngleOrientation.COMPASS_HEADING)),
                "");
    }

    @Override
    public void loop() {
        super.loop();
        if ((leftY.encoder.getCurrentPosition() - leftY.lastEnc) == (rightY.encoder.getCurrentPosition() - rightY.lastEnc)) {
            long dy = (leftY.encoder.getCurrentPosition() - leftY.lastEnc);
            long dx = (x.encoder.getCurrentPosition() - x.lastEnc);
            leftY.lastEnc += dy;
            rightY.lastEnc += dy;
            x.lastEnc += dx;
            myLocation.translate(dx * TICKS_TO_INCHES, dy * TICKS_TO_INCHES);
        }
        Solution solved = solve(leftY.getABCD(), rightY.getABCD(), x.getABCD());
        double rotCenterRelX = solved.xc;
        double rotCenterRelY = solved.yc;
        double rotRadCcw = solved.tr;
        myLocation.direction = new Angle(
                myLocation.direction.getValue(Angle.AngleUnit.RADIANS, Angle.AngleOrientation.COMPASS_HEADING) - rotRadCcw,
                Angle.AngleUnit.RADIANS,
                Angle.AngleOrientation.COMPASS_HEADING
                );
        myLocation.direction.getValue(Angle.AngleUnit.DEGREES, Angle.AngleOrientation.COMPASS_HEADING);
        double diffx = -rotCenterRelX;
        double diffy = -rotCenterRelY;
        double atan = Math.atan2(diffy, diffx);
        atan += rotRadCcw;
        double radius = Math.hypot(diffx, diffy);
        double ndiffx = radius * Math.cos(atan);
        double ndiffy = radius * Math.sin(atan);
        myLocation.translate(ndiffx - diffx, ndiffy - diffy);
        telemetry.addData("Odometer Value", myLocation);
    }

    private class Wheel {
        private final Location relativeLocation;
        private final DcMotor encoder;
        private long lastEnc;

        public Wheel (Location relativeLocation, String hwMapName) {
            this.relativeLocation = relativeLocation;
            this.encoder = hardwareMap.get(DcMotor.class, hwMapName);
            lastEnc = encoder.getCurrentPosition();
        }

        public WheelEquation getABCD() {
            double xw = relativeLocation.x;
            double yw = relativeLocation.y;
            double tw = relativeLocation.direction.getValue(Angle.AngleUnit.RADIANS, Angle.AngleOrientation.UNIT_CIRCLE);
            double deltaEnc = encoder.getCurrentPosition() - lastEnc;
            lastEnc += deltaEnc;
            deltaEnc *= TICKS_TO_INCHES;
            return new WheelEquation(-Math.sin(tw), Math.cos(tw), xw*Math.sin(tw)-yw*Math.cos(tw), deltaEnc);
        }
    }

    private class WheelEquation {
        private final double a, b, c, d;
        public WheelEquation(double a, double b, double negD, double negC) {
            this.a = a;
            this.b = b;
            this.c = -negC;
            this.d = -negD;
        }
    }

    public Solution solve(WheelEquation one, WheelEquation two, WheelEquation three) {
        double [][] augmentedMatrix = new double[][]{
                {one.a, one.b, one.c, one.d},
                {one.a, one.b, one.c, one.d},
                {one.a, one.b, one.c, one.d}
        };
        int numWheels = 3;
        for (int i = 0; i < numWheels; i++) {
            double pivot = augmentedMatrix[i][i];
            for (int j = i + 1; j < numWheels; j++) {
                double scalar = -pivot/augmentedMatrix[j][i];
                if (scalar == 0 || augmentedMatrix[j][i] == 0) {
                    break;
                }
                for (int k = 0; k <= numWheels; k++) {
                    augmentedMatrix[j][k] *= scalar;
                }
                for (int k = 0; k <= numWheels; k++) {
                    augmentedMatrix[j][k] += augmentedMatrix[i][k];
                }
                augmentedMatrix[j][i] = 0;
            }
            if (pivot == 0) {
                continue;
            }
            for (int k = 0; k <= numWheels; k++) {
                augmentedMatrix[i][k] /= pivot;
            }
        }
        for (int i = numWheels - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                double[] newRow = new double[numWheels + 1];
                for (int k = 0; k <= numWheels; k++) {
                    newRow[k] = augmentedMatrix[j][i]*augmentedMatrix[i][k];
                }
                for (int k = 0; k <= numWheels; k++) {
                    augmentedMatrix[j][k] -= newRow[k];
                }
                augmentedMatrix[j][i] = 0;
            }
        }
        double[] solutions = new double[numWheels];
        for (int k = 0; k < numWheels; k++) {
            solutions[k] = augmentedMatrix[k][numWheels];
        }
        return new Solution(solutions[0], solutions[1], solutions[2]);
    }

    private class Solution {
        private final double xc, yc, tr;
        public Solution(double xc, double yc, double invtr) {
            this.xc = xc;
            this.yc = yc;
            this.tr = 1 / invtr;
        }
    }

}
