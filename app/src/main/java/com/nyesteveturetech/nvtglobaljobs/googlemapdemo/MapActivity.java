package com.nyesteveturetech.nvtglobaljobs.googlemapdemo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.LoginItems;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.Trip;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Items.DataConductorId;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Items.DataHolder;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.WebService.RestBuilderPro;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnMapClickListener ,GoogleMap.OnMarkerClickListener{
    private ArrayList<LatLng> markerPoints;
    private ArrayList<StopsD> stoplistmark;
    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    private ProgressDialog dialog;

    int PLACE_PICKER_REQUEST = 14;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 15;
    private static double current_lat;
    private static double current_longi;
    Button searchplace,start,clearb,path,delaysend;
    TextView _souce,_destin,_soucetime,_desttime,_tothrs,_tokm,_currentplace,_remaikm;
    TextView desPlace,kmTime,sourceplace,currentplace,remainkm;
    EditText delayEdit;
    boolean br=false;
    Location dest_location;
    double lat_o,log_o;
    int distance_o;
    int _id;
    boolean nopath,startb;
    String s_source,s_destination,_s_stime,_s_dtime,s_thrs,s_tokm,s_cuplace,s_remainkm,s_date;
    List<Trip> list;
    LatLng firstpoint;
    LatLng lastpoint;
    float distance_Total=0;
    float current_dis1=0;
    boolean marchcheck;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view);
        Intent intent=getIntent();
        _id=intent.getIntExtra("sposition",1);


        start=(Button)findViewById(R.id.distaceplacebutton);
        clearb=(Button)findViewById(R.id.clearplacebutton);
        path=(Button)findViewById(R.id.statrtyplacebutton) ;
        delayEdit=(EditText)findViewById(R.id.delaytime) ;
        delaysend=(Button)findViewById(R.id.buttonsendresume);

        _souce=(TextView)findViewById(R.id.bsource) ;
        _destin=(TextView)findViewById(R.id.bdestinatio) ;
        _soucetime=(TextView)findViewById(R.id.bstime) ;
        _desttime=(TextView)findViewById(R.id.bdtime ) ;


        _tothrs=(TextView)findViewById(R.id.bthrs) ;

        _tokm=(TextView)findViewById(R.id.btkm) ;
        _currentplace=(TextView)findViewById(R.id.bcuplace) ;
      _remaikm=(TextView)findViewById(R.id.brkm);




        markerPoints = new ArrayList<>();
        stoplistmark=new ArrayList<>();
        list=new ArrayList<>();
        dialog=new ProgressDialog(MapActivity.this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();

        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


/*
textview=(FloatingSearchView)findViewById(R.id.floating_search_view);
        textview.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery)
            {
                Log.e("search",newQuery);
               // List<Address> adress=


            }
        });
*/


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

       list= DataHolder.getInstance().getDistributor_id();

          s_source=list.get(_id).getSource();
        s_destination=list.get(_id).getDestination();
        _s_stime=list.get(_id).getSourceTime();
        _s_dtime=list.get(_id).getDestinationTime();
        s_date=list.get(_id).getDate();

        for(int i=0;i<list.get(_id).getStops().size();i++) {

            String slati = list.get(_id).getStops().get(i).getLatitude();
            String slong=list.get(_id).getStops().get(i).getLongitude();
            Log.e("sla",slati);
            Log.e("slang",slong);
            double v1=Double.parseDouble(slati);
            double d2=Double.parseDouble(slong);
            String place=list.get(_id).getStops().get(i).getPlace();
            LatLng latLng = new LatLng(v1, d2);
            stoplistmark.add(new StopsD(place,latLng));
        }
        _souce.setText(s_source);
        _destin.setText(s_destination);
        _soucetime.setText(s_date+" "+_s_stime);
          _desttime.setText(s_date+" "+_s_dtime);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        Date d1 = null;
        Date d2 = null;
        try {
            d1=format.parse(_s_stime);
            d2=format.parse(_s_dtime);
            long timde=d2.getTime()-d1.getTime();
            //long diffHours = timde / (60 * 60 * 1000) % 24;

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;

            long elapsedMinutes = timde/ minutesInMilli;

          _tothrs.setText(""+elapsedMinutes+" minutes");



        } catch (ParseException e) {
            e.printStackTrace();
        }




        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                LongClick(latLng);
            }
        });
        clearb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Stops();
               /* markerPoints.clear();
                mMap.clear();
                br=false;*/
            }
        });
        path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            LocationPath();

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartJjourney();
            }
        });







        delaysend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendValue(v);
            }
        });

    }

    private void Stops() {
        if(stoplistmark.size()==0){

            Toast.makeText(this, "no stops", Toast.LENGTH_SHORT).show();
        }
        else {
            br=true;
            for (int i = 0; i < stoplistmark.size(); i++) {
                MarkerOptions options = new MarkerOptions();

                firstpoint=stoplistmark.get(0).getLatitude();
                lastpoint=stoplistmark.get(stoplistmark.size()-1).getLatitude();

                LatLng latLng = new LatLng(8.5582542, 76.862711);
                options.position(stoplistmark.get(i).getLatitude());
                options.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                options.title(stoplistmark.get(i).getStopplace());
                mMap.addMarker(options);
                mMap.setOnMarkerClickListener(this);
                markerPoints.add(stoplistmark.get(stoplistmark.size()-1).getLatitude());

                //Marker TP = googleMap.addMarker(new MarkerOptions().position(stoplistmark.get(i).getLatitude()).title(stoplistmark.get(i).getStopplace()));
            }

        }

    }


    private void StartJjourney() {
        if(!br&&!nopath)
        {
            Toast.makeText(this, "no_path", Toast.LENGTH_SHORT).show();
            return;

        }
        startb=true;


        final LatLng origin = markerPoints.get(0);
        LatLng dest = markerPoints.get(1);
        final Location mylocation = new Location("");
        final Location   dest_location = new Location("");
        double lat = dest.latitude;
        double lon = dest.longitude;
        dest_location.setLatitude(lastpoint.latitude);
        dest_location.setLongitude(lastpoint.longitude);

        mylocation.setLatitude(firstpoint.latitude);
        mylocation.setLongitude(firstpoint.longitude);
        try {
            Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
            List<android.location.Address> addresses,adress = null;
            addresses = geocoder.getFromLocation(origin.latitude, origin.longitude, 1);
            adress=geocoder.getFromLocation(dest.latitude, dest.longitude, 1);
            //String cityName = addresses.get(0).getAddressLine(0);
          //  String cityName1 = adress.get(0).getAddressLine(0);

            distance_Total = (float) mylocation.distanceTo(dest_location) / 1000;
            _tokm.setText("" + distance_Total + "KM");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LocationPath() {
        if(br) {
            nopath=true;
            final LatLng origin = firstpoint;
            LatLng dest = lastpoint;

            // Getting URL to the Google Directions API
            String url = getDirectionsUrl(origin, dest);

            DownloadTask downloadTask = new DownloadTask();

            // Start downloading json data from Google Directions API

            downloadTask.execute(url);
        }
        else{
            Toast.makeText(this, "no stops", Toast.LENGTH_SHORT).show();
        }
    }


    private void LongClick(LatLng latLng) {
        if(!startb)
        {

            Toast.makeText(this, "start journey", Toast.LENGTH_SHORT).show();
            return;
        }

        markerPoints.add(latLng);
        MarkerOptions options = new MarkerOptions();

        // Setting the position of the marker
        options.position(latLng);
        try {
           lat_o=latLng.latitude;
          log_o=latLng.longitude;
            final Location mylocation = new Location("");
            mylocation.setLatitude(latLng.latitude);
            mylocation.setLongitude(latLng.longitude);

             Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
            List<android.location.Address> addresses,adress = null;
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String cityName = addresses.get(0).getAddressLine(0);
            // adress=geocoder.getFromLocation(dest.latitude, dest.longitude, 1);
            distance_o = (int) mylocation.distanceTo(dest_location) / 1000;

           _currentplace.setText(cityName);
            _remaikm.setText(distance_o+"KM");

        }catch (Exception e){}
        options.snippet("Destitantion point");
        options.title("Destitantion");
        if (markerPoints.size() == 1) {
            options.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        } else if (markerPoints.size() == 2) {
            options.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        }

        // Add new marker to the Google Map Android API V2
        mMap.addMarker(options);

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(final Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

/*  new Handler().post(new Runnable() {
    @Override
    public void run() {
       LatLng latLng=CurrentLOcation(location);

        MarkerOptions options = new MarkerOptions();


        double lat = latLng.latitude;
        double lon = latLng.longitude;

        try {
            Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
            List<android.location.Address> addresses, adress = null;
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            String cityName = addresses.get(0).getAddressLine(0);
            final Location mylocation = new Location("");

            mylocation.setLatitude(latLng.latitude);
            mylocation.setLongitude(latLng.longitude);

            int distance = (int) mylocation.distanceTo(dest_location) / 1000;



            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(12));

        }catch (Exception e)
        {

        }
    }
});*/
        //move map camera




        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    private LatLng CurrentLOcation(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
      // markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
      //  mCurrLocationMarker = mMap.addMarker(markerOptions);
        markerPoints.add(latLng);
return latLng;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }




    private void initilizeMap(String paramString) {
        if (this.mMap != null) {
            mMap.clear();
            markerPoints.clear();
        ///   CurrentLOcation (mLastLocation);
            //  PrefrencesUtils.putDouble("latitude", current_lat);
            // PrefrencesUtils.putDouble("longitude", current_longi);
            //startIntentService();
            LatLng localLatLng = new LatLng(current_lat, current_longi);
            this.mMap.addMarker(new MarkerOptions().position(localLatLng).title(paramString));
            this.mMap.moveCamera(CameraUpdateFactory.newLatLng(localLatLng));
            markerPoints.add(localLatLng);
           // Log.d("MainActivity", " current_lat initilizeMap " + current_lat);
            //Log.d("MainActivity", "current_longi  initilizeMap" + current_longi);
        }
    }

    @Override
    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if(paramInt2==RESULT_OK)
        {
            if (paramInt1 == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
                Place place = PlaceAutocomplete.getPlace(MapActivity.this, paramIntent);
                //paramIntent = (Intent) PlaceAutocomplete.getPlace(MapActivity.this, paramIntent);
                // Log.i("MainActivity", "Place Selected: " + paramIntent.);
                current_lat = place.getLatLng().latitude;
                current_longi = place.getLatLng().longitude;
               // Log.e("MainActivity", " current_lat initilizeMap " + current_lat);
               // Log.d("MainActivity", "current_longi  initilizeMap" + current_longi);
               // // PrefrencesUtils.putDouble("latitude", current_lat);
                //  PrefrencesUtils.putDouble("longitude", current_longi);
                String locName = place.getName().toString();
                initilizeMap(locName);
                if (TextUtils.isEmpty(place.getAttributions()));
            }
        }else
        {
          //  Place place = PlaceAutocomplete.getPlace(MapActivity.this, paramIntent);
            Log.e("MainActivity", "Error: Status = ");
        }



    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Map Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + ","
                + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=true";

        // Waypoints
        String waypoints = "";
        for (int i = 2; i < markerPoints.size(); i++) {
            LatLng point = (LatLng) markerPoints.get(i);
            if (i == 2)
                waypoints = "waypoints=";
            waypoints += point.latitude + "," + point.longitude + "|";
        }

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&"
                + waypoints;

        // Output format
        String output = "json";
       // 8.509402, 76.897015
       // 8.509402, 76.897015
        // https://maps.googleapis.com/maps/api/directions/json ? origin=8.509402, 76.897015 & destination=8.990189, 76.533449 & sensor=true
        //destination=8.990189, 76.533449 & sensor=true

        String url = "https://maps.googleapis.com/maps/api/directions/"
                + output + "?" + parameters;

        return url;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        marchcheck=true;
        String latitude = String.valueOf(marker.getPosition().latitude);
        String longitude = String.valueOf(marker.getPosition().longitude);
        String place = String.valueOf(marker.getTitle());
        double lat = marker.getPosition().latitude;
        double log = marker.getPosition().longitude;
        lat_o = lat;
        log_o = log;
        double f_lat = firstpoint.latitude;
        double f_long = firstpoint.longitude;
        final Location mylocation = new Location("");

        Location dest = new Location("");
        if (lat != f_lat || log != f_long) {
            mylocation.setLatitude(f_lat);
            mylocation.setLongitude(f_long);
            dest = new Location("");
            dest.setLatitude(lat);
            dest.setLongitude(log);
           float current_dis = (float) mylocation.distanceTo(dest) / 1000;
            _remaikm.setText(current_dis + "KM");

            current_dis1=current_dis;
        }else{
            _remaikm.setText(0+ "KM");
            current_dis1=0;

        }


        // adress=geocoder.getFromLocation(dest.latitude, dest.longitude, 1);


        _currentplace.setText(marker.getTitle());



        return false;
    }


    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service

            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();
            if (result != null) {
                // Invokes the thread for parsing the JSON data
                parserTask.execute(result);
            }
        }
    }

    private class ParserTask extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {

            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(6);
                lineOptions.color(Color.BLUE);
            }

            // Drawing polyline in the Google Map for the i-th route
            mMap.addPolyline(lineOptions);
        }
    }


    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            //Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }


           /* else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);

                Log.i("TAG", "error_error");

            }
            else if (resultCode == RESULT_CANCELED) {
              // The user canceled the operation.
            }
        }
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
               *//* Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();*//*
            }*/



    private void SendValue(final View v) {
        //v.setClickable(false);

        if(!startb){
            Toast.makeText(this, "start Juerney", Toast.LENGTH_SHORT).show();
            if(!marchcheck){

                Toast.makeText(this, "mark a position ", Toast.LENGTH_SHORT).show();
                return;
            }
           // v.setClickable(true);
            return;
        }

        if(lat_o!=0.0||log_o!=0.0){
            String delaytimec=delayEdit.getText().toString();
            String busid=list.get(_id).getBusId();
            String trip=list.get(_id).getTripId();
            String conductorID = DataConductorId.getInstance().getDistributor_id();

            Log.e("delaytime",delaytimec);
            Log.e("busId",busid);
            Log.e("triId",trip);
            Log.e("conductorId",conductorID);
            /*Log.e("lat_0",""+lat_o);
            Log.e("long",""+log_o);*/
            Log.e("remin",""+(distance_Total));
            Log.e("remin",""+(current_dis1));
           float remi=distance_Total-current_dis1;
            DecimalFormat dec=new DecimalFormat("0.00");
            String remnkm=dec.format(remi);
            Log.e("remin kmmmm",remnkm);
            dialog.show();
            RestBuilderPro.getService().authenticate1("addposition",""+lat_o,""+log_o,remnkm,delaytimec,conductorID,busid,trip).enqueue(new Callback<LoginItems>() {
                @Override
                public void onResponse(Call<LoginItems> call, Response<LoginItems> response) {
                   if(response.isSuccessful()){
                       dialog.dismiss();
                    //   v.setClickable(true);
                       LoginItems lob=response.body();
                       int k=Integer.parseInt(""+lob.getSuccess());
                       String message=lob.getMessage();
                       if(k==1)
                       {

                           Toast.makeText(MapActivity.this, message, Toast.LENGTH_SHORT).show();
                       }
                       else{
                           Toast.makeText(MapActivity.this, message, Toast.LENGTH_SHORT).show();
                       }

                   }

                }

                @Override
                public void onFailure(Call<LoginItems> call, Throwable t) {

                    dialog.dismiss();
                   // v.setClickable(true);
                }
            });
        }





    }
}







