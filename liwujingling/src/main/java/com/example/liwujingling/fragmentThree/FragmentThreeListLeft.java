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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhengQian on 2016/8/15.
 */
public class FragmentThreeListLeft extends Fragment {


    private ListView mListView;
    private MyListViewAdapter myAdatper = new MyListViewAdapter();
    private List<DatasFragmentThreeLeft> dataList = new ArrayList();
    private Context mContext;

    public static FragmentThreeListLeft newInstance() {
        return new FragmentThreeListLeft();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();
        initDatas();
        View view = inflater.inflate(R.layout.fragment3_left, container, false);
        mListView = (ListView) view.findViewById(R.id.fragment3_list);
        mListView.setAdapter(myAdatper);
        return view;

    }

    private void initDatas() {

        for (int i = 0; i < 20; i++) {
            DatasFragmentThreeLeft datasFragmentThreeLeft = new DatasFragmentThreeLeft();
            datasFragmentThreeLeft.setImageView(R.mipmap.ic_launcher);
            datasFragmentThreeLeft.setContent("追求极限 超越障碍 无所畏惧");
            datasFragmentThreeLeft.setTime("2016-07-26");
            dataList.add(datasFragmentThreeLeft);
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
                return dataList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                DoLog.logd("getView");
                View view = convertView;
                ViewHolder viewHolder = null;
                if (view == null) {
                    view = LayoutInflater.from(mContext).inflate(R.layout.fragment_three_left_list, parent, false);
                    viewHolder = new ViewHolder(view);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                setView(viewHolder, position);
                return view;
            }
        }

            private void setView(ViewHolder viewHolder, int position) {
                DatasFragmentThreeLeft data = dataList.get(position);
//            String imgUrl = data.getImgUrl();
//            ImageLoader.init(mContext).load(100, imgUrl, viewHolder.imageView);


                String content = data.getContent();
                String dataTime = data.getTime();
                int imageView = data.getImageView();
                viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
                viewHolder.contentTv.setText(content);
                viewHolder.timeTv.setText(dataTime);


            }

            class ViewHolder {
                private ImageView imageView;
                private TextView  contentTv, timeTv;

                public ViewHolder(View view) {
                    view.setTag(this);
                    imageView = (ImageView) view.findViewById(R.id.fragment3_left_list_iv);
                    contentTv = (TextView) view.findViewById(R.id.fragment3_left_list_tv_content);
                    timeTv = (TextView) view.findViewById(R.id.fragment3_left_list_tv_time);
                }
            }


        }


