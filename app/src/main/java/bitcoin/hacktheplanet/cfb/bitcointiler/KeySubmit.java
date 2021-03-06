package bitcoin.hacktheplanet.cfb.bitcointiler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KeySubmit extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "bitcoin.hacktheplanet.cfb.bitcointiler.MESSAGE";

    Button submitButton;
    EditText publicKey;
    Context context;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set EditText and Button
        submitButton = (Button) findViewById(R.id.key_submit_button);

        context = getApplicationContext();
        toast = Toast.makeText(context, "Invalid Public Key", Toast.LENGTH_SHORT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_submit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_key_submit, menu);
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

    public boolean buildQR(View view) {
        publicKey = (EditText) findViewById(R.id.key_enter);
        String pkey = publicKey.getText().toString();
        if (pkey.matches("^[13][a-km-zA-HJ-NP-Z0-9]{26,33}$")) {
            Intent pi = new Intent(this, MainActivity.class);
            pi.putExtra(EXTRA_MESSAGE, pkey);
            publicKey.setText("");
            startActivity(pi);
            return true;
        } else {
            toast.show();
            return false;
        }
    }
}
