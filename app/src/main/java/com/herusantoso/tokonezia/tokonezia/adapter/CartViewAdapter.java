package com.herusantoso.tokonezia.tokonezia.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.model.Cart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.ViewHolder> {

    private Context context;
    private List<Cart> carts;

    public CartViewAdapter(Context context, List<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cart cart = carts.get(position);
        holder.textProductName.setText(cart.getProduct().getName());
        holder.textProductPrice.setText("Rp." + cart.getProduct().getPrice().toString());
        Glide.with(context)
                .load(cart.getProduct().getImage())
                .placeholder(R.drawable.tokenezia)
                .into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_product)
        ImageView imageProduct;
        @BindView(R.id.text_product_name)
        TextView textProductName;
        @BindView(R.id.text_product_price)
        TextView textProductPrice;
        @BindView(R.id.button_descrease_qty)
        Button buttonDescreaseQty;
        @BindView(R.id.text_quantity)
        TextView textQuantity;
        @BindView(R.id.button_increase_qty)
        Button buttonIncreaseQty;

        Activity activity;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            activity = (Activity) context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(activity.getApplicationContext(), CartActivity.class);
//            activity.startActivity(intent);
        }

        @OnClick({R.id.button_descrease_qty, R.id.button_increase_qty})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.button_descrease_qty:
                    if(Integer.parseInt(textQuantity.getText().toString()) > 0){
                        Integer qty = Integer.parseInt(textQuantity.getText().toString()) - 1;
                        textQuantity.setText(qty.toString());
                    }
                    break;
                case R.id.button_increase_qty:
                    Integer qty = Integer.parseInt(textQuantity.getText().toString()) + 1;
                    textQuantity.setText(qty.toString());
                    break;
            }
        }
    }

}
