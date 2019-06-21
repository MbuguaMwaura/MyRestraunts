package com.example.myrestraunts.ui;

import android.graphics.Typeface;
import android.os.Bundle;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myrestraunts.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.myrestraunts.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindRestrauntsButton;
    private EditText mLocationEditText;
    private TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mFindRestrauntsButton = (Button) findViewById(R.id.findRestrauntsButton);

        Typeface shadedFont = Typeface.createFromAsset(getAssets(), "fonts/fontone.ttf");
        mAppNameTextView.setTypeface(shadedFont);


        mFindRestrauntsButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
//                    Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_LONG).show();
        if (view == mFindRestrauntsButton) {
            String location =mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, RestrauntsActivity.class);
            Toast.makeText(MainActivity.this, "worked " + location, Toast.LENGTH_LONG).show();
            intent.putExtra("location", location);

            startActivity(intent);
        }
    }
}
