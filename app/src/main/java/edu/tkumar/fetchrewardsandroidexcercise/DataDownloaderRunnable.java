package edu.tkumar.fetchrewardsandroidexcercise;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static java.net.HttpURLConnection.HTTP_OK;

public class DataDownloaderRunnable implements Runnable{

    private final MainActivity mainActivity;
    private static final String TAG = "DataDownloaderRunnable";

    public DataDownloaderRunnable(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {
        HttpsURLConnection connection = null;
        BufferedReader reader = null;

        try{
            String urlString = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
            Uri.Builder buildURL = Uri.parse(urlString).buildUpon();

            String urlToUse = buildURL.build().toString();
            URL url = new URL(urlToUse);

            Log.d(TAG, "run: " + urlToUse);

            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            StringBuilder result = new StringBuilder();

            boolean error;
            if(responseCode == HTTP_OK){
                error= false;
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                while((line = reader.readLine())!=null){
                    result.append(line).append("\n");
                }
            }else {
                error = true;
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

                String line;

                while((line = reader.readLine())!=null){
                    result.append(line).append("\n");
                }
            }
            Log.d(TAG, "run: " + result.toString());
            process(result.toString(), error);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(connection!=null){
                connection.disconnect();
            }
            if(reader != null){
                try{
                    reader.close();
                }catch(IOException e){
                    Log.e(TAG, "run: " + e.getMessage());
                }
            }
        }
    }

    private void process(String s, boolean error){
        if(error){
            mainActivity.runOnUiThread(()->mainActivity.showErrorDialog(s));
            return;
        }
        try{
            JSONArray jsonArray = new JSONArray(s);
            int length = jsonArray.length();
            for(int i = 0; i < length; i++) {
                String id = "";
                String listId = "";
                String name = "";
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.has("name")){
                    name = jsonObject.getString("name");
                    if(!(name.equals("null")) && !(name.equals(""))) {
                        Log.d(TAG, "process: " + name);
                        if(jsonObject.has("id"))
                            id = jsonObject.getString("id");
                        if(jsonObject.has("listId"))
                            listId = jsonObject.getString("listId");
                        Data data = new Data(id, listId, name);
                        switch (listId) {
                            case "1":
                                mainActivity.updateListIdOne(data);
                                break;
                            case "2":
                                mainActivity.updateListIdTwo(data);
                                break;
                            case "3":
                                mainActivity.updateListIdThree(data);
                                break;
                            case "4":
                                mainActivity.updateListIdFour(data);
                                break;
                        }
                    }
                }
            }
            mainActivity.updateGroupedData();
            mainActivity.runOnUiThread(()->mainActivity.setUpRecyclerView());
        }catch(JSONException e){
            e.printStackTrace();
        }catch (NullPointerException e){

        }
    }
}
