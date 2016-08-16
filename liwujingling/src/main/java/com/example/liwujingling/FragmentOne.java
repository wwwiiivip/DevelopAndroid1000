package com.example.liwujingling;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.liwujingling.fragmentOne.FragmentOneDesign;
import com.example.liwujingling.fragmentOne.FragmentOneList;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Zhengqian on 2016/8/7.
 */
public class FragmentOne extends Fragment {
    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private MyAdapter myAdapter ;
    private TabLayout mTabLayout;
    private List<String> list = new ArrayList<>();
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }
    //
//        private FragmentTransaction fragmentTransaction;
//    private Fragment currentFragment = FragmentOneDesign.newInstance();

    static FragmentOne newInstance(){
        return new FragmentOne();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initFragment();
        myAdapter = new MyAdapter(getChildFragmentManager());
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.fragment1_tablayout);
        mViewPager = (ViewPager) view.findViewById(R.id.fragment1_viewpager);
//        FragmentManager childFragmentManager = getChildFragmentManager();
//        fragmentTransaction = childFragmentManager.beginTransaction();
        mViewPager.setAdapter(myAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    private void initFragment() {
        fragments.add(FragmentOneDesign.newInstance());
        list.add("开服");
        fragments.add(FragmentOneList.newInstance());
        list.add("开测");
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }
}
