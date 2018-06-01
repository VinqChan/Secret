package com.vinchan.shareumbrella.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinchan.shareumbrella.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealNameCertifiActivity extends BaseActivity {


    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.upload_card)
    ImageView uploadCard;
    @BindView(R.id.upload_handle_card)
    ImageView uploadHandleCard;
    @BindView(R.id.submit_btn)
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);
        ButterKnife.bind(this);
        titleTv.setText(getString(R.string.main_title));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.title_back_iv, R.id.upload_card, R.id.upload_handle_card, R.id.submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.upload_card:
                break;
            case R.id.upload_handle_card:
                break;
            case R.id.submit_btn:
                startActivity(new Intent(RealNameCertifiActivity.this,MainNoRealNameActivity.class));
                break;
        }
    }
}
