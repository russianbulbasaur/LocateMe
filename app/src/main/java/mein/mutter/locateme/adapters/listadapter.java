package mein.mutter.locateme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mein.mutter.locateme.R;

public class listadapter extends ArrayAdapter {
    private Context con;
    private ArrayList list;
    public listadapter(@NonNull Context context, int resource,ArrayList arr) {
        super(context, resource);
        con = context;
        list = arr;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(con).inflate(R.layout.listview_item,parent,false);
        TextView lat = (TextView) v.findViewById(R.id.latitude);
        TextView time = (TextView) v.findViewById(R.id.timestamp);
        TextView longg = (TextView) v.findViewById(R.id.longitude);
        String[] a = ((String[])list.get(position));
        lat.setText("Latitude : "+a[0]);
        longg.setText("Longitude : "+a[1]);
        time.setText("Timestamp : "+(a[2].replace("&",":").replace("%"," ")));
        return v;
    }
}
