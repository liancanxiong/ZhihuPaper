package com.brilliantbear.zhihupaper.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by cx.lian on 2016/4/13.
 */
public class DB {
    private static volatile DB db;
    private final Realm realm;

    private DB() {
        realm = Realm.getDefaultInstance();
    }

    public static DB getInstance() {
        if (null == db) {
            synchronized (DB.class) {
                if (null == db) {
                    db = new DB();
                }
            }
        }
        return db;
    }

    public void saveDetail(ZhihuDetail detail) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(detail);
        realm.commitTransaction();
    }

    public ZhihuDetail getZhihuDetailById(String id) {
        RealmResults<ZhihuDetail> detail = realm.where(ZhihuDetail.class).equalTo("id", id).findAll();
        if (null != detail && detail.size() > 0) {
            return detail.first();
        }
        return null;
    }

    public List<ZhihuStory> getLeastStories() {
        List<ZhihuStory> stories = new ArrayList<>();
        RealmResults<ZhihuStory> results = realm.where(ZhihuStory.class).findAllSorted("date", Sort.DESCENDING);
        if (null != results && results.size() > 0) {
            stories.addAll(realm.where(ZhihuStory.class).equalTo("date", results.first().getDate()).findAll());
        }
        return stories;
    }

    public List<ZhihuStory> getStoriesByDate(String date) {
        return realm.where(ZhihuStory.class).equalTo("date", date).findAll();
    }

    public void saveZhihuTops(List<ZhihuTop> tops) {
        if (null != tops) {
            realm.beginTransaction();
            for (ZhihuTop top : tops) {
                realm.copyToRealmOrUpdate(top);
            }
            realm.commitTransaction();
        }
    }

    public void saveZhihuStories(List<ZhihuStory> stories) {
        if (null != stories) {
            realm.beginTransaction();
            for (ZhihuStory story : stories) {
                realm.copyToRealmOrUpdate(story);
            }
            realm.commitTransaction();
        }
    }

    public void deleteZhihuStory(String date){
        if(null != date){
            realm.beginTransaction();
            realm.where(ZhihuStory.class).equalTo("date", date).findAll().clear();
            realm.commitTransaction();
        }
    }
}
