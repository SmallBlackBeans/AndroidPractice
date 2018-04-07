package com.example.helloworld.Projects.WangYI.News.View.Fragment.News_Inner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.map.DotOptions;
import com.example.helloworld.Basic.AsynTask.Constant;
import com.example.helloworld.Projects.WangYI.News.Adapter.BannerApapter;
import com.example.helloworld.Projects.WangYI.News.Adapter.WYHotAdapter;
import com.example.helloworld.Projects.WangYI.News.Bean.Banner;
import com.example.helloworld.Projects.WangYI.News.Bean.Hot;
import com.example.helloworld.Projects.WangYI.News.Bean.HotDetail;
import com.example.helloworld.Projects.WangYI.News.View.Activity.WYNewsDetailctivity;
import com.example.helloworld.Projects.WangYI.Services.WYHttpResponse;
import com.example.helloworld.Projects.WangYI.Utils.WYHttpUtil;
import com.example.helloworld.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanchenghai on 2018/3/25.
 */

public class HotFragment extends Fragment implements ViewPager.OnPageChangeListener, AbsListView.OnScrollListener {

    ListView mlistView;
    //轮播图
    ArrayList<Banner> mBanners;
    ArrayList<ImageView> dot_imgs;
    ArrayList<View> mViews;
    ArrayList<HotDetail> mHotDetails;

    LayoutInflater mInflater;

    MainHandler mMainHandler;
    WYHotAdapter mAdapter;
    BannerApapter mBannerApapter;

    static final int INIT_SUCCESS = 0;
    static final int LOAD_MORE = 1;

    boolean isToEnd = false;

    //轮播图
    ViewPager mViewPager;
    private TextView mBannerTitle;
    private LinearLayout mDots;


    int startIndex = 0;
    int endIndex = 0;
    int pageSize = 20;
    int pageNum = 0;
    boolean isRequesting = false;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_hot, container, false);
        mlistView = view.findViewById(R.id.listView);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        initSource();
        initView();
        getData();
    }


    public void getCurrentIndex() {
        if (isRequesting)return;
        if (pageNum <= 0) {
            pageNum = 0;
            endIndex = pageSize;
            startIndex = 0;
        } else {
            startIndex = pageNum * pageSize;
            endIndex = startIndex + pageSize;
        }
    }

    private void initSource() {
        mBanners = new ArrayList<>();
        mHotDetails = new ArrayList<>();
        mMainHandler = new MainHandler(this);
    }

    private void initView() {
        mInflater = LayoutInflater.from(getActivity());

        //轮播图
        mViews = new ArrayList<>();
        dot_imgs = new ArrayList<>();

        View bannerView = mInflater.inflate(R.layout.include_banner, null);
        mlistView.addHeaderView(bannerView);
        mlistView.setOnScrollListener(this);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), WYNewsDetailctivity.class);
                HotDetail detail = (HotDetail) mAdapter.getItem(position - mlistView.getHeaderViewsCount());
                intent.putExtra(WYNewsDetailctivity.DOCID,detail.getDocid());
                startActivity(intent);

                getActivity().overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
            }
        });


        mViewPager = bannerView.findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(this);

        //标题
        mBannerTitle = bannerView.findViewById(R.id.title);
        mDots = bannerView.findViewById(R.id.dots);
    }

    private void getData() {
        getCurrentIndex();
        isRequesting = true;
        WYHttpUtil util = WYHttpUtil.getInstance();
        util.getData(Constant.getHotUrl(startIndex, endIndex), new WYHttpResponse<Hot>(Hot.class) {
            @Override
            public void onError(String msg) {
                pageNum--;
                isRequesting  = false;
            }

            @Override
            public void onSuccess(Hot hot) {
                isRequesting  = false;
                if (hot != null && null != hot.getT1348647909107()) {
                    pageNum++;
                    List<HotDetail> details = hot.getT1348647909107();
                    HotDetail hot_banner = details.get(0);
                    List<Banner> ads = hot_banner.getAds();
                    mBanners.clear();
                    if (pageNum == 0 && ads != null && ads.size() > 0) {
                        mBanners.addAll(ads);
                        details.remove(0);
                        mHotDetails.addAll(details);
                        mMainHandler.sendEmptyMessage(INIT_SUCCESS);
                    } else {
                        Message message = mMainHandler.obtainMessage(LOAD_MORE);
                        message.obj = details;
                        mMainHandler.sendMessage(message);
                    }


                }
            }
        });
    }


    public void initData() {
        mAdapter = new WYHotAdapter(mHotDetails, getActivity());
        mlistView.setAdapter(mAdapter);
    }

    public void updateData(List<HotDetail> newData) {
        if (mAdapter == null) {
            mHotDetails = new ArrayList<>();
            mHotDetails.addAll(newData);
            mAdapter = new WYHotAdapter(mHotDetails, getActivity());
            mlistView.setAdapter(mAdapter);
        }
        mAdapter.addData(newData);
    }

    public void initBanner() {
        mDots.removeAllViews();
        dot_imgs.clear();
        mViews.clear();

        if (mBanners != null && mBanners.size() > 0) {
            for (int i = 0; i < mBanners.size(); i++) {
                View view = mInflater.inflate(R.layout.include_banner_item, null);
                mViews.add(view);


                ImageView dot = new ImageView(getActivity());
                dot.setImageResource(R.drawable.dot_gray);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                p.setMargins(0, 0, 10, 0);

                dot_imgs.add(dot);
                mDots.addView(dot, p);
            }


            mBannerApapter = new BannerApapter(mViews, mBanners);
            mViewPager.setAdapter(mBannerApapter);
            int half = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % mBanners.size();
            mViewPager.setCurrentItem(half);

            setImageDot(0);
            setBannerTitle(0);
        }
    }

    public void setImageDot(int index) {
        int size = dot_imgs.size();
        int remind = index % size;
        for (int i = 0; i < size; i++) {
            ImageView dot = dot_imgs.get(i);
            if (i == remind) {
                dot.setImageResource(R.drawable.dot_white);
            } else {
                dot.setImageResource(R.drawable.dot_gray);
            }
        }
    }


    public void setBannerTitle(int index) {
        int size = dot_imgs.size();
        int remind = index % size;
        mBannerTitle.setText(mBanners.get(remind).getTitle());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageDot(position);
        setBannerTitle(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == SCROLL_STATE_IDLE && isToEnd) {
            //获取更多
            getData();
        } else {

        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        if (absListView.getLastVisiblePosition() == i2 - 1) {
            isToEnd = true;
        } else {
            isToEnd = false;
        }
    }


    static class MainHandler extends Handler {
        WeakReference<HotFragment> mReference;

        public MainHandler(HotFragment reference) {
            mReference = new WeakReference<HotFragment>(reference);
        }

        @Override
        public void handleMessage(Message msg) {
            HotFragment fragment = mReference.get();
            if (null == fragment) {
                return;
            }
            switch (msg.what) {
                case INIT_SUCCESS:
                    fragment.initData();
                    fragment.initBanner();
                    break;
                case LOAD_MORE:
                    List<HotDetail> datas = (List<HotDetail>) msg.obj;
                    fragment.updateData(datas);
                    break;
                default:
                    break;
            }
        }
    }
}
