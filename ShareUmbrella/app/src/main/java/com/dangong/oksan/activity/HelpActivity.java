package com.dangong.oksan.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseFragmentActivity;
import com.dangong.oksan.fragment.HelpFragment;
import com.dangong.oksan.view.PagerSlidingTabStrip;

import butterknife.BindView;

public class HelpActivity extends BaseFragmentActivity {


    @BindView(R.id.activity_help_viewpager)
    ViewPager activityHelpViewpager;
    @BindView(R.id.activity_help_tabstrip)
    PagerSlidingTabStrip activityHelpTabstrip;
    private  final int COMMONQUESTION =0 ;
    private  final int RULE =1 ;
    private String [] titles= {"常见问题","规则说明"};
    private int dataTypes[] = new int[]{COMMONQUESTION, RULE};

    @Override
    public int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    public String setTitle() {
        return getString(R.string.help);
    }

    @Override
    public void init() {
        super.init();
        HelpPagerAdapter pagerAdapter = new HelpPagerAdapter(getSupportFragmentManager());
        activityHelpViewpager.setAdapter(pagerAdapter);
        activityHelpTabstrip.setViewPager(activityHelpViewpager);
    }

    private class HelpPagerAdapter extends FragmentPagerAdapter{

        public HelpPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return HelpFragment.getInstance(dataTypes[position]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
        @Override
        public int getCount() {
            return titles.length;
        }
    }

}
