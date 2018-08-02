package cn.scu.petcommunity.wechat.controller;

import cn.scu.petcommunity.wechat.service.CoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 此控制器主要用于验证微信服务器以及处理用户发送的消息
 * Created by luohui on 2017/7/17 11:16.
 */
@Controller
public class WeChatController {

	@Resource
	private CoreService coreService;

	/**
	 * 验证微信服务器
	 * @param request http请求
	 * @param response http回复
	 */
	@RequestMapping(value = "/wechat", method = RequestMethod.GET)
	public void confirmServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		coreService.confirmServer(request, response);
	}

	/**
	 * 处理用户发送的消息
	 * @param request http请求
	 * @param response http回复
	 */
	@RequestMapping(value = "/wechat", method = RequestMethod.POST)
	public void responseServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		coreService.responseServer(request, response);
	}
}
