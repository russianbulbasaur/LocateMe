package mein.mutter.locateme.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import mein.mutter.locateme.R;
import mein.mutter.locateme.database.datalink;
import mein.mutter.locateme.database.timestamp;
import mein.mutter.locateme.location_alarm;
import mein.mutter.locateme.location_service;
import mein.mutter.locateme.viewmodels.login_done;

public class login extends Fragment {

    private String email = "";
    private ViewModelProvider provider;
    private EditText passwordet;
    private String password = "";
    public login() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     provider = new ViewModelProvider(requireActivity());
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        EditText emailet = (EditText) v.findViewById(R.id.emailet);
      passwordet =  (EditText) v.findViewById(R.id.passet);
        MaterialButton but = (MaterialButton) v.findViewById(R.id.loginbut);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailet.getText().toString().trim();
                password = passwordet.getText().toString().trim();
                if(email.isEmpty())
                    emailet.setError("Email Required");
                else
                {
                    if(password.isEmpty())
                        passwordet.setError("Password Required");
                    else
                    {
                        sql_connect(email,password);
                    }
                }
            }
        });
        return v;
    }
    private void sql_connect(String local_email,String local_password)
    {
        datalink data = new datalink(requireActivity(),local_email,local_password);
        if(data.check())
        {
            if(data.confirmpass())
            {
                signin(email,password);
            }
            else
            {
                passwordet.setError("Password mismatch");
            }
        }
        else {
            data.newuser();
            signin(email,password);
        }
    }
    private void signin(String email,String password) {
        File f = new File(requireActivity().getFilesDir(),"login");
        try {
            if (!f.exists())
                f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            String comb = email+" "+password;
            fos.write(comb.getBytes());
            fos.flush();
            fos.close();
            FileReader fr =new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            comb = br.readLine();
            br.close();
            String[] data = comb.split("\\s+");
            provider.get(login_done.class).a.setValue(222);
        }catch (Exception e)
        {
            Toast.makeText(requireActivity(),"IO Exception",Toast.LENGTH_LONG).show();
        }
    }
}