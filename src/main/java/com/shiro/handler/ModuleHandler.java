package com.shiro.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单模块
 * @author guigl
 *
 */
@Controller
@RequestMapping("/module")
public class ModuleHandler {
	
	@RequestMapping("/data")
	public String druid(){
		return "redirect:/druid/index.html";
	}
}
