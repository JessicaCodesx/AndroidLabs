package com.example.firstapplicationcst2355;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //execute the asynctask to fetch data from star wars api
        new StarWarsApiTask().execute();
    }

    //fetch data from the star wars api
    private class StarWarsApiTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://swapi.dev/api/people/?format=json");

                //open connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                //input stream to a string
                InputStream inputStream = urlConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                if (scanner.hasNext()) {
                    return scanner.next();
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //parse the response and get data
            try {
                JSONObject jsonResult = new JSONObject(result);
                JSONArray characters = jsonResult.getJSONArray("results");

                // get names and start the adapter
                List<String> characterNames = new ArrayList<>();
                for (int i = 0; i < characters.length(); i++) {
                    JSONObject character = characters.getJSONObject(i);
                    String name = character.getString("name");
                    characterNames.add(name);
                }

                //create instance of adapter
                CharacterListAdapter adapter = new CharacterListAdapter(MainActivity.this, characterNames);
                ListView listView = findViewById(R.id.listView);

                //set the adapter
                listView.setAdapter(adapter);

                //set an listener for listview
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedCharacter = (String) parent.getItemAtPosition(position);

                        //check phone or tablet
                        View frameLayout = findViewById(R.id.frameLayout);

                        if (frameLayout == null) {
                            //phone: start empty activity and pass relevant information in a bundle
                            Intent intent = new Intent(MainActivity.this, EmptyActivity.class);
                            intent.putExtra("selectedCharacter", selectedCharacter);
                            startActivity(intent);
                        } else {
                            //tablet:replace the frame with details fragment
                            DetailsFragment detailsFragment = new DetailsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("selectedCharacter", selectedCharacter);
                            detailsFragment.setArguments(bundle);

                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.frameLayout, detailsFragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
