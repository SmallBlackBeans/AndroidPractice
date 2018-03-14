package com.example.helloworld.BaiduMap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.example.helloworld.R;

public class MarkerActivity extends AppCompatActivity {


        BitmapDescriptor bdA = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        private MapView mMapView;
        private BaiduMap mBaiduMap;
        private Marker mMarkerA;
        private InfoWindow mInfoWindow;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_marker);

            //找控件,再去拿BaiduMap
            mMapView = (MapView) findViewById(R.id.bmapView);
            mBaiduMap = mMapView.getMap();
            initOverlay();
            //设置标注覆盖物的点击事件
            mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                public boolean onMarkerClick(final Marker marker) {
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    InfoWindow.OnInfoWindowClickListener listener = null;
                    if (marker == mMarkerA ) {
                        button.setText("更改位置");
                        listener = new InfoWindow.OnInfoWindowClickListener() {
                            public void onInfoWindowClick() {
                                LatLng ll = marker.getPosition();
                                LatLng llNew = new LatLng(ll.latitude + 0.005,
                                        ll.longitude + 0.005);
                                marker.setPosition(llNew);
                                mBaiduMap.hideInfoWindow();
                            }
                        };
                        LatLng ll = marker.getPosition();
                        mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), ll, -47, listener);
                        mBaiduMap.showInfoWindow(mInfoWindow);
                    }
                    return true;
                }
            });

        }


        //初始化一个覆盖物
        public void initOverlay() {
            // add marker overlay
            //longitude 经度      latitude 纬度
            LatLng llA = new LatLng(39.963175, 116.400244);

            MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA)
                    .zIndex(9).draggable(true);
            mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));

            // add ground overlay
            LatLng southwest = new LatLng(39.92235, 116.380338);
            LatLng northeast = new LatLng(39.947246, 116.414977);
            LatLngBounds bounds = new LatLngBounds.Builder().include(northeast)
                    .include(southwest).build();

//        OverlayOptions ooGround = new GroundOverlayOptions()
//                .positionFromBounds(bounds).image(bdGround).transparency(0.8f);
//        mBaiduMap.addOverlay(ooGround);

            MapStatusUpdate u = MapStatusUpdateFactory
                    .newLatLng(bounds.getCenter());
            mBaiduMap.setMapStatus(u);

            mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
                public void onMarkerDrag(Marker marker) {
                }

                public void onMarkerDragEnd(Marker marker) {
                    Toast.makeText(
                            MarkerActivity.this,
                            "拖拽结束，新位置：" + marker.getPosition().latitude + ", "
                                    + marker.getPosition().longitude,
                            Toast.LENGTH_LONG).show();
                }

                public void onMarkerDragStart(Marker marker) {
                }
            });
        }
    }