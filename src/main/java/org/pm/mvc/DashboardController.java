package org.pm.mvc;

import org.pm.dao.AccountDao;
import org.pm.dao.RoleDao;
import org.pm.entity.Account;
import org.pm.entity.Role;
import org.pm.repository.AccountRepository;
import org.pm.repository.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private HttpSession httpSession;
    private final RoleRepository roleRepository;

    private final RoleDao roleDao;
    private final AccountRepository accountRepository;

    public DashboardController(RoleRepository roleRepository,
                               AccountRepository accountRepository,
                               RoleDao roleDao)
    {
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.roleDao = roleDao;
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
