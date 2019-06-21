package com.example.myrestraunts.services;
import com.example.myrestraunts.Constants;
import com.example.myrestraunts.models.Restraunt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class YelpService {

    public static void findRestaurants(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.YELP_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }


    public ArrayList<Restraunt> processResults(Response response) {
        ArrayList<Restraunt> restraunts = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject yelpJSON = new JSONObject(jsonData);
            JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
            if (response.isSuccessful()) {
                for (int i = 0; i < businessesJSON.length(); i++) {
                    JSONObject restrauntJSON = businessesJSON.getJSONObject(i);
                    String name = restrauntJSON.getString("name");
                    String phone = restrauntJSON.optString("display_phone", "Phone not available");
                    String website = restrauntJSON.getString("url");
                    double rating = restrauntJSON.getDouble("rating");
                    String imageUrl = restrauntJSON.getString("image_url");
                    double latitude = restrauntJSON.getJSONObject("coordinates").getDouble("latitude");
                    double longitude = restrauntJSON.getJSONObject("coordinates").getDouble("longitude");
                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = restrauntJSON.getJSONObject("location").getJSONArray("display_address");
                    for (int y = 0; y < addressJSON.length(); y++) {
                        address.add(addressJSON.get(y).toString());
                    }
                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoriesJSON = restrauntJSON.getJSONArray("categories");
                    for (int y = 0; y < categoriesJSON.length(); y++) {
                        categories.add(categoriesJSON.getJSONObject(y).getString("title"));
                    }
                    Restraunt restraunts1 = new Restraunt(name, phone, website, rating, imageUrl, address, latitude, longitude, categories);
                    restraunts.add(restraunts1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return restraunts;

    }
}