package ca.prieto.countbook.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import ca.prieto.countbook.Model.ICounterObserver;
import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.R;

public class AddCounterActivity extends AppCompatActivity implements ICounterObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);
    }

    public void createCounter() {
        EditText counterName = (EditText) findViewById(R.id.counterName);
        EditText initialValue = (EditText) findViewById(R.id.initialValue);
        EditText counterDescription = (EditText) findViewById(R.id.counterDescription);

        finish();
    }

    @Override
    public void onCounterUpdated() {
        CounterRepository.getInstance().getCounterById("");
    }
}
