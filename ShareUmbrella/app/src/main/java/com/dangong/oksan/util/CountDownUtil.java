package com.dangong.oksan.util;

import android.os.CountDownTimer;
import android.util.Log;

import com.blankj.utilcode.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Vinchan
 * CountDownUtil
 */
public class CountDownUtil {
    public static final String TAG = "CountDownUtil";

    /** 促销活动倒计时 */
    public static final int TYPE_ACTIVITY_CANCEL = 1;
    /** 订单支付倒计时 */
    public static final int TYPE_ORDER_CANCEL = 2;
    /** 拼团活动倒计时 */
    public static final int TYPE_GROUP_ON_FAIL = 3;
    /**拼团详情活动倒计时*/
    public static final int TYPE_GROUP_ON_DETAIL = 4;
    /**一秒毫秒数*/
    public static final int SECOND_MILLISECOND = 1000;
    /**一分钟毫秒数*/
    public static final int MINUTE_MILLISECOND = SECOND_MILLISECOND * 60;
    /**一小时毫秒数*/
    public static final int HOUR_MILLISECOND = MINUTE_MILLISECOND * 60;
    /**一天的毫秒数*/
    public static final int DAY_MILLISECOND = HOUR_MILLISECOND * 24;

    private CountdownListener listener = null;
    //距离结束的毫秒数
    private long millisInFuture;
    //隔多长时间通知一次
    private long countDownInterval;
    //用于格式化显示结果
    private IResultFormat resultFormat;
    //用于实际倒计时
    private CountDownTimer countDownTimer;

    private class U1CountDownTimer extends CountDownTimer {

        public U1CountDownTimer(long millisInFuture,long countDownInterval){
            super(millisInFuture,countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long day = millisUntilFinished / DAY_MILLISECOND;
            long hour = (millisUntilFinished % DAY_MILLISECOND) / HOUR_MILLISECOND;
            long minite = (millisUntilFinished % HOUR_MILLISECOND) / MINUTE_MILLISECOND;
            long second = (millisUntilFinished % MINUTE_MILLISECOND) / SECOND_MILLISECOND;
            CharSequence result = resultFormat.format(day,hour,minite,second);
            if (listener != null) {
                listener.onTick(result);
            }
        }

        @Override
        public void onFinish() {
            onTick(0);
            if (listener != null) {
                listener.onFinish();
            }
        }
    }
    interface IResultFormat{
        CharSequence format(long day, long hour, long minute, long second);
        void setMillisInFuture(long millisInFuture);
    }

    static final class ResultFormatFactory{
        public static IResultFormat getResultFormat(int type){
            switch (type){
                case TYPE_ACTIVITY_CANCEL:
                    return new ACCResultFormat();
                case TYPE_GROUP_ON_FAIL:
                    return new GOFResultFormat();
                case TYPE_ORDER_CANCEL:
                    return new ORCResultFormat();
//                case TYPE_GROUP_ON_DETAIL:
//                    return new GODResultFormat();
                default:
                    throw new IllegalArgumentException("请传入正确的type");
            }
        }
    }

    /**提供addZeroIfNeeded方法给子类使用*/
    static abstract class ResultFormatAdapter implements IResultFormat{
        /**
         * 当time为0-9时，在time前面插入'0'
         */
        public String addZeroIfNeeded(long time){
            StringBuilder sb = new StringBuilder();
            if(time < 10){
                sb.append("0").append(time);
            }else{
                sb.append(time);
            }
            return sb.toString();
        }

        @Override
        public void setMillisInFuture(long millisInFuture) {
            //默认不做任何事情
        }
    }

    /**格式化类型为TYPE_ACTIVITY_CANCEL的显示结果*/
    static final class ACCResultFormat extends ResultFormatAdapter{
        private long millisInFuture;
        public void setMillisInFuture(long millisInFuture){
            this.millisInFuture = millisInFuture;
        }

        @Override
        public CharSequence format(long day, long hour, long minute, long second) {
            String result;
            String dayStr = addZeroIfNeeded(day);
            String hourStr = addZeroIfNeeded(hour);
            String minuteStr = addZeroIfNeeded(minute);
            String secondStr = addZeroIfNeeded(second);
            if(millisInFuture > DAY_MILLISECOND)
                result = String.format("%s天%s小时%s分%s秒", dayStr, hourStr, minuteStr, secondStr);
            else
                result = String.format("%s小时%s分%s秒", hourStr, minuteStr, secondStr);
            return result;
        }
    }

