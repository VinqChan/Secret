package com.vinchan.shareumbrella.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vinchan.shareumbrella.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonCenterActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
        ButterKnife.bind(this);
        //titleTv.setText(getString(R.string.main_title));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
