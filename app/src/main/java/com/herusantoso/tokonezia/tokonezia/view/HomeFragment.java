package com.herusantoso.tokonezia.tokonezia.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.adapter.ProductViewAdapter;
import com.herusantoso.tokonezia.tokonezia.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    @BindView(R.id.rv_best_seller_product)
    RecyclerView rvBestSellerProduct;
    Unbinder unbinder;
    private View view;

    private ProductViewAdapter productViewAdapter;
    private List<Product> products = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        //Best Seller Recycler View
        rvBestSellerProduct.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        rvBestSellerProduct.setItemAnimator(new DefaultItemAnimator());
        rvBestSellerProduct.setAdapter(productViewAdapter);

        getAllProduct();

        return view;
    }

    private void getAllProduct() {
        Product product = new Product();
        product.setName("Jaket Sweater Hooide Original Treebeard");
        product.setImage("https://s3.bukalapak.com/img/8029743145/w-1000/5510411_cd9e55d0_1696_4aac_ba20_924b6f19616c_1028_1100.jpg");
        product.setPrice(new BigDecimal(100000));
        products.add(product);

        Product product2 = new Product();
        product2.setName("Jaket Sweater Hooide Original Treebeard");
        product2.setImage("https://s3.bukalapak.com/img/3993143145/w-1000/0_f921457f_2c4a_486a_8ec7_f488f700f00f_2048_0.jpg");
        product2.setPrice(new BigDecimal(100000));
        products.add(product2);

        Product product3 = new Product();
        product3.setName("Jaket Sweater Hooide Original Treebeard");
        product3.setImage("https://s2.bukalapak.com/img/2388541934/w-300/Baju_muslim_gamis_dress_wanita_Ruby_2.jpg");
        product3.setPrice(new BigDecimal(100000));
        products.add(product3);

        Product product4 = new Product();
        product4.setName("Jaket Sweater Hooide Original Treebeard");
        product4.setImage("https://s3.bukalapak.com/img/8040190615/w-1000/20190104_152233.jpg");
        product4.setPrice(new BigDecimal(100000));
        products.add(product4);

        Product product5 = new Product();
        product5.setName("Jaket Sweater Hooide Original Treebeard");
        product5.setImage("https://s2.bukalapak.com/img/2388541934/w-300/Baju_muslim_gamis_dress_wanita_Ruby_2.jpg");
        product5.setPrice(new BigDecimal(100000));
        products.add(product5);

        Product product6 = new Product();
        product6.setName("Jaket Sweater Hooide Original Treebeard");
        product6.setImage("https://s3.bukalapak.com/img/8040190615/w-1000/20190104_152233.jpg");
        product6.setPrice(new BigDecimal(100000));
        products.add(product6);

        productViewAdapter = new ProductViewAdapter(getActivity(), products);
        rvBestSellerProduct.setAdapter(productViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
