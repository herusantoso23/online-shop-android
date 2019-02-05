package com.herusantoso.tokonezia.tokonezia.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.model.LoginResponse;
import com.herusantoso.tokonezia.tokonezia.restapi.RestApi;
import com.herusantoso.tokonezia.tokonezia.restapi.RestApiDomain;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.edit_username)
    EditText editUsername;

    @BindView(R.id.edit_password)
    EditText editPassword;

    ProgressDialog progressDialog;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        sharedpreferences = this.getSharedPreferences("mypref", Context.MODE_PRIVATE);
    }

    @OnClick({R.id.button_sign_in, R.id.text_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_sign_in:

                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                signIn(editUsername.getText().toString(), editPassword.getText().toString());
                break;
            case R.id.text_sign_up:
                Intent signUp = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signUp);
                break;
        }
    }

    public void signIn(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApiDomain.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String basicAuth = "Basic " + Base64.encodeToString("adminapp:password".getBytes(), Base64.NO_WRAP);

        RestApi restApi = retrofit.create(RestApi.class);
        Call<LoginResponse> call = restApi.signIn(basicAuth, "password", username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code() == 200) {
                    progressDialog.dismiss();

                    //save accessToken into sharedPrefrence
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("accessToken", response.body().getAccessToken().toString());
                    editor.apply();

                    Toast.makeText(SignInActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();

                    Intent main = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(main);
                } else if (response.code() == 401) {
                    progressDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(SignInActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
