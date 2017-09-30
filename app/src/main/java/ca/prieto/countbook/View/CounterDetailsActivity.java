package ca.prieto.countbook.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.R;

public class CounterDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CounterRepository instance = CounterRepository.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_details);

        Intent intent = getIntent();
        String counterId = intent.getStringExtra(CounterListActivity.CounterListMessage);

        TextView currentCounterName = (TextView) findViewById(R.id.currentCounterName);
        currentCounterName.setText(instance.getCounterById(counterId).getName());

        TextView currentCount = (TextView) findViewById(R.id.currentCount);
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
}
