package shivanktibrewalms.doms5;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/

        Button signUpButton = (Button) findViewById(R.id.btnSign);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    private void signUp() {
        //System.out.println("\nHELLO HELLO HELLO \n");

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

        if (result.contains("<!-- views/profile.ejs -->")) {
            //info.setText("Login Successful");
            Intent i = new Intent(getApplicationContext(), MainActivity.class);

            //this.resultKey = result;
            //i.putExtra(resultKey, result);
            Toast.makeText(getApplicationContext(), "Sign Up Success.",
                    Toast.LENGTH_LONG).show();
            startActivity(i);
        }
        else {
            //Toast.makeText(getApplicationContext(), "Sign Up Success. Please try again",
                 //   Toast.LENGTH_LONG).show();
            //info.setText("Login Failed");

            Intent i = new Intent(getApplicationContext(), MainActivity.class);

            //this.resultKey = result;
            //i.putExtra(resultKey, result);
            Toast.makeText(getApplicationContext(), "Sign Up Success.",
                    Toast.LENGTH_LONG).show();
            startActivity(i);
        }

        System.out.println("RESULT: " + result);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sign_up2, container, false);
            return rootView;
        }
    }
}
