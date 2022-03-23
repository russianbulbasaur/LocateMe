package mein.mutter.locateme.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

import mein.mutter.locateme.R;
import mein.mutter.locateme.adapters.listadapter;
import mein.mutter.locateme.database.datalink;


public class list_fragment extends Fragment {
    private String email = "";
    private String password = "";
    public list_fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        ListView lv = (ListView) v.findViewById(R.id.locationhistory_list);
        getemailpass();
        datalink data = new datalink(getContext(),email,password);
        String locations = data.get_locations();
        ArrayList locat = new ArrayList();
        try {
            JSONObject ob = new JSONObject(locations);
            JSONArray arr = ob.getJSONArray("locations");
            JSONObject child;
            for(int i=1;i<arr.length();i++)
            {
                child = arr.getJSONObject(i);
                locat.add(new String[]{child.getString("lat"),child.getString("long"),child.getString("time")});
            }
            if(!locat.isEmpty())
            {
                listadapter adapter = new listadapter(requireContext(),R.layout.listview_item,locat);
                lv.setAdapter(adapter);
            }
        }catch (Exception e){
            Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
        return v;
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