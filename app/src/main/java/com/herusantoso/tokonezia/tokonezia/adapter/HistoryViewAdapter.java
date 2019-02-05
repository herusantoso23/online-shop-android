package com.herusantoso.tokonezia.tokonezia.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.model.History;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryViewAdapter extends RecyclerView.Adapter<HistoryViewAdapter.ViewHolder> {

    private Context context;
    private List<History> historys;

    public HistoryViewAdapter(Context context, List<History> historys) {
        this.context = context;
        this.historys = historys;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        History history = historys.get(position);
        holder.textOrderId.setText(history.getOrderId());
        holder.textProductPrice.setText("Rp." + history.getAmount().toString());
        holder.textProductStatus.setText(history.getStatus());
        if (history.getStatus().equalsIgnoreCase("waiting for payment")) {
            holder.textProductStatus.setBackground(context.getDrawable(R.drawable.custom_rounded_corner_yellow));
        } else if (history.getStatus().equalsIgnoreCase("already paid")) {
            holder.textProductStatus.setBackground(context.getDrawable(R.drawable.custom_rounded_corner_green));
        } else {
            holder.textProductStatus.setBackground(context.getDrawable(R.drawable.custom_rounded_corner_red));
        }
    }

    @Override
    public int getItemCount() {
        return historys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.text_order_id)
        TextView textOrderId;
        @BindView(R.id.text_product_price)
        TextView textProductPrice;
        @BindView(R.id.text_virtual_account)
        TextView textVirtualAccount;
        @BindView(R.id.text_product_status)
        TextView textProductStatus;
        Activity activity;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            activity = (Activity) context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(activity.getApplicationContext(), HistoryActivity.class);
//            activity.startActivity(intent);
        }
    }

}
