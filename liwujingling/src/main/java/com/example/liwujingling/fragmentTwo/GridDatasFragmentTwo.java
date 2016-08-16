package com.example.liwujingling.fragmentTwo;

import android.widget.ImageView;

/**
 * Created by ZhengQian on 2016/8/15.
 */
public class GridDatasFragmentTwo
{
    private int imageId;
    private String gname;

    public GridDatasFragmentTwo(){

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageView) {
        this.imageId = imageView;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public GridDatasFragmentTwo(int imageView, String gname) {
        this.imageId = imageView;
        this.gname = gname;
    }
}
