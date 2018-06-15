package com.dangong.oksan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnlockActivity extends BaseActivity {


    @BindView(R.id.button_1)
    TextView button1;
    @BindView(R.id.button_2)
    TextView button2;
    @BindView(R.id.button_3)
    TextView button3;
    @BindView(R.id.button_4)
    TextView button4;
    @BindView(R.id.button_5)
    TextView button5;
    @BindView(R.id.button_6)
    TextView button6;
    @BindView(R.id.button_7)
    TextView button7;
    @BindView(R.id.button_8)
    TextView button8;
    @BindView(R.id.button_9)
    TextView button9;
    @BindView(R.id.button_10)
    TextView button10;
    @BindView(R.id.top_view)
    LinearLayout topView;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_unlock_san;
    }

    @Override
    public String setTitle() {
        return getString(R.string.unlock);
    }

    @Override
    public void init() {
        super.init();
        ownInfoIv.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_1:
                setBackgroud(button1);
                break;
            case R.id.button_2:
                setBackgroud(button2);
                break;
            case R.id.button_3:
                setBackgroud(button3);
                break;
            case R.id.button_4:
                setBackgroud(button4);
                break;
            case R.id.button_5:
                setBackgroud(button5);
                break;
            case R.id.button_6:
                setBackgroud(button6);
                break;
            case R.id.button_7:
                setBackgroud(button7);
                break;
            case R.id.button_8:
                setBackgroud(button8);
                break;
            case R.id.button_9:
                setBackgroud(button9);
                break;
            case R.id.button_10:
                setBackgroud(button10);
                break;
        }
    }

    private void setBackgroud(TextView tv) {
        tv.setBackground(getResources().getDrawable(R.drawable.ic_kscsatn02));
    }
}
