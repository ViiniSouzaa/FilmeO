package com.example.filmeo.activity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaFragment extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> tituloFragments =  new ArrayList<>();

    public AdapterListaFragment(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String titulo){
        fragments.add(fragment);
        tituloFragments.add(titulo);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tituloFragments.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
