package cn.scu.petcommunity.wechat.model.view;

/**
 * 微信的button
 * Created by luohui on 2017/7/19 10:39.
 */
public class Button {
    /**
     * button的类型
     */
    private String type;

    /**
     * button的名称
     */
    private String name;

    /**
     * button的子按钮
     */
    private Button[] sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
