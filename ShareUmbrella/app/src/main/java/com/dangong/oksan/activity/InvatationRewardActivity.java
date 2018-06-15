package com.dangong.oksan.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.wxapi.WechatShareManager;

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
    private WechatShareManager mShareManager;

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
        mShareManager = WechatShareManager.getInstance(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.wchat_iv, R.id.link_iv,R.id.copy_invation_code_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wchat_iv:
                if (!isWebchatAvaliable()) {
                    ToastUtils.showShort( "请先安装微信");
                    return;
                }
                WechatShareManager.ShareContentText mShareContentText = (WechatShareManager.ShareContentText) mShareManager.getShareContentText("微信文本分享");
                mShareManager.shareByWebchat(mShareContentText, WechatShareManager.WECHAT_SHARE_TYPE_FRENDS);
                break;
            case R.id.link_iv:
                break;
            case R.id.copy_invation_code_tv:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String invationCode = invationCodeTv.getText().toString().substring(8);
                cm.setText(invationCode);
                ToastUtils.showShort("复制成功！");
                break;
        }
    }
    private boolean isWebchatAvaliable() {
        //检测手机上是否安装了微信
        try {
            getPackageManager().getPackageInfo("com.tencent.mm", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
