package com.example.myrestraunts.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myrestraunts.R;
import com.example.myrestraunts.adapters.RestrauntListAdapter;
import com.example.myrestraunts.models.Restraunt;
import com.example.myrestraunts.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class RestrauntsActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private RestrauntListAdapter mAdapter;

    public ArrayList<Restraunt> mRestraunts  = new ArrayList<>();
    public static final String TAG = RestrauntsActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restraunts);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getRestraunts(location);

    }


    private void getRestraunts(String location){
        final YelpService yelpService = new YelpService();

        yelpService.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {

                    mRestraunts = yelpService.processResults(response);
                    RestrauntsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String[] restrauntNames = new String[mRestraunts.size()];
                            for (int i=0; i < restrauntNames.length; i++){
                                restrauntNames[i] = mRestraunts.get(i).getName();
                            }
                            mAdapter = new RestrauntListAdapter(getApplicationContext(),mRestraunts);
                            mRecyclerView.setAdapter(mAdapter);
                           RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RestrauntsActivity.this);
                           mRecyclerView.setLayoutManager(layoutManager);
                           mRecyclerView.setHasFixedSize(true);
                        }
                    });
                }

        });
    }


}
