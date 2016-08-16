package com.example.liwujingling.fragmentThree;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liwujingling.DoLog;
import com.example.liwujingling.R;
import com.example.liwujingling.fragmentOne.DatasFragmentOne;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhengQian on 2016/8/15.
 */
public class FragmentThreeListRight extends Fragment {


    private ListView mListView;
    private MyListViewAdapter myAdatper = new MyListViewAdapter();
    private List<DatasFragmentThreeRight> dataList = new ArrayList();
    private Context mContext;

    public static FragmentThreeListRight newInstance() {
        return new FragmentThreeListRight();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment3_left, container, false);
        mListView = (ListView) view.findViewById(R.id.fragment3_list);
        mListView.setAdapter(myAdatper);
        initDatas();
//        TextView tv = new TextView(mContext);
//        tv.setText("aaaaaa");
        return view;

    }

    private void initDatas() {

        for (int i = 0; i < 20; i++) {
            DatasFragmentThreeRight datasFragmentThreeRight = new DatasFragmentThreeRight();
            datasFragmentThreeRight.setContent("国奥加油");
            datasFragmentThreeRight.setImageView(R.mipmap.ic_launcher);
            dataList.add(datasFragmentThreeRight);
        }
    }

    class MyListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return dataList == null ? 0 : dataList.size();
        }

        @Override
        public Object getItem(int position) {
            DoLog.logd(String.valueOf(position));
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            DoLog.logd("getView");
            View view = convertView;
            ViewHolder viewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_three_right_list, parent, false);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            setView(viewHolder, position);
            return view;
        }

        private void setView(ViewHolder viewHolder, int position) {
            DatasFragmentThreeRight datasFragmentThreeRight = dataList.get(position);
//            String imgUrl = data.getImgUrl();
//            ImageLoader.init(mContext).load(100, imgUrl, viewHolder.imageView);


            String content = datasFragmentThreeRight.getContent();
            viewHolder.contentTv.setText(content);


        }

        class ViewHolder {
            private ImageView imageView;
            private TextView titleTv, contentTv, timeTv;

            public ViewHolder(View view) {
                view.setTag(this);
                imageView = (ImageView) view.findViewById(R.id.fragment3_right_list_iv);
                contentTv = (TextView) view.findViewById(R.id.fragment3_right_list_tv_time);

            }
        }


    }
}
