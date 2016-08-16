package com.example.liwujingling.fragmentOne;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.liwujingling.R;
import com.example.liwujingling.Utils.FinalCollection;
import com.example.liwujingling.httpUtils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhengQian on 2016/8/15.
 */
public class FragmentOneDesign extends android.support.v4.app.Fragment {
    private ExpandableListView mExpandListView;
    private MyAdapter myAdapter = new MyAdapter();
    private List<List<String>> timeList = new ArrayList<>();
    private List<String> timeStrList = new ArrayList<>();
    private List<String> dataList = new ArrayList<>();
    private Context mContext;
    private String jsonStr;

    public static FragmentOneDesign newInstance(){
        return new FragmentOneDesign();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_one_design, container, false);
        mExpandListView = (ExpandableListView) view.findViewById(R.id.fragment_one_design_expand_listview);
        initDatas();
        mExpandListView.setAdapter(myAdapter);
        expandListView();
        return view;
    }

    private void expandListView() {
        for (int i = 0; i < timeList.size(); i++) {
            mExpandListView.expandGroup(i);
        }
    }

//    private void initDatas() {
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 2; j++) {
//                dataList.add("j");
//            }
//
//            timeStrList.add("parent"+i);
//            timeList.add(dataList);
//        }
//    }

    private void initDatas(){

        jsonStr = HttpUtils.getJsonStr(FinalCollection.FRAGMENT_ONE_LEFT_URL,null);
    }



    class MyAdapter extends BaseExpandableListAdapter{

        @Override
        public int getGroupCount() {
            return 5;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 2;
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
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String s = timeStrList.get(groupPosition);
            View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_one_design_parent, parent, false);
            TextView parentTv = (TextView) view.findViewById(R.id.fragment_one_design_parent_tv);
            parentTv.setText(s);
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            List<String> strList = timeList.get(groupPosition);
            String s = strList.get(childPosition);
            View view = LayoutInflater.from(mContext).inflate(R.layout.fragmentone_list_view_layout, parent, false);
            TextView gnameTv = (TextView) view.findViewById(R.id.fragment_one_list_gname);
            gnameTv.setText(s);
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
