package com.example.puzzle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsTreatment extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText locationSearch;
    GPSTracker gpsTracker;
    String distance_address = "";
    Polyline polylinel;
    int count = 0;
    // add to line on map
    private List<Polyline> polyline = null;
    //add location of hospital clinic ....

    private static final LatLng[] treatment_latlong = {
            new LatLng(31.220367699999997, 29.941382199999996),
            new LatLng(30.1073055, 31.3263961),
            new LatLng(30.047347600000002, 31.4674247),
            new LatLng(42.370038699999995, -71.19157369999999),
            new LatLng(-1.2501347, 36.820366899999996),
            new LatLng(30.0473021, 31.353748999999997),
            new LatLng(30.092738699999995, 31.2503243),
            new LatLng(30.049049999999998, 31.199142),
            new LatLng(29.951609800000004, 30.924935500000004),
            new LatLng(31.256929000000003, 29.980830999999995),
            new LatLng(31.2275012, 29.9534416),
            new LatLng(42.6763803, -73.6269475),
            new LatLng(23.039950899999997, 72.5276896),
            new LatLng(30.098442900000002, 31.342459800000004),
            new LatLng(31.353297999999995, 27.2330749),
            new LatLng(31.218607100000003, 29.930063299999997),
            new LatLng(44.484681699999996, -87.9235126),
            new LatLng(31.033498099999996, 31.3648506),
            new LatLng(30.0473021, 31.353748999999997),
            new LatLng(29.3570143, 30.6805381),
            new LatLng(42.4876617, -71.5495577),
            new LatLng(33.781230199999996, -118.03135700000001),
            new LatLng(30.101841, 31.375252),
            new LatLng(26.565079899999997, 31.6943154),
            new LatLng(25.6900796, 32.6334154),
            new LatLng(24.476025399999997, 54.3781235),
            new LatLng(27.9081495, 34.2815301),
            new LatLng(21.7644725, 72.15193040000001),
            new LatLng(30.528394499999997, 31.0977687),
            new LatLng(30.4666567, 31.1288704),
            new LatLng(30.145373, 31.3312583),
            new LatLng(20.6348308, -105.22667009999999),
            new LatLng(30.098373499999997, 31.3347515),
            new LatLng(28.9497771, 48.110589),
            new LatLng(30.071771199999993, 31.3545342),
            new LatLng(34.436416, -118.563443),
            new LatLng(30.0603588, 31.293896999999998),
            new LatLng(30.894456400000003, -84.20625509999999),
            new LatLng(29.954696900000002, 31.274593000000003),
            new LatLng(29.9817602, 31.347048899999997),
            new LatLng(29.953164599999997, 31.262879099999996),
            new LatLng(-26.012665, 28.115257999999997),
            new LatLng(30.03346, 31.011589599999997),
            new LatLng(47.863161299999994, -122.17543889999999),
            new LatLng(-34.6287373, 138.73283),
            new LatLng(29.954696900000002, 31.274593000000003),
            new LatLng(29.958422600000002, 30.9178947),
            new LatLng(29.949136199999995, 30.918702200000002),
            new LatLng(35.8571947, 14.519585999999999),
            new LatLng(30.7391258, 30.804832599999994),
            new LatLng(51.507011000000006, -2.3582609999999997),
            new LatLng(30.135189899999997, 31.6125412),
            new LatLng(1.3584011, 103.82760359999999),
            new LatLng(30.0603588, 31.293896999999998),
            new LatLng(28.705112999999997, 80.584524),
            new LatLng(31.247838899999998, 29.974318399999998),
            new LatLng(30.0707391, 31.2200257),
            new LatLng(31.2301748, 29.9486186),
            new LatLng(21.5837479, 39.173870199999996),
            new LatLng(31.127817899999997, 29.7807746),
            new LatLng(31.207677, 29.929443),
            new LatLng(30.000430999999995, 31.417220999999994),
            new LatLng(29.9634294, 31.306768199999993),
            new LatLng(29.9707346, 31.2731516),
            new LatLng(29.9585283, 31.252059600000003),
            new LatLng(30.0567067, 31.4919685),
            new LatLng(30.047347600000002, 31.4674247),
            new LatLng(10.530353, -61.386367),
            new LatLng(30.0216171, 31.225420699999997),
            new LatLng(30.077837799999998, 31.2511516),
            new LatLng(30.1010134, 31.325614599999994),
            new LatLng(30.0473021, 31.353748999999997),
            new LatLng(30.0822269, 31.3330141),
            new LatLng(9.3131437, 123.29721029999999),
            new LatLng(30.101586599999997, 31.3393871),
            new LatLng(30.127058700000003, 31.3481123),
            new LatLng(29.9817602, 31.347048899999997),
            new LatLng(30.008149, 31.415523),
            new LatLng(30.0355533, 31.232971799999998),
            new LatLng(34.436416, -118.563443),
            new LatLng(30.098373499999997, 31.3347515),
            new LatLng(30.114284599999998, 31.3529047),
            new LatLng(30.0621392, 31.1929973),
            new LatLng(30.0707391, 31.2200257),
            new LatLng(30.166546200000003, 31.3074147),
            new LatLng(30.1978882, 31.1333965),
            new LatLng(29.983197999999998, 31.275525),
            new LatLng(29.9536932, 31.096204000000004),
            new LatLng(26.1396822, 50.493074500000006),
            new LatLng(30.045869000000003, 31.475568199999998),
            new LatLng(30.088223300000003, 31.3382212),
            new LatLng(30.035216700000003, 31.3435444),
            new LatLng(30.071771199999993, 31.3545342),
            new LatLng(42.370038699999995, -71.19157369999999),
            new LatLng(1.3584011, 103.82760359999999),
            new LatLng(30.0603588, 31.293896999999998),

    };

    private static final String[] treatment_name = {

            "عيادة الإيمان البيطرية",
            "Dr Obeida Vet Clinic",
            "Vetology Veterinary Clinic",
            "PetMedic Veterinary Clinic",
            "Pets center Veterinary clinic",
            "Egyvet Clinic",
            "Shubra Veterinary Clinic Dr. Ahmed Essam Mohamed",
            "Pet Zone Veterinary Clinic - Dokki Branch",
            "Light's Veterinary Center",
            "Khaled Ibn El- Walid Vet Clinic",
            "Dr. Vet Clinic",
            "4PAWS Veterinary Clinic",
            "Happy Pets - Veterinary Clinic",
            "بيت الدواء البيطري",
            "Togo Vet Clinic",
            "عيادة كوكب الحيوان البيطرية",
            "Animal House pet clinic",
            "عيادة فلامنجو للحيونات المنزلية",
            "Egy Vet Clinic",
            "El Rowad Veterinary Clinic",
            "Shepherd Vet. Clinic",
            "Vetcare Clinic",
            "Sheraton Veterinary Clinic",
            "vet clinic sohag عيادة حيوانات اليفة / د. مهيتاب مهدى",
            "Veterinary Medicine in Luxor",
            "New Veterinary Clinic",
            "Sharm Animal Clinic",
            "Veterinary Hospital Bhnbarh",
            "الوحدة البيطرية بمنطقة ربيع عزب",
            "الوحدة البيطرية باسطنها",
            "مستشفى بيطرى بالمرج",
            "Dr Wolf Veterinary Hospital مستشفي بيطري",
            "Rise Vet Hospital",
            "International Veterinary Hospital",
            "المستشفى البيطري للقوات المسلحه",
            "Happy Pets - veterinary Center",
            "People's Hospital for Animals' Treatment",
            "Cairo Vet Center",
            "BRITISH ANIMAL HOSPITAL",
            "International Vet. Hospital & Pet Motel",
            "المركز البيطري المتقدم للقوات المسلحة بالمعادي",
            "Vet Land clinic & pharmacy",
            "GERMAN VETERINARY HOSPITAL EGYPT",
            "Pet Care Hospital",
            "Animalia vet clinic",
            "British Animal Hospital",
            "Pets Mall & Hospital",
            "Catdog hospital عيادة بيطرية ٦ اكتوبر",
            "pharma vet",
            "Al Baraka Vet Veterinary Pharmaceuticals and Poultry Engineer Mohammad Arab",
            "freedom vet clinic",
            "Cats&Dogs Vet Clinic عياده كاتس اند دوجز البيطريه",
            "Oasis vet clinic",
            "People's Hospital for Animals' Treatment",
            "VET CARE Centre For Veterinary Services",
            "Whiskers & Wags Veterinary Clinic",
            "American veterinary center",
            "Dr Green Animal Hospital",
            "Petopia vet clinic",
            "Agamy Veterinary Hospital (pets parking)",
            "Alex Pet Center",
            "Pet Station Vet Clinic New Cairo",
            "Harmony vet clinic - HVC Maadi",
            "I-Vet Clinic",
            "Korashy Vet Clinic",
            "Petopia Veterinary Clinic",
            "Vetology Veterinary Clinic",
            "Pet Point Veterinary Clinic",
            "Pets Plus Vet Clinic بيتس بلس عياده بيطريه",
            "Shoubra Vet Clinic",
            "Paris vet.clinic عياده باريس البيطريه",
            "عيادة ايجى فيت البيطرية | د. على عبدالرحمن | Egy Vet Clinic | عيادة بيطرية بمدينة نصر القاهرة",
            "عيادة بيتس فاللي البيطرية",
            "Cats N' Dogs Vet.clinic ",
            "Souls Veterinary Clinic",
            "Nour Vet Clinic - عيادة نور فيت البيطرية",
            "المستشفي البيطري الدولي",
            "Pet Cure Veterinary Clinic New Cairo",
            "British Animal Hospital - Garden City",
            "Happy Pets - veterinary Center",
            "مستشفى رايز البيطرى",
            "عيادة النزهة البيطرية",
            "عيادة الصفطاوى البيطرية",
            "المركز البيطري الأمريكي - American Veterinary Center",
            "المركز البيطري لطب و رعاية الحيوان Veterinary Clinic",
            "Ramcovet for veterinary services - رامكوفيت للخدمات البيطرية",
            "Pharma Vet فارما فيت",
            "صيدلية بيطرية - Nada4Pet Pharmacy",
            "عياده الحمد البيطريه",
            "grand pharmacy, stella branch",
            "شركة الدشناوى البيطرية",
            "مركز بى دبليو سى البيطرى",
            "المستشفى البيطري للقوات المسلحه",
            "PetMedic Veterinary Hospital",
            "عيادة بيطرية Oasis vet clinic",
            "مستشفى الشعب لمعالجة الحيوانات",

    };

    private static final String[] tre = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_treatment);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationSearch = (EditText) findViewById(R.id.txt_search_map_treatment);

        //Permissions
        if (ContextCompat.checkSelfPermission(MapsTreatment.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsTreatment.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (int i = 0; i < treatment_name.length; i++) {
            mMap.addMarker(new MarkerOptions()
                    .position(treatment_latlong[i])
                    .title(treatment_name[i]));
        }


        // NORMAL OR SATELLITE TERRAIN  SHOW
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //this part;
        mMap.setTrafficEnabled(true);
        mMap.setIndoorEnabled(true);

        get_mylocation(null);

        //GET LAT LONG BY USING CAMERA POSITION
        CameraPosition cameraPosition = mMap.getCameraPosition();
        double latitude = cameraPosition.target.latitude;
        double longitude = cameraPosition.target.longitude;
        Log.e("flag : ", latitude + "  " + longitude + "");

        // CHANGE OF TYPE MAP WITH CHANGE ZOOM IN MAP
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                CameraPosition cameraPosition = mMap.getCameraPosition();
                if (cameraPosition.zoom > 20.0) {
                    // Toast.makeText(context, "Map Type Changed", Toast.LENGTH_SHORT).show();
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.e("flag SHORT CLICK : ", latLng.latitude + "  " + latLng.longitude + "");
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                //add to mark on location
                mMap.addMarker(new MarkerOptions().position(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                Log.e("flag LONG CLICK: ", latLng.latitude + "  " + latLng.longitude + "");
            }
        });


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                double latitude = marker.getPosition().latitude;
                double longitude = marker.getPosition().longitude;
                double stringLatitude = 0, stringLongitude = 0;
                String countr, address, cit;
                //  Log.e("flag onMarkerClick : ", latitude+"  "+longitude +"    "+marker.getTitle());
                gpsTracker = new GPSTracker(getApplicationContext());
                if (gpsTracker.getIsGPSTrackingEnabled()) {
                    stringLatitude = gpsTracker.latitude;
                    stringLongitude = gpsTracker.longitude;
                    countr = gpsTracker.getCountryName(getApplicationContext());
                    address = gpsTracker.getAddressLine(getApplicationContext());
                    cit = gpsTracker.getLocality(getApplicationContext());

                    // create line between two location
                    if (polylinel != null) {
                        polylinel.remove();
                    }
                    polylinel = mMap.addPolyline(new PolylineOptions().add(marker.getPosition(),
                            new LatLng(stringLatitude, stringLongitude)).width(5).color(Color.RED));

                    double valueResult = CalculationByDistance(marker.getPosition(), new LatLng(stringLatitude, stringLongitude));
                    double km = valueResult / 1;
                    DecimalFormat newFormat = new DecimalFormat("####");
                    int kmInDec = Integer.valueOf(newFormat.format(km));
                    double meter = valueResult % 1000;
                    int meterInDec = Integer.valueOf(newFormat.format(meter));

                    String distance = "KM " + kmInDec + " Meter   " + meterInDec;
                    String decimalFormat = new DecimalFormat("#.###").format(valueResult);
                    Toast.makeText(getApplicationContext(), " distance between " + countr
                            + " : " + address + " and " + marker.getTitle() + " is : " + decimalFormat + "  . ", Toast.LENGTH_LONG).show();
                    //speed = 408.47 km/h
                    String time = new DecimalFormat("#.###").format((Double.valueOf(decimalFormat) / 408.47));
                    distance_address = " distance between " + countr
                            + " : " + address + " and " + marker.getTitle() + " is : " + distance + " , It will take time "
                            + time + " Hours";
                } else {
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gpsTracker.showSettingsAlert();
                }

                return false;
            }
        });


