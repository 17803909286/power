package com.power.home.data.bean;

import java.util.List;

/**
 * Created by XWL on 2020/3/9.
 * Description:
 */
public class BusinessGrowthBean {


    /**
     * landingPage : http://www.wlfxdata.com/wlfx_mobile/datapic?imageUrl=http://www.image.wlfxdata.com/%E5%8F%98%E7%8E%B0%E8%90%A5-%E8%AF%BE%E7%A8%8B%E7%AE%80%E4%BB%8B3.png
     * shareImg : http://www.image.wlfxdata.com/%E5%8F%98%E7%8E%B0%E8%90%A5-%E5%88%86%E4%BA%AB%E5%B0%8F%E5%9B%BE%E6%A0%87%403x.png
     * shareUrl : http://www.wlfxdata.com/wlfx_mobile/growth
     * posterImgs : ["http://www.test.image.wlfxdata.com/%E5%B0%81%E9%9D%A2%403x%20%281%29.png","http://www.test.image.wlfxdata.com/%E5%B0%81%E9%9D%A2%403x%20%282%29.png","http://www.test.image.wlfxdata.com/%E5%B0%81%E9%9D%A2%403x.png","http://www.test.image.wlfxdata.com/%E5%B0%81%E9%9D%A2%E5%A4%87%E4%BB%BD%202%403x%20%281%29.png","http://www.test.image.wlfxdata.com/%E5%B0%81%E9%9D%A2%E5%A4%87%E4%BB%BD%202%403x.png","http://www.test.image.wlfxdata.com/%E5%B0%81%E9%9D%A2%E5%A4%87%E4%BB%BD%403x.png"]
     * shareTitle : 用户裂变100倍现金增长100倍的盈利系统！这300+节课讲透了！
     * shareSubtitle : 动力营|盈利爆炸式增长的企业变现系统
     * price : 498.00
     * slogans : ["书山有路勤为径，学海无涯苦作舟","胸藏文墨怀若谷，腹有诗书气自华","山重水复疑无路，柳暗花明又一村","明日复明白，明日何其多，今日若不为，此事何时了","花开堪折直须折，莫待无花空折枝","三更灯火五更鸡，正是男儿读书时，黑发不知勤学早，白首方悔读书迟"]
     * isBuy : false
     */

    private String landingPage;
    private String shareImg;
    private String shareUrl;
    private String shareTitle;
    private String shareSubtitle;
    private String price;
    private boolean isBuy;
    private List<String> posterImgs;
    private List<String> slogans;

    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareSubtitle() {
        return shareSubtitle;
    }

    public void setShareSubtitle(String shareSubtitle) {
        this.shareSubtitle = shareSubtitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isIsBuy() {
        return isBuy;
    }

    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public List<String> getPosterImgs() {
        return posterImgs;
    }

    public void setPosterImgs(List<String> posterImgs) {
        this.posterImgs = posterImgs;
    }

    public List<String> getSlogans() {
        return slogans;
    }

    public void setSlogans(List<String> slogans) {
        this.slogans = slogans;
    }
}
