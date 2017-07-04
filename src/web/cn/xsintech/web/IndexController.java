package cn.xsintech.web;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("indexController")
public class IndexController {

	/**
	 * ログイン画面の遷移
	 *
	 * @param model
	 * @return ログイン画面遷移先
	 */
	@RequestMapping(value = "/")
	public String login(Device device, Model model) {
		return "html/login";
	}
}
