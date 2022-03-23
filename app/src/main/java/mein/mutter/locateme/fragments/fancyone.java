package mein.mutter.locateme.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import mein.mutter.locateme.R;
import mein.mutter.locateme.adapters.pager_adapter;

public class fancyone extends Fragment {
    private int PERMS_CODE = 22;
    public fancyone() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        anim();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void anim()
    {
        ViewPager vp = (ViewPager) requireActivity().findViewById(R.id.viewpager);
        TabLayout tabs = (TabLayout)requireActivity().findViewById(R.id.tabs);
        TextView but = (TextView)requireActivity().findViewById(R.id.next);
        vp.setOffscreenPageLimit(0);
        tabs.setupWithViewPager(vp);
        pager_adapter ob = new pager_adapter(requireActivity(),requireActivity().getSupportFragmentManager());
        vp.setAdapter(ob);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre_init();
            }
        });
    }
    private void pre_init() {
        boolean perms = check_perms();
        if (perms)
            initiate();
        else
            ask_perms();
    }
    private void initiate() {
        ViewModelProvider v = new ViewModelProvider(requireActivity());
        v.get(mein.mutter.locateme.viewmodels.fancyone_done.class).setdata(111);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fancyone, container, false);
    }
    private boolean check_perms(){
        return ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
    private void ask_perms()
    {
        String s[];
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q)
            s = new String[]{ Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
        else
            s= new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
        if(Build.VERSION.SDK_INT>=23)
            requestPermissions(s,PERMS_CODE);
        else
            ActivityCompat.requestPermissions(requireActivity(),s,PERMS_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMS_CODE)
        {
            pre_init();
        }
    }
}