package com.brilliantbear.zhihupaper.bean;

import java.util.List;

/**
 * Created by cx.lian on 2016/4/12.
 */
public class ZhihuNews{

    /**
     * date : 20160413
     * stories : [{"images":["http://pic3.zhimg.com/703cf961fa3fdb0a677cd4f843a22afa.jpg"],"type":0,"id":8145349,"ga_prefix":"041316","title":"停电了，感觉每分每秒都很难熬"},{"images":["http://pic3.zhimg.com/2d1052b053b8b13e66e69f79ed4bdc6a.jpg"],"type":0,"id":8150324,"ga_prefix":"041315","title":"不去英国也能逛遍大英博物馆的方法是____"},{"images":["http://pic3.zhimg.com/4d75727eeff376d9b255e92fd9f5ccce.jpg"],"type":0,"id":8140895,"ga_prefix":"041314","title":"真想保肝，就别随便吃「保肝药」"},{"images":["http://pic4.zhimg.com/3c224ba5cba2a4b492216c85d43a1a3b.jpg"],"type":0,"id":8146148,"ga_prefix":"041313","title":"「2100 年人类会灭绝，上海将被海水淹没」，你小时候是不是也听说过？"},{"images":["http://pic1.zhimg.com/697adc2844ca73e73b02f4b0cda67040.jpg"],"type":0,"id":8147448,"ga_prefix":"041312","title":"实力辟谣：海关新政后，还能不能愉快地买买买了"},{"images":["http://pic1.zhimg.com/668234ebf9014f27e866eeeb915b6b00.jpg"],"type":0,"id":8145803,"ga_prefix":"041311","title":"一位双胞胎产妇的真实案例，差点哪家医院都不敢收"},{"images":["http://pic3.zhimg.com/4293e71ddec0d6304b73ab01c05d415e.jpg"],"type":0,"id":8148672,"ga_prefix":"041310","title":"民主党？共和党？他们一开始也叫得乱七八糟"},{"images":["http://pic1.zhimg.com/1b6f102982ad9cd5762b9789c6ab4f90.jpg"],"type":0,"id":8146058,"ga_prefix":"041309","title":"搞房地产的富豪那么多，为什么总有人说利润率低？"},{"images":["http://pic2.zhimg.com/263644c05d79674d6e70ea625f4f7b09.jpg"],"type":0,"id":8145891,"ga_prefix":"041308","title":"关于眼镜和近视，你可能还有这些误解"},{"images":["http://pic1.zhimg.com/292ca3de6b404ebac8b5b1ab9bbe8548.jpg"],"type":0,"id":8146541,"ga_prefix":"041307","title":"看到产品里有奶精不要慌，教你辨别好奶精和坏奶精"},{"title":"教你 Excel 快捷键，用「键盘侠」的姿态轻松完成工作","ga_prefix":"041307","images":["http://pic1.zhimg.com/07e2ca2cb2c093fa2bd97323f2f5d4c0.jpg"],"multipic":true,"type":0,"id":8145928},{"images":["http://pic4.zhimg.com/18b0c828a64634ce6ba0b0ffe4eaf37f.jpg"],"type":0,"id":8145944,"ga_prefix":"041307","title":"一个来自地球的小孩走失在了茫茫太空"},{"images":["http://pic4.zhimg.com/dabf5fc7e148033d130a50a16839c077.jpg"],"type":0,"id":8148448,"ga_prefix":"041307","title":"读读日报 24 小时热门 TOP 5 · 人直接暴露在太空中会发生什么？"},{"images":["http://pic1.zhimg.com/9e0775e0c6a7b9948a4c271c1240b548.jpg"],"type":0,"id":7718350,"ga_prefix":"041306","title":"瞎扯 · 这些东西不要买"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/b143aacabf276a820713dc96af6d3b50.jpg","type":0,"id":8147448,"ga_prefix":"041312","title":"实力辟谣：海关新政后，还能不能愉快地买买买了"},{"image":"http://pic2.zhimg.com/35a2b893370f67e5c2f50217ddbb6d35.jpg","type":0,"id":8148448,"ga_prefix":"041307","title":"读读日报 24 小时热门 TOP 5 · 人直接暴露在太空中会发生什么？"},{"image":"http://pic3.zhimg.com/2a5c9064102e2e808db22054b47ebdde.jpg","type":0,"id":8145803,"ga_prefix":"041311","title":"一位双胞胎产妇的真实案例，差点哪家医院都不敢收"},{"image":"http://pic2.zhimg.com/ca45a8b6d8bd1c4b6a89c3b59338e985.jpg","type":0,"id":8146541,"ga_prefix":"041307","title":"看到产品里有奶精不要慌，教你辨别好奶精和坏奶精"},{"image":"http://pic2.zhimg.com/a02c66d0330fbab96153716284976ba9.jpg","type":0,"id":8145928,"ga_prefix":"041307","title":"教你 Excel 快捷键，用「键盘侠」的姿态轻松完成工作"}]
     */

    private String date;
    /**
     * images : ["http://pic3.zhimg.com/703cf961fa3fdb0a677cd4f843a22afa.jpg"]
     * type : 0
     * id : 8145349
     * ga_prefix : 041316
     * title : 停电了，感觉每分每秒都很难熬
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic1.zhimg.com/b143aacabf276a820713dc96af6d3b50.jpg
     * type : 0
     * id : 8147448
     * ga_prefix : 041312
     * title : 实力辟谣：海关新政后，还能不能愉快地买买买了
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private int type;
        private String id;
        private String ga_prefix;
        private String title;
        private List<String> images;

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

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private String id;
        private String ga_prefix;
        private String title;

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

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @Override
    public String toString() {
        return "ZhihuNews{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
