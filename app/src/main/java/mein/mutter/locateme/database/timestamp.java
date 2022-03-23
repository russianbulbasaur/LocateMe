package mein.mutter.locateme.database;

import java.util.Calendar;

public class timestamp {
    public String get_timestamp()
    {
        Calendar c = Calendar.getInstance();
        String total = "";
        total = total + c.get(Calendar.HOUR)+"&";
        total = total + c.get(Calendar.MINUTE)+"%";
        String am = (c.get(Calendar.AM_PM)==0)?"am":"pm";
        total = total + am;
        return total;
    }
}
