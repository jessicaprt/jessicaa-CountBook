package ca.prieto.countbook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import ca.prieto.countbook.Model.Counter;
import ca.prieto.countbook.Model.CounterRepository;
import ca.prieto.countbook.Model.ICounterObserver;
import ca.prieto.countbook.R;

public class CounterListActivity extends AppCompatActivity implements ICounterObserver, CounterAdapter.IOnItemClickListener {
    ArrayList<Counter> counters;
    CounterAdapter adapter;
    public static final String CounterListMessage = "com.prieto.CounterBook.CounterList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_list);

        RecyclerView recyclerViewCounters = (RecyclerView)findViewById(R.id.recyclerViewCounters);

        counters = (ArrayList<Counter>) CounterRepository.getInstance().getCounterList();
        adapter = new CounterAdapter(this);
        adapter.setOnItemClickListener(this);
        recyclerViewCounters.setAdapter(adapter);

        recyclerViewCounters.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerViewCounters.addItemDecoration(itemDecoration);
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
    public void onCounterUpdated() {
        adapter.setCounterList(CounterRepository.getInstance().getCounterList());
    }

    @Override
    public void onItemClick(View itemView, int position) {
        Counter counter = counters.get(position);
        Intent intent = new Intent(this, CounterActivity.class);
        intent.putExtra(CounterListMessage, counter.getId());
        startActivity(intent);
    }

    public void addCounter(View view) {
        Intent intent = new Intent(this, AddCounterActivity.class);
        startActivity(intent);
    }
}
