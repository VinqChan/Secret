package com.vinchan.shareumbrella.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.WheelPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * 双项选择器，选择两项，数据不联动。
 */
public class FourPicker extends WheelPicker {
    private List<String> firstData = new ArrayList<>();
    private List<String> secondData = new ArrayList<>();
    private List<String> thirdData = new ArrayList<>();
    private List<String> fourData = new ArrayList<>();
    private int selectedFirstIndex = 0;
    private int selectedSecondIndex = 0;
    private int selectedThirdIndex = 0;
    private int selectedFourIndex = 0;
    private OnWheelListener onWheelListener;
    private OnPickListener onPickListener;
    private CharSequence firstPrefixLabel, firstSuffixLabel;
    private CharSequence secondPrefixLabel, secondSuffixLabel;
    private CharSequence thirdPrefixLabel, thirdSuffixLabel;
    private CharSequence fourPrefixLabel, fourSuffixLabel;

    public FourPicker(Activity activity, List<String> firstData, List<String> secondData, List<String> thirdData, List<String> fourData) {
        super(activity);
        this.firstData = firstData;
        this.secondData = secondData;
        this.thirdData = thirdData;
        this.fourData = fourData;
    }

    public void setSelectedIndex(int firstIndex, int secondIndex ,int thirdIndex, int fourIndex ) {
        if (firstIndex >= 0 && firstIndex < firstData.size()) {
            selectedFirstIndex = firstIndex;
        }
        if (secondIndex >= 0 && secondIndex < secondData.size()) {
            selectedSecondIndex = secondIndex;
        }
        if (thirdIndex >= 0 && thirdIndex < thirdData.size()) {
            selectedThirdIndex = thirdIndex;
        }
        if (fourIndex >= 0 && fourIndex < fourData.size()) {
            selectedFourIndex = fourIndex;
        }
    }

    public void setFirstLabel(CharSequence firstPrefixLabel, CharSequence firstSuffixLabel) {
        this.firstPrefixLabel = firstPrefixLabel;
        this.firstSuffixLabel = firstSuffixLabel;
    }

    public void setSecondLabel(CharSequence secondPrefixLabel, CharSequence secondSuffixLabel) {
        this.secondPrefixLabel = secondPrefixLabel;
        this.secondSuffixLabel = secondSuffixLabel;
    }
        public void setThirdLabel(CharSequence thirdPrefixLabel, CharSequence thirdSuffixLabel) {
        this.thirdPrefixLabel = thirdPrefixLabel;
        this.thirdSuffixLabel = thirdSuffixLabel;
    }
    public void setFourLabel(CharSequence fourPrefixLabel, CharSequence fourSuffixLabel) {
        this.fourPrefixLabel = fourPrefixLabel;
        this.fourSuffixLabel = fourSuffixLabel;
    }

    public String getSelectedFirstItem() {
        if (firstData.size() > selectedFirstIndex) {
            return firstData.get(selectedFirstIndex);
        }
        return "";
    }

