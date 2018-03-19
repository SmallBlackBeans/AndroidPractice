package com.example.helloworld.UI.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;

/**
 * Created by hanchenghai on 2018/2/26.
 */

public class FragmentA extends Fragment {

    private TextView mTvTitle;

    private Button mBtnChange;
    private Button mBtnReset;
    private Button mBtnMessage;

    private FragmentB bFragment;

    //声明接口
    private IOnMessageClick listener;


    public static FragmentA newInstance(String title) {
        FragmentA fragment = new FragmentA();
        //传值
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }


    public interface IOnMessageClick {
        void onClick(String message);
    }

    //和Activity之间的关系 连接 断开
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (IOnMessageClick) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity 必须实现IOnMessageClick 接口");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            //横竖屏的时候保存的一些状态
            savedInstanceState.getInt("saveKey");
        }
    }

    //横竖屏保存状态
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("saveKey",1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_a, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvTitle = view.findViewById(R.id.tv_title);
        mBtnChange = view.findViewById(R.id.btn_change);
        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bFragment == null) {
                    //实例化
                    bFragment = new FragmentB();
                }
                Fragment fragment = getFragmentManager().findFragmentByTag("tagA");
                if (fragment != null) {
                    //添加到Activity
                    getFragmentManager()
                            .beginTransaction()
                            .hide(fragment)
                            .add(R.id.fl_container, bFragment)
                            .addToBackStack(null)//Fragment 回退栈
                            .commitAllowingStateLoss();
                } else {
                    //替换
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_container, bFragment)
                            .addToBackStack(null)//Fragment 回退栈
                            .commitAllowingStateLoss();
                }
            }
        });

        //消息传递
        mBtnMessage = view.findViewById(R.id.btn_message);
        mBtnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((ContainerActivity)getActivity()).setData("你好");
                if (listener != null) {
                    listener.onClick("你好");
                }
            }
        });


        mBtnReset = view.findViewById(R.id.btn_reset);
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvTitle.setText("我是新文字");
            }
        });


        if (getArguments() != null) {
            mTvTitle.setText(getArguments().getString("title"));
        }


        if (getActivity() != null) {
            // TODO: 2018/2/26
        } else {

        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    //UI不可见
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //回收内存
    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消异步
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
