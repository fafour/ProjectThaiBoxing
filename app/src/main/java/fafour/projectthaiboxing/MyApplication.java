package fafour.projectthaiboxing;

import android.app.Application;

import fafour.projectthaiboxing.util.IabHelper;

/**
 * Created by Fafour on 27/3/2560.
 */

public class MyApplication extends Application {
    private IabHelper mHelper;

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public IabHelper getmHelper() {
        return mHelper;
    }
    public void setmHelper(IabHelper mHelper) {
        this.mHelper = mHelper;
    }
}