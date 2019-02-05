package com.herusantoso.tokonezia.tokonezia.view;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.adapter.OrderViewAdapter;
import com.herusantoso.tokonezia.tokonezia.model.Cart;
import com.herusantoso.tokonezia.tokonezia.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends AppCompatActivity {

    @BindView(R.id.layout_shipping_address)
    LinearLayout layoutShippingAddress;
    @BindView(R.id.layout_payment_methode)
    LinearLayout layoutPaymentMethode;
    @BindView(R.id.layout_items)
    LinearLayout layoutItems;
    @BindView(R.id.edit_address)
    EditText editAddress;
    @BindView(R.id.edit_village)
    EditText editVillage;
    @BindView(R.id.edit_district)
    EditText editDistrict;
    @BindView(R.id.edit_city)
    EditText editCity;
    @BindView(R.id.edit_province)
    EditText editProvince;
    @BindView(R.id.edit_zip_code)
    EditText editZipCode;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;

    private OrderViewAdapter orderViewAdapter;
    private List<Cart> carts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        rvOrder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvOrder.setItemAnimator(new DefaultItemAnimator());
        rvOrder.setAdapter(orderViewAdapter);

        getAllOrder();

    }

    @OnClick({R.id.layout_act_shipping_address, R.id.layout_act_payment_methode, R.id.layout_act_items, R.id.button_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_act_shipping_address:
                if (layoutShippingAddress.getVisibility() == View.GONE) {
                    layoutShippingAddress.setVisibility(View.VISIBLE);
                } else {
                    layoutShippingAddress.setVisibility(View.GONE);
                }
                break;
            case R.id.layout_act_payment_methode:
                if (layoutPaymentMethode.getVisibility() == View.GONE) {
                    layoutPaymentMethode.setVisibility(View.VISIBLE);
                } else {
                    layoutPaymentMethode.setVisibility(View.GONE);
                }
                break;
            case R.id.layout_act_items:
                if (layoutItems.getVisibility() == View.GONE) {
                    layoutItems.setVisibility(View.VISIBLE);
                } else {
                    layoutItems.setVisibility(View.GONE);
                }
                break;
            case R.id.button_order:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(OrderActivity.this);
                View myView = getLayoutInflater().inflate(R.layout.dialog_order_success,null);
                TextView textOrderId = myView.findViewById(R.id.text_order_id);
                TextView textVirtualAccount = myView.findViewById(R.id.text_virtual_account);
                TextView textAmount = myView.findViewById(R.id.text_amount);
                Button buttonOk = myView.findViewById(R.id.buttom_ok);
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                mBuilder.setView(myView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

                break;
        }
    }

    private void getAllOrder() {
        Cart cart = new Cart();
        Product product = new Product();
        product.setName("Jaket Sweater Hooide Original Treebeard");
        product.setImage("https://s3.bukalapak.com/img/8029743145/w-1000/5510411_cd9e55d0_1696_4aac_ba20_924b6f19616c_1028_1100.jpg");
        product.setPrice(new BigDecimal(100000));
        cart.setProduct(product);
        cart.setQuantity(2);
        carts.add(cart);

        Cart cart2 = new Cart();
        Product product2 = new Product();
        product2.setName("Jaket Sweater Hooide Original Treebeard");
        product2.setImage("https://s3.bukalapak.com/img/8029743145/w-1000/5510411_cd9e55d0_1696_4aac_ba20_924b6f19616c_1028_1100.jpg");
        product2.setPrice(new BigDecimal(100000));
        cart2.setProduct(product2);
        cart2.setQuantity(2);
        carts.add(cart2);

        Cart cart3 = new Cart();
        Product product3 = new Product();
        product3.setName("Jaket Sweater Hooide Original Treebeard");
        product3.setImage("https://s3.bukalapak.com/img/8029743145/w-1000/5510411_cd9e55d0_1696_4aac_ba20_924b6f19616c_1028_1100.jpg");
        product3.setPrice(new BigDecimal(100000));
        cart3.setProduct(product3);
        cart3.setQuantity(2);
        carts.add(cart3);

        orderViewAdapter = new OrderViewAdapter(OrderActivity.this, carts);
        rvOrder.setAdapter(orderViewAdapter);
    }
}
