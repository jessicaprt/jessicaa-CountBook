package ca.prieto.countbook.View;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import ca.prieto.countbook.Controller.CounterController;
import ca.prieto.countbook.R;

public class AddCounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);
    }

    public void createCounter(View view) {
        EditText counterName = (EditText) findViewById(R.id.counterName);
        TextInputLayout counterNameWrapper = (TextInputLayout) findViewById(R.id.counterNameWrapper);
        counterNameWrapper.setErrorEnabled(true);
        counterNameWrapper.setError("This field cannot be blank!");

        EditText initialValue = (EditText) findViewById(R.id.initialValue);
        TextInputLayout initialValueWrapper = (TextInputLayout) findViewById(R.id.initialValueWrapper);
        initialValueWrapper.setErrorEnabled(true);
        initialValueWrapper.setError("This field cannot be blank");

        EditText counterDescription = (EditText) findViewById(R.id.counterDescription);

        if ( !counterName.getText().toString().trim().equalsIgnoreCase("") ||
             !initialValue.getText().toString().trim().equalsIgnoreCase("") ) {

            CounterController.addCounter(counterName.getText().toString(),
                    Integer.parseInt(initialValue.getText().toString()),
                    counterDescription.getText().toString());
            finish();
        }
    }
}
