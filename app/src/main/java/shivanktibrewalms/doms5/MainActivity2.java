package shivanktibrewalms.doms5;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;


public class MainActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        Toast.makeText(getApplicationContext(), "Login Successful",
                Toast.LENGTH_LONG).show();
        Intent intent = getIntent();



        String result = intent.getStringExtra(MainActivity.resultKey);

        TextView driverInfo = (TextView) findViewById(R.id.txtDriverInfo);


        driverInfo.setText("LOGIN SUCCESSFUL");

        //String info = "";
        //int flag = 0;
        /*
        //Step 1. Remove Tags
        for (int i = 0; i < result.length(); i++) {

            if (result.charAt(i) == '<') {
                flag = 1;
                i++;
                continue;
            }
            else if (result.charAt(i) == '>') {
                flag = 0;
                i++;
                continue;
            }

            if (flag == 1) {
                i++;
            }
            else {
                info += result.charAt(i);
            }

        }*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
