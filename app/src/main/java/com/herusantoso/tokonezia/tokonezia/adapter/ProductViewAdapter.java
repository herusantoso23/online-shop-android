package com.herusantoso.tokonezia.tokonezia.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.model.Product;
import com.herusantoso.tokonezia.tokonezia.view.ProductActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ViewHolder> {

    private Context context;
    private List<Product> products;

    public ProductViewAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productId = product.getId();
        holder.textProductName.setText(product.getName());
        holder.textProductPrice.setText("Rp." + product.getPrice().toString());
        Glide.with(context)
                .load(product.getImage())
                .placeholder(R.drawable.tokenezia)
                .into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_product)
        ImageView imageProduct;
        @BindView(R.id.text_product_name)
        TextView textProductName;
        @BindView(R.id.text_product_price)
        TextView textProductPrice;

        String productId;

        Activity activity;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            activity = (Activity) context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(activity.getApplicationContext(), ProductActivity.class);
            intent.putExtra("productId", productId);
            activity.startActivity(intent);
        }
    }

}
