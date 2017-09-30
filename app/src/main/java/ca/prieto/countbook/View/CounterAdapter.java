package ca.prieto.countbook.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.prieto.countbook.Model.Counter;
import ca.prieto.countbook.R;

/**
 * Created by Jessica on 2017-09-28.
 */

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.ViewHolder> {

    private List<Counter> _counters = new ArrayList<Counter>();
    private OnItemClickListener listener;
    private Context _context;

    public CounterAdapter(Context mContext) {
        this._context = mContext;
    }

    /**
     * inflating a layout from XML and returning the holder
     *
     * @param parent the parent view to obtain the layout from
     * @param viewType
     * @return the ViewHolder (the holder for the XML layout) from the parent
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext(); //returns the context the view is running in, gets access to the resources
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View counterView = inflater.inflate(R.layout.activity_item_counter, parent, false);

        ViewHolder viewHolder = new ViewHolder(counterView);

        return (viewHolder);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Populates the data into the item through the holder
     * @param holder the ViewHolder that holds the layout
     * @param position
     */
    @Override
    public void onBindViewHolder(CounterAdapter.ViewHolder holder, int position) {
        Counter counter = _counters.get(position);

        TextView textView = holder.counterName;
        textView.setText(counter.getName());
    }

    @Override
    public int getItemCount() {
        return _counters.size();
    }

    public void setCounterList(List<Counter> counter) {
        this._counters = counter;
        notifyDataSetChanged();
    }



    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView counterName;

        public ViewHolder(final View itemView) {
            super(itemView);
            counterName = (TextView) itemView.findViewById(R.id.counter_name);
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
