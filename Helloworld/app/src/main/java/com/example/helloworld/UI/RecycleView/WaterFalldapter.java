package com.example.helloworld.UI.RecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.helloworld.R;

import java.util.List;

/**
 * Created by hanchenghai on 2018/2/22.
 */

public class WaterFalldapter extends RecyclerView.Adapter<WaterFalldapter.LinearViewHolder> {
    private Context mContext;

    private OnItemClickListener mListener;

    private List<String> lists;


    public WaterFalldapter(Context context, OnItemClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public WaterFalldapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_waterfall_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(WaterFalldapter.LinearViewHolder holder, final int position) {
        if (position % 2 == 0) {
            holder.imageView.setImageResource(R.drawable.water1);
        } else if (position % 3 == 0){
            holder.imageView.setImageResource(R.drawable.water2);
        } else {
            holder.imageView.setImageResource(R.drawable.water3);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 100;
    }


    class LinearViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public LinearViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_iv);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
