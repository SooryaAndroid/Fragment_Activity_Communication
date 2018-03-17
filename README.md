# Fragment_Activity_Communication

### FragmentClass
```java
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private EditText edtMessage;
    private Button sendButton;
    OnMessageReadListener messageReadListener;//callback interface

    public MessageFragment() {
        // Required empty public constructor
    }

    public interface OnMessageReadListener {
        public void onMeaasgeRead(String message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        edtMessage = view.findViewById(R.id.edt_message);
        sendButton = view.findViewById(R.id.btn_send);
        sendButtonClick();

        return view;
    }

    private void sendButtonClick() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = edtMessage.getText().toString().trim();
                messageReadListener.onMeaasgeRead(message);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

        try {
            messageReadListener = (OnMessageReadListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must override on messageRead ...");
        }
    }
}
```

### MainActivity
```java
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
```