/*
        // create line between MANY location
        PolygonOptions polygonOptions = new PolygonOptions();
        for(int i=0;i<PLACES.length;i++){
            mMap.addMarker(new MarkerOptions()
                    .position(PLACES[i])
                    .title(i+""));
            polygonOptions.add(PLACES[i]);
        }
        polygonOptions.clickable(true);
        polygonOptions.strokeColor(Color.RED);
        polygonOptions.strokeWidth(5);
        googleMap.addPolygon(polygonOptions).setTag("polygon");
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(0, 0)));
*/


/*
        for (int i=0;i<treatment_name.length;i++){
            locationSearch.setText(treatment_name[i]);
            searchLocation_treatment(null);
        }
        Log.e("fvfv",count+"");
*/
    }


    // search of specify location
    public void searchLocation_treatment(View view) {
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;
        if (location != null && !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addressList.size() > 0 && addressList != null) {
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                Log.e("", "new LatLng(" + address.getLatitude() + "," + address.getLongitude() + "),  " + "\"" + location + "\",");
                count++;
                Toast.makeText(getApplicationContext(), address.getLatitude() + " " + address.getLongitude(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Location not Found, Please try again !!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Location is Empty , Please Enter Location !!", Toast.LENGTH_SHORT).show();
        }
    }


    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

    //to get location through mic
    public void getSpeechInput_treatment(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    locationSearch.setText(result.get(0));

                    searchLocation_treatment(null);
                }
                break;
        }
    }


    public void get_mylocation(View view) {
        // check if GPS enabled
        gpsTracker = new GPSTracker(this);
        if (gpsTracker.getIsGPSTrackingEnabled()) {
            String stringLatitude = String.valueOf(gpsTracker.latitude);
            String stringLongitude = String.valueOf(gpsTracker.longitude);
            String countr = gpsTracker.getCountryName(this);
            String address = gpsTracker.getAddressLine(this);
            String cit = gpsTracker.getLocality(this);
            Toast.makeText(getApplicationContext(), "my location is : " + countr + " , " + cit + " , " + address, Toast.LENGTH_SHORT).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpsTracker.showSettingsAlert();
        }
        float zoomLevel = 16.0f; //This goes up to 21
        mMap.addMarker(new MarkerOptions().position(new LatLng(gpsTracker.latitude, gpsTracker.longitude)).title(gpsTracker.getAddressLine(this))
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.addCircle(new CircleOptions().center(new LatLng(gpsTracker.latitude, gpsTracker.longitude)).
                radius(30).fillColor(R.color.teal_20).strokeWidth(R.color.teal_20));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gpsTracker.latitude, gpsTracker.longitude), zoomLevel));

    }

    public void get_distance(View view) {
        if (distance_address.equals("")) {
            Toast.makeText(getApplicationContext(), "Please click on the mark of the location you want !! ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), distance_address, Toast.LENGTH_LONG).show();
        }
    }


    public void zoomout(View view) {
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

    }


    public void zoomin(View view) {
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
    }


    public void nearest_place(View view) {
        double stringLatitude = 0, stringLongitude = 0, distance = 0;
        LatLng latLng = null;
        String name = "";
        //  Log.e("flag onMarkerClick : ", latitude+"  "+longitude +"    "+marker.getTitle());
        gpsTracker = new GPSTracker(getApplicationContext());
        if (gpsTracker.getIsGPSTrackingEnabled()) {
            stringLatitude = gpsTracker.latitude;
            stringLongitude = gpsTracker.longitude;
        }
        double min = CalculationByDistance(treatment_latlong[0], new LatLng(stringLatitude, stringLongitude));

        for (int i = 1; i < treatment_latlong.length; i++) {
            distance = CalculationByDistance(treatment_latlong[i], new LatLng(stringLatitude, stringLongitude));
            if (distance < min) {
                latLng = treatment_latlong[i];
                name = treatment_name[i];
                min = distance;
            }
        }

        polylinel = mMap.addPolyline(new PolylineOptions().add(latLng,
                new LatLng(stringLatitude, stringLongitude)).width(5).color(Color.RED));

        double km = min / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = min % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        String distan = "KM " + kmInDec + " Meter   " + meterInDec;
        Toast.makeText(getApplicationContext(), "the nearest place is : " + name + " , distance is : " + distan, Toast.LENGTH_LONG).show();

    }


}


