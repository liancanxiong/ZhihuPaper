package com.brilliantbear.zhihupaper.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by cx.lian on 2016/4/13.
 */
public class ZhihuStory extends RealmObject {
    @PrimaryKey
    private String id;
    private String image;
    private int type;
    private String title;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
