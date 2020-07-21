package com.shiro.handler;

import com.alibaba.fastjson.JSON;
import com.shiro.entity.Result;
import com.shiro.entity.Menu;
import com.shiro.service.MenuService;
import com.shiro.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 提供接口请求
 */
@RestController
@RequestMapping("/api")
@Api(value = "api接口调用")
public class ApiHandler {

    @Resource
    private MenuService menuService;
    
    @Resource
    private UserService userServcice;

    @RequestMapping("/index")
    @ApiOperation(value = "测试接口", notes = "根据用户名和密码登录界面")
    public String toIndex() {
        return "123456";
    }

	@PostMapping("/findMenu")
    @ApiOperation(value = "获取菜单接口", notes = "根据用户Id查询")
    public String findMenu(@ApiParam(name = "userId", value = "用户id", required = true)
                @RequestParam(value = "userId", required = true) String userId){
        List<Menu> menus = menuService.findMenu(userId);
        if(menus.isEmpty()){
            return JSON.toJSONString(new Result<List<Menu>>(400,"查询为空",menus));
        }else{
            return JSON.toJSONString(new Result<List<Menu>>(200,"查询菜单成功",menus));
        }
    }

    public String upload(MultipartFile file, HttpServletRequest request){
        //String fileName = file.getOriginalFilename();
        return null;
    }
    
    @PostMapping("/findPermissions")
    @ApiOperation(value = "获取权限接口", notes = "根据用户Id查询")
    public String findPermissions(@ApiParam(name = "userId", value = "用户id", required = true)
    @RequestParam(value = "userId", required = true) int userId){
    	List<String> permissions = userServcice.findPermissionsByUserId(userId);
        if(permissions.isEmpty()){
            return JSON.toJSONString(new Result<List<String>>(400,"查询为空",permissions));
        }else{
            return JSON.toJSONString(new Result<List<String>>(200,"查询菜单成功",permissions));
        }
    }
}
