package com.example.liwujingling;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.liwujingling.fragmentTwo.DatasFragmentTwo;
import com.example.liwujingling.fragmentTwo.GridDatasFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhengqian on 2016/8/7.
 */
public class FragmentTwo extends Fragment {


    private TextView mTextView1;
    private TextView mTextView2;
    private ListView mListView;
    private GridView mGridView;
    private List<DatasFragmentTwo> datas = new ArrayList<>();
    private List<GridDatasFragmentTwo> gridDatas = new ArrayList<>();
    private Context mContext;
    private String[] from = {
            "部落冲突",
            "皇室冲突",
            "黑白冲突",
            "各种冲突",
            "没有冲突",
            "还有冲突",
    };
    private int[] to = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };
    private LayoutInflater mLayoutInflater;

    static FragmentTwo newInstance(){
        return new FragmentTwo();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_two,container,false);
        initView(view);
        initDatas();
        return view;
    }

    private void initDatas() {
        for (int i = 0; i < 3; i++) {
            DatasFragmentTwo datasFragmentTwo = new DatasFragmentTwo();
            datasFragmentTwo.setGname("王者荣耀"+i);
            datasFragmentTwo.setImageView(R.mipmap.ic_launcher);
            datasFragmentTwo.setNumber("1000");
            datasFragmentTwo.setSize("大小:200M");
            datasFragmentTwo.setType("类型:动作格斗");
            datas.add(datasFragmentTwo);
        }

        for (int i = 0; i < 6; i++) {
            GridDatasFragmentTwo gridDatasFragmentTwo = new GridDatasFragmentTwo();
            gridDatasFragmentTwo.setGname("部落冲突:皇室战争"+i);
            gridDatasFragmentTwo.setImageId(R.mipmap.ic_launcher);
            gridDatas.add(gridDatasFragmentTwo);
        }

    }

    private void initView(View view) {
        mLayoutInflater = LayoutInflater.from(mContext);
        mTextView1 = (TextView) view.findViewById(R.id.fragment2_textview1);
        mTextView2 = (TextView) view.findViewById(R.id.fragment2_textview2);
        mListView = (ListView) view.findViewById(R.id.fragment2_listview);
        mGridView = (GridView) view.findViewById(R.id.fragment2_gridview);
        mGridView.setAdapter(new MyGridAdapter());
        mListView.setAdapter(new MyListAdapter());
    }

    class MyListAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = mLayoutInflater.inflate(R.layout.fragmenttwo_list_view_layout, parent, false);
            DatasFragmentTwo datasFragmentTwo = datas.get(position);
            String gname = datasFragmentTwo.getGname();
            int imageView = datasFragmentTwo.getImageView();
            String number = datasFragmentTwo.getNumber();
            String size = datasFragmentTwo.getSize();
            String type = datasFragmentTwo.getType();
            TextView nameTv = (TextView) view.findViewById(R.id.fragment_two_list_gname);
            ImageView imgIv = (ImageView) view.findViewById(R.id.fragment_two_list_img);
            TextView numberTv = (TextView) view.findViewById(R.id.fragment_two_list_number);
            TextView sizeTv = (TextView)view.findViewById(R.id.fragment_two_list_size);
            TextView typeTv = (TextView)view.findViewById(R.id.fragment_two_list_type);
            nameTv.setText(gname);
            imgIv.setImageResource(imageView);
            numberTv.setText(number);
            sizeTv.setText(size);
            typeTv.setText(type);
            return view;
        }
    }

    class MyGridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return gridDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return gridDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = mLayoutInflater.inflate(R.layout.frgament2_gridview_item, parent, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.gridview_item_imageview);
            TextView textView = (TextView) view.findViewById(R.id.gridview_item_name_tv);
            GridDatasFragmentTwo gridDatasFragmentTwo = gridDatas.get(position);
            imageView.setImageResource(gridDatasFragmentTwo.getImageId());
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            textView.setText(gridDatasFragmentTwo.getGname());
            return view;
        }
    }

}
