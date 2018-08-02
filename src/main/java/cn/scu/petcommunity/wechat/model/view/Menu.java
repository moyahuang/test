package cn.scu.petcommunity.wechat.model.view;

/**
 * 微信的菜单
 * Created by luohui on 2017/7/19 10:43.
 */
public class Menu {
    /**
     * 菜单的按钮
     */
   private  Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
