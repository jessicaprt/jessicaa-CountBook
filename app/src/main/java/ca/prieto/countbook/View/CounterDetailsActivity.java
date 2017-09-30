package ca.prieto.countbook.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.R;

public class CounterDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_details);

        Intent intent = getIntent();
        String counterId = intent.getStringExtra(CounterListActivity.CounterListMessage);

        TextView textView = (TextView) findViewById(R.id.currentCounterName);
        textView.setText(CounterRepository.getInstance().getCounterById(counterId).getName());
    }
}
