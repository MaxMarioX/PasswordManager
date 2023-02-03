package org.pm.mvc;

import org.pm.dao.RoleDao;
import org.pm.entity.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/role")
public class RoleFromController {

    private final RoleDao roleDao;

    public RoleFromController(RoleDao roleDao)
    {
        this.roleDao = roleDao;
    }

    @GetMapping("/ok-role")
    public String OkStatus()
    {
        return "ok";
    }

    @GetMapping("/add-role")
    public String RoleAdd(Model model)
    {
        model.addAttribute("role", new Role());
        return "add-role";
    }

    @PostMapping("/add-role")
    public String RoleAddNow(Role role)
    {
        role.setRole_active(true);
        roleDao.save(role);
        return "redirect:/test/role/ok-role";
    }
}
