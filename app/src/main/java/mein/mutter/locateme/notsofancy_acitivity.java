package mein.mutter.locateme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class notsofancy_acitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notsofancy_acitivity);
        Intent i = new Intent(this,fancy_activity.class);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
//AIzaSyCNmAEL_mCujGCg7mI6G8SHco7tDhF_Mg0
