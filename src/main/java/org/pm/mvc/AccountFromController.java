package org.pm.mvc;

import org.pm.dao.AccountDao;
import org.pm.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class AccountFromController {

    private final AccountDao accountDao;

    public AccountFromController(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    @GetMapping("/ok")
    public String OkStatus()
    {
        return "ok";
    }

    @GetMapping("/add-account")
    public String AccountAdd(Model model)
    {
        model.addAttribute("account", new Account());
        return "add-account";
    }

    @PostMapping(value = "/add-account")
    public String AccountAddNow(Account account)
    {
        account.setAccount_password_blk(false);
        account.setAccount_strong_auth(false);

        accountDao.save(account);

        return "redirect:/test/ok";

    }
}
