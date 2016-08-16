package com.example.liwujingling.fragmentTwo;

import android.widget.ImageView;

/**
 * Created by ZhengQian on 2016/8/15.
 */
public class DatasFragmentTwo {
    public int imageView;
    public String gname;
    public String type;
    public String size;
    public String number;

    public DatasFragmentTwo(){

    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public DatasFragmentTwo(int imageView, String gname, String type, String size, String number) {
        this.imageView = imageView;
        this.gname = gname;
        this.type = type;
        this.size = size;
        this.number = number;
    }
}
