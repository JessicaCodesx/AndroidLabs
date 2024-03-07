package com.example.firstapplicationcst2355;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        //create an instance of the detail fragment
        DetailsFragment detailsFragment = new DetailsFragment();

        new StarWarsApiTask(detailsFragment).execute();
    }

    //asynctask to get data from api
    private class StarWarsApiTask extends AsyncTask<Void, Void, Bundle> {
        private DetailsFragment detailsFragment;

        public StarWarsApiTask(DetailsFragment detailsFragment) {
            this.detailsFragment = detailsFragment;
        }

        @Override
        protected Bundle doInBackground(Void... voids) {
            Bundle bundle = new Bundle();

            try {
                URL url = new URL("https://swapi.dev/api/people/1/?format=json");

                //open connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // input stream to string
                InputStream inputStream = urlConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                if (scanner.hasNext()) {
                    String result = scanner.next();

                    // get info from response
                    JSONObject jsonResult = new JSONObject(result);
                    String name = jsonResult.getString("name");
                    String height = jsonResult.getString("height");
                    String mass = jsonResult.getString("mass");

                    //add data to the bundle
                    bundle.putString("name", name);
                    bundle.putString("height", height);
                    bundle.putString("mass", mass);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return bundle;
        }

        @Override
        protected void onPostExecute(Bundle result) {
            super.onPostExecute(result);

            //set the data to the detail fragment
            if (result != null) {
                detailsFragment.setArguments(result);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        }
    }
}