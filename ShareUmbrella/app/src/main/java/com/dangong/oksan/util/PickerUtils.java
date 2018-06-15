package com.dangong.oksan.util;

import android.app.Activity;
import android.view.Gravity;

import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.callback.CustomerPickerCallBack;
import com.dangong.oksan.callback.TimePickerCallBack;
import com.dangong.oksan.callback.BussinessTimePickerCallBack;
import com.dangong.oksan.callback.YearMonthDayPickerCallBack;
import com.dangong.oksan.callback.YearMonthPickerCallBack;

import java.util.ArrayList;
import java.util.Calendar;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.DateUtils;
import cn.qqtheme.framework.widget.WheelView;

/**
 * Created by Jian on 2018/6/7.
 */

public class PickerUtils {
    public static void yearMonthPicker(Activity context, final YearMonthPickerCallBack callBack) {

        DatePicker picker = new DatePicker(context, DatePicker.YEAR_MONTH);
        picker.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        picker.setDividerColor(context.getResources().getColor(R.color.picker_line_color));
        picker.setTextColor(context.getResources().getColor(R.color.gray_color));
        picker.setRangeStart(2018, 01, 01);
        picker.setTopLineColor(context.getResources().getColor(R.color.gray_color));
        picker.setRangeEnd(2100, 11, 11);
        picker.setSelectedItem(2018, 6);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                callBack.yearAndMonth(year, month);
            }
        });
        picker.show();
        picker.getCancelButton().setTextSize(16);
        picker.getSubmitButton().setTextSize(16);
        picker.getCancelButton().setTextColor(context.getResources().getColor(R.color.gray_color));
        picker.getSubmitButton().setTextColor(context.getResources().getColor(R.color.main_color));
    }

    public static void yearMonthDayPicker(Activity context, final YearMonthDayPickerCallBack callBack) {

        DatePicker picker = new DatePicker(context, DatePicker.YEAR_MONTH_DAY);
        picker.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        picker.setDividerColor(context.getResources().getColor(R.color.picker_line_color));
        picker.setTextColor(context.getResources().getColor(R.color.gray_color));
        picker.setRangeStart(2018, 01, 01);
        picker.setTopLineColor(context.getResources().getColor(R.color.gray_color));
        picker.setRangeEnd(2100, 11, 11);
        picker.setSelectedItem(2018, 6, 1);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                callBack.yearMonthDay(year, month, day);
            }
        });
        picker.show();
        picker.getCancelButton().setTextSize(16);
        picker.getSubmitButton().setTextSize(16);
        picker.getCancelButton().setTextColor(context.getResources().getColor(R.color.gray_color));
        picker.getSubmitButton().setTextColor(context.getResources().getColor(R.color.main_color));
    }

    public static void timePicker(Activity context, final TimePickerCallBack callBack, boolean isStart) {
        TimePicker picker = new TimePicker(context, TimePicker.HOUR_24);
        picker.setUseWeight(false);
        picker.setCycleDisable(false);
        picker.setDividerColor(context.getResources().getColor(R.color.picker_line_color));
        picker.setTextColor(context.getResources().getColor(R.color.gray_color));
        picker.setRangeStart(0, 0);//00:00
        picker.setRangeEnd(23, 59);//23:59
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        picker.setSelectedItem(currentHour, currentMinute);
        picker.setTopLineVisible(true);
        picker.setTopLineColor(context.getResources().getColor(R.color.picker_line_color));
        picker.setTitleTextSize(16);
        picker.setTitleText(isStart ? "选择营业开始时间" : "选择营业結束时间");
        //  picker.setTextPadding(ConvertUtils.toPx(this, 15));
        picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                ToastUtils.showShort(hour + ":" + minute);
            }
        });
        picker.show();
        picker.getCancelButton().setTextSize(16);
        picker.getSubmitButton().setTextSize(16);
        picker.getCancelButton().setTextColor(context.getResources().getColor(R.color.gray_color));
        picker.getSubmitButton().setTextColor(context.getResources().getColor(R.color.main_color));
    }

    public static  void onBusinessTimePicker(Activity context,final BussinessTimePickerCallBack callBack) {
        final ArrayList<String> hours = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            hours.add(DateUtils.fillZero(i));
        }
        final ArrayList<String> minutes = new ArrayList<>();
        minutes.add("00");
        minutes.add("15");
        minutes.add("30");
        FourPicker picker = new FourPicker(context, hours, minutes,hours, minutes);
        picker.setDividerColor(context.getResources().getColor(R.color.picker_line_color));
        picker.setTextColor(context.getResources().getColor(R.color.gray_color));
        picker.setCanceledOnTouchOutside(true);
        picker.setTopLineColor(context.getResources().getColor(R.color.picker_line_color));
        picker.setSubmitTextColor(context.getResources().getColor(R.color.main_color));
        picker.setCancelTextColor(context.getResources().getColor(R.color.gray_color));
        picker.setLineSpaceMultiplier(2.2f);
        picker.setTextSize(15);
        picker.setTitleText("营业时间");
        picker.setContentPadding(10, 8);
       // picker.setUseWeight(true);
        picker.setSelectedIndex(9,0,19,0);
        picker.setFirstLabel("", ":");
        picker.setSecondLabel("", "");
        picker.setOnPickListener(new FourPicker.OnPickListener() {
            @Override
            public void onPicked(int selectedFirstIndex, int selectedSecondIndex, int selectedThirdIndex, int selectedFourIndex) {
                callBack.time(hours.get(selectedFirstIndex),minutes.get(selectedSecondIndex),hours.get(selectedThirdIndex),minutes.get(selectedFourIndex));
            }
        });
        picker.show();
    }

    public static void customerPicker(Activity context,String[] str,final CustomerPickerCallBack callBack){
            OptionPicker picker = new OptionPicker(context, str);
            picker.setCanceledOnTouchOutside(true);
            picker.setDividerRatio(WheelView.DividerConfig.FILL);
            picker.setDividerColor(context.getResources().getColor(R.color.picker_line_color));
            picker.setTextColor(context.getResources().getColor(R.color.gray_color));
            picker.setTopLineColor(context.getResources().getColor(R.color.gray_color));
            picker.setSelectedIndex(1);
            picker.setCycleDisable(true);
            picker.setTextSize(16);
            picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                @Override
                public void onOptionPicked(int index, String item) {
                   callBack.selecte(item);
                }
            });
            picker.show();
            picker.getCancelButton().setTextSize(16);
            picker.getSubmitButton().setTextSize(16);
            picker.getCancelButton().setTextColor(context.getResources().getColor(R.color.gray_color));
            picker.getSubmitButton().setTextColor(context.getResources().getColor(R.color.main_color));
        }
    }

