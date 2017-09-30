package ca.prieto.countbook.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.prieto.countbook.Controller.CounterController;
import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.Model.ICounterObserver;
import ca.prieto.countbook.R;

/**
 *  TODO: implement increment and decrement methods to CounterController
 *  TODO: implement delete method in CounterController
 *  TODO: implement edit method for each counter
 *  TODO: set counter limit
 *
 */
public class CounterDetailsActivity extends AppCompatActivity {
    CounterRepository instance;
    String counterId;

    public TextView getCurrentCount() {
        TextView currentCount = (TextView) findViewById(R.id.currentCount);
        return currentCount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = CounterRepository.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_details);

        Intent intent = getIntent();
        counterId = intent.getStringExtra(CounterListActivity.CounterListMessage);

        TextView currentCounterName = (TextView) findViewById(R.id.currentCounterName);
        currentCounterName.setText(instance.getCounterById(counterId).getName());

        TextView currentCount = getCurrentCount();
        currentCount.setText(instance.getCounterById(counterId).getCurrentValue().toString());

        TextView date = (TextView) findViewById(R.id.date);
        date.setText("Created: " + instance.getCounterById(counterId).getDate().toString());

        Button resetButton = (Button) findViewById(R.id.resetCounterButton);
        resetButton.setText("Reset Counter (" +
                            CounterRepository
                                    .getInstance()
                                    .getCounterById(counterId)
                                    .getInitialValue() +
                            ")");
    }

    public void incrementCounter(View view) {
        Integer currentValue = instance.getCounterById(counterId).getCurrentValue();
        String currentValueString = CounterController.incrementCounter(counterId, currentValue);
        changeCounterDisplay(getCurrentCount(), currentValueString);
    }

    public void decrementCounter(View view) {
        Integer currentValue = instance.getCounterById(counterId).getCurrentValue();
        String currentValueString =CounterController.decrementCounter(counterId, currentValue);
        changeCounterDisplay(getCurrentCount(), currentValueString);
    }

    public void resetCounter(View view) {
        Integer initialValue = instance.getCounterById(counterId).getInitialValue();
        CounterController.resetCounter(counterId, initialValue);
        changeCounterDisplay(getCurrentCount(), initialValue.toString());
    }

    public void changeCounterDisplay(TextView textView, String string) {
        textView.setText(string);
    }
}
