package ca.prieto.countbook.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.prieto.countbook.Model.Counter;
import ca.prieto.countbook.R;

/**
 * Created by Jessica on 2017-09-28.
 */

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.ViewHolder> {

    private List<Counter> counters;
    private Context mCOntext;

    public CounterAdapter(List<Counter> counters, Context mCOntext) {
        this.counters = counters;
        this.mCOntext = mCOntext;
    }

    private Context getContext() {
        return this.mCOntext;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView counterName;

        public ViewHolder(View itemView) {
            super(itemView);

            counterName = (TextView) itemView.findViewById(R.id.counter_name);
        }
    }
}
