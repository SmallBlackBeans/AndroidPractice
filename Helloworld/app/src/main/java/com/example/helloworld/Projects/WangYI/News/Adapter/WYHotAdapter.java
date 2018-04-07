package com.example.helloworld.Projects.WangYI.News.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.platform.comapi.map.C;
import com.example.helloworld.Projects.WangYI.News.Bean.Hot;
import com.example.helloworld.Projects.WangYI.News.Bean.HotDetail;
import com.example.helloworld.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanchenghai on 2018/3/30.
 */

public class WYHotAdapter extends BaseAdapter {


    private final DisplayImageOptions mOptions;
    ArrayList<HotDetail> mHotDetails;
    LayoutInflater mInflater;


    public WYHotAdapter(ArrayList<HotDetail> hotDetails, Context context) {
        mHotDetails = hotDetails;
        mInflater = LayoutInflater.from(context);

        //建造者模式-> 创建一个复杂的对象
        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher_foreground)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(500))
                .cacheInMemory(true).cacheOnDisk(true).build();
        //三级缓存 SD卡  - 内存 - 内存池
    }

    @Override
    public int getCount() {
        return mHotDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return mHotDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        HotDetail detail = mHotDetails.get(i);
        if (view == null) {
            view = mInflater.inflate(R.layout.list_item_hot, null);
            holder = new ViewHolder();
            holder.icon = view.findViewById(R.id.image);
            holder.title = view.findViewById(R.id.title);
            holder.source = view.findViewById(R.id.resource);
            holder.reply_count = view.findViewById(R.id.reply_count);
            holder.special = view.findViewById(R.id.special);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        initViews(holder, detail);
        return view;
    }


    public void initViews(ViewHolder holder, HotDetail detail) {
        holder.title.setText(detail.getTitle());
        holder.source.setText(detail.getSource());
        holder.reply_count.setText(detail.getReplyCount() + "跟帖");
        holder.special.setText(detail.getSpecialID());

        ImageLoader.getInstance().displayImage(detail.getImg(), holder.icon, mOptions, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }


    public void addData(List<HotDetail> datas) {
        if (mHotDetails == null) {
            mHotDetails = new ArrayList<>();
        }
        mHotDetails.addAll(datas);
        notifyDataSetChanged();
    }



    class ViewHolder {
        ImageView icon;
        TextView title;
        TextView source;
        TextView reply_count;
        TextView special;
    }
}
