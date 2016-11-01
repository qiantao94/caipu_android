package com.qiantao.caicai.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by qiantao on 2016/6/28.
 * 菜单详情实体
 */
public class CookDetail extends BaseObservable implements Parcelable {
    private int id;
    private String name;//名称
    private String food;//食物
    private String img;//图片
    private String images;//图片,
    private String description;//描述
    private String keywords;//关键字
    private String message;//资讯内容
    private int count;//访问次数
    private int fcount;//收藏数
    private int rcount;//评论读数

    protected CookDetail(Parcel in) {
        id = in.readInt();
        name = in.readString();
        food = in.readString();
        img = in.readString();
//        images = in.readString();
//        description = in.readString();
        keywords = in.readString();
//        message = in.readString();
//        count = in.readInt();
//        fcount = in.readInt();
//        rcount = in.readInt();
    }

    public static final Creator<CookDetail> CREATOR = new Creator<CookDetail>() {
        @Override
        public CookDetail createFromParcel(Parcel in) {
            return new CookDetail(in);
        }

        @Override
        public CookDetail[] newArray(int size) {
            return new CookDetail[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyChange();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
        notifyChange();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
        notifyChange();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
        notifyChange();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyChange();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
        notifyChange();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyChange();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        notifyChange();
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
        notifyChange();
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
        notifyChange();
    }

    @Override
    public String toString() {
        return "CookDetail{" +
                "id=" + id +
                ", name=" + name +
                ", food=" + food +
                ", img=" + img +
                ", images=" + images +
                ", description=" + description +
                ", keywords=" + keywords +
                ", message=" + message +
                ", count=" + count +
                ", fcount=" + fcount +
                ", rcount=" + rcount +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(food);
        dest.writeString(img);
//        dest.writeString(images);
//        dest.writeString(description);
        dest.writeString(keywords);
//        dest.writeString(message);
//        dest.writeInt(count);
//        dest.writeInt(fcount);
//        dest.writeInt(rcount);
    }
}
