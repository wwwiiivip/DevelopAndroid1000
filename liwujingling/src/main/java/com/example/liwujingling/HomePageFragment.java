package com.example.liwujingling;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qianimageloader.ImageLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by ZhengQian on 2016/8/7.
 */
public class HomePageFragment extends Fragment {
    private List<Datas> dataList = new ArrayList<>();
    private Context mContext;
    private ViewPager mViewPage;
    private List<Bitmap> adImgList = new ArrayList<>();
    private LinearLayout mIndicatorLayout;
    private int childCount;
    private String jsonStr = "";
    private MyListViewAdapter mListAdapter;
    private MyViewPageAdapter mPageAdapter;
    private ListView mListView;
    private boolean isTouch;
    private Handler sHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object obj = msg.obj;
            if(obj instanceof Bitmap){
                Bitmap bm = (Bitmap) obj;
            }
            if(msg.what != 1){
                if(isTouch){
                    return;
                }
                if(msg.what == 0){
                    mViewPage.setCurrentItem(Integer.MAX_VALUE/2+1);
                    sendEmptyMessageDelayed(msg.what+1,2000);
                }else{
                        mViewPage.setCurrentItem(msg.what+1);
                        sendEmptyMessageDelayed(msg.what+1,2000);
                }
            }
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what ==2){
                mPageAdapter = new MyViewPageAdapter();
                mViewPage.setAdapter(mPageAdapter);
            mPageAdapter.notifyDataSetChanged();
            }else{
                mListAdapter = new MyListViewAdapter();
                mListView.setAdapter(mListAdapter);
            mListAdapter.notifyDataSetChanged();
            }
