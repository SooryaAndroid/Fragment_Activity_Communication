package com.projects.samplefragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MessageFragment.OnMessageReadListener {
    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            MessageFragment messageFragment = new MessageFragment();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, messageFragment, null);
            fragmentTransaction.commit();
        }
    }

    @Override//communication through fragment
    public void onMeaasgeRead(String message) {
        txtMessage = findViewById(R.id.txt_display_message);
        txtMessage.setText(message);
    }
}
