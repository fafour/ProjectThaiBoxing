package fafour.projectthaiboxing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Fafour on 14/1/2560.
 */

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:  TabFragmentAllPackage tab1 = new TabFragmentAllPackage();
                return tab1;
            case 1:  TabFragmentTraining tab2 = new TabFragmentTraining();
                return tab2;
            case 2:  TabFragmentAccommodation tab3 = new TabFragmentAccommodation();
                return tab3;
            case 3:  TabFragmentAllPackage tab4 = new TabFragmentAllPackage();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
