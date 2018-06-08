package com.vinchan.shareumbrella.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.R;
import com.vinchan.shareumbrella.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvatationRewardActivity extends BaseActivity {


    @BindView(R.id.wchat_iv)
    ImageView wchatIv;
    @BindView(R.id.link_iv)
    ImageView linkIv;
    @BindView(R.id.invation_code_tv)
    TextView invationCodeTv;
    @BindView(R.id.copy_invation_code_tv)
    TextView copyInvationCodeTv;
    @BindView(R.id.withdrawal_rule_tv)
    TextView withdrawalRuleTv;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invitation_reward;
    }

    @Override
    public String setTitle() {
        return getString(R.string.invatation_reward);
    }

    @Override
    public void init() {
        super.init();
        ButterKnife.bind(this);
        withdrawalRuleTv.setVisibility(View.VISIBLE);
        withdrawalRuleTv.setText(getString(R.string.reward_detail));

    }

    @OnClick(R.id.copy_invation_code_tv)
    public void onViewClicked() {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String invationCode = invationCodeTv.getText().toString().substring(8);
        cm.setText(invationCode);
        ToastUtils.showShort("复制成功！");
    }
}
