package org.pm.mvc;

import org.pm.dao.AccountDao;
import org.pm.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountFromController {

    HttpSession httpSession;

    private final AccountDao accountDao;

    public AccountFromController(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    @GetMapping("/listAll")
    public String accountViewAll(Model model)
    {
        model.addAttribute("accounts",accountDao.findAll());
        return "list-accounts";
    }

    @GetMapping("/add")
    public String accountAdd(Model model)
    {
        model.addAttribute("account", new Account());
        return "add-account";
    }

    @PostMapping("/add")
    public String accountAddNow(Account account)
    {
        account.setAccount_password_blk(false);
        account.setAccount_strong_auth(false);

        accountDao.save(account);

        return "ok";
    }

    @GetMapping("/edit")
    public String accountEdit(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();

        model.addAttribute("account", httpSession.getAttribute("LoggedUser"));

        return "edit-user";
    }

    @PostMapping("/edit")
    public String accountEditNow(HttpServletRequest httpServletRequest, Model model)
    {
        Account account = (Account) httpSession.getAttribute("LoggedUser");

        account.setAccount_name(httpServletRequest.getParameter("accountName"));
        account.setAccount_surname(httpServletRequest.getParameter("accountSurname"));
        account.setAccount_email(httpServletRequest.getParameter("accountEmail"));
        account.setAccount_phone(httpServletRequest.getParameter("accountPhone"));

        if(httpServletRequest.getParameter("accountStrongAuth") != null)
        {
            account.setAccount_strong_auth(true);
        } else {
            account.setAccount_strong_auth(false);
        }

        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("LoggedUser",account);

        accountDao.update(account);

        model.addAttribute("account", httpSession.getAttribute("LoggedUser"));
        model.addAttribute("Message","Data has been updated!");

        return "edit-user";
    }
}
