package com.example.helloworld.RecycleView;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.helloworld.R;

public class WaterfallRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRvWaterFall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterfall_recycler_view);
        mRvWaterFall = findViewById(R.id.rv_waterfall);
        mRvWaterFall.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRvWaterFall.addItemDecoration(new MyDecoration());
        mRvWaterFall.setAdapter(new WaterFalldapter(WaterfallRecyclerViewActivity.this, new WaterFalldapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        }));
    }


    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int gap = getResources().getDimensionPixelSize(R.dimen.dividerGap);
            outRect.set(gap, gap, gap, gap);
        }
    }
}
