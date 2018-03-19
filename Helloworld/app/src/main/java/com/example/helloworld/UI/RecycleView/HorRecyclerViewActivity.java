package com.example.helloworld.UI.RecycleView;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.helloworld.R;

public class HorRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRVHor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_recycler_view);
        mRVHor = findViewById(R.id.rv_hor);
        LinearLayoutManager manager = new LinearLayoutManager(HorRecyclerViewActivity.this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRVHor.addItemDecoration(new MyDecoration());
        mRVHor.setLayoutManager(manager);
        mRVHor.setAdapter(new HorAdapter(HorRecyclerViewActivity.this, new HorAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        }));
    }

    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, getResources().getDimensionPixelOffset(R.dimen.dividerHeight), 0);
        }
    }
}
