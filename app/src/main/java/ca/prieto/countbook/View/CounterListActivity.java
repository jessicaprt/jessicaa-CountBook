package ca.prieto.countbook.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import ca.prieto.countbook.Model.Counter;
import ca.prieto.countbook.Model.ICounterObserver;
import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.R;

public class CounterListActivity extends AppCompatActivity implements ICounterObserver {
    ArrayList<Counter> counters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_list);

        RecyclerView recyclerViewCounters = (RecyclerView)findViewById(R.id.recyclerViewCounters);

        counters = (ArrayList<Counter>) CounterRepository.getInstance().getCounterList();
        CounterAdapter adapter = new CounterAdapter(counters, this);
        recyclerViewCounters.setAdapter(adapter);
        recyclerViewCounters.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addCounter(View view) {
        Intent intent = new Intent(this, AddCounterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCounterUpdated() {
        //TO-DO : render counters

        //updated list to be viewed on the page
        CounterRepository.getInstance().getCounterList();
    }
}
