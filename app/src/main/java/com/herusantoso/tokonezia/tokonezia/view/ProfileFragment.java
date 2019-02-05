package com.herusantoso.tokonezia.tokonezia.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herusantoso.tokonezia.tokonezia.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment {

    Unbinder unbinder;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.button_change_profile, R.id.button_change_password, R.id.button_open_shop, R.id.button_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_change_profile:
                Intent changeProfileIntent = new Intent(getActivity(), ChangeProfileActivity.class);
                startActivity(changeProfileIntent);
                break;
            case R.id.button_change_password:
                Intent changePasswordIntent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(changePasswordIntent);
                break;
            case R.id.button_open_shop:
                Intent openShopIntent = new Intent(getActivity(), ShopActivity.class);
                startActivity(openShopIntent);
                break;
            case R.id.button_logout:
                Intent signInIntent = new Intent(getActivity(), SignInActivity.class);
                startActivity(signInIntent);
                break;
        }
    }
}
