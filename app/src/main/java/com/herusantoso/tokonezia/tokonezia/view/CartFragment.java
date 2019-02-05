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
        cart.setName("Jaket Sweater Hooide Original Treebeard");
        cart.setImage("https://s3.bukalapak.com/img/8029743145/w-1000/5510411_cd9e55d0_1696_4aac_ba20_924b6f19616c_1028_1100.jpg");
        cart.setPrice(new BigDecimal(100000));
        carts.add(cart);

        Cart cart2 = new Cart();
        cart2.setName("Jaket Sweater Hooide Original Treebeard");
        cart2.setImage("https://s3.bukalapak.com/img/3993143145/w-1000/0_f921457f_2c4a_486a_8ec7_f488f700f00f_2048_0.jpg");
        cart2.setPrice(new BigDecimal(100000));
        carts.add(cart2);

        Cart cart3 = new Cart();
        cart3.setName("Jaket Sweater Hooide Original Treebeard");
        cart3.setImage("https://s2.bukalapak.com/img/2388541934/w-300/Baju_muslim_gamis_dress_wanita_Ruby_2.jpg");
        cart3.setPrice(new BigDecimal(100000));
        carts.add(cart3);

        Cart cart4 = new Cart();
        cart4.setName("Jaket Sweater Hooide Original Treebeard");
        cart4.setImage("https://s3.bukalapak.com/img/8040190615/w-1000/20190104_152233.jpg");
        cart4.setPrice(new BigDecimal(100000));
        carts.add(cart4);

        Cart cart5 = new Cart();
        cart5.setName("Jaket Sweater Hooide Original Treebeard");
        cart5.setImage("https://s2.bukalapak.com/img/2388541934/w-300/Baju_muslim_gamis_dress_wanita_Ruby_2.jpg");
        cart5.setPrice(new BigDecimal(100000));
        carts.add(cart5);

        Cart cart6 = new Cart();
        cart6.setName("Jaket Sweater Hooide Original Treebeard");
        cart6.setImage("https://s3.bukalapak.com/img/8040190615/w-1000/20190104_152233.jpg");
        cart6.setPrice(new BigDecimal(100000));
        carts.add(cart6);

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
