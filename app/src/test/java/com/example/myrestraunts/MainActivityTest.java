package com.myrestraunts;

import android.content.Intent;
import android.widget.TextView;

import com.example.myrestraunts.ui.MainActivity;
import com.example.myrestraunts.R;
import com.example.myrestraunts.ui.RestrauntsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.TestCase.assertTrue;

@RunWith(RobolectricTestRunner.class)

public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewContent(){
        TextView appNameTextView = activity.findViewById(R.id.appNameTextView);
        assertTrue("MyRestraunts".equals(appNameTextView.getText().toString()));
    }
    @Test
    public void secondActivityStarted(){
        activity.findViewById(R.id.findRestrauntsButton).performClick();
        Intent expectedIntent = new Intent(activity, RestrauntsActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}