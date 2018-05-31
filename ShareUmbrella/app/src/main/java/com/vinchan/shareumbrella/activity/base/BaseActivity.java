package com.vinchan.shareumbrella.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.vinchan.shareumbrella.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseActivity extends AppCompatActivity {

 private int layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
