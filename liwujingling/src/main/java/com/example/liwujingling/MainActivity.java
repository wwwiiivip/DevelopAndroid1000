package com.example.liwujingling;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {
    private RadioGroup mRg;
    private Fragment currentFragment = HomePageFragment.newInstance();
    private FragmentManager mManager;
    private View fragmentMain = null;
    private View fragment1View = null;
    private View fragment2View = null;
    private View fragment3View = null;
    private LayoutInflater mInflater = null;

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.homepage_gift_rb:
                    ctrlFragment(homePageFragment);
                    if(mFrameLayout.getChildCount()!=0){
                        mFrameLayout.removeAllViews();
                    }
                    mFrameLayout.addView(fragmentMain);
                    break;
                case R.id.homepage_game_rb:
                    ctrlFragment(oneFragment);
                    if(mFrameLayout.getChildCount()!=0){
                        mFrameLayout.removeAllViews();
                    }
                    mFrameLayout.addView(fragment1View);
                    break;
                case R.id.homepage_play_rb:
                    ctrlFragment(twoFragment);
                    if(mFrameLayout.getChildCount()!=0){
                        mFrameLayout.removeAllViews();
                    }
                    mFrameLayout.addView(fragment2View);
                    break;
                case R.id.homepage_mine_rb:
                    ctrlFragment(threeFragment);
                    if(mFrameLayout.getChildCount()!=0){
                        mFrameLayout.removeAllViews();
                    }
                    mFrameLayout.addView(fragment3View);
                    break;
            }
        }
    };
    private FrameLayout mFrameLayout;

    private void ctrlFragment(Fragment fragment) {
        FragmentTransaction transaction = mManager.beginTransaction();
        if(currentFragment != null && currentFragment.isAdded()){
            transaction.hide(currentFragment);
        }
        if(!fragment.isAdded()){
            transaction.add(R.id.home_page_layout,fragment);
        }else{
            transaction.show(fragment);
        }
        transaction.commit();
        currentFragment = fragment;

    }


    private HomePageFragment homePageFragment = null;
    private FragmentOne oneFragment = null;
    private FragmentTwo twoFragment = null;
    private FragmentThree threeFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mManager = getSupportFragmentManager();
        initFragment();
        initView();

    }

    private void initView() {
        mRg=  (RadioGroup) findViewById(R.id.home_bottom_rg);
        mRg.check(R.id.homepage_gift_rb);
        mRg.setOnCheckedChangeListener(onCheckedChangeListener);
        mFrameLayout = (FrameLayout) findViewById(R.id.main_framlayout);
        fragmentMain = mInflater.inflate(R.layout.main_title_layout,null,false);
        fragment1View = mInflater.inflate(R.layout.fragment1_title_layout,null,false);
        fragment2View = mInflater.inflate(R.layout.fragment2_title_layout,null,false);
        fragment3View = mInflater.inflate(R.layout.fragment3_layout,null,false);
        mFrameLayout.addView(fragmentMain);
    }

    private void initFragment(){
        homePageFragment = HomePageFragment.newInstance();
        oneFragment = FragmentOne.newInstance();
        twoFragment = FragmentTwo.newInstance();
        threeFragment = FragmentThree.newInstance();
        ctrlFragment(homePageFragment);
    }







}
