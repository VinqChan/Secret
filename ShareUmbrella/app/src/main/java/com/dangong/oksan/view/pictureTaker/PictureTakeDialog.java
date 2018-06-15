package com.dangong.oksan.view.pictureTaker;


import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.dangong.oksan.R;
import com.dangong.oksan.view.dialog.BaseDialog;


/**
 * 拍照或从相册选择图片的弹窗
 * 
 * @author zhengjb
 */
public class PictureTakeDialog extends BaseDialog
{
    private View customView;

    private Button cancelBtn;

    private Button cameraBtn;
    private Button galleryBtn;

    private PictureTaker pictureTaker;

    private View.OnClickListener onCancelListener;

    /**
     * 拍照或从相册选择图片的弹窗
     * 默认为MATCH_PARENT
     *
     * @param context
     * @param pictureTaker 图片采集者
     * @author zhengjb
     */
    public PictureTakeDialog(Activity context, PictureTaker pictureTaker)
    {
        this(context, pictureTaker, WindowManager.LayoutParams.MATCH_PARENT);
    }

    /**
     * 拍照或从相册选择图片的弹窗
     * 
     * @param context
     * @param width 弹窗宽度为px或MATCH_PARENT、WRAP_CONTENT
     * @param pictureTaker 图片采集者
     * @author zhengjb
     */
    public PictureTakeDialog(Activity context, final PictureTaker pictureTaker, int width)
    {
        this(context, R.layout.dialog_picture_taker_default, pictureTaker, width);
    }


    /**
     * 拍照或从相册选择图片的弹窗
     *
     * @param context
     * @param layoutResId 自定义布局 在构造方法中已经固定了三个按钮的id，其他的可以自定义
     * @param pictureTaker 图片采集者
     * @author zhengjb
     */
    public PictureTakeDialog(Activity context, int layoutResId, final PictureTaker pictureTaker){
        this(context, layoutResId, pictureTaker, WindowManager.LayoutParams.MATCH_PARENT);
    }

    /**
     * 拍照或从相册选择图片的弹窗
     *
     * @param context
     * @param layoutResId 自定义布局 在构造方法中已经固定了三个按钮的id，其他的可以自定义
     * @param width 弹窗宽度为px或MATCH_PARENT、WRAP_CONTENT
     * @param pictureTaker 图片采集者
     * @author zhengjb
     */
    public PictureTakeDialog(Activity context, int layoutResId, final PictureTaker pictureTaker, int width){
        super(context, layoutResId);
        setCancelable(true);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        customView = LayoutInflater.from(getContext()).inflate(layoutResId, null);
        setContentView(customView);

        cameraBtn = (Button) customView.findViewById(R.id.picture_taker_camera_btn);
        cameraBtn.setOnClickListener(this);

        galleryBtn = (Button) customView.findViewById(R.id.picture_taker_gallery_btn);
        galleryBtn.setOnClickListener(this);

        cancelBtn = (Button) customView.findViewById(R.id.picture_taker_cancel_btn);
        cancelBtn.setOnClickListener(this);

        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = width;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);


        this.pictureTaker = pictureTaker;

        setCanceledOnTouchOutside(false);
    }



    public View getCustomView()
    {
        return customView;
    }

    public TextView getGalleryButton()
    {
        return galleryBtn;
    }

    public TextView getCameraButton()
    {
        return cameraBtn;
    }

    public TextView getCancelButton()
    {
        return cancelBtn;
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        if (id == R.id.picture_taker_camera_btn)// 拍照
        {
            pictureTaker.takePhoto();
            dismiss();
        }
        else if (id == R.id.picture_taker_cancel_btn)
        {// 取消修改头像
            if(onCancelListener != null){
                onCancelListener.onClick(v);
            }
            dismiss();
        }
        else if (id == R.id.picture_taker_gallery_btn)
        {// 从相册获取
            pictureTaker.takeGallery();
            dismiss();
        }
    }

    /** 设置取消监听 */
    public void setOnCancelListener(View.OnClickListener onCancelListener){
        this.onCancelListener = onCancelListener;
    }
}
