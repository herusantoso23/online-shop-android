package com.herusantoso.tokonezia.tokonezia.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.model.Product;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductActivity extends AppCompatActivity {

    @BindView(R.id.text_product_name)
    TextView textProductName;
    @BindView(R.id.text_shop_name)
    TextView textShopName;
    @BindView(R.id.text_shop_location)
    TextView textShopLocation;
    @BindView(R.id.text_product_price)
    TextView textProductPrice;
    @BindView(R.id.image_product)
    ImageView imageProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_add_to_cart)
    public void onViewClicked() {
        finish();
    }

    public void getProduct() {
        textProductName.setText("Jaket Sweater Hooide Original Treebeard");
        textProductPrice.setText("Rp. " + 1000);
        textShopName.setText("Art Fashion");
        textShopLocation.setText("Jakarta");
        Glide.with(this)
                .load("https://s3.bukalapak.com/img/8029743145/w-1000/5510411_cd9e55d0_1696_4aac_ba20_924b6f19616c_1028_1100.jpg")
                .placeholder(R.drawable.tokenezia)
                .into(imageProduct);

    }
}
