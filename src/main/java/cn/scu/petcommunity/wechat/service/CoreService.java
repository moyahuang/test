package cn.scu.petcommunity.wechat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证服务器和回复服务器消息
 * Created by luohui on 2017/7/19 9:12.
 */
public interface CoreService {
    /**
     * 验证微信服务器，成为开发者
     * @param request
     * @param response
     */
    void confirmServer(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 回复微信服务器消息
     * @param request
     * @param response
     */
    void responseServer(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
