package mein.mutter.locateme.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.JsonReader;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class datalink extends SQLiteOpenHelper {
    private String email = "";
    private String password = "";
    public datalink(Context con,String e,String p)
    {
        super(con,"location-data",null,1);
        this.email = e;
        this.password = p;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE location(email TEXT,password TEXT,location TEXT)";
        sqLiteDatabase.execSQL(query);
    }
    public void newuser()
    {
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email",email);
        values.put("password",password);
        values.put("location","{'locations':[{}]}");
        db.insert("location",null,values);
    }
    private String getPassword()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM location WHERE email='"+email+"'",null);
        cursor.moveToFirst();
        try {
            return cursor.getString(1);
        }catch (Exception e)
        {
            return "";
        }
    }
    public boolean confirmpass()
    {
       String readpass = getPassword();
       if(readpass.trim().equals(password.trim()))
           return true;
       else
           return false;
    }
    public boolean check()
    {
        String readpass = getPassword();
        if(readpass.trim().isEmpty())
            return false;
        else
            return true;
    }
    public String update_location(Location location,String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM location WHERE email='"+email+"'";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        String locations = cursor.getString(2);
        locations = locations.substring(0,locations.length()-2);
        String attach = ",{"+String.valueOf("'lat':"+location.getLatitude())+",'long':"+String.valueOf(location.getLongitude())+",'time':"+time+"}]}";
        locations = locations + attach;
        ContentValues cv = new ContentValues();
        cv.put("location",locations);
        db.update("location",cv,"email='"+email+"'",null);
        return locations;
    }
    public String get_locations()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM location WHERE email='"+email+"'";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        return cursor.getString(2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
