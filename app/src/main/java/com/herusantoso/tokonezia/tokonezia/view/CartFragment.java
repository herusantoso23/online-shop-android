package com.herusantoso.tokonezia.tokonezia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.adapter.CartViewAdapter;
import com.herusantoso.tokonezia.tokonezia.model.Cart;
import com.herusantoso.tokonezia.tokonezia.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CartFragment extends Fragment {

    @BindView(R.id.rv_cart)
    RecyclerView rvCart;
    @BindView(R.id.best_seller_layout)
    LinearLayout bestSellerLayout;
    @BindView(R.id.button_add_to_cart)
    Button buttonAddToCart;
    Unbinder unbinder;
    private View view;

    private CartViewAdapter cartViewAdapter;
    private List<Cart> carts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, view);

        rvCart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvCart.setItemAnimator(new DefaultItemAnimator());
        rvCart.setAdapter(cartViewAdapter);

        getAllCart();

        return view;
    }

    private void getAllCart() {
        Cart cart = new Cart();
        Product product = new Product();
        product.setName("Jaket Sweater Hooide Original Treebeard");
        product.setImage("https://s3.bukalapak.com/img/8029743145/w-1000/5510411_cd9e55d0_1696_4aac_ba20_924b6f19616c_1028_1100.jpg");
        product.setPrice(new BigDecimal(100000));
        cart.setProduct(product);
        carts.add(cart);

        Cart cart2 = new Cart();
        Product product2 = new Product();
        product2.setName("Jaket Sweater Hooide Original Treebeard");
        product2.setImage("https://s3.bukalapak.com/img/8029743145/w-1000/5510411_cd9e55d0_1696_4aac_ba20_924b6f19616c_1028_1100.jpg");
        product2.setPrice(new BigDecimal(100000));
        cart2.setProduct(product2);
        carts.add(cart2);

        Cart cart3 = new Cart();
        Product product3 = new Product();
        product3.setName("Jaket Sweater Hooide Original Treebeard");
        product3.setImage("https://s3.bukalapak.com/img/8029743145/w-1000/5510411_cd9e55d0_1696_4aac_ba20_924b6f19616c_1028_1100.jpg");
        product3.setPrice(new BigDecimal(100000));
        cart3.setProduct(product3);
        carts.add(cart3);

        Cart cart4 = new Cart();
        Product product4 = new Product();
        product4.setName("Jaket Sweater Hooide Original Treebeard");
        product4.setImage("https://s3.bukalapak.com/img/8029743145/w-1000/5510411_cd9e55d0_1696_4aac_ba20_924b6f19616c_1028_1100.jpg");
        product4.setPrice(new BigDecimal(100000));
        cart4.setProduct(product4);
        carts.add(cart4);

        cartViewAdapter = new CartViewAdapter(getActivity(), carts);
        rvCart.setAdapter(cartViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_add_to_cart)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), OrderActivity.class);
        startActivity(intent);
    }
}
