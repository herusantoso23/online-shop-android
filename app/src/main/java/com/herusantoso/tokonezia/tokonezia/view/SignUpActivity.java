package com.herusantoso.tokonezia.tokonezia.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.herusantoso.tokonezia.tokonezia.R;
import com.herusantoso.tokonezia.tokonezia.model.ResultMessage;
import com.herusantoso.tokonezia.tokonezia.model.User;
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

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.edit_fullname)
    EditText editFullname;
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_phone_number)
    EditText editPhoneNumber;
    @BindView(R.id.edit_password)
    EditText editPassword;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text_sign_up)
    public void onViewClicked() {
        User user = new User();
        user.setEmail(editEmail.getText().toString());
        user.setFullname(editFullname.getText().toString());
        user.setPassword(editPassword.getText().toString());
        user.setPhoneNumber(editPhoneNumber.getText().toString());
        user.setUsername(editUsername.getText().toString());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        signIn(user);
    }

    public void signIn(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApiDomain.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi restApi = retrofit.create(RestApi.class);
        Call<ResultMessage> call = restApi.signUp(user);
        call.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                if (response.code() == 200) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultMessage> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
