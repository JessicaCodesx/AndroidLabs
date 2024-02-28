package com.example.firstapplicationcst2355;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private Bitmap result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image);
        progressBar = findViewById(R.id.progress);

        new Images().execute();
    }

    //inner class for cat photos
    private class Images extends AsyncTask<Void, Integer, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... voids) {
            while (!isCancelled()) {
                try {
                    URL jsonUrl = new URL("https://cataas.com/cat?json=true");
                    HttpURLConnection jsonConnection = (HttpURLConnection) jsonUrl.openConnection();
                    jsonConnection.connect();

                    InputStream jsonInput = jsonConnection.getInputStream();
                    String jsonString = convertStreamToString(jsonInput);

                    //oarse JSON for _id
                    JSONObject json = new JSONObject(jsonString);
                    String catID = null;

                    if (json.has("_id")) {
                        catID = json.getString("_id");

                        //construct image URL based on _id
                        String catUrl = "https://cataas.com/cat/" + catID;

                        //check if image exists locally
                        File imageFile = new File(getFilesDir(), catID + ".jpg");
                        if (imageFile.exists()) {
                            //load the local image
                            result = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                        } else {
                            //download image from URL
                            URL imageUrl = new URL(catUrl);
                            HttpURLConnection imageConnection = (HttpURLConnection) imageUrl.openConnection();
                            imageConnection.connect();

                            InputStream imageInput = imageConnection.getInputStream();
                            result = BitmapFactory.decodeStream(imageInput);

                            //save
                            saveImageToDevice(result, catID);

                            //simulate progress update
                            for (int i = 0; i < 100; i++) {
                                try {
                                    publishProgress(i);
                                    Thread.sleep(30);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        System.out.println("Error");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //sleep before the next
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //update progress bar with progress value
            int progressValue = values[0];
            progressBar.setProgress(progressValue);

            //if a new cat picture is selected, update the image view
            if (progressValue == 0) {
                imageView.setImageBitmap(result);
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(result);
        }

        private void saveImageToDevice(Bitmap bitmap, String catID) {
            FileOutputStream outputStream = null;
            try {
                outputStream = openFileOutput(catID + ".jpg", Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private String convertStreamToString(InputStream is) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
    }
}