    public String getSelectedSecondItem() {
        if (secondData.size() > selectedSecondIndex) {
            return secondData.get(selectedSecondIndex);
        }
        return "";
    }
    public String getSelectedThirdItem() {
        if (thirdData.size() > selectedThirdIndex) {
            return thirdData.get(selectedThirdIndex);
        }
        return "";
    }
    public String getSelectedFourItem() {
        if (fourData.size() > selectedFourIndex) {
            return fourData.get(selectedFourIndex);
        }
        return "";
    }
    @NonNull
    @Override
    protected View makeCenterView() {
        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        TextView beiginTitle = createLabelView();
        beiginTitle.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        beiginTitle.setText("开始   ");
        layout.addView(beiginTitle);
        if (!TextUtils.isEmpty(firstPrefixLabel)) {
            TextView firstPrefixLabelView = createLabelView();
            firstPrefixLabelView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            firstPrefixLabelView.setText(firstPrefixLabel);
            layout.addView(firstPrefixLabelView);
        }
        final WheelView firstView = createWheelView();
        firstView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        layout.addView(firstView);
        if (!TextUtils.isEmpty(firstSuffixLabel)) {
            TextView firstSuffixLabelView = createLabelView();
            firstSuffixLabelView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            firstSuffixLabelView.setText(firstSuffixLabel);
            layout.addView(firstSuffixLabelView);
        }
        if (!TextUtils.isEmpty(secondPrefixLabel)) {
            TextView secondPrefixLabelView = createLabelView();
            secondPrefixLabelView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            secondPrefixLabelView.setText(secondPrefixLabel);
            layout.addView(secondPrefixLabelView);
        }
        final WheelView secondView = createWheelView();
        secondView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        layout.addView(secondView);
        if (!TextUtils.isEmpty(secondSuffixLabel)) {
            TextView secondSuffixLabelView = createLabelView();
            secondSuffixLabelView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            secondSuffixLabelView.setText(secondSuffixLabel);
            layout.addView(secondSuffixLabelView);
        }
        TextView endTimeTitle = createLabelView();
        endTimeTitle.setText("   结束   ");
        endTimeTitle.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        layout.addView(endTimeTitle);
        if (!TextUtils.isEmpty(thirdPrefixLabel)) {
            TextView thirdPrefixLabelView = createLabelView();
            thirdPrefixLabelView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            thirdPrefixLabelView.setText(thirdPrefixLabel);
            layout.addView(thirdPrefixLabelView);
        }
        final WheelView thirdView = createWheelView();
        thirdView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        layout.addView(thirdView);
        if (!TextUtils.isEmpty(thirdSuffixLabel)) {
            TextView thirdSuffixLabelView = createLabelView();
            thirdSuffixLabelView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            thirdSuffixLabelView.setText(thirdSuffixLabel);
            layout.addView(thirdSuffixLabelView);
        }
        if (!TextUtils.isEmpty(fourPrefixLabel)) {
            TextView fourPrefixLabelView = createLabelView();
            fourPrefixLabelView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            fourPrefixLabelView.setText(fourPrefixLabel);
            layout.addView(fourPrefixLabelView);
        }
        final WheelView fourView = createWheelView();
        fourView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        layout.addView(fourView);
        if (!TextUtils.isEmpty(fourSuffixLabel)) {
            TextView fourSuffixLabelView = createLabelView();
            fourSuffixLabelView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            fourSuffixLabelView.setText(fourSuffixLabel);
            layout.addView(fourSuffixLabelView);
        }

        firstView.setItems(firstData, selectedFirstIndex);
        firstView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                selectedFirstIndex = index;
                if (onWheelListener != null) {
                    onWheelListener.onFirstWheeled(selectedFirstIndex, firstData.get(selectedFirstIndex));
                }
            }
        });
        secondView.setItems(secondData, selectedSecondIndex);
        secondView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                selectedSecondIndex = index;
                if (onWheelListener != null) {
                    onWheelListener.onSecondWheeled(selectedSecondIndex, secondData.get(selectedSecondIndex));
                }
            }
        });
        thirdView.setItems(thirdData, selectedThirdIndex);
        thirdView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                selectedThirdIndex = index;
                if (onWheelListener != null) {
                    onWheelListener.onThirdWheeled(selectedThirdIndex, thirdData.get(selectedThirdIndex));
                }
            }
        });
        fourView.setItems(fourData, selectedFourIndex);
        fourView.setOnItemSelectListener(new WheelView.OnItemSelectListener() {
            @Override
            public void onSelected(int index) {
                selectedFourIndex = index;
                if (onWheelListener != null) {
                    onWheelListener.onFourWheeled(selectedFourIndex, fourData.get(selectedFourIndex));
                }
            }
        });
        return layout;
    }

    @Override
    public void onSubmit() {
        if (onPickListener != null) {
            onPickListener.onPicked(selectedFirstIndex, selectedSecondIndex,selectedThirdIndex,selectedFourIndex);
        }
    }

    public void setOnWheelListener(OnWheelListener onWheelListener) {
        this.onWheelListener = onWheelListener;
    }

    public void setOnPickListener(OnPickListener onPickListener) {
        this.onPickListener = onPickListener;
    }

    /**
     * 数据条目滑动监听器
     */
    public interface OnWheelListener {

        void onFirstWheeled(int index, String item);

        void onSecondWheeled(int index, String item);

        void onThirdWheeled(int index, String item);

        void onFourWheeled(int index, String item);
    }

    /**
     * 数据选择完成监听器
     */
    public interface OnPickListener {

        void onPicked(int selectedFirstIndex, int selectedSecondIndex ,int selectedThirdIndex, int selectedFourIndex );

    }

}
