package com.sparecode.vipul.onlynow.fragments;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.location.LocationHelper;
import com.sparecode.vipul.onlynow.location.LocationProvider;
import com.sparecode.vipul.onlynow.permission.PiemissionsCallback;
import com.sparecode.vipul.onlynow.permission.PiemissionsRequest;
import com.sparecode.vipul.onlynow.permission.PiemissionsUtils;
import com.sparecode.vipul.onlynow.view.CircleImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class MapFragment extends BaseFragment implements OnMapReadyCallback, LocationProvider {

    private GoogleMap mMap;
    Context context;
    Marker marker;
    LatLng previouslatlng;
    //private static final int MY_LOCATION_REQUEST_CODE = 1;
    private static final int PERMISSIONS_CODE = 13370;
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE

    };
    LocationHelper locationHelper;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("", "");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        context = getActivity();
        //locationHelper = new LocationHelper(getActivity(),this);
        //locationHelper = new LocationHelper(getActivity(),this);
        final PiemissionsRequest request = new PiemissionsRequest(PERMISSIONS_CODE, PERMISSIONS);
        request.setCallback(new PiemissionsCallback() {
            @Override
            public void onGranted() {
                Log.e("log----::", "Permission Granted");
                locationHelper = new LocationHelper(getActivity(), MapFragment.this);
            }

            @Override
            public boolean onDenied(HashMap<String, Boolean> rationalizablePermissions) {
                Log.e("log---::", "Permission Denied");

                return true;
            }
        });
        PiemissionsUtils.requestPermission(request);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //locationHelper = new LocationHelper(getActivity(),this);
        Log.e("AllWentWell Flga::", "::::::");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        LatLng sydney1 = new LatLng(-30,150);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//        mMap.addMarker(new MarkerOptions().position(sydney1).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney1));
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("Search By Map");
        ((BaseActivity) getActivity()).getImgSearchMap().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
    }

    @Override
    public void onNewLcoationReceived(Location location) {
        System.out.println("------>location" + location);
        Log.d("::", location + "");
        Toast.makeText(getActivity(), "RECEIVED LOC" + location, Toast.LENGTH_SHORT).show();
        //ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


        if (previouslatlng == null) {
            previouslatlng = new LatLng(location.getLatitude(), location.getLongitude());
            for (int i = 0; i < 10; i++) {
                Log.d("::", "" + getRandomLocation(new LatLng(location.getLatitude(), location.getLongitude()), 50));
                mMap.addMarker(new MarkerOptions().position(getRandomLocation(new LatLng(location.getLatitude(), location.getLongitude()), 50)).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon)));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17);
                mMap.animateCamera(cameraUpdate);
            }
        } else {
            if (previouslatlng.latitude == location.getLatitude() && previouslatlng.longitude == location.getLongitude()) {
                Log.e("hi", "hello");
                locationHelper.doLicationDisconnect();
                return;
            } else {
                for (int i = 0; i < 10; i++) {
                    Log.d("::", "" + getRandomLocation(new LatLng(location.getLatitude(), location.getLongitude()), 50));
                    mMap.addMarker(new MarkerOptions().position(getRandomLocation(new LatLng(location.getLatitude(), location.getLongitude()), 50)).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_icon)));
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17);
                    mMap.animateCamera(cameraUpdate);
                }
            }
        }


    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_LOCATION_REQUEST_CODE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//    }

    public LatLng getRandomLocation(LatLng point, int radius) {

        List<LatLng> randomPoints = new ArrayList<>();
        List<Float> randomDistances = new ArrayList<>();
        Location myLocation = new Location("");
        myLocation.setLatitude(point.latitude);
        myLocation.setLongitude(point.longitude);

        //This is to generate 10 random points
        for (int i = 0; i < 10; i++) {
            double x0 = point.latitude;
            double y0 = point.longitude;

            Random random = new Random();

            // Convert radius from meters to degrees
            double radiusInDegrees = radius / 111000f;

            double u = random.nextDouble();
            double v = random.nextDouble();
            double w = radiusInDegrees * Math.sqrt(u);
            double t = 2 * Math.PI * v;
            double x = w * Math.cos(t);
            double y = w * Math.sin(t);

            // Adjust the x-coordinate for the shrinking of the east-west distances
            double new_x = x / Math.cos(y0);

            double foundLatitude = new_x + x0;
            double foundLongitude = y + y0;
            LatLng randomLatLng = new LatLng(foundLatitude, foundLongitude);
            randomPoints.add(randomLatLng);
            Location l1 = new Location("");
            l1.setLatitude(randomLatLng.latitude);
            l1.setLongitude(randomLatLng.longitude);
            randomDistances.add(l1.distanceTo(myLocation));
        }
        //Get nearest point to the centre
        int indexOfNearestPointToCentre = randomDistances.indexOf(Collections.min(randomDistances));
        return randomPoints.get(indexOfNearestPointToCentre);
    }

    public void addMarker(final LatLng latLng) {


        final View markerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_marker, null);
        final CircleImageView userImage = (CircleImageView) markerView.findViewById(R.id.imgMarker);


//        Picasso.with(getActivity()).load("https://fakeimg.pl/300/").into(new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                userImage.setImageBitmap(bitmap);
//                addItems(latLng);
//                marker = googleMap.addMarker(new MarkerOptions()
//                        .position(latLng)
//                        .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), markerView))));
//            }
//
//            @Override
//            public void onBitmapFailed(Drawable errorDrawable) {
//
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        });


        //mClusterManager = new ClusterManager<MyItem>(getActivity(), googleMap);


/*        marker = googleMap.addMarker(new MarkerOptions()
                .position(latLng));*/
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);

        //mMap.setOnMarkerClickListener(new GoogleMap().OnMarkerClickListener);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //DebugLog.e("CLICKED MARKER!!");
                //openMatchesDialog();
                return false;
            }
        });
        mMap.animateCamera(cameraUpdate);
    }
}
