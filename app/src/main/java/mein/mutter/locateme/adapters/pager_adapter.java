package mein.mutter.locateme.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import mein.mutter.locateme.R;

public class pager_adapter extends FragmentPagerAdapter {
    private Context c;
    public pager_adapter(Context con, FragmentManager fm)
    {
        super(fm);
        this.c = con;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new lottie_frag(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
