package org.pm.mvc;

import org.pm.dao.AccountDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class AccountFromController {

    private final AccountDao accountDao;

    public AccountFromController(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    @GetMapping("/add")
    public String AccountAdd()
    {
        return "add-account";
    }
}
