package com.example.liwujingling;

/**
 * Created by Zhengqian on 2016/8/8.
 */
public class Datas {

   public String sgname ;
   public String sgiftname;
   public String snumber ;
   public String saddtime ;
   public String imgUrl;

    public Datas(){

    }
    public Datas(String sgname, String sgiftname, String snumber, String saddtime, String imgUrl) {
        this.sgname = sgname;
        this.sgiftname = sgiftname;
        this.snumber = snumber;
        this.saddtime = saddtime;
        this.imgUrl = imgUrl;

    }

    public void setSgname(String sgname) {
        this.sgname = sgname;
    }

    public void setSgiftname(String sgiftname) {
        this.sgiftname = sgiftname;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }

    public void setSaddtime(String saddtime) {
        this.saddtime = saddtime;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSgname() {
        return sgname;
    }

    public String getSgiftname() {
        return sgiftname;
    }

    public String getSnumber() {
        return snumber;
    }

    public String getSaddtime() {
        return saddtime;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
