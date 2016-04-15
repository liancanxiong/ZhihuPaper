package com.brilliantbear.zhihupaper.bean;

import java.util.List;

/**
 * Created by cx.lian on 2016/4/14.
 */
public class ZhihuDetailJson {

    /**
     * body : <div class="main-wrap content-wrap">
     * <div class="headline">
     * <p/>
     * <div class="img-place-holder"></div>
     * <p/>
     * <p/>
     * <p/>
     * </div>
     * <p/>
     * <div class="content-inner">
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <div class="question">
     * <h2 class="question-title">商场里的儿童乐园经营情况是怎么样的？</h2>
     * <p/>
     * <div class="answer">
     * <p/>
     * <div class="meta">
     * <img class="avatar" src="http://pic4.zhimg.com/e06ffde89bd00e145dcbebd6395ab137_is.jpg">
     * <span class="author">言雀，</span><span class="bio">微信公众号：地产三日侃</span>
     * </div>
     * <p/>
     * <div class="content">
     * <ul>
     * <li>社会因素；目前城市中的家庭结构主要以小家庭结构为主，这种家庭结构影响了消费结构。儿童消费在继女性消费为主之后，成为消费环境中重要因素。</li>
     * <li>家庭消费，目前已经是 70% 以上的 Big-Box（大卖场）主要面对的消费群体便是家庭消费。</li>
     * </ul>
     * <p>第一，是因为 Big-Box 中业态品牌属于中高档，家庭消费是属于购买力强的主要社会消费者单元。</p>
     * <p>第二，因为在 Big-Box 中，租金、运营等成本相对比较高，所以在单品消费价格上也比较高。但是儿童类业态并不满足单品高价的特点。所以购物中心的业态还是主要以成人消费为主。</p>
     * <ul>
     * <li>业态转型；零售业的业态不断更新成熟中，儿童类业态日趋成为商业物业业态组合中的重要部分。在商业物业业态布局中，儿童类的业态主要对项目的起到人流牵引的作用。</li>
     * </ul>
     * <ol>
     * <li>在业态的相互牵引影响中，儿童类业态主要起到吸引家庭人流。</li>
     * <li>儿童类的业态，单次消费少，但频次相对较高。</li>
     * <li>儿童类的业态引入也是为了丰富整个购物中心的业态丰富程度。现代型的购物中心中，已经不是单纯的零售业的市场环境。现代性的消费在购物中心中，消费者需要实现购物、休闲、娱乐、家庭亲子互动等元素。</li>
     * </ol>
     * <ul>
     * <li>招商目的；大多数的项目引来的儿童品牌是不指望以此对项目进行大面积的去化。而是（1、2、3）</li>
     * <li>拓展方式；经营方式主要以：① 联营；② 自营；③ 品牌加盟代理等</li>
     * <li>招商政策；一般的购物中心在儿童类的品牌招商中，一般会对商家采取很大的优惠幅度&mdash;&mdash;比如免租三年等。</li>
     * </ul>
     * <p>综合：商场内的儿童乐园还是可以有很大的盈利空间的。一方面是中心运营方让步，另一方商家也确实也可以赚到钱了。</p>
     * <p>--</p>
     * <p>注：以上对儿童类的业态具有普适性。另我更多的从商业地产的整个领域来看待这个这个问题。具体的案例商家经营情况，还需要其他知友贡献。</p>
     * </div>
     * </div>
     * <p/>
     * <p/>
     * <div class="view-more"><a href="http://www.zhihu.com/question/21007887">查看知乎讨论<span class="js-question-holder"></span></a></div>
     * <p/>
     * </div>
     * <p/>
     * <p/>
     * </div>
     * </div>
     * image_source : Inside Out
     * title : 把「蹦极攀岩」搬到商场里，确实是能赚钱的
     * image : http://pic4.zhimg.com/eda058d1aae5e66fc685aedfcc5bd107.jpg
     * share_url : http://daily.zhihu.com/story/8156170
     * js : []
     * ga_prefix : 041410
     * images : ["http://pic3.zhimg.com/12272793be1f391db00737657b600cb2.jpg"]
     * type : 0
     * id : 8156170
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private String id;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
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

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    @Override
    public String toString() {
        return "ZhihuDetailJson{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
