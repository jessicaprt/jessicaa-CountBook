package ca.prieto.countbook.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ca.prieto.countbook.Model.Counter;
import ca.prieto.countbook.R;

/**
 * Created by Jessica on 2017-09-28.
 * based on ReclyclerView tutorial found on:
 *      https://guides.codepath.com/android/Using-the-RecyclerView#create-the-recyclerview-within-layout
 */

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.ViewHolder> {

    private List<Counter> counters = new ArrayList<Counter>();
    private IOnItemClickListener listener;
    private Context context;

    public CounterAdapter(Context mContext) {
        this.context = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext(); //returns the context the view is running in, gets access to the resources
        LayoutInflater inflater = LayoutInflater.from(context);
        View counterView = inflater.inflate(R.layout.activity_item_counter, parent, false);
        ViewHolder viewHolder = new ViewHolder(counterView);
        return (viewHolder);
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(CounterAdapter.ViewHolder holder, int position) {
        Counter counter = counters.get(position);

        TextView counterName = holder.counterName;
        counterName.setText(counter.getName());

        TextView currentCount = holder.currentCount;
        currentCount.setText(counter.getCurrentValue().toString());

        TextView currentDate = holder.currentDate;
        currentDate.setText(new SimpleDateFormat("yyyy-MM-dd")
                            .format(counter.getDate()));
    }

    @Override
    public int getItemCount() {
        return counters.size();
    }

    public void setCounterList(List<Counter> counters) {
        this.counters = counters;
        notifyDataSetChanged();
    }

    public interface IOnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView counterName;
        private TextView currentCount;
        private TextView currentDate;

        public ViewHolder(final View itemView) {
            super(itemView);

            counterName = (TextView) itemView.findViewById(R.id.counter_name);
            currentCount = (TextView) itemView.findViewById(R.id.currentCount);
            currentDate = (TextView) itemView.findViewById(R.id.lastUpdated);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }
}
