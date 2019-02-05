package com.herusantoso.tokonezia.tokonezia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.herusantoso.tokonezia.tokonezia.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_sign_in, R.id.text_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_sign_in:
                Intent main = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(main);
                break;
            case R.id.text_sign_up:
                Intent signUp = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signUp);
                break;
        }
    }
}
