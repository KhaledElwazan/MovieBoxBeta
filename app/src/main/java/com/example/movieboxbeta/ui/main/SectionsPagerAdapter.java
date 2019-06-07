package com.example.movieboxbeta.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.movieboxbeta.R;
import com.example.movieboxbeta.fragment.LatestFragment;
import com.example.movieboxbeta.fragment.NowPlayingFragment;
import com.example.movieboxbeta.fragment.PopularFragment;
import com.example.movieboxbeta.fragment.TopRatedFragment;
import com.example.movieboxbeta.fragment.UpcomingFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3,R.string.tab_text_4,R.string.tab_text_5};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        Fragment ret=null;
        switch(position)
        {
            case 0:
                ret = new PopularFragment();
                break;
            case 1:
                ret = new TopRatedFragment();
                break;
            case 2:
                ret = new UpcomingFragment();
                break;
            case 3:
                ret = new NowPlayingFragment();
                break;
            case 4:
                ret = new LatestFragment();
        }



        return ret;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {

       // return TAB_TITLES.length;
        return 2;
    }
}