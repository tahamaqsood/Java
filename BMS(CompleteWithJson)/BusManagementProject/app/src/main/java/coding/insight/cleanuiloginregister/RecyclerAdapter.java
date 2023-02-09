package coding.insight.cleanuiloginregister;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public RecyclerAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView From;
        private TextView To;
        private TextView Bus;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            From = (TextView) itemView.findViewById(R.id.From_text);
            To = (TextView) itemView.findViewById(R.id.To_text);
            Bus = (TextView) itemView.findViewById(R.id.Bus_text);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.cardsview, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                Holidays holidays = (Holidays) listRecyclerItem.get(i);

                itemViewHolder.From.setText(holidays.getFrom());
                itemViewHolder.To.setText(holidays.getTo());
                itemViewHolder.Bus.setText(holidays.getBus());
        }

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}