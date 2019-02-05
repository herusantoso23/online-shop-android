package com.herusantoso.tokonezia.tokonezia.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.herusantoso.tokonezia.tokonezia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.edit_old_password)
    EditText editOldPassword;
    @BindView(R.id.edit_new_password)
    EditText editNewPassword;
    @BindView(R.id.edit_confirm_password)
    EditText editConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_change_password)
    public void onViewClicked() {
        finish();
    }
}
