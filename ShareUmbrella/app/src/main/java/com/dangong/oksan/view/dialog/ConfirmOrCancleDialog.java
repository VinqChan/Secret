package com.dangong.oksan.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.dangong.oksan.R;
import com.dangong.oksan.util.DimensUtil;


/**
 * Created by Vinchan on 2018/6/7.
 */
public class ConfirmOrCancleDialog extends BaseDialog {



    TextView contentTv;
    Button confirmBtn;
    Button cancleBtn;
    private int width;
    private EnumDialogType dialogType;
    private Context context;
    private View customView;
    private Listener listener;
    private boolean isAutoClose = true;
    private TextView tvTipContent;

    /**
     * 构造函数 默认宽度为：屏幕宽度 * 5 / 6
     *
     * @param context    上下文对象
     * //@param dialogType 对话框类型 枚举EnumDialogType
     */
    public ConfirmOrCancleDialog(Activity context) {
        this(context, DimensUtil.getDisplayWidth(context) * 5 / 6);
    }

    /**
     * 构造函数
     *
     * @param context    上下文对象
     * //@param dialogType 对话框类型 枚举EnumDialogType
     * @param width      自定义宽度
     */
    public ConfirmOrCancleDialog(Activity context, int width) {
        super(context);
        this.context = context;
        //this.dialogType = dialogType;
        this.width = width;
        init();
    }


    @Override
    public void init() {
        super.init();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = width;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        getWindow().setGravity(Gravity.CENTER);

    }

    @Override

    public void initView() {
        super.initView();
        customView = LayoutInflater.from(context).inflate(R.layout.dialog_confirm_cancel, null);
        getWindow().setContentView(customView);


        contentTv = customView.findViewById(R.id.content_tv);
        cancleBtn = customView.findViewById(R.id.cancle_btn);
        confirmBtn = customView.findViewById(R.id.confirm_btn);
        cancleBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);

    }


    /**
     * 设置提示内容
     */

    public void setTipContent(String tipContent) {
        contentTv.setText(tipContent);
    }

    /**
     * 获取Diolog布局页面 可根据业务需要修改布局元素属性
     */
    public View getCustomView() {
        return customView;
    }

    /**
     * 点击对话窗操作时是否默认u自动关闭对话框 初始化为默认关闭
     */
    public void isAutoClose(boolean isAutoClose) {
        this.isAutoClose = isAutoClose;
    }

    /**
     * 设置时间监听
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_btn:
                dismiss();
                break;
            case R.id.cancle_btn:
                dismiss();
                break;
        }
    }


    /**
     * 事件监听
     */
    public interface Listener {
        /**
         * 点击确认监听回掉
         */
        void sure();

        /**
         * 点击取消或提示按钮监听回掉
         */
        void cancle();
    }


    /**
     * 确认操作
     */
    private void sure() {
        if (listener != null) {
            listener.sure();
        }
        if (isAutoClose) {
            dismiss();
        }
    }

    /**
     * 取消操作
     */
    private void cancle() {
        if (listener != null) {
            listener.cancle();
        }
        if (isAutoClose) {
            dismiss();
        }
    }

    /**
     * 商品所在仓库发货类型枚举
     */
    public enum EnumDialogType {
        ADD, CUT
    }
}

