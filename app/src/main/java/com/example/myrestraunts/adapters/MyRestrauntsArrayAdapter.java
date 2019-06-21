package com.example.myrestraunts.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyRestrauntsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mRestraunts;
    private String[] mCuisines;

    public MyRestrauntsArrayAdapter(Context mContext, int resource, String[] mRestraunts,
                                    String[] mCuisines )
    {
        super(mContext, resource);
        this.mContext = mContext;
        this.mCuisines = mCuisines;
        this.mRestraunts = mRestraunts;
    }

    @Override
    public Object getItem(int position){
        String restraunt = mRestraunts[position];
        String cousine = mCuisines[position];
        return String.format("%s \nServes great: %s", restraunt, cousine);
    }

    @Override
    public int getCount(){
        return mRestraunts.length;
    }
}
