package com.example.puzzle.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.puzzle.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.io.IOException;
import java.util.List;

public class MapsDiseases extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText locationSearch;
    // add to line on map
    private List<Polyline> polyline = null;
    String diseases_name_str = "";
    //add location of hospital clinic ....

    private static final LatLng[] diseases_latlong = {
            new LatLng(9.91, 15.13),
            new LatLng(11.1475, 16.366137), new LatLng(36.18306, 44.01667), new LatLng(30.4158134, 47.6561737), new LatLng(32.612275, 44.611861), new LatLng(14.181, -4.184),
            new LatLng(-13.823705, -72.818961), new LatLng(-13.508855, -72.83628), new LatLng(-15.345539, -69.444653), new LatLng(-13.259708, -74.288481), new LatLng(-3.755148, -73.249111), new LatLng(23.166, 107.696), new LatLng(30.4, 114.83333), new LatLng(25.015, 102.744), new LatLng(22.543447, 114.057818), new LatLng(37.26667, 100.26667), new LatLng(37.26667, 100.26667), new LatLng(37.26667, 100.26667), new LatLng(37.26667, 100.26667), new LatLng(41.095123, 121.12701), new LatLng(33.01667, 96.73306), new LatLng(33.01667, 96.73306), new LatLng(33.01667, 96.73306), new LatLng(33.01667, 96.73306),
            new LatLng(33.01667, 96.73306), new LatLng(34.4774096, 100.2420896), new LatLng(34.4774096, 100.2420896), new LatLng(34.4774096, 100.2420896), new LatLng(34.4774096, 100.2420896), new LatLng(31.482312, 92.056796),
            new LatLng(31.482312, 92.056796), new LatLng(27.0469, 118.3252), new LatLng(36.2810308, 100.6135479), new LatLng(36.2810308, 100.6135479), new LatLng(31.195476, 121.358757), new LatLng(31.098395, 121.370231), new LatLng(45.361394, 118.351096), new LatLng(34.46, 111.11), new LatLng(34.46, 111.11), new LatLng(22.815478, 108.327546), new LatLng(28.675697, 115.909228), new LatLng(36.66853, 117.020359), new LatLng(34.751053, 110.88145), new LatLng(28, 120.7), new LatLng(47.85427, 120.265), new LatLng(30.1676, 31.2091), new LatLng(31.0333333, 30.4666667), new LatLng(31.4, 31.8), new LatLng(5.54972, -1.78306), new LatLng(5.79801, 0.14337), new LatLng(28.6, 77.24), new LatLng(28.6, 77.24), new LatLng(28.66, 77.12), new LatLng(12.31, 76.63), new LatLng(46.301565, 12.317864), new LatLng(46.475696, 12.445388), new LatLng(44.539755, 10.936332), new LatLng(44.539755, 10.936332), new LatLng(42.47, 13.92), new LatLng(45.9352, 13.6193), new LatLng(35.443384, 133.30569), new LatLng(37.396142, 140.368409), new LatLng(34.79506, 135.398083), new LatLng(43.047314, 145.016441), new LatLng(43.047314, 145.016441), new LatLng(33.575012, 133.169472), new LatLng(35.416541, 133.397362), new LatLng(32.131974, 131.392752), new LatLng(32.51135, 131.33031), new LatLng(34.760856, 135.785833), new LatLng(32.090572, 130.352753), new LatLng(33.8206258, 134.4788303), new LatLng(33.141474, 131.658534), new LatLng(36.76019, 139.857095), new LatLng(35.457623, 134.176797), new LatLng(35.428106, 133.330819), new LatLng(38.691913, 141.187814), new LatLng(32.090492, 130.352791), new LatLng(39.695164, 140.116981), new LatLng(35.181428, 136.9064), new LatLng(36.365847, 140.471232), new LatLng(36.365866, 140.471206), new LatLng(39.081895, 141.708553), new LatLng(36.365866, 140.471206), new LatLng(36.302668, 136.314686), new LatLng(32.8474, 13.4427), new LatLng(26.6465, 88.0277), new LatLng(27.69479, 85.29339), new LatLng(26.70165, 87.296111), new LatLng(28.19287, 83.99979), new LatLng(46.8451, -1.8795), new LatLng(43.43, 0.4156), new LatLng(43.595833, -0.325833), new LatLng(43.4211, 0.3642), new LatLng(45.9533, 5.0408), new LatLng(44.093889, 0.675556), new LatLng(46.0652, 5.0733), new LatLng(40.202889, 21.600952), new LatLng(31.4586, 35.3888), new LatLng(32.08180663, 34.81338034), new LatLng(32.833333, 35.333333), new LatLng(31.737025, 34.723035), new LatLng(29.572, 34.972), new LatLng(32.051, 34.8229), new LatLng(32.553673, 34.915682), new LatLng(31.218974, 34.88099), new LatLng(32.83736, 35.386941), new LatLng(32.081697, 34.779489), new LatLng(28.59472, 47.97028), new LatLng(20.80559, -102.76356), new LatLng(20.81, -102.77), new LatLng(52.590556, 14.668334), new LatLng(45.221902, 19.60928), new LatLng(55.942539, 13.377308), new LatLng(55.831129, 13.38718), new LatLng(23.788408, 120.872612), new LatLng(39.81667, 35.18306), new LatLng(41.067222, 36.05), new LatLng(-0.31173552, 32.0024871), new LatLng(23.0861, -82.3106), new LatLng(-7.272192, -78.03655), new LatLng(-7.272192, -78.03655), new LatLng(-7.272192, -78.03655), new LatLng(-6.149923, -79.949254), new LatLng(-35.4271, -71.3743), new LatLng(-35.4271, -71.3743), new LatLng(-35.4271, -71.3743), new LatLng(-35.4271, -71.3743), new LatLng(38.267, 104.744), new LatLng(38.267, 104.744), new LatLng(24.98045, 93.56102), new LatLng(38.36583, 15.958334), new LatLng(3.193631, 40.718828), new LatLng(35.936249, 14.39576), new LatLng(41.760701, 2.030505), new LatLng(49.224476, -124.307099), new LatLng(6.233, -57.35), new LatLng(20.95, -105.354166), new LatLng(27.08565, -104.87136), new LatLng(9.2442, -79.6021), new LatLng(58.963375, 16.11812), new LatLng(-16.789484, 27.0502739), new LatLng(12.859587, 7.700742), new LatLng(51.55, 5.68), new LatLng(36.86, -104.44), new LatLng(36.63, 46.09), new LatLng(50.731411, -2.1696341), new LatLng(14.39611, 0.82444), new LatLng(4.8651, 13.1678), new LatLng(0.99474, 115.937329), new LatLng(55.512, 11.7425), new LatLng(50.12, 5.18), new LatLng(-17.7307, -149.3374), new LatLng(40.828, -73.841), new LatLng(48.313, 62.073), new LatLng(42.654, -111.604), new LatLng(58.4154, 12.0616), new LatLng(29.84, -103.23), new LatLng(49.9, 5.73), new LatLng(24.68, -81.3659),
    };


    private static final String[] diseases_name = {
            "African swine fever", "Contagious bovine pleuropneumonia", "Influenza - Avian", "Influenza - Avian",
            "Foot and mouth disease", "Rift Valley fever", "Rabies", "Rabies", "Classical swine fever", "Anthrax",
            "Rabies", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Rabies", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "African swine fever", "Foot and mouth disease", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Rabies", "Rabies", "Influenza - Avian", "Influenza - Avian", "Bluetongue", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Newcastle disease", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Foot and mouth disease",
            "Rabies", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Rabies", "Influenza - Avian", "Newcastle disease", "Rabies", "Influenza - Avian", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Rabies", "Newcastle disease", "Influenza - Avian",
            "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Rabies", "Influenza - Avian",
            "Rabies", "Influenza - Avian", "Influenza - Avian", "Influenza - Avian", "Rabbit haemorrhagic disease", "Vesicular stomatitis",
            "Vesicular stomatitis", "Vesicular stomatitis", "Avian infectious laryngotracheitis", "Fowl typhoid", "Fowl typhoid",
            "Fowl typhoid", "Fowl typhoid", "Vesicular stomatitis", "Vesicular stomatitis", "Blackleg",
            "Small hive beetle infestation (Aethina tumida)", "Unknown disease", "European foulbrood of honey bees", "COVID-19 (SARS-COV-2)",
            "Rabbit haemorrhagic disease", "Duck virus hepatitis", "Equine encephalomyelitis (Western)", "Rabbit haemorrhagic disease",
            "New world screwworm (Cochliomyia hominivorax)", "Echinococcosis/hydatidosis", "Theileriosis", "Unknown disease",
            "COVID-19 (SARS-COV-2)", "Rabbit haemorrhagic disease", "Botulism", "Unknown disease", "Strangles",
            "Monkey Pox", "Ebola Virus", "Pullorum disease", "Aujeszky's disease", "Avian chlamydiosis", "COVID-19 (SARS-COV-2)",
            "Haemorrhagic Septicaemia", "bacterial diseases", "Echinococcosis/hydatidosis", "Rabbit haemorrhagic disease",
            "American foulbrood of honey bees", "New world screwworm (Cochliomyia hominivorax)",
    };


    private static final String[] diseases_location = {
            "Fianga, Chad, Africa", "unknown, Chad, Africa", "Irbil, Iraq, Asia", "Basrah (Centroid), Iraq, Asia ", "Babil, Iraq, Asia ", "Bofara, Mali, Africa ", "LAMBRANA, Peru, Americas ", "San Pedro de Cachora, Peru, Americas ", "Chipiano, Peru, Americas ", "OCROS, Peru, Americas  ", "Maynas province, Peru, Americas ", "Longan, China, Asia ", "Ezhou, China, Asia ", "Guandu, China, Asia  ", "Shenzhen, China, Asia ", "Gangca Xian, China, Asia ", "Gangca Xian, China, Asia  ", "Gangca Xian, China, Asia  ", "Gangca Xian, China, Asia  ", "Jinzhou, China, Asia ", "Yu-shu Hsien, China, Asia  ", "Yu-shu Hsien, China, Asia", "Yu-shu Hsien, China, Asia", "Yu-shu Hsien, China, Asia", "Yu-shu Hsien, China, Asia", "Ngolog Tibetan Autonomous District, China, Asia", "Ngolog Tibetan Autonomous District, China, Asia  ", "Ngolog Tibetan Autonomous District, China, Asia  ", "Ngolog Tibetan Autonomous District, China, Asia ", "Nagqu, China, Asia ", "Nagqu, China, Asia ", "Jian Ou, China, Asia  ", "Genggahu Lake, China, Asia ", "Genggahu Lake, China, Asia  ", "Shanghai's zoo, China, Asia  ", "Fengzhuang market, China, Asia ", "Nei Mongol Zizhiqu, China, Asia  ", "Yellow River Wetland, Sanmenxia, China, Asia ", "Yellow River Wetland, Sanmenxia, China, Asia  ", "Guangxi, China, Asia ", "Jiangxi, China, Asia", "Shandong, China, Asia ", "Ruicheng County, China, Asia ", "Wenzhou, China, Asia  ", "Sandol forest farm, China, Asia", "Kaliub, Egypt, Africa  ", "Damanhur, Egypt, Africa ", "Ghiet Elnasara, Egypt, Africa ", "Akra, Ghana, Africa ", "Otinibi, Ghana, Africa ", "National Zoological Park (Delhi zoo), India, Asia ", "National Zoological Park (Delhi zoo), India, Asia  ", "Madipur Lake, India, Asia ", "Kukkarahalli Lake, India, Asia ", "OSPITALE DI CADORE, Italy, Europe ", "LOCALITA' LAGO, Italy, Europe ", "Modena, Italy, Europe ", "Modena, Italy, Europe ", "PENNE, Italy, Europe ", "Gorizia, Italy, Europe", "Around the wild bird park, Japan, Asia  ", "Toyoda-cho water purification facility, Japan, Asia", "Zougaike, Japan, Asia", "Maruyamachirippu (swamp), Japan, Asia", "Maruyamachirippu (swamp), Japan, Asia", "Osaki, Japan, Asia", "Akaide, Japan, Asia", "Hokita, Japan, Asia", "Morotsukason, Japan, Asia", "Seika-cho, Japan, Asia", "Izumi Crane Park, Japan, Asia", "Naga-cho, Japan, Asia", "Kamihetsugi, Japan, Asia", "Shioya-machi, Japan, Asia", "Tottorisi, Japan, Asia", "Yonago-shi, Japan, Asia", "Tome-shi, Japan, Asia", "Izumi-shi, Japan, Asia", "Akita-shi, Japan, Asia", "Nagoya-shi, Japan, Asia", "Mito-shi, Japan, Asia", "Mito-shi, Japan, Asia", "Ofunato-shi, Japan, Asia", "Mito-shi, Japan, Asia", "Kaga-shi, Japan, Asia", "Tripoli, Libya, Africa", "Anarmani, Nepal, Asia", "Ravi Bhavan, Nepal, Asia", "Itahari-2, Taltalaiya, Sunsari, Nepal, Asia", "Phalepatan, Kaski, Nepal, Asia", "Challans, France, Europe", "SAINT MICHEL, France, Europe", "MIRAMONT SENSACQ, France, Europe", "SAINTE DODE, France, Europe", "Birieux, France, Europe", "FALS, France, Europe", "MARLIEUX, France, Europe", "Palaiokastro Kozanis, Greece, Europe", "Ein Gedi Kibbutz, Dead Sea District, Hadarom Province, Israel, Asia", "RAMAT GAN, Israel, Asia", "Hatzafon, Israel, Asia", "BEER TOVIYYA, Israel, Asia", "Eilat, Israel, Asia", "RAMAT GAN, Israel, Asia", "MA AGAN MIKHA EL, Israel, Asia", "NEVATIM, Israel, Asia", "Amakim region, Wadi Ara, Israel, Asia", "Yarkon Park, Israel, Asia", "Wafra, Kuwait, Asia", "Tepatitlan de Morelos, Mexico, Americas", "Tepatitlan de Morelos, Mexico, Americas", "Kustrin, Poland, Europe", "Begecka jama park prirode, Begec, Serbia, Europe", "Stockam?llan, Sweden, Europe", "Sweden, Europe", "Shueili Township, Taiwan, Asia", "Yesilova, Turkey, Asia", "Kavak, Turkey, Asia", "Kachanga village, Uganda, Africa",
            "Arroyo Naranjo 1, Cuba, Americas",
            "Pedro Galvez, Peru, Americas", "Pedro Galvez, Peru, Americas",
            "Pedro Galvez, Peru, Americas", "SAN JOSE, Peru, Americas", "El Retiro, Chile, Americas", "El Retiro, Chile, Americas", "El Retiro, Chile, Americas",
            "El Retiro, Chile, Americas", "Pueblo County, China, Asia", "Pueblo County, China, Asia", "Tamenglong, India, Asia", "Reggio Calabria, Italy, Europe", "Mandera, Kenya, Africa", "Wardija, Malta, Europe", "Barcelona, Spain, Europe", "Nanaimo, Canada, Americas", "Region 6, Guyana, Americas", " de Banderas, Mexico, Americas", "Mexico, Americas", "Buena Vista, Panama, Americas", "Klastorp, Sweden, Europe", "Mapanza veterinary Camp, Zambia, Africa", "Majen Gobir Village, Nigeria, Africa", "Gemert-Bakel, Netherlands, Europe", "Colfax County, United States of America, Americas", "Nakhjavan, Iran  (Islamic Republic of), Asia", "Dorsetshire, U.K. of Great Britain and Northern Ireland, Europe", "Karta, Niger, Africa", "Haute-Sanaga, Cameroon, Africa", "Kalimantan Timur, Indonesia, Asia", "Ringsted, Denmark, Europe", "Han sur Lesse, Belgium, Europe", "Sanitary landfill, Paihoro (Taravao), Taiarapu-Est, TAHITI, ILES DU VENT, French Polynesia, Oceania", "Bronx County, United States of America, Americas", "Atambas system, Sunkarkiya area, Rural district Taupinsk, Irgiz, Kazakhstan, Asia", "Caribou County, United States of America, Americas", " Sweden, Europe", "Brewster County, United States of America, Americas", "Fauvillers, Belgium, Europe", "Big Pine Key, United States of America, Americas",
    };


    private static final String[] diseases_source = {
            "OIE", "National authorities", "Other", "FAO Field Officer", "International reference laboratory", "FAO-Report", "National authorities", "National authorities", "National authorities", "National authorities", "National authorities", "Media", "Other", "Other", "Other", "Other", "Other", "Other", "Other", "OIE", "National authorities", "National authorities", "National authorities", "National authorities", "National authorities", "National authorities", "National authorities", "National authorities", "National authorities", "National authorities", "National authorities", "National authorities", "Other", "Other", "National authorities", "OIE", "National authorities", "OIE", "OIE", "International reference laboratory", "International reference laboratory", "International reference laboratory", "National authorities", "National authorities", "National authorities", "OIE", "FAO Field Officer", "National authorities", "Media", "OIE", "FAO Field Officer", "FAO Field Officer", "National authorities", "Media", "OIE", "OIE", "EC", "EC", "OIE", "National authorities",
            "National authorities", "OIE", "National authorities", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "National authorities", "OIE", "OIE", "OIE", "OIE", "OIE",
            "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "National authorities", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE",
            "National authorities", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "National authorities", "OIE", "OIE", "OIE", "OIE", "National authorities", "National authorities", "FAO Field Officer", "OIE", "National authorities", "National authorities", "National authorities",
            "National authorities", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "National authorities", "OIE", "Media", "OIE", "Media", "OIE", "OIE", "OIE", "OIE", "OIE", "OIE", "FAO Field Officer", "FAO Field Officer", "National authorities", "OIE", "OIE", "National authorities", "Other", "OIE", "Other", "OIE", "OIE", "OIE", "OIE", "FAO Field Officer", "OIE", "OIE", "OIE", "OIE", "OIE"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_diseases);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationSearch = (EditText) findViewById(R.id.txt_search_map_diseases);

        //Permissions
        if (ContextCompat.checkSelfPermission(MapsDiseases.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsDiseases.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //  diseases_name_str="Influenza - Avian".toLowerCase().replace("-","").replace(" ","_");

        Intent intent = getIntent();
        diseases_name_str = intent.getStringExtra("diseases");
        Log.e("sss ", diseases_name_str);
        if (diseases_name_str.equals("influenza avian")) {
            diseases_name_str = "Influenza - Avian";

            for (int i = 0; i < diseases_name.length; i++) {
                if (diseases_name[i].equals(diseases_name_str)) {
                    mMap.addMarker(new MarkerOptions()
                            .position(diseases_latlong[i])
                            .title(diseases_location[i] + " -- " + diseases_source[i]));
                }
            }
        } else {
            Log.e("sssssss ", diseases_name_str);
            for (int i = 0; i < diseases_name.length; i++) {
                if (diseases_name[i].toLowerCase().equals(diseases_name_str)) {
                    mMap.addMarker(new MarkerOptions()
                            .position(diseases_latlong[i])
                            .title(diseases_location[i] + " -- " + diseases_source[i]));
                }
            }
        }


        // NORMAL OR SATELLITE TERRAIN  SHOW
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //this part;
        mMap.setTrafficEnabled(true);
        mMap.setIndoorEnabled(true);

        //to zoom in location
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.65838301153973, 29.898033902146093),1f));


        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                CameraPosition cameraPosition = mMap.getCameraPosition();
                if (cameraPosition.zoom > 20.0) {
                    // Toast.makeText(context, "Map Type Changed", Toast.LENGTH_SHORT).show();

                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                double latitude = marker.getPosition().latitude;
                double longitude = marker.getPosition().longitude;
                Log.e("flag onMarkerClick : ", latitude + "  " + longitude + "    " + marker.getTitle());

                return false;
            }
        });


/*
        for (int i=0;i<treatment_name.length;i++){
            locationSearch.setText(treatment_name[i]);
            searchLocation_diseases(null);
        }
 */

    }


    // search of specify location
    public void searchLocation_diseases(View view) {
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;
        if (location != null && !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addressList.size() > 0) {
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                //Log.e("","new LatLng("+address.getLatitude() + "," + address.getLongitude()+"),");
                Toast.makeText(getApplicationContext(), address.getLatitude() + " " + address.getLongitude(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Location not Found, Please try again !!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Location is Empty , Please Enter Location !!", Toast.LENGTH_SHORT).show();
        }
    }

}