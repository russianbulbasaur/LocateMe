package mein.mutter.locateme;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.airbnb.lottie.L;

import java.util.Calendar;

public class location_alarm extends Service {
    public location_alarm() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Toast.makeText(getBaseContext(),"here",Toast.LENGTH_LONG).show();
        String id= "mein.mutter";
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notif;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(id,"LocateME",NotificationManager.IMPORTANCE_NONE);
            channel.setLightColor(Color.BLUE);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notif = new NotificationCompat.Builder(this,id);
        }
        else
        {
            notif = new NotificationCompat.Builder(this);
        }
        notif.setContentTitle("LocateMe");
        notif.setContentText("Uploading location");
        notif.setCategory(Notification.CATEGORY_SERVICE);
        notif.setSmallIcon(R.drawable.ic_baseline_arrow_forward_24);
        manager.notify(28,notif.build());
        return Service.START_STICKY;
    }
    @SuppressLint("MissingPermission")
    private void location()
    {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(),location_alarm.class);
        PendingIntent pi = PendingIntent.getService(getBaseContext(),23,intent,0);
        am.set(AlarmManager.RTC,System.currentTimeMillis()+20000,pi);
        lm.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Toast.makeText(getBaseContext(),String.valueOf(location.getLatitude()),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                LocationListener.super.onProviderDisabled(provider);
                Toast.makeText(getBaseContext(),"enable",Toast.LENGTH_LONG).show();
            }
        }, Looper.myLooper());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}