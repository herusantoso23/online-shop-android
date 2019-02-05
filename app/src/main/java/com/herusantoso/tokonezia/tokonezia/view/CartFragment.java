package com.herusantoso.tokonezia.tokonezia.view;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.adapter.CartViewAdapter;
import com.herusantoso.tokonezia.tokonezia.model.Cart;
import com.herusantoso.tokonezia.tokonezia.model.ResultMessage;
import com.herusantoso.tokonezia.tokonezia.restapi.RestApi;
import com.herusantoso.tokonezia.tokonezia.restapi.RestApiDomain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    SharedPreferences sharedPreferences;
    String accessToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, view);

        sharedPreferences = getActivity().getSharedPreferences("mypref", getActivity().MODE_PRIVATE);
        accessToken = "Bearer " + sharedPreferences.getString("accessToken", null);

        rvCart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvCart.setItemAnimator(new DefaultItemAnimator());
        rvCart.setAdapter(cartViewAdapter);

        getAllCart();

        return view;
    }

    private void getAllCart() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApiDomain.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi restApi = retrofit.create(RestApi.class);
        Call<ResultMessage> call = restApi.getAllCart(accessToken);
        call.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                if (response.code() == 200) {
                    Gson gson = new Gson();
                    JsonArray jsonArray = gson.toJsonTree(response.body().getResult()).getAsJsonArray();
                    carts = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<Cart>>(){}.getType());

                    cartViewAdapter = new CartViewAdapter(getActivity(), carts);
                    rvCart.setAdapter(cartViewAdapter);
                } else if (response.code() == 400 || response.code() == 500){
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401) {
                    Toast.makeText(getActivity(), "Session expired, please sign in again", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), SignInActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }  else{
                    Toast.makeText(getActivity(), "Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultMessage> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
