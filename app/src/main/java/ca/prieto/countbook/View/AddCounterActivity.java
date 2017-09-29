package ca.prieto.countbook.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ca.prieto.countbook.Controller.CounterController;
import ca.prieto.countbook.Model.ICounterObserver;
import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.R;

public class AddCounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);
    }

    public void createCounter(View view) {
        EditText counterName = (EditText) findViewById(R.id.counterName);
        EditText initialValue = (EditText) findViewById(R.id.initialValue);
        EditText counterDescription = (EditText) findViewById(R.id.counterDescription);

        CounterController.addCounter(counterName.getText().toString(),
                                     Integer.parseInt(initialValue.getText().toString()),
                                     counterDescription.getText().toString());
        finish();
    }
}
