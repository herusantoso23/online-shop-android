package com.herusantoso.tokonezia.tokonezia.restapi;

import com.herusantoso.tokonezia.tokonezia.model.CartRequest;
import com.herusantoso.tokonezia.tokonezia.model.LoginResponse;
import com.herusantoso.tokonezia.tokonezia.model.Product;
import com.herusantoso.tokonezia.tokonezia.model.ResultMessage;
import com.herusantoso.tokonezia.tokonezia.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApi {

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<LoginResponse> signIn(@Header("Authorization") String auth,
                               @Field("grant_type") String grantType,
                               @Field("username") String username,
                               @Field("password") String password);

    @Headers("Content-Type: application/json")
    @POST("/api/v1/user")
    Call<ResultMessage> signUp(@Body User user);

    @Headers("Content-Type: application/json")
    @GET("/api/v1/product")
    Call<ResultMessage> getAllProduct(@Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @GET("/api/v1/product/{id}")
    Call<ResultMessage> getDetailProductById(@Header("Authorization") String auth,
                                             @Path("id") String id);

    @Headers("Content-Type: application/json")
    @POST("/api/v1/cart")
    Call<ResultMessage> addToCart(@Header("Authorization") String auth,
                                  @Body CartRequest cartRequest);

    @Headers("Content-Type: application/json")
    @GET("/api/v1/cart")
    Call<ResultMessage> getAllCart(@Header("Authorization") String auth);

}
