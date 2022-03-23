package mein.mutter.locateme.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Objects;

import mein.mutter.locateme.R;
import mein.mutter.locateme.database.datalink;
import mein.mutter.locateme.database.timestamp;
import mein.mutter.locateme.location_service;

public class location_fragment extends Fragment implements OnMapReadyCallback {
    private String email= "";
    private String password = "";
    private ProgressDialog pd;
    public location_fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       location_service service = new location_service();
        Intent intent = new Intent(requireActivity(),service.getClass());
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            requireContext().startForegroundService(intent);
        else
            requireContext().startService(intent);
        View v = inflater.inflate(R.layout.location_fragment, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        return v;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        pd = new ProgressDialog(requireActivity());
        pd.setCancelable(false);
        pd.setMessage("Fetching Location.....");
        pd.show();
        LocationManager lm = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireActivity(),"Persmissions Denied",Toast.LENGTH_LONG).show();
            return;
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                pd.cancel();
                pd.dismiss();
                timestamp t = new timestamp();
                datalink data = new datalink(getContext(),email,password);
                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,20f,0f,0f));
                googleMap.moveCamera(update);
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                googleMap.addMarker(options);
                Location l =new Location("");
                l.setLongitude(latLng.longitude);
                l.setLatitude(latLng.latitude);
                data.update_location(l,t.get_timestamp());
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                LocationListener.super.onProviderDisabled(provider);
                Toast.makeText(requireActivity(),"Enable GPS",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getemailpass() {
        File f = new File(requireActivity().getFilesDir(),"login");
        try {
            String comb = "";
            FileReader fr =new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            comb = br.readLine();
            br.close();
            String[] data = comb.split("\\s+");
            email = data[0];
            password = data[1];
        }catch (Exception e)
        {
            Toast.makeText(requireActivity(),"IO Exception",Toast.LENGTH_LONG).show();
        }
    }
}