package mein.mutter.locateme;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Timer;

public class location_service extends Service {
    public location_service() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        location();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void location()
    {
        String id= "mein.mutter";
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notif = new NotificationCompat.Builder(getBaseContext(),id);
        NotificationChannel channel = new NotificationChannel(id,"LocateME",NotificationManager.IMPORTANCE_NONE);channel.setLightColor(Color.BLUE);channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);notif = new NotificationCompat.Builder(this,id);
        manager.createNotificationChannel(channel);
        notif.setContentTitle("LocateMe");
        notif.setContentText("Holding a thread in background");
        notif.setCategory(Notification.CATEGORY_SERVICE);
        notif.setSmallIcon(R.drawable.ic_baseline_arrow_forward_24);
        startForeground(1,notif.build());
    }
    @Override
    public void onDestroy() {
        Intent broadcast = new Intent();
        broadcast.setAction("starter");
        broadcast.setClass(this,service_starter.class);
        this.sendBroadcast(broadcast);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}