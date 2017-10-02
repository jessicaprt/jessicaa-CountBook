package ca.prieto.countbook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import ca.prieto.countbook.Controller.CounterController;
import ca.prieto.countbook.Model.Counter;
import ca.prieto.countbook.R;

public class CounterDetailsActivity extends AppCompatActivity {
    private String counterId;
    private Counter currentCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_details);

        Intent intent = getIntent();
        counterId = intent.getStringExtra(CounterActivity.CounterDetailsMessage);

        currentCounter = CounterController.getCounter(counterId);
        renderView();
    }

    public void renderView() {
        setText((TextView) findViewById(R.id.counterName),
                currentCounter.getName().toString());

        setText((TextView) findViewById(R.id.currentValue),
                "Current Value: " + currentCounter.getCurrentValue().toString());

        setText((TextView) findViewById(R.id.comment),
                "Comment: " + currentCounter.getComment().toString());

        setText((TextView) findViewById(R.id.currentDate),
                "Last updated: " + new SimpleDateFormat("yyyy-MM-dd")
                                            .format(currentCounter.getDate()));
    }

    public void setText(TextView textView, String message) {
        textView.setText(message);
    }
}
