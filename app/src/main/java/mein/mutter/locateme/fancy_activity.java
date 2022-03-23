package mein.mutter.locateme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import mein.mutter.locateme.adapters.pager_adapter;
import mein.mutter.locateme.fragments.fancyone;
import mein.mutter.locateme.fragments.fancytwo;
import mein.mutter.locateme.fragments.login;
import mein.mutter.locateme.viewmodels.fancyone_done;
import mein.mutter.locateme.viewmodels.login_done;


public class fancy_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fancy);
        getSupportActionBar().hide();
        FragmentManager fm = getSupportFragmentManager();
        fancyone one = new fancyone();
        fm.beginTransaction().add(R.id.fragment_container,one).commit();
        ViewModelProvider v = new ViewModelProvider(this);
        v.get(fancyone_done.class).a.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer==111)
                {
                    login login = new login();
                    fm.beginTransaction().remove(one).add(R.id.fragment_container,login).commit();
                    v.get(login_done.class).a.observe(fancy_activity.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer integer) {
                            if(integer==222)
                            {
                                fancytwo two = new fancytwo();
                                fm.beginTransaction().remove(login).add(R.id.fragment_container,two).commit();
                            }
                        }
                    });
                }
            }
        });
    }
}
