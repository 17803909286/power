package com.power.home.data.bean;

/**
 * Created by XWL on 2020/3/13.
 * Description:
 */
public class ForwardAddressBean {
    private String type;
    private String router;
    private String id;
    private String webUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