    /**格式化TYPE_GROUP_ON_FAIL的显示结果*/
    static final class GOFResultFormat extends ResultFormatAdapter{
        @Override
        public CharSequence format(long day, long hour, long minute, long second) {
            long dayAndHour = day*24 + hour;
            return String.format("%s:%s",  addZeroIfNeeded(minute), addZeroIfNeeded(second));
        }
    }

    /**格式化TYPE_ORDER_CANCEL的显示结果*/
    static final class ORCResultFormat extends ResultFormatAdapter{

        private long millisInFuture;
        public void setMillisInFuture(long millisInFuture){
            this.millisInFuture = millisInFuture;
        }
        @Override
        public CharSequence format(long day, long hour, long minute, long second) {
            String result;
            String dayStr = addZeroIfNeeded(day);
            String hourStr = addZeroIfNeeded(hour);
            String minuteStr = addZeroIfNeeded(minute);
            String secondStr = addZeroIfNeeded(second);
            if(millisInFuture > DAY_MILLISECOND)
                result = String.format("%s天%s小时", dayStr, hourStr);
            else if(millisInFuture > HOUR_MILLISECOND){
                result = String.format("%s小时%s分", hourStr, minuteStr);
            }else{
                result = String.format("%s分%s秒", minuteStr, secondStr);
            }
            return result;
        }
    }

    public CountDownUtil(long millisInFuture, long countDownInterval, int type) {
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
        resultFormat = ResultFormatFactory.getResultFormat(type);
        resultFormat.setMillisInFuture(millisInFuture);
        this.countDownTimer = new U1CountDownTimer(this.millisInFuture,this.countDownInterval);
    }

    public CountDownUtil(){
    }

    public CountDownUtil(String startTimeStamp, String endTimeStamp, long countDownInterval, int type){
        Log.e(TAG, "======create countDownUtil======");
        this.millisInFuture = timeStamp2MilliSeconds(endTimeStamp) - timeStamp2MilliSeconds(startTimeStamp);
        this.countDownInterval = countDownInterval;
        resultFormat = ResultFormatFactory.getResultFormat(type);
        resultFormat.setMillisInFuture(millisInFuture);
        this.countDownTimer = new U1CountDownTimer(this.millisInFuture,this.countDownInterval);
    }

    //开始倒计时
    public void start(){
        if(this.countDownTimer != null){
            this.countDownTimer.start();
        }
    }
    //取消倒计时
    public void cancel(){
        if(this.countDownTimer != null){
            this.countDownTimer.cancel();
        }
    }

    /**
     * 设置倒计时监听事件
     */
    public void setCountdownListener(CountdownListener listener) {
        this.listener = listener;
    }

    /**
     * 开始倒计时
     *
     * @param endCountdownMillisecond 倒计时开始毫秒
     * @param countdownInterval       倒计时间隔毫秒刻度 如 1000为1秒
     * @param type                    1-每一次都倒计时到秒 2- 倒计时时间小于一小时显示秒
     */
    public void startCountdown(final long endCountdownMillisecond, long countdownInterval, final int type) {
        this.millisInFuture = endCountdownMillisecond;
        this.countDownInterval = countdownInterval;
        resultFormat = ResultFormatFactory.getResultFormat(type);
        resultFormat.setMillisInFuture(millisInFuture);
        this.countDownTimer = new U1CountDownTimer(endCountdownMillisecond,countdownInterval);
        start();
    }

    /** 获取倒计时时间 */
    public long getCountDownTime(String startTime, String endPayTime) {
        long time;
        time = timeStamp2MilliSeconds(endPayTime) - timeStamp2MilliSeconds(startTime);
        return time;
    }

    /**把时间戳转换成毫秒数:yyyy-MM-dd HH:mm:ss*/
    public long timeStamp2MilliSeconds(String timeStamp){
        if(StringUtils.isEmpty(timeStamp)){
            throw new IllegalArgumentException("时间戳不能为空");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date d = sdf.parse(timeStamp);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("时间格式不正确");
        }
    }

    /**
     * 倒计时事件监听
     */
    public interface CountdownListener {
        /**
         * 倒计时
         *
         * @param countDownText 倒计时当前结果
         */
        void onTick(CharSequence countDownText);

        /**
         * 倒计时结束
         */
        void onFinish();
    }
}
