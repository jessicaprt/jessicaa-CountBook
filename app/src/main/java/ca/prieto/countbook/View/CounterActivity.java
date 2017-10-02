package ca.prieto.countbook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ca.prieto.countbook.Controller.CounterController;
import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.Model.ICounterObserver;
import ca.prieto.countbook.R;

/**
 *  TODO: set counter limit
 *
 */
public class CounterActivity extends AppCompatActivity implements ICounterObserver {
    public static final String CounterDetailsMessage = "com.prieto.CounterBook.CounterDetails";
    CounterRepository instance;
    String counterId;

    public TextView getCurrentCount() {
        return (TextView) findViewById(R.id.currentCount);
    }

    public TextView getCounterName() {
        return (TextView) findViewById(R.id.currentCounterName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CounterRepository.getInstance().addObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        CounterRepository.getInstance().removeObserver(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = CounterRepository.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        Intent intent = getIntent();
        counterId = intent.getStringExtra(CounterListActivity.CounterListMessage);

        render();
    }

    public void render() {
        setTextFromView(getCounterName(), instance.getCounterById(counterId).getName());
        setTextFromView(getCurrentCount(), instance.getCounterById(counterId).getCurrentValue().toString());

        Button resetButton = (Button) findViewById(R.id.resetCounterButton);
        resetButton.setText("Reset Counter (" +
                CounterRepository
                        .getInstance()
                        .getCounterById(counterId)
                        .getInitialValue() +
                ")");
    }

    public void setTextFromView(TextView textView, String message) {
        textView.setText(message);
    }

    public void incrementCounter(View view) {
        CounterController.incrementCounter(counterId);
    }

    public void decrementCounter(View view) {
        CounterController.decrementCounter(counterId);
    }

    public void resetCounter(View view) {
        CounterController.resetCounter(counterId);
    }

    public void editCounter(View view) {
        Intent intent = new Intent(this, CounterEditActivity.class);
        intent.putExtra(CounterDetailsMessage, counterId);
        startActivity(intent);
    }

    public void viewDetails(View view) {
        Intent intent = new Intent(this, CounterDetailsActivity.class);
        intent.putExtra(CounterDetailsMessage, counterId);
        startActivity(intent);
    }

    @Override
    public void onCounterUpdated() {
        try {
            setTextFromView(getCurrentCount(), instance.getCounterById(counterId).getCurrentValue().toString());

           render();

        } catch (CounterRepository.CannotFindCounterException e) {
            finish();
        }
    }
}
