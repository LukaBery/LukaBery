package com.nhnacademy.nhnmart.admin.controller;

import com.nhnacademy.nhnmart.admin.repo.impl.AdminLoginRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminLoginController {

    private final AdminLoginRepo adminLoginRepo;

    @GetMapping("/login")
    public String login() {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("password") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        if (adminLoginRepo.validateUser(id, pwd)) {

            Cookie cookie = new Cookie("ADMIN-SESSION", id);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);


            return "redirect:/cs/admin";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("ADMIN-SESSION", null);
        cookie.setMaxAge(0); // 쿠키 삭제
        cookie.setPath("/"); // 경로 설정
        response.addCookie(cookie);
        return "redirect:/login";
    }
}
