package org.firstinspires.ftc.teamcode.Skystone.Test;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Skystone.Robot;

@Autonomous(name="Spline test", group="Linear Opmode")
public class SplineTest extends LinearOpMode
{
    @Override
    public void runOpMode(){

        double[][] arc = {
                {      0.000000000000,       0.000000000000,       0.000000000000,       0.000000000000,       0.000000000000,       0.000000000000,      90.000000000000},
                {      9.507008464052,      19.858247892180,       0.057442181877,       0.495035879104,       0.273051654648,       0.570350543588,      88.611141760174},
                {     15.140294929793,      32.509619114390,       0.080834347563,       0.980359480838,       0.561328557003,       1.182849454417,      87.194750502860},
                {     17.674884912219,      39.182315171216,       0.098524026819,       1.456258606283,       0.851573560073,       1.816953115801,      85.750852689066},
                {     18.212363951468,      41.742193157437,       0.114106367501,       1.923020719570,       1.131177229037,       2.447449744945,      84.279541207334},
                {     17.781184966659,      42.218815072311,       0.129370102067,       2.380932756538,       1.405875217417,       3.088229016684,      82.780979175130},
                {     17.329022329807,      42.670977712362,       0.144348374093,       2.830280917322,       1.668820335307,       3.723980220970,      81.255403367008},
                {     16.870948901582,      43.129051143800,       0.159050691612,       3.271350442907,       1.920229753408,       4.354709854683,      79.703127173506},
                {     16.408179668155,      43.591820380402,       0.173486522713,       3.704425375915,       2.160435693006,       4.980653781779,      78.124542992225},
                {     15.942037753056,      44.057962298579,       0.187665287053,       4.129788306129,       2.389778747595,       5.602036588327,      76.520123952457},
                {     15.473946403975,      44.526053650577,       0.201596346899,       4.547720101522,       2.608607725308,       6.219071202139,      74.890424877660},
                {     15.005418302891,      44.994581754348,       0.215288997710,       4.958499625841,       2.817279374239,       6.831958602613,      73.236082396162},
                {     14.538042177854,      45.461957881772,       0.228752458319,       5.362403444095,       3.016157982449,       7.440887631697,      71.557814120212},
                {     14.073466785340,      45.926533276302,       0.241995860768,       5.759705517585,       3.205614846395,       8.046034915535,      69.856416826726},
                {     13.613382432046,      46.386617631171,       0.255028239864,       6.150676890451,       3.386027603876,       8.647564904598,      68.132763590046},
                {     13.159500310070,      46.840499754221,       0.267858522514,       6.535585369962,       3.557779430349,       9.245630037966,      66.387799837263},
                {     12.713530024527,      47.286470040283,       0.280495516953,       6.914695203124,       3.721258100575,       9.840371034880,      64.622538319928},
                {     12.277155791958,      47.722844272776,       0.292947901927,       7.288266752362,       3.876854920858,      10.431917313879,      62.838053021487},
                {     11.852011875236,      48.147988188801,       0.305224215960,       7.656556173334,       4.024963540669,      11.020387536802,      61.035472046895},
                {     11.439657889849,      48.560342172863,       0.317332846784,       8.019815098070,       4.165978655905,      11.605890271805,      59.215969568427},
                {     11.041554662135,      48.958445398637,       0.329282021075,       8.378290326781,       4.300294619427,      12.188524766445,      57.380756928807},
                {     10.659041337728,      49.340958720524,       0.341079794575,       8.732223531779,       4.428303977637,      12.768381818932,      55.531073028111},
                {     10.293314425503,      49.706685629700,       0.352734042746,       9.081850976926,       4.550395954559,      13.345544732966,      53.668174143490},
                {      9.945409417749,      50.054590633948,       0.364252452050,       9.427403256039,       4.666954907065,      13.920090339301,      51.793323349419},
                {      9.616185552426,      50.383814495396,       0.375642511966,       9.769105053517,       4.778358776446,      14.492090065443,      49.907779720103},
                {      9.306314181625,      50.693685862052,       0.386911507859,      10.107174930318,       4.884977562343,      15.061611033663,      48.012787504183},
                {      9.016271086929,      50.983728952440,       0.398066514787,      10.441825138147,       4.987171845145,      15.628717166982,      46.109565464505},
                {      8.746332944166,      51.253667090838,       0.409114392331,      10.773261464460,       5.085291382242,      16.193470282923,      44.199296572426},
                {      8.496577994784,      51.503422035903,       0.420061780533,      11.101683110519,       5.179673802132,      16.755931155510,      42.283118237052},
                {      8.266890836899,      51.733109189617,       0.430915096995,      11.427282604376,       5.270643418247,      17.316160527420,      40.362113235463},
                {      8.056971113810,      51.943028908762,       0.441680535191,      11.750245750261,       5.358510181723,      17.874220055977,      38.437301491063},
                {      7.866345758322,      52.133654260599,       0.452364064029,      12.070751615418,       5.443568789228,      18.430173179008,      36.509632824730},
                {      7.694384353086,      52.305615662528,       0.462971428683,      12.388972555018,       5.526097958510,      18.984085889110,      34.579980778369},
                {      7.540317094093,      52.459682918584,       0.473508152694,      12.705074275355,       5.606359880781,      19.536027407662,      32.649137584039},
                {      7.403254798366,      52.596745211757,       0.483979541353,      13.019215935121,       5.684599855400,      20.086070752693,      30.717810325123},
                {      7.282210377637,      52.717789630311,       0.494390686321,      13.331550284176,       5.761046108838,      20.634293197459,      28.786618310014},
                {      7.176121205772,      52.823878800360,       0.504746471478,      13.642223838892,       5.835909796643,      21.180776619160,      26.856091654534},
                {      7.083871835717,      52.916128168929,       0.515051579942,      13.951377092797,       5.909385184134,      21.725607739535,      24.926671047426},
                {      7.004316567674,      52.995683435782,       0.525310502216,      14.259144761015,       5.981649999039,      22.268878261106,      22.998708654360},
                {      6.936301429364,      53.063698573157,       0.535527545406,      14.565656056728,       6.052865947128,      22.810684904475,      21.072470100303},
                {      6.878685196700,      53.121314805102,       0.545706843439,      14.871034997718,       6.123179380222,      23.351129353382,      19.148137457940},
                {      6.830359154193,      53.169640847067,       0.555852368211,      15.175400740863,       6.192722104750,      23.890318115159,      17.225813161051},
                {      6.790265364776,      53.209734636084,       0.565967941595,      15.478867942385,       6.261612318179,      24.428362304786,      15.305524756209},
                {      6.757413284725,      53.242586715846,       0.576057248233,      15.781547141538,       6.329955660246,      24.965377361032,      13.387230403370},
                {      6.730894618302,      53.269105382065,       0.586123849028,      16.083545165373,       6.397846365772,      25.501482703180,      11.470825035707},
                {      6.709896356531,      53.290103643694,       0.596171195256,      16.384965552208,       6.465368506023,      26.036801336603,       9.556147090611},
                {      6.693711984102,      53.306288016027,       0.606202643229,      16.685908991413,       6.532597305885,      26.571459415151,       7.642985726897},
                {      6.681750867248,      53.318249132819,       0.616221469419,      16.986473777108,       6.599600524644,      27.105585767784,       5.731088447262},
                {      6.673545853897,      53.326454146131,       0.626230885963,      17.286756273434,       6.666439888620,      27.639311396460,       3.820169049426},
                {      6.668759126187,      53.331240873820,       0.636234056483,      17.586851389022,       6.733172564541,      28.172768951717,       1.909915833860},
                {      6.667186345812,      53.332813654189,       0.646234112127,      17.886853058351,       6.799852662935,      28.706092191980,       0.000000000000},
                {      6.668759126187,      53.331240873820,       0.656234167771,      18.186854727680,       6.866532761330,      29.239415432243,      -1.909915833860},
                {      6.673545853897,      53.326454146131,       0.666237338291,      18.486949843268,       6.933265437250,      29.772872987499,      -3.820169049426},
                {      6.681750867248,      53.318249132819,       0.676246754835,      18.787232339595,       7.000104801227,      30.306598616176,      -5.731088447262},
                {      6.693711984102,      53.306288016028,       0.686265581025,      19.087797125290,       7.067108019985,      30.840724968809,      -7.642985726897},
                {      6.709896356531,      53.290103643694,       0.696297028999,      19.388740564494,       7.134336819848,      31.375383047356,      -9.556147090611},
                {      6.730894618302,      53.269105382065,       0.706344375226,      19.690160951329,       7.201858960098,      31.910701680780,     -11.470825035707},
                {      6.757413284725,      53.242586715846,       0.716410976021,      19.992158975164,       7.269749665624,      32.446807022927,     -13.387230403370},
                {      6.790265364776,      53.209734636084,       0.726500282659,      20.294838174317,       7.338093007691,      32.983822079174,     -15.305524756209},
                {      6.830359154193,      53.169640847067,       0.736615856043,      20.598305375840,       7.406983221121,      33.521866268801,     -17.225813161051},
                {      6.878685196700,      53.121314805102,       0.746761380815,      20.902671118984,       7.476525945648,      34.061055030578,     -19.148137457940},
                {      6.936301429364,      53.063698573157,       0.756940678848,      21.208050059974,       7.546839378743,      34.601499479485,     -21.072470100303},
                {      7.004316567674,      52.995683435782,       0.767157722038,      21.514561355688,       7.618055326831,      35.143306122854,     -22.998708654360},
                {      7.083871835717,      52.916128168929,       0.777416644312,      21.822329023905,       7.690320141737,      35.686576644425,     -24.926671047426},
                {      7.176121205772,      52.823878800360,       0.787721752776,      22.131482277810,       7.763795529227,      36.231407764800,     -26.856091654534},
                {      7.282210377637,      52.717789630311,       0.798077537933,      22.442155832526,       7.838659217032,      36.777891186500,     -28.786618310014},
                {      7.403254798366,      52.596745211757,       0.808488682901,      22.754490181582,       7.915105470471,      37.326113631267,     -30.717810325123},
                {      7.540317094093,      52.459682918584,       0.818960071560,      23.068631841347,       7.993345445089,      37.876156976298,     -32.649137584039},
                {      7.694384353086,      52.305615662528,       0.829496795572,      23.384733561684,       8.073607367361,      38.428098494850,     -34.579980778369},
                {      7.866345758322,      52.133654260599,       0.840104160225,      23.702954501284,       8.156136536643,      38.982011204951,     -36.509632824730},
                {      8.056971113810,      51.943028908762,       0.850787689063,      24.023460366441,       8.241195144147,      39.537964327982,     -38.437301491063},
                {      8.266890836899,      51.733109189617,       0.861553127260,      24.346423512326,       8.329061907624,      40.096023856540,     -40.362113235463},
                {      8.496577994784,      51.503422035903,       0.872406443722,      24.672023006183,       8.420031523738,      40.656253228450,     -42.283118237052},
                {      8.746332944166,      51.253667090838,       0.883353831923,      25.000444652242,       8.514413943629,      41.218714101037,     -44.199296572426},
                {      9.016271086929,      50.983728952439,       0.894401709467,      25.331880978555,       8.612533480726,      41.783467216977,     -46.109565464505},
                {      9.306314181625,      50.693685862052,       0.905556716395,      25.666531186384,       8.714727763527,      42.350573350297,     -48.012787504183},
                {      9.616185552426,      50.383814495396,       0.916825712288,      26.004601063185,       8.821346549425,      42.920094318516,     -49.907779720103},
                {      9.945409417749,      50.054590633948,       0.928215772204,      26.346302860663,       8.932750418806,      43.492094044659,     -51.793323349419},
                {     10.293314425503,      49.706685629700,       0.939734181508,      26.691855139776,       9.049309371312,      44.066639650994,     -53.668174143490},
                {     10.659041337728,      49.340958720524,       0.951388429680,      27.041482584923,       9.171401348234,      44.643802565027,     -55.531073028111},
                {     11.041554662135,      48.958445398637,       0.963186203179,      27.395415789921,       9.299410706443,      45.223659617515,     -57.380756928807},
                {     11.439657889849,      48.560342172863,       0.975135377470,      27.753891018633,       9.433726669965,      45.806294112155,     -59.215969568427},
                {     11.852011875236,      48.147988188800,       0.987244008294,      28.117149943368,       9.574741785201,      46.391796847157,     -61.035472046895},
                {     12.277155791958,      47.722844272775,       0.999520322327,      28.485439364340,       9.722850405013,      46.980267070081,     -62.838053021487},
                {     12.713530024527,      47.286470040283,       1.011972707301,      28.859010913578,       9.878447225296,      47.571813349080,     -64.622538319928},
                {     13.159500310070,      46.840499754220,       1.024609701740,      29.238120746740,      10.041925895521,      48.166554345994,     -66.387799837263},
                {     13.613382432046,      46.386617631171,       1.037439984390,      29.623029226252,      10.213677721995,      48.764619479361,     -68.132763590046},
                {     14.073466785340,      45.926533276301,       1.050472363486,      30.014000599117,      10.394090479475,      49.366149468425,     -69.856416826726},
                {     14.538042177854,      45.461957881772,       1.063715765936,      30.411302672608,      10.583547343421,      49.971296752263,     -71.557814120212},
                {     15.005418302891,      44.994581754348,       1.077179226544,      30.815206490861,      10.782425951632,      50.580225781347,     -73.236082396162},
                {     15.473946403974,      44.526053650577,       1.090871877355,      31.225986015181,      10.991097600562,      51.193113181820,     -74.890424877660},
                {     15.942037753056,      44.057962298579,       1.104802937201,      31.643917810573,      11.209926578275,      51.810147795632,     -76.520123952457},
                {     16.408179668155,      43.591820380402,       1.118981701542,      32.069280740787,      11.439269632864,      52.431530602181,     -78.124542992225},
                {     16.870948901582,      43.129051143799,       1.133417532642,      32.502355673795,      11.679475572462,      53.057474529277,     -79.703127173506},
                {     17.329022329807,      42.670977712362,       1.148119850161,      32.943425199380,      11.930884990563,      53.688204162990,     -81.255403367008},
                {     17.781184966658,      42.218815072311,       1.163098122188,      33.392773360164,      12.193830108454,      54.323955367275,     -82.780979175130},
                {     18.226335399881,      41.773664635945,       1.178361856753,      33.850685397132,      12.468634725074,      54.964974825162,     -84.279541207334},
                {     18.663488815155,      41.336511217622,       1.193920593863,      34.317447510419,      12.755614263564,      55.611519513779,     -85.750852689066},
                {     19.091777820856,      40.908222208996,       1.209783898044,      34.793346635865,      13.055075903114,      56.263856125617,     -87.194750502860},
                {     19.510451321038,      40.489548706034,       1.225961351435,      35.278670237599,      13.367318784483,      56.922260448177,     -88.611141760174},
                {     30.000000012860,      30.000000012860,       1.242462547405,      35.773706116702,      13.775809614498,      57.503841376805,     -90.000000000000},
                {     40.489548706034,      19.510451321038,       1.258963743376,      36.268741995806,      14.357390543126,      57.912332206820,     -88.611141760174},
                {     40.908222208996,      19.091777820856,       1.275141196767,      36.754065597540,      15.015794865686,      58.224575088188,     -87.194750502860},
                {     41.336511217622,      18.663488815155,       1.291004500948,      37.229964722986,      15.668131477523,      58.524036727739,     -85.750852689066},
                {     41.773664635946,      18.226335399880,       1.306563238058,      37.696726836273,      16.314676166141,      58.811016266229,     -84.279541207334},
                {     42.218815072311,      17.781184966659,       1.321826972623,      38.154638873240,      16.955695624027,      59.085820882849,     -82.780979175130},
                {     42.670977712362,      17.329022329807,       1.336805244650,      38.603987034024,      17.591446828313,      59.348766000739,     -81.255403367008},
                {     43.129051143799,      16.870948901582,       1.351507562169,      39.045056559610,      18.222176462026,      59.600175418840,     -79.703127173506},
                {     43.591820380402,      16.408179668155,       1.365943393269,      39.478131492618,      18.848120389122,      59.840381358438,     -78.124542992225},
                {     44.057962298578,      15.942037753056,       1.380122157610,      39.903494422832,      19.469503195670,      60.069724413027,     -76.520123952457},
                {     44.526053650577,      15.473946403974,       1.394053217456,      40.321426218224,      20.086537809482,      60.288553390740,     -74.890424877660},
                {     44.994581754348,      15.005418302891,       1.407745868267,      40.732205742543,      20.699425209955,      60.497225039671,     -73.236082396162},
                {     45.461957881771,      14.538042177854,       1.421209328875,      41.136109560797,      21.308354239040,      60.696103647881,     -71.557814120212},
                {     45.926533276301,      14.073466785340,       1.434452731325,      41.533411634288,      21.913501522878,      60.885560511827,     -69.856416826726},
                {     46.386617631171,      13.613382432046,       1.447485110421,      41.924383007153,      22.515031511941,      61.065973269308,     -68.132763590046},
                {     46.840499754220,      13.159500310070,       1.460315393071,      42.309291486665,      23.113096645309,      61.237725095781,     -66.387799837263},
                {     47.286470040282,      12.713530024527,       1.472952387510,      42.688401319826,      23.707837642223,      61.401203766007,     -64.622538319928},
                {     47.722844272775,      12.277155791958,       1.485404772484,      43.061972869064,      24.299383921222,      61.556800586290,     -62.838053021487},
                {     48.147988188801,      11.852011875236,       1.497681086517,      43.430262290037,      24.887854144145,      61.704909206101,     -61.035472046895},
                {     48.560342172863,      11.439657889849,       1.509789717341,      43.793521214772,      25.473356879148,      61.845924321337,     -59.215969568427},
                {     48.958445398637,      11.041554662135,       1.521738891632,      44.151996443484,      26.055991373787,      61.980240284859,     -57.380756928807},
                {     49.340958720524,      10.659041337728,       1.533536665131,      44.505929648482,      26.635848426275,      62.108249643069,     -55.531073028111},
                {     49.706685629700,      10.293314425503,       1.545190913303,      44.855557093629,      27.213011340308,      62.230341619991,     -53.668174143490},
                {     50.054590633948,       9.945409417749,       1.556709322607,      45.201109372741,      27.787556946643,      62.346900572497,     -51.793323349419},
                {     50.383814495396,       9.616185552426,       1.568099382523,      45.542811170220,      28.359556672786,      62.458304441878,     -49.907779720103},
                {     50.693685862052,       9.306314181625,       1.579368378416,      45.880881047020,      28.929077641006,      62.564923227775,     -48.012787504183},
                {     50.983728952439,       9.016271086929,       1.590523385344,      46.215531254849,      29.496183774325,      62.667117510577,     -46.109565464505},
                {     51.253667090838,       8.746332944166,       1.601571262888,      46.546967581163,      30.060936890266,      62.765237047674,     -44.199296572426},
                {     51.503422035903,       8.496577994784,       1.612518651089,      46.875389227221,      30.623397762853,      62.859619467564,     -42.283118237052},
                {     51.733109189617,       8.266890836899,       1.623371967551,      47.200988721079,      31.183627134762,      62.950589083679,     -40.362113235463},
                {     51.943028908762,       8.056971113810,       1.634137405748,      47.523951866964,      31.741686663320,      63.038455847155,     -38.437301491063},
                {     52.133654260599,       7.866345758322,       1.644820934586,      47.844457732121,      32.297639786351,      63.123514454660,     -36.509632824730},
                {     52.305615662528,       7.694384353086,       1.655428299239,      48.162678671721,      32.851552496453,      63.206043623942,     -34.579980778369},
                {     52.459682918584,       7.540317094093,       1.665965023251,      48.478780392058,      33.403494015005,      63.286305546213,     -32.649137584039},
                {     52.596745211756,       7.403254798366,       1.676436411910,      48.792922051823,      33.953537360036,      63.364545520832,     -30.717810325123},
                {     52.717789630311,       7.282210377637,       1.686847556878,      49.105256400879,      34.501759804802,      63.440991774270,     -28.786618310014},
                {     52.823878800360,       7.176121205772,       1.697203342035,      49.415929955595,      35.048243226503,      63.515855462075,     -26.856091654534},
                {     52.916128168929,       7.083871835717,       1.707508450499,      49.725083209500,      35.593074346878,      63.589330849566,     -24.926671047426},
                {     52.995683435782,       7.004316567674,       1.717767372773,      50.032850877717,      36.136344868449,      63.661595664471,     -22.998708654360},
                {     53.063698573156,       6.936301429364,       1.727984415963,      50.339362173431,      36.678151511818,      63.732811612560,     -21.072470100303},
                {     53.121314805102,       6.878685196700,       1.738163713996,      50.644741114420,      37.218595960725,      63.803125045654,     -19.148137457940},
                {     53.169640847067,       6.830359154193,       1.748309238768,      50.949106857565,      37.757784722502,      63.872667770182,     -17.225813161051},
                {     53.209734636085,       6.790265364776,       1.758424812152,      51.252574059088,      38.295828912129,      63.941557983611,     -15.305524756209},
                {     53.242586715846,       6.757413284725,       1.768514118790,      51.555253258241,      38.832843968375,      64.009901325678,     -13.387230403370},
                {     53.269105382064,       6.730894618302,       1.778580719585,      51.857251282075,      39.368949310523,      64.077792031204,     -11.470825035707},
                {     53.290103643694,       6.709896356531,       1.788628065812,      52.158671668911,      39.904267943946,      64.145314171455,      -9.556147090611},
                {     53.306288016027,       6.693711984102,       1.798659513786,      52.459615108115,      40.438926022493,      64.212542971318,      -7.642985726897},
                {     53.318249132819,       6.681750867248,       1.808678339976,      52.760179893810,      40.973052375127,      64.279546190076,      -5.731088447262},
                {     53.326454146131,       6.673545853897,       1.818687756520,      53.060462390137,      41.506778003803,      64.346385554052,      -3.820169049426},
                {     53.331240873820,       6.668759126187,       1.828690927040,      53.360557505725,      42.040235559060,      64.413118229973,      -1.909915833860},
                {     53.332813654189,       6.667186345812,       1.838690982684,      53.660559175054,      42.573558799323,      64.479798328367,       0.000000000000},
                {     53.331240873820,       6.668759126188,       1.848691038328,      53.960560844382,      43.106882039585,      64.546478426762,       1.909915833860},
                {     53.326454146131,       6.673545853897,       1.858694208848,      54.260655959971,      43.640339594842,      64.613211102682,       3.820169049426},
                {     53.318249132819,       6.681750867248,       1.868703625392,      54.560938456297,      44.174065223519,      64.680050466659,       5.731088447262},
                {     53.306288016028,       6.693711984102,       1.878722451582,      54.861503241992,      44.708191576152,      64.747053685417,       7.642985726897},
                {     53.290103643695,       6.709896356531,       1.888753899555,      55.162446681196,      45.242849654699,      64.814282485280,       9.556147090611},
                {     53.269105382064,       6.730894618302,       1.898801245783,      55.463867068032,      45.778168288123,      64.881804625530,      11.470825035707},
                {     53.242586715845,       6.757413284725,       1.908867846578,      55.765865091866,      46.314273630270,      64.949695331057,      13.387230403370},
                {     53.209734636084,       6.790265364776,       1.918957153216,      56.068544291019,      46.851288686517,      65.018038673123,      15.305524756209},
                {     53.169640847067,       6.830359154193,       1.929072726600,      56.372011492542,      47.389332876143,      65.086928886553,      17.225813161051},
                {     53.121314805102,       6.878685196700,       1.939218251372,      56.676377235687,      47.928521637921,      65.156471611081,      19.148137457940},
                {     53.063698573156,       6.936301429364,       1.949397549405,      56.981756176677,      48.468966086828,      65.226785044175,      21.072470100303},
                {     52.995683435782,       7.004316567673,       1.959614592595,      57.288267472390,      49.010772730197,      65.298000992263,      22.998708654360},
                {     52.916128168929,       7.083871835717,       1.969873514869,      57.596035140608,      49.554043251768,      65.370265807169,      24.926671047426},
                {     52.823878800359,       7.176121205772,       1.980178623333,      57.905188394512,      50.098874372143,      65.443741194659,      26.856091654534},
                {     52.717789630311,       7.282210377637,       1.990534408490,      58.215861949229,      50.645357793843,      65.518604882464,      28.786618310014},
                {     52.596745211758,       7.403254798366,       2.000945553458,      58.528196298284,      51.193580238610,      65.595051135903,      30.717810325123},
                {     52.459682918585,       7.540317094093,       2.011416942117,      58.842337958049,      51.743623583641,      65.673291110522,      32.649137584039},
                {     52.305615662527,       7.694384353086,       2.021953666128,      59.158439678386,      52.295565102193,      65.753553032793,      34.579980778369},
                {     52.133654260599,       7.866345758322,       2.032561030782,      59.476660617987,      52.849477812294,      65.836082202075,      36.509632824730},
                {     51.943028908761,       8.056971113810,       2.043244559620,      59.797166483143,      53.405430935325,      65.921140809579,      38.437301491063},
                {     51.733109189616,       8.266890836899,       2.054009997816,      60.120129629029,      53.963490463883,      66.009007573056,      40.362113235463},
                {     51.503422035903,       8.496577994784,       2.064863314278,      60.445729122886,      54.523719835793,      66.099977189171,      42.283118237052},
                {     51.253667090838,       8.746332944166,       2.075810702480,      60.774150768945,      55.086180708380,      66.194359609061,      44.199296572426},
                {     50.983728952440,       9.016271086929,       2.086858580024,      61.105587095258,      55.650933824320,      66.292479146158,      46.109565464505},
                {     50.693685862052,       9.306314181625,       2.098013586952,      61.440237303087,      56.218039957640,      66.394673428959,      48.012787504183},
                {     50.383814495397,       9.616185552426,       2.109282582845,      61.778307179887,      56.787560925859,      66.501292214857,      49.907779720103},
                {     50.054590633949,       9.945409417749,       2.120672642761,      62.120008977366,      57.359560652002,      66.612696084238,      51.793323349419},
                {     49.706685629700,      10.293314425504,       2.132191052065,      62.465561256479,      57.934106258337,      66.729255036744,      53.668174143490},
                {     49.340958720524,      10.659041337728,       2.143845300236,      62.815188701626,      58.511269172370,      66.851347013666,      55.531073028111},
                {     48.958445398638,      11.041554662135,       2.155643073736,      63.169121906623,      59.091126224858,      66.979356371876,      57.380756928807},
                {     48.560342172863,      11.439657889849,       2.167592248027,      63.527597135335,      59.673760719498,      67.113672335397,      59.215969568427},
                {     48.147988188799,      11.852011875235,       2.179700878851,      63.890856060071,      60.259263454500,      67.254687450633,      61.035472046895},
                {     47.722844272776,      12.277155791958,       2.191977192884,      64.259145481043,      60.847733677423,      67.402796070445,      62.838053021487},
                {     47.286470040283,      12.713530024527,       2.204429577858,      64.632717030281,      61.439279956423,      67.558392890728,      64.622538319928},
                {     46.840499754220,      13.159500310070,       2.217066572297,      65.011826863443,      62.034020953336,      67.721871560953,      66.387799837263},
                {     46.386617631170,      13.613382432046,       2.229896854947,      65.396735342954,      62.632086086704,      67.893623387427,      68.132763590046},
                {     45.926533276301,      14.073466785340,       2.242929234043,      65.787706715819,      63.233616075768,      68.074036144907,      69.856416826726},
                {     45.461957881772,      14.538042177854,       2.256172636492,      66.185008789310,      63.838763359606,      68.263493008854,      71.557814120212},
                {     44.994581754348,      15.005418302891,       2.269636097101,      66.588912607564,      64.447692388690,      68.462371617064,      73.236082396162},
                {     44.526053650577,      15.473946403974,       2.283328747912,      66.999692131883,      65.060579789163,      68.671043265994,      74.890424877660},
                {     44.057962298578,      15.942037753056,       2.297259807758,      67.417623927276,      65.677614402975,      68.889872243707,      76.520123952457},
                {     43.591820380401,      16.408179668154,       2.311438572098,      67.842986857489,      66.298997209524,      69.119215298296,      78.124542992225},
                {     43.129051143799,      16.870948901582,       2.325874403199,      68.276061790497,      66.924941136620,      69.359421237895,      79.703127173506},
                {     42.670977712363,      17.329022329807,       2.340576720718,      68.717131316083,      67.555670770333,      69.610830655996,      81.255403367008},
                {     42.218815072310,      17.781184966658,       2.355554992744,      69.166479476867,      68.191421974618,      69.873775773886,      82.780979175130},
                {     41.741908898659,      18.212237757624,       2.370818727310,      69.624391513835,      68.832199076932,      70.148472799171,      84.279541207334},
                {     39.179434583035,      17.673566633948,       2.386401281512,      70.091153627121,      69.462679687381,      70.428069045177,      85.750852689066},
                {     32.503784518571,      15.137566831762,       2.404093452169,      70.567052752567,      70.096795560172,      70.718319131538,      87.194750502860},
                {     19.856968683938,       9.506431356640,       2.427491002007,      71.052376354301,      70.709352226479,      71.006623719449,      88.611141760174},
                {      0.000000000000,       0.000000000000,       2.484913741113,      71.547412233405,      71.279472992573,      71.279566383263,      90.000000000000},
        };



        Robot robot = new Robot(hardwareMap,telemetry,this);
        robot.driveMotorsBreakZeroBehavior();
        robot.resetEncoders();
        waitForStart();
        robot.intializeIMU();
        robot.changeRunModeToUsingEncoder();

        while (opModeIsActive()){
            robot.finalTurn(90);

            sleep(500);
            break;
        }
    }
}