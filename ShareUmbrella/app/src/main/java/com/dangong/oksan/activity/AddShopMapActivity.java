package com.dangong.oksan.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dangong.oksan.R;
import com.dangong.oksan.activity.base.BaseActivity;
import com.dangong.oksan.adapter.ShopLocationListAdapter;
import com.dangong.oksan.api.ApiUtils;
import com.dangong.oksan.callback.ApiCallBack;
import com.dangong.oksan.model.NearShopModel;
import com.dangong.oksan.model.ResponseModel;
import com.dangong.oksan.view.PullRereshRecycleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dangong.oksan.activity.ScannerActivity.SCANNER_KEY;
import static com.dangong.oksan.activity.ScannerActivity.TYPE_OPEN;
import static com.dangong.oksan.activity.ScannerActivity.TYPE_REMOVE;

public class AddShopMapActivity extends BaseActivity {


    @BindView(R.id.bmapView)
    MapView mMapView;
    @BindView(R.id.add_btn)
    Button addBtn;
    @BindView(R.id.maintain_btn)
    Button maintainBtn;
    @BindView(R.id.bottom_button_layout)
    LinearLayout bottomButtonLayout;
    @BindView(R.id.location_recycler_view)
    PullRereshRecycleView locationRecyclerView;
    @BindView(R.id.own_info_iv)
    ImageView ownInfoIv;
    private BaiduMap mBaiduMap;
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private ShopLocationListAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_shop_map;
    }

    @Override
    public String setTitle() {
        return getString(R.string.add_shop);
    }

    @Override
    public void init() {
        super.init();
        ownInfoIv.setVisibility(View.VISIBLE);
        initLocation();
    }

    @Override
    public void initData() {
        super.initData();
        getNearShop();
    }

    private void getNearShop() {
        ApiUtils.getNearShop(116.4111328125, 39.6733703918, 2000.0, "", new ApiCallBack() {
            @Override
            public void success(Object response) {
                final NearShopModel nearShopModel = (NearShopModel) response;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setLayout(nearShopModel.getResult());
                    }
                });
            }

            @Override
            public void fail() {

            }
        });
    }

    private void getWorkHistory() {
        ApiUtils.getWorkHistory("10", "10", new ApiCallBack() {
            @Override
            public void success(Object response) {

            }

            @Override
            public void fail() {

            }
        });
    }

    private void getInvitecode() {
        ApiUtils.getinvitecode(new ApiCallBack() {
            @Override
            public void success(Object response) {
                String inviteCode = ((ResponseModel) response).getResult();
                ToastUtils.showShort(inviteCode);
            }

            @Override
            public void fail() {

            }
        });
    }

    private void setLayout(List<NearShopModel.ResultBean> list) {


        mAdapter = new ShopLocationListAdapter(list, this);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        locationRecyclerView.setAdapter(mAdapter);
        locationRecyclerView.setReFreshEnabled(false);
        locationRecyclerView.setLoadMoreEnabled(false);
        // 监听刷新事件
        locationRecyclerView.setRefreshAndLoadMoreListener(new PullRereshRecycleView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        // 刷新数据结束时调用
                        locationRecyclerView.setReFreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
            }
        });

        for (int i = 0; i < list.size(); i++) {
            LatLng point = new LatLng(list.get(i).getLatitude(), list.get(i).getLongitude());
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_dy_dw);
            OverlayOptions option2 = new MarkerOptions().position(point).icon(bitmap);
            Marker marker = (Marker) mBaiduMap.addOverlay(option2);
            //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
            Bundle bundle = new Bundle();
            //info必须实现序列化接口
            bundle.putSerializable("info", point.latitude + " , " + point.longitude);
            marker.setExtraInfo(bundle);


        }
        //将地图显示在最后一个marker的位置
        LatLng point = new LatLng(24.487216, 118.156801);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(point);
        mBaiduMap.setMapStatus(msu);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_btn, R.id.maintain_btn,R.id.own_info_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                Bundle bundle2 = new Bundle();
                bundle2.putInt(SCANNER_KEY, TYPE_OPEN);
                ActivityUtils.startActivity(bundle2,ScannerActivity.class);
                break;
            case R.id.maintain_btn:
                Bundle bundle = new Bundle();
                bundle.putInt(ManagerAndMaintainMainActivity.TYPE_KEY, ManagerAndMaintainMainActivity.TYPE_MATAIN);
                ActivityUtils.startActivity(bundle, ManagerAndMaintainMainActivity.class);
                break;
            case R.id.own_info_iv:
                ActivityUtils.startActivity(MaintainPersonCenterActivity.class);
                break;
        }
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            LogUtils.d(TAG, "mylocation  " + mCurrentLat + "  " + mCurrentLon);
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    private void initLocation() {
        // 不显示地图缩放控件（按钮控制栏）
        mMapView.showZoomControls(false);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        //  mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        //mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        //option.setScanSpan(1000);

        mLocClient.setLocOption(option);
        mLocClient.start();


//        LatLng point = new LatLng( 24.494577,118.192556);
//        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_dy_dw);
//        OverlayOptions option2 = new MarkerOptions().position(point).icon(bitmap);
//        Marker marker = (Marker) mBaiduMap.addOverlay(option2);
//        //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
//        Bundle bundle = new Bundle();
//        //info必须实现序列化接口
//        bundle.putSerializable("info", point.latitude+" , "+point.longitude);
//        marker.setExtraInfo(bundle);
//
//        //将地图显示在最后一个marker的位置
//        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(point);
//        mBaiduMap.setMapStatus(msu);

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle info = marker.getExtraInfo();
                ToastUtils.showShort((String) info.getSerializable("info"));
                return false;
            }
        });
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(14));

    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }


}
