package shivanktibrewalms.doms5;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by shivanktibrewal on 11/11/15.
 */
public class RetrieveFeedTask extends AsyncTask<String, String, String> {

    private Exception exception;
    public String result = "";
    public boolean gotResult = false;

    protected String doInBackground(String...strs) {

        //System.out.println("USERNAME: "+ strs[0]);

        String username = strs[0];
        String password = strs[1];
        String url = strs[2];

        System.out.println("URL: " + url);

        InputStream inputStream = null;
        String result = "";

        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("username", username);
            jsonObject.accumulate("password", password);

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            System.out.println("JSON string: " + json);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null) {
                result = convertInputStreamToString(inputStream);
                this.result = result;
                this.gotResult = true;
            }
            else
                result = "Did not work!";

            //System.out.println("RESULT: " + result);

        }

        catch (Exception e) {
            e.printStackTrace();
        }


        return result;

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}
