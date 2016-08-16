package com.example.liwujingling.fragmentThree;

/**
 * Created by ZhengQian on 2016/8/15.
 */
public class DatasFragmentThreeLeft {
    public int imageView;
    public String content;
    public String time;
    public DatasFragmentThreeLeft(){

    }
    public DatasFragmentThreeLeft(int imageView, String content, String time) {
        this.imageView = imageView;
        this.content = content;
        this.time = time;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
