package org.pm.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    HttpSession httpSession;

    public DashboardController()
    {

    }

    @GetMapping("/main")
    public String showDashboard(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();

        model.addAttribute("account", httpSession.getAttribute("LoggedUser"));

        return "dashboard";
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest httpServletRequest)
    {
        this.httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("LoggedUser",null);

        return "redirect:/authentication/login";
    }

}
