package shivanktibrewalms.doms5;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.Intent;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.IOException;
import java.net.MalformedURLException;

public class MainActivity extends ActionBarActivity {

    public static String resultKey = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDriver();

            }
        });

        Button signupButton = (Button) findViewById(R.id.buttonSignUp);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpDriver();
            }
        });

        //setContentView(R.layout.splash);

        /*Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 3000){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    };
                    //startActivity(new Intent("com.tutorial.CLEARSCREEN"));
                    //setContentView(R.layout.activity_main);

                }

                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                finally{
                    finish();
                }
            }

        };

        logoTimer.start();*/


    }
    private void signUpDriver() {
        System.out.println("\nSIGN UP!\n");

        Intent i = new Intent(getApplicationContext(), SignUpActivity.class);

        startActivity(i);

    }
    private void loginDriver() {


        //Get credentials
        EditText txtUsername = (EditText) findViewById(R.id.txtusername);
        String username = txtUsername.getText().toString();

        EditText txtPassword = (EditText) findViewById(R.id.txtpassword);
        String password = txtPassword.getText().toString();


        RetrieveFeedTask connect = new RetrieveFeedTask();

        try {
            String url = "http://10.0.0.2:8000/driverLogin";
            connect.execute(username, password, url);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        String result = "";

        while (connect.gotResult == false) {
            //Wait to get result
        }

        result = connect.result;

        //System.out.println("RESULT: " + result);

        //System.out.println("\nLOGIN BUTTON CLICKED!\n\n");

        EditText info = (EditText) findViewById(R.id.txtusername);
        if (result.contains("<!-- views/profile.ejs -->")) {
            //info.setText("Login Successful");
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);

            this.resultKey = result;
            i.putExtra(resultKey, result);
            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(), "Login Failed. Please try again",
                    Toast.LENGTH_LONG).show();
            //info.setText("Login Failed");
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
