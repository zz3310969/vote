package com.vote.main;

import org.roof.spring.Result;
import org.roof.web.menu.entity.Menu;
import org.roof.web.menu.service.api.IMenuFilter;
import org.roof.web.user.entity.User;
import org.roof.web.user.service.api.BaseUserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("mainAction")
public class MainAction {
    private IMenuFilter menuFilter;

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request, HttpSession httpSession) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Menu menu = menuFilter.doFilter(1L, new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                authentication.getCredentials(), authentication.getAuthorities()));
        model.addAttribute("menus", menu.getChildren());
        return "/web_common/user_main_auto.jsp";
    }

    @RequestMapping("/main")
    public String main(Model model, HttpServletRequest request, HttpSession httpSession) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Menu menu = menuFilter.doFilter(1L, new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                authentication.getCredentials(), authentication.getAuthorities()));
        model.addAttribute("menus", menu.getChildren());
        User user = (User) BaseUserContext.getCurrentUser(request);
        model.addAttribute("user", user);

        return "/web_common/user_main_auto.jsp";
    }


    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request, HttpSession httpSession) {
        return "/web_common/user_main_auto.jsp";
    }




    @RequestMapping("/menu")
    public @ResponseBody
    Result menu(Model model, HttpServletRequest request, HttpSession httpSession) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Menu menu = menuFilter.doFilter(1L, new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                authentication.getCredentials(), authentication.getAuthorities()));
        model.addAttribute("menus", menu.getChildren());
        return new Result(Result.SUCCESS, menu);
    }

    @RequestMapping("/user")
    public @ResponseBody
    Result user(Model model, HttpServletRequest request, HttpSession httpSession) {
        User user = (User) BaseUserContext.getCurrentUser(request);
        return new Result(Result.SUCCESS, user);
    }




    /**
     * 进入tab标签
     *
     * @return
     */
    @RequestMapping("/tab")
    public String tab(Model model) {
        return "/web_common/main/tab.jsp";
    }

    @Autowired(required = true)
    public void setMenuFilter(@Qualifier("menuFilter") IMenuFilter menuFilter) {
        this.menuFilter = menuFilter;
    }

}