//            mListAdapter = new MyListViewAdapter();
//            mListView.setAdapter(mListAdapter);
//            mPageAdapter.notifyDataSetChanged();
        }
    };

    static HomePageFragment newInstance(){
        return new HomePageFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        new Thread(new Runnable() {
            @Override
            public void run() {
                initDatas();

            }
        }).start();
    }



    private void initDatas() {
        StringBuffer sb = null;
        try {
            String postStr = "pageno=1";
            String URL_DATAS = "http://www.1688wan.com/majax.action?method=getGiftList";
            URL url = new URL(URL_DATAS);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(5000);
            urlConnection.getOutputStream().write(postStr.getBytes());
            urlConnection.getOutputStream().flush();
            urlConnection.connect();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = urlConnection.getInputStream();
                int len = 0;
                byte [] buf = new byte[1024];
                 sb = new StringBuffer();
                while(true){
                    len = inputStream.read(buf);
                    if(len == -1){
                        break;
                    }
                    sb.append(new String(buf,0,len));
                }
                close(inputStream);
                jsonStr = sb.toString();
                DoLog.logd(jsonStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray datasArray = null;
        JSONArray adArray = null;
        String URL = "http://www.1688wan.com";
        try {
            assert jsonObject != null;
            datasArray = jsonObject.getJSONArray("list");
            adArray = jsonObject.getJSONArray("ad");
            for (int i = 0; i < adArray.length(); i++) {
               DoLog.logd(String.valueOf(adArray.length()));
//                boolean iconurl1 = adArray.getJSONObject(i).isNull("iconurl");
//                if(iconurl1){
                String iconurl = URL + adArray.getJSONObject(i).getString("iconurl");
                DoLog.logd(iconurl);
                Bitmap bm = DownloadData.getDatasFromInt(iconurl);
                adImgList.add(bm);
//                }
            }
            handler.sendEmptyMessage(2);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < datasArray.length(); i++) {

            try {
                JSONObject datasObject = datasArray.getJSONObject(i);
                String sgname       = datasObject.getString("gname");
                String sgiftname = datasObject.getString("giftname");
                String snumber      = datasObject.getString("number");
                String saddtime = datasObject.getString("addtime");
                boolean iconurl = datasObject.isNull("iconurl");
                if(!iconurl){
                    String  imgUrl = URL +datasObject.getString("iconurl");
                Datas datas =new Datas(sgname,
                        sgiftname,
                        snumber,
                        saddtime,
                        imgUrl);
                dataList.add(datas);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        handler.sendEmptyMessage(1);


    }


    private void close(Closeable closeable){
        if(closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_page_list_view, container, false);
        View pagerLayout = LayoutInflater.from(mContext).inflate(R.layout.view_pager_layout,null,false);
        mListView = (ListView) view.findViewById(R.id.home_page_lv);
        mListView.addHeaderView(pagerLayout);
        mIndicatorLayout = (LinearLayout) pagerLayout.findViewById(R.id.indicator_linearlayout);
        childCount = mIndicatorLayout.getChildCount();
        mViewPage = (ViewPager) pagerLayout.findViewById(R.id.top_ads_vp);
        controlIndicator(0);
        mViewPage.setCurrentItem(Integer.MAX_VALUE/2);

        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                controlIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isTouch = true;
                        int currentItem1 = mViewPage.getCurrentItem();
                        sHandler.sendEmptyMessageDelayed(currentItem1,2000);
                        break;
                    case MotionEvent.ACTION_UP:
                        isTouch = false;
                        int currentItem2 = mViewPage.getCurrentItem();
                        sHandler.sendEmptyMessageDelayed(currentItem2,2000);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        isTouch = true;
                        int currentItem3 = mViewPage.getCurrentItem();
                        sHandler.sendEmptyMessageDelayed(currentItem3,2000);
                        break;
                }
                return isTouch;
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                initDatas();
//
//            }
//        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = sHandler.obtainMessage();
                message.what = mViewPage.getCurrentItem();
                sHandler.sendEmptyMessageDelayed(2,2000);
            }
        }).start();
//        mPageAdapter = new MyViewPageAdapter();
//        mViewPage.setAdapter(mPageAdapter);
//        mListAdapter = new MyListViewAdapter();
//        mListView.setAdapter(mListAdapter);
        return view;
    }

    private void controlIndicator(final int index) {
        ImageView imageView = (ImageView) mIndicatorLayout.getChildAt(index % 5);
        for (int i = 0; i < childCount; i++) {
            ImageView imageView1 = (ImageView) mIndicatorLayout.getChildAt(i % 5);
            imageView1.setEnabled(false);
        }


        imageView.setEnabled(true);
    }




    class MyViewPageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
//            new Thread(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            bm = DownloadData.getDatasFromInt(adImgList.get(position));
//                            Message message = new Message();
//                            message.obj = bm;
//                            sHandler.sendMessage(message);
//                        }
//                    }
//            ).start();
            if(!adImgList.isEmpty()){
                if(adImgList.size()>position%4){
                imageView.setImageBitmap(adImgList.get(position%4));
                }
            }else{
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            return imageView;
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
            if(view == null){
                view = LayoutInflater.from(mContext).inflate(R.layout.list_view_layout,parent,false);
                viewHolder = new ViewHolder(view);
            }else{
                viewHolder = (ViewHolder) view.getTag();
            }
            setView(viewHolder,position);
            return view;
        }

        private void setView(ViewHolder viewHolder,int position) {
            Datas data = dataList.get(position);
            String imgUrl = data.getImgUrl();
            ImageLoader.init(mContext).load(100,imgUrl,viewHolder.imageView);
            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


                String sgname =   data.getSgname();
                String sgiftname =data.getSgiftname();
                String snumber =  data.getSnumber();
                String saddtime = data.getSaddtime();


                if(Integer.valueOf(snumber) == 0){
                    viewHolder.titleTv.setText(sgname);
                    viewHolder.contentTv.setText(sgiftname);
                    viewHolder.timeTv.setText(saddtime);
                    viewHolder.bottomNumTv.setText(snumber);
                    viewHolder.button.setText("淘号");
                    viewHolder.button.setBackgroundColor(getResources().getColor(R.color.colorGray));
                }else{
                    viewHolder.button.setText("立即领取");
                    viewHolder.button.setBackgroundColor(getResources().getColor(R.color.colorPink));
                    viewHolder.titleTv.setText(sgname);
                    viewHolder.contentTv.setText(sgiftname);
                    viewHolder.timeTv.setText(saddtime);
                    viewHolder.bottomNumTv.setText(snumber);
                }


        }
        class ViewHolder{
            private ImageView imageView ;
            private Button button;
            private TextView titleTv ,contentTv,
                     bottomNumTv , timeTv;
            public ViewHolder(View view){
                view.setTag(this);
                button = (Button) view.findViewById(R.id.list_commit_btn);
                imageView = (ImageView) view.findViewById(R.id.list_letf_pic_iv);
                titleTv = (TextView) view.findViewById(R.id.list_big_title_tv);
                contentTv = (TextView) view.findViewById(R.id.list_content_tv);

                bottomNumTv = (TextView) view.findViewById(R.id.list_bottom_number_tv);
                timeTv = (TextView) view.findViewById(R.id.list_bottom_time_tv);
            }
        }

    }

}
