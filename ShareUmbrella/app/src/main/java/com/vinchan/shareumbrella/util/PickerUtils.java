package com.vinchan.shareumbrella.util;

import android.app.Activity;
import android.view.Gravity;

import com.dangong.oksan.R;
import com.vinchan.shareumbrella.callback.PickerCallBack;

import cn.qqtheme.framework.picker.DatePicker;

/**
 * Created by Jian on 2018/6/7.
 */

public class PickerUtils {
    public static void yearMonthPicker(Activity context, final PickerCallBack callBack) {

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

    public static void yearMonthDayPicker(Activity context, final PickerCallBack callBack) {

        DatePicker picker = new DatePicker(context, DatePicker.YEAR_MONTH_DAY);
        picker.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        picker.setDividerColor(context.getResources().getColor(R.color.picker_line_color));
        picker.setTextColor(context.getResources().getColor(R.color.gray_color));
        picker.setRangeStart(2018, 01, 01);
        picker.setTopLineColor(context.getResources().getColor(R.color.gray_color));
        picker.setRangeEnd(2100, 11, 11);
        picker.setSelectedItem(2018, 6,1);
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
}
