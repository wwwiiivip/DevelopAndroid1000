package com.example.liwujingling.fragmentOne;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liwujingling.Datas;
import com.example.liwujingling.DoLog;
import com.example.liwujingling.R;
import com.example.qianimageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhengQian on 2016/8/15.
 */
public class FragmentOneList extends Fragment {


    private ListView mListView;
    private MyListViewAdapter myAdatper = new MyListViewAdapter();
    private List<DatasFragmentOne> dataList = new ArrayList();
    private Context mContext;

    public static FragmentOneList newInstance() {
        return new FragmentOneList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_one_list, container, false);
        mListView = (ListView) view.findViewById(R.id.fragment_one_list_listview);
        mListView.setAdapter(myAdatper);
        initDatas();
//        TextView tv = new TextView(mContext);
//        tv.setText("aaaaaa");
        return view;

    }

    private void initDatas() {

        for (int i = 0; i < 20; i++) {
            DatasFragmentOne datasFragmentOne = new DatasFragmentOne();
            datasFragmentOne.setCompanyName("公司" + i);
            datasFragmentOne.setTime("2010");
            datasFragmentOne.setTitle("游戏"+i);
            dataList.add(datasFragmentOne);
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
                view = LayoutInflater.from(mContext).inflate(R.layout.fragmentone_list_view_layout, parent, false);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            setView(viewHolder, position);
            return view;
        }

        private void setView(ViewHolder viewHolder, int position) {
            DatasFragmentOne data = dataList.get(position);
//            String imgUrl = data.getImgUrl();
//            ImageLoader.init(mContext).load(100, imgUrl, viewHolder.imageView);


            String gname = data.getTitle();
            String companyName = data.getCompanyName();
            String time = data.getTime();
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            viewHolder.titleTv.setText(gname);
            viewHolder.contentTv.setText(companyName);
            viewHolder.timeTv.setText(time);


        }

        class ViewHolder {
            private ImageView imageView;
            private TextView titleTv, contentTv, timeTv;

            public ViewHolder(View view) {
                view.setTag(this);
                imageView = (ImageView) view.findViewById(R.id.fragment_one_list_img);
                titleTv = (TextView) view.findViewById(R.id.fragment_one_list_gname);
                contentTv = (TextView) view.findViewById(R.id.fragment_one_list_companyname);
                timeTv = (TextView) view.findViewById(R.id.fragment_one_list_time);
            }
        }


    }
}
