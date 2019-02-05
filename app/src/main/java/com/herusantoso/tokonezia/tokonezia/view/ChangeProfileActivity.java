package com.herusantoso.tokonezia.tokonezia.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.herusantoso.tokonezia.tokonezia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeProfileActivity extends AppCompatActivity {

    @BindView(R.id.edit_fullname)
    EditText editFullname;
    @BindView(R.id.edit_phone_number)
    EditText editPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_change_profile)
    public void onViewClicked() {
        finish();
    }
}
