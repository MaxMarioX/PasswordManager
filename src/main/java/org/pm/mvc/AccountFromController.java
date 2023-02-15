package org.pm.mvc;

import org.pm.dao.AccountDao;
import org.pm.dao.LogDao;
import org.pm.dao.RoleDao;
import org.pm.entity.Account;
import org.pm.entity.Log;
import org.pm.entity.Role;
import org.pm.repository.AccountRepository;
import org.pm.repository.RoleRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/account")
public class AccountFromController {

    private HttpSession httpSession;

    private final AccountDao accountDao;

    private final LogDao logDao;

    private final RoleDao roleDao;

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    private SimpleMailMessage mailMessage;

    private final JavaMailSender javaMailSender;

    public AccountFromController(AccountDao accountDao, LogDao logDao, RoleDao roleDao, AccountRepository accountRepository, RoleRepository roleRepository, JavaMailSender javaMailSender)
    {
        this.accountDao = accountDao;
        this.logDao = logDao;
        this.roleDao = roleDao;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.javaMailSender = javaMailSender;

        this.mailMessage = new SimpleMailMessage();
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

        accountDao.save(account);

        Role role = roleDao.findById(2L);
        Role createdRole = roleRepository.save(role);

        Set<Role> roles = new HashSet<>();
        roles.add(createdRole);

        account.setRoleList(roles);

        accountRepository.save(account);

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
    @GetMapping("/recover")
    public String Recover()
    {
        return "recover-password";
    }
    @PostMapping("/recover")
    public String RecoverNow(HttpServletRequest httpServletRequest, Model model)
    {
        String email_address = httpServletRequest.getParameter("email");

        try {

            Account account = accountDao.findByEmail(email_address);
            String new_password = accountDao.generateNewPassword();

            account.setAccount_password(new_password);
            accountDao.update(account);

            mailMessage.setFrom("pm@mp-programs.pl");
            mailMessage.setTo(email_address);
            mailMessage.setSubject("New password");
            mailMessage.setText("New password is: " + new_password);

            javaMailSender.send(mailMessage);

            model.addAttribute("Message", "New password has been sent!");

            return "recover-password";

        } catch (NoResultException x)
        {
            model.addAttribute("Message", email_address + "There is no account with this e-mail address");

        }

        return "recover-password";
    }
}
