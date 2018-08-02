package cn.scu.petcommunity.tag;

import cn.scu.petcommunity.wechat.service.OAuthService;
import cn.scu.petcommunity.wechat.service.impl.OAuthServiceImpl;
import cn.scu.petcommunity.wechat.util.WechatUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by luohui on 2017/7/28 9:31.
 */
public class MyTag extends SimpleTagSupport {

    StringWriter sw = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        getJspBody().invoke(sw);
        OAuthService oAuthService = new OAuthServiceImpl();
        getJspContext().getOut().println(oAuthService.getCodeURL(
        		WechatUtil.DOMAIN_URL + sw.toString(), false, "12"));
    }
}
