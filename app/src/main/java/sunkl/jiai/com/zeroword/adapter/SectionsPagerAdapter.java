package sunkl.jiai.com.zeroword.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sunkl.jiai.com.zeroword.view.ItemFragment;
import sunkl.jiai.com.zeroword.view.StartFragment;

/**
 * Created by admin on 16/3/4.
 * 主界面的adapter
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        switch (position){
            case 0:
                return  ItemFragment.newInstance(position);
            case 1:
                return  StartFragment.newInstance(position);
            case 2:
                return  ItemFragment.newInstance(position+1);
        }
        return null;

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "1";
            case 1:
                return "2";
            case 2:
                return "3";
        }
        return null;
    }
}
