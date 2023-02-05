package org.pm.mvc;

import org.pm.dao.AccountDao;
import org.pm.dao.LogDao;
import org.pm.entity.Account;
import org.pm.entity.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@RequestMapping("/account")
public class AccountFromController {

    private HttpSession httpSession;

    private final AccountDao accountDao;

    private final LogDao logDao;

    public AccountFromController(AccountDao accountDao, LogDao logDao)
    {
        this.accountDao = accountDao;
        this.logDao = logDao;
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
        Log log = new Log();

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

        log.setLog_msg("Profile data has been changed!");
        log.setLog_date(LocalDate.now().toString());
        log.setAccount(account);

        logDao.save(log);

        model.addAttribute("account", httpSession.getAttribute("LoggedUser"));
        model.addAttribute("Message","Profile has been updated!");

        return "edit-user";
    }
    @GetMapping("/editpassword")
    public String accountEditPassword(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();

        model.addAttribute("account", httpSession.getAttribute("LoggedUser"));

        return "edit-password";
    }

    @PostMapping("/editpassword")
    public String accountEditPasswordNow(HttpServletRequest httpServletRequest, Model model)
    {
        Account account = (Account) httpSession.getAttribute("LoggedUser");
        Log log = new Log();

        String password = httpServletRequest.getParameter("accountPassword");
        String password_r = httpServletRequest.getParameter("accountPasswordR");

        if(!(password == null) && !(password_r == null))
        {
            if(password.equals(password_r))
            {
                account.setAccount_password(password);
                httpSession.setAttribute("LoggedUser",account);
                model.addAttribute("Message","Password has been changed!");

                accountDao.update(account);

                log.setLog_msg("Password has been changed!");
                log.setLog_date(LocalDate.now().toString());
                log.setAccount(account);

                logDao.save(log);

            } else {
                model.addAttribute("Message","Error: Passwords are different!");
            }
        } else {
            model.addAttribute("Message","Error: Password field can't be empty!");
        }

        model.addAttribute("account", httpSession.getAttribute("LoggedUser"));

        return "edit-password";
    }
}
