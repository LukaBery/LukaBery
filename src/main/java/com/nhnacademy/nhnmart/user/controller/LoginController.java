package com.nhnacademy.nhnmart.user.controller;

import com.nhnacademy.nhnmart.user.repo.impl.UserLoginRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserLoginRepo userLoginRepo;

    @GetMapping("/login")
    public String login(@CookieValue(value = "USER-SESSION", required = false) String userSession,
                        @CookieValue(value = "ADMIN-SESSION", required = false) String adminSession,
                        Model model) {
        if (StringUtils.hasText(userSession)) {
            return "redirect:/mypage";
        } else if (StringUtils.hasText(adminSession)) {
            return "redirect:/cs/admin" ;
        } else {
            return "login/loginForm";
        }
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("password") String pwd,
                          @RequestParam("role") String role,
                          HttpServletRequest request,
                          HttpServletResponse response) throws Exception {

        if(role.equals("ADMIN")){
            return "forward:/admin/login";

        }else if(role.equals("USER")){
            if(userLoginRepo.validateUser(id, pwd)){
                Cookie cookie = new Cookie("USER-SESSION", id);
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60);
                response.addCookie(cookie);

                return "redirect:/mypage";
            }else {

                return "redirect:/login/loginForm";
            }

        }else {
            return "redirect:/login/loginForm";
        }

    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("USER-SESSION", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/login";
    }
}
