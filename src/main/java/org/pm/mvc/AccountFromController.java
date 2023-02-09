package org.pm.mvc;

import org.pm.dao.AccountDao;
import org.pm.dao.LogDao;
import org.pm.dao.RoleDao;
import org.pm.entity.Account;
import org.pm.entity.Log;
import org.pm.entity.Role;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountFromController {

    private HttpSession httpSession;

    private final AccountDao accountDao;

    private final LogDao logDao;

    private final RoleDao roleDao;

    public AccountFromController(AccountDao accountDao, LogDao logDao, RoleDao roleDao)
    {
        this.accountDao = accountDao;
        this.logDao = logDao;
        this.roleDao = roleDao;
    }

    @GetMapping("/listAll")
    public String accountViewAll(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();

        model.addAttribute("account", httpSession.getAttribute("LoggedUser"));
        model.addAttribute("accounts",accountDao.findAll());

        return "list-accounts";
    }

    @GetMapping("/add")
    public String accountAdd(Model model)
    {
        model.addAttribute("account", new Account());
        model.addAttribute("roles", roleDao.findAll());

        return "add-account";
    }

    @PostMapping("/add")
    public String accountAddNow(Account account)
    {
        account.setAccount_password_blk(false);
        account.setAccount_strong_auth(false);
        account.setRoleList(roleDao.findAll());


        accountDao.save(account);

        return "redirect:/account/listAll";
    }

    @GetMapping("/edit")
    public String accountEdit(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();

        model.addAttribute("account", httpSession.getAttribute("LoggedUser"));

        return "edit-user";
    }

    @GetMapping("/editAdm/{accountNumber}")
    public String accountEditAdm(HttpServletRequest httpServletRequest, Model model, @PathVariable("accountNumber") Long accountNumber)
    {
        this.httpSession = httpServletRequest.getSession();

        Account account = (Account) httpSession.getAttribute("LoggedUser");
        Account accountEdit = accountDao.findByNumber(accountNumber);

        model.addAttribute("account", account);
        model.addAttribute("accountEdit", accountEdit);

        return "edit-user-adm";
    }

    @PostMapping("/edit")
    public String accountEditNow(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();
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

    @PostMapping("/editAdmNow")
    public String accountEditAdmNow(HttpServletRequest httpServletRequest, Model model, Account accountEdit)
    {
        this.httpSession = httpServletRequest.getSession();

        Account account = (Account) httpSession.getAttribute("LoggedUser");

        Account accountToUpdate = accountDao.findById(accountEdit.getAccount_id());
        accountToUpdate.setAccount_number(accountEdit.getAccount_number());
        accountToUpdate.setAccount_name(accountEdit.getAccount_name());
        accountToUpdate.setAccount_surname(accountEdit.getAccount_surname());
        accountToUpdate.setAccount_email(accountEdit.getAccount_email());
        accountToUpdate.setAccount_phone(accountEdit.getAccount_phone());
        accountToUpdate.setAccount_password_blk(accountEdit.getAccount_password_blk());
        accountToUpdate.setAccount_strong_auth(accountEdit.getAccount_strong_auth());

        accountDao.update(accountToUpdate);

        model.addAttribute("account", account);
        model.addAttribute("Message", "User " + accountEdit.getAccount_number() + "has been updated!");

        return "redirect:/account/listAll";
    }

    @GetMapping("/editpassword")
    public String accountEditPassword(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();

        model.addAttribute("account", httpSession.getAttribute("LoggedUser"));

        return "edit-password";
    }

    @GetMapping("/editpasswordAdm/{accountNumber}")
    public String accountEditPasswordAdm(HttpServletRequest httpServletRequest, Model model, @PathVariable("accountNumber") Long accountNumber)
    {
        this.httpSession = httpServletRequest.getSession();

        model.addAttribute("account", httpSession.getAttribute("LoggedUser"));
        model.addAttribute("accountNumber", accountNumber);

        return "edit-password-adm";
    }

    @PostMapping("/editpassword")
    public String accountEditPasswordNow(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();
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

    @PostMapping("/editpasswordAdm")
    public String accountEditPasswordAdmNow(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");
        Log log = new Log();

        String password = httpServletRequest.getParameter("accountPassword");
        String password_r = httpServletRequest.getParameter("accountPasswordR");
        String account_id = httpServletRequest.getParameter("accountID");

        if(!(password == null) && !(password_r == null))
        {
            if(password.equals(password_r))
            {
                Account accountEdit = accountDao.findByNumber(Long.parseLong(account_id));
                accountEdit.setAccount_password(password);

                model.addAttribute("Message","Password has been changed!");

                accountDao.update(accountEdit);

                log.setLog_msg("Password has been changed by !" + account.getAccount_number());
                log.setLog_date(LocalDate.now().toString());
                log.setAccount(accountEdit);

                logDao.save(log);

            } else {
                model.addAttribute("Message","Error: Passwords are different!");
            }
        } else {
            model.addAttribute("Message","Error: Password field can't be empty!");
        }

        model.addAttribute("account", account);

        return "edit-password-adm";
    }
}
