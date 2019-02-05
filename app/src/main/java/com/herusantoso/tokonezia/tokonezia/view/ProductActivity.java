package com.herusantoso.tokonezia.tokonezia.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.model.Cart;
import com.herusantoso.tokonezia.tokonezia.model.CartRequest;
import com.herusantoso.tokonezia.tokonezia.model.DetailProduct;
import com.herusantoso.tokonezia.tokonezia.model.Product;
import com.herusantoso.tokonezia.tokonezia.model.ResultMessage;
import com.herusantoso.tokonezia.tokonezia.restapi.RestApi;
import com.herusantoso.tokonezia.tokonezia.restapi.RestApiDomain;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    SharedPreferences sharedPreferences;
    String accessToken;

    ProgressDialog progressDialog;

    DetailProduct detailProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);

        sharedPreferences = this.getSharedPreferences("mypref", this.MODE_PRIVATE);
        accessToken = "Bearer " + sharedPreferences.getString("accessToken", null);

        detailProduct = new DetailProduct();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        getProduct();
    }

    @OnClick(R.id.button_add_to_cart)
    public void onViewClicked() {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setProductId(detailProduct.getId());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApiDomain.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi restApi = retrofit.create(RestApi.class);
        Call<ResultMessage> call = restApi.addToCart(accessToken, cartRequest);
        call.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                if (response.code() == 200) {
                    progressDialog.dismiss();
                    Toast.makeText(ProductActivity.this, "Add to cart success", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (response.code() == 400 || response.code() == 500){
                    progressDialog.dismiss();
                    Toast.makeText(ProductActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401) {
                    progressDialog.dismiss();
                    Toast.makeText(ProductActivity.this, "Session expired, please sign in again", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    progressDialog.dismiss();
                    Toast.makeText(ProductActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultMessage> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(ProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getProduct() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApiDomain.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi restApi = retrofit.create(RestApi.class);
        Call<ResultMessage> call = restApi.getDetailProductById(accessToken, getIntent().getStringExtra("productId"));
        call.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                if (response.code() == 200) {
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.toJsonTree(response.body().getResult()).getAsJsonObject();
                    detailProduct = gson.fromJson(jsonObject.toString(), DetailProduct.class);

                    textProductName.setText(detailProduct.getName());
                    textProductPrice.setText("Rp. " + detailProduct.getPrice());
                    textShopName.setText(detailProduct.getShop().getName());
                    textShopLocation.setText(detailProduct.getShop().getCity());
                    Glide.with(ProductActivity.this)
                            .load(detailProduct.getImage())
                            .placeholder(R.drawable.tokenezia)
                            .into(imageProduct);

                    progressDialog.dismiss();
                } else if (response.code() == 400 || response.code() == 500){
                    progressDialog.dismiss();
                    Toast.makeText(ProductActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401) {
                    Toast.makeText(ProductActivity.this, "Session expired, please sign in again", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(ProductActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }  else{
                    progressDialog.dismiss();
                    Toast.makeText(ProductActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultMessage> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(ProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
