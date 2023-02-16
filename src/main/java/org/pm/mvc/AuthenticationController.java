package org.pm.mvc;

import org.mindrot.jbcrypt.BCrypt;
import org.pm.dao.AccountDao;
import org.pm.dao.LogDao;
import org.pm.entity.Account;
import org.pm.entity.Log;
import org.pm.entity.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AccountDao accountDao;
    private final LogDao logDao;

    private Log log;

    public AuthenticationController(AccountDao accountDao, LogDao logDao)
    {
        this.accountDao = accountDao;
        this.logDao = logDao;
    }

    @GetMapping("/login")
    public String LogIn()
    {
        return "authentication";
    }

    @PostMapping("/login")
    public String LogInCheck(HttpServletRequest request)
    {
        String accountID = request.getParameter("accountID");
        String password = request.getParameter("password");

        HttpSession httpSession = request.getSession();
        Account account;

        log = new Log();
        log.setLog_date(LocalDate.now().toString());

        if(LogInCheckAccount(accountID)) {
            if (LogInLock(accountID)) {

                account = accountDao.findByNumber(Long.parseLong(accountID));

                if (LogInAuth(accountID, password)) {

                    httpSession.setAttribute("LoggedUser", account);

                    log.setAccount(account);
                    log.setLog_msg("Successfully logged in");

                    logDao.save(log);

                    return "redirect:/dashboard/main";

                } else {
                    request.setAttribute("Message", "The entered ID or password is incorrect!");
                    log.setLog_msg("Log in - wrong password");
                    log.setAccount(account);
                }
            } else {
                request.setAttribute("Message", "The account has been blocked!");
                log.setLog_msg("Log in - The account has been blocked");

                account = accountDao.findByNumber(Long.parseLong(accountID));
                log.setAccount(account);
            }
        } else {
            request.setAttribute("Message", "The entered ID or password is incorrect!");
        }

        logDao.save(log);

        return "authentication";
    }

    private boolean hashPassword(String password, String hpassword)
    {
        return BCrypt.checkpw(password, hpassword);
    }

    private boolean LogInCheckAccount(String accountID)
    {
        boolean status = false;

        try {
            Account account = accountDao.findByNumber(Long.parseLong(accountID));

            if(account != null)
            {
                status = true;
            }
        } catch (NoResultException e)
        {
            log = new Log();

            log.setLog_date(LocalDate.now().toString());
            log.setLog_msg("Log in - " + e.getMessage() + " ID Account: [" + accountID + "]");

            logDao.save(log);
        }
        return status;
    }

    private boolean LogInLock(String accountID)
    {
        boolean status = false;

        Account account = accountDao.findByNumber(Long.parseLong(accountID));

        if(account != null)
        {
            if(!account.getAccount_password_blk())
            {
                status = true;
            }
        }

        return status;
    }

    private boolean LogInAuth(String accountID, String password)
    {
        boolean status = false;

        Account account = accountDao.findByNumber(Long.parseLong(accountID));

        if(account != null)
        {
            if(hashPassword(password,account.getAccount_password()))
            {
                status = true;
            }
        }

        return status;
    }
}
