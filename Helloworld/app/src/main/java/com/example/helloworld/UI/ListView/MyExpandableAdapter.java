package com.example.helloworld.UI.ListView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hanchenghai on 2018/3/2.
 */

public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private  ArrayList<String> mGroupNames;
    private HashMap<String, ArrayList<String>> mChildNames;

    public MyExpandableAdapter(ArrayList<String> groupNames, HashMap<String, ArrayList<String>> childNames) {
        mGroupNames =  groupNames;
        mChildNames = childNames;
    }

    @Override
    public int getGroupCount() {
        return mGroupNames.size();
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        TextView tv = itemView.findViewById(android.R.id.text1);
        tv.setText(mGroupNames.get(groupPosition));
        return itemView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String groupName = mGroupNames.get(groupPosition);
        ArrayList<String> arrayList = mChildNames.get(groupName);
        return arrayList.size();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_group_child_item, null);
        TextView tv = itemView.findViewById(R.id.tv);
        String groupName = mGroupNames.get(groupPosition);
        ArrayList<String> arrayList = mChildNames.get(groupName);
        tv.setText(arrayList.get(childPosition));
        return itemView;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


}
