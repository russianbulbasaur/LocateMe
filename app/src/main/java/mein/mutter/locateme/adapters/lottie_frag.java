package mein.mutter.locateme.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

import org.w3c.dom.Text;

import mein.mutter.locateme.R;

public class lottie_frag extends Fragment
{
    private int pos = 0;
    private LottieAnimationView lottieAnimationView;
    public lottie_frag(int position)
    {
        this.pos = position;
    }

    @Override
    public void onResume() {
        super.onResume();
        lottieAnimationView.playAnimation();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = (View) inflater.inflate(R.layout.viewpager_lottie_animation,container,false);
        lottieAnimationView = (LottieAnimationView) v.findViewById(R.id.lottie);
        TextView tv = (TextView)v.findViewById(R.id.extra_text);
        switch (pos){
            case 0:
                lottieAnimationView.setAnimation("location.json");
                lottieAnimationView.playAnimation();
                tv.setText("Easy Location updates using GPS");
                break;
            case 1:
                lottieAnimationView.setAnimation("database.json");
                lottieAnimationView.playAnimation();
                tv.setText("Uses local SQL Database with faster data recovery methods");
                break;
        }
        return v;
    }
}

