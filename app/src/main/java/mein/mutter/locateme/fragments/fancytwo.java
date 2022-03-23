package mein.mutter.locateme.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import mein.mutter.locateme.R;
public class fancytwo extends Fragment {
    private static int s = 0;
    private location_fragment loc;
    private list_fragment list;
    public fancytwo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fancytwo, container, false);
        FragmentManager fm = getChildFragmentManager();
        loc = new location_fragment();
        list = new list_fragment();
        fm.beginTransaction().add(R.id.main_frag,loc).commit();
        BottomNavigationView bm = (BottomNavigationView) v.findViewById(R.id.bottom);
        bm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.mylocation:
                        fm.beginTransaction().replace(R.id.main_frag,loc).commit();
                        break;
                    case R.id.pastlocations:
                        fm.beginTransaction().replace(R.id.main_frag,list).commit();
                        break;
                }
                return false;
            }
        });
        return v;
    }
}