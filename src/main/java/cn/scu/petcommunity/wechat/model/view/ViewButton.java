package cn.scu.petcommunity.wechat.model.view;

/**
 * Created by luohui on 2017/7/19 10:45.
 */
public class ViewButton extends Button {
    /**
     * 按钮上绑定的url
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
