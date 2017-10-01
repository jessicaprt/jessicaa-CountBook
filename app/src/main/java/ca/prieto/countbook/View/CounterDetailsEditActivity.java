package ca.prieto.countbook.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ca.prieto.countbook.Controller.CounterController;
import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.R;

public class CounterDetailsEditActivity extends AppCompatActivity {
    CounterRepository instance;
    String counterId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = CounterRepository.getInstance();
        Intent intent = getIntent();
        counterId = intent.getStringExtra(CounterDetailsActivity.CounterDetailsMessage);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_details_edit);
    }

    public void deleteCounter(View view) {
        CounterController.deleteCounter(counterId);
        finish();
    }
}
