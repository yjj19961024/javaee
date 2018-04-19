package com.sptpc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sptpc.domain.Userlogin;
import com.sptpc.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView formLogin(String username,String password,
			HttpServletRequest request,HttpServletResponse response,
			HttpSession session){
//		System.out.println(username+"----------"+password);
		Userlogin userlogin = loginService.getUserByNameAndPwd(username, password);
		ModelAndView mv = new ModelAndView("index");
		String str = userlogin.getRoleName();
		if(str.equals("admin")){
			session.setAttribute("ul", userlogin);
			mv = new ModelAndView("redirect:ctr_showStudent");
		}		
		return mv;
	}

}
