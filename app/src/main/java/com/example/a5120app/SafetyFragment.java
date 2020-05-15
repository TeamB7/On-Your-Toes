package com.example.a5120app;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;

import org.json.JSONException;

import java.io.IOException;

import androidx.fragment.app.Fragment;

public class SafetyFragment extends Fragment implements OnMapReadyCallback {
    private View view;
    private MapView mapView;
    private GoogleMap mMap;
    private UiSettings mUiSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_safety, container, false);
        mapView = (MapView) view.findViewById(R.id.safety_map);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        GeoJsonLayer layer = null;
        try {
            layer = new GeoJsonLayer(mMap,R.raw.boundaries, getContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        layer.addLayerToMap();

        for (GeoJsonFeature feature : layer.getFeatures()) {
            if (feature.hasProperty("LC_PLY_PID")) {
                //String group = feature.getProperty("TEST_EXPORT_GROUP");
                int rating = Integer.parseInt(feature.getProperty("SAFETY_EXPORT_GROUP"));

                Log.d("ADebugTag", "Value:" + feature.getProperty("SAFETY_EXPORT_GROUP"));


                if (rating == 3) {
                    GeoJsonPolygonStyle polygonStyleRed = new GeoJsonPolygonStyle();
                    polygonStyleRed.setFillColor(Color.parseColor("#80FF6680"));
                    polygonStyleRed.setStrokeWidth(3);
                    feature.setPolygonStyle(polygonStyleRed);
                } else if (rating == 2) {
                    GeoJsonPolygonStyle polygonStyleYellow = new GeoJsonPolygonStyle();
                    polygonStyleYellow.setFillColor(Color.parseColor("#80EBC427"));
                    polygonStyleYellow.setStrokeWidth(3);
                    feature.setPolygonStyle(polygonStyleYellow);
                } else if (rating==1){
                    GeoJsonPolygonStyle polygonStyleGreen = new GeoJsonPolygonStyle();
                    polygonStyleGreen.setFillColor(Color.parseColor("#993f960d"));
                    polygonStyleGreen.setStrokeWidth(3);

                    feature.setPolygonStyle(polygonStyleGreen);
                } else {
                    GeoJsonPolygonStyle polygonStyleGray = new GeoJsonPolygonStyle();
                    polygonStyleGray.setFillColor(Color.parseColor("#998F8F8F"));
                    polygonStyleGray.setStrokeWidth(3);
                    feature.setPolygonStyle(polygonStyleGray);
                }
            }
        }
//        ------------------------------------------------------------------------------------------


//        Set Style

        // Add a marker in Sydney and move the camera
        LatLng melbourne = new LatLng(-37.8136, 141.9631);
        mMap.addMarker(new MarkerOptions().position(melbourne));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(melbourne));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
}