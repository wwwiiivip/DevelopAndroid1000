package com.example.liwujingling.fragmentOne;

/**
 * Created by ZhengQian on 2016/8/15.
 */
public class DatasFragmentOne {
    public String imgUrl;
    public String title;
    public String companyName;
    public String time;

    public DatasFragmentOne(){

    }
    public DatasFragmentOne(String time, String imgUrl, String title, String companyName) {
        this.time = time;
        this.imgUrl = imgUrl;
        this.title = title;
        this.companyName = companyName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
