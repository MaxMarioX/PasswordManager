package org.pm.mvc;

import org.pm.dao.RoleDao;
import org.pm.entity.Account;
import org.pm.entity.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/role")
public class RoleFromController {

    private final RoleDao roleDao;

    private HttpSession httpSession;

    public RoleFromController(RoleDao roleDao)
    {
        this.roleDao = roleDao;
    }

    /*
    @GetMapping("/add")
    public String RoleAdd(Model model)
    {
        model.addAttribute("role", new Role());
        return "add-role";
    }

    @PostMapping("/add")
    public String RoleAddNow(Role role)
    {
        role.setRole_active(true);
        roleDao.save(role);
        return "ok";
    }
    */
    @GetMapping("/listAll")
    public String RoleListAll(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");

        model.addAttribute("account", account);
        model.addAttribute("roles", roleDao.findAll());

        return "permissions";
    }
}
