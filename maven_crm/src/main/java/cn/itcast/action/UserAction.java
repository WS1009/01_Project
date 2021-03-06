package cn.itcast.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.itcast.entity.User;
import cn.itcast.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author WS
 * @date 创建时间：2017-6-5 下午8:37:06
 * @version 1.0
 */
public class UserAction extends ActionSupport {
	
	//spring的对象类型属性注入 使用set方法注入属性
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * struts直接把表单提交属性封装到action的属性里面
	 * 1.属性封装 变量名称和表单输入项的name属性值一样	
	 * 2.生成变量的set方法（把set和get方法都写出来）
	 */
	private String username;
	
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 登录的方法
	 * 属性封装来获取
	 * */
	public String login() {
		//封装实体类对象
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		User userExist=userService.login(user);
		if (userExist!=null) {//表示登陆成功
			//使用session保持登录状态
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("user", userExist);
			return "loginsuccess";
		}else{//登录失败
			return "login";
		}
	}

}
