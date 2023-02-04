package org.pm.mvc;

import org.mindrot.jbcrypt.BCrypt;
import org.pm.dao.AccountDao;
import org.pm.dao.LogDao;
import org.pm.entity.Account;
import org.pm.entity.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AccountDao accountDao;
    private final LogDao logDao;

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
    public String LogInCheck(HttpServletRequest request, HttpServletResponse response)
    {
        String accountID = request.getParameter("accountID");
        String password = request.getParameter("password");

        Log log = new Log();
        log.setLog_date(LocalDate.now().toString());

        if(LogInLock(accountID)) {
            if (LogInAuth(accountID, password)) {
                log.setLog_msg("Logowanie do konta ["+ accountID +"] - SUKCES");

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("LoggedUser",accountDao.findByNumber(Long.parseLong(accountID)));

                return "redirect:/dashboard/main";

            } else {
                request.setAttribute("Message", "Podane ID lub hasło jest nieprawidłowe!");
                log.setLog_msg("Logowanie do konta ["+ accountID +"] - BŁĘDNY LOGIN LUB HASŁO");
            }
        } else {
            request.setAttribute("Message", "Konto zablokowane!");
            log.setLog_msg("Logowanie do konta ["+ accountID +"] - KONTO ZABLOKOWANWE");
        }

        logDao.save(log);

        return "authentication";
    }

    private boolean hashPassword(String password, String hpassword)
    {
        return BCrypt.checkpw(password, hpassword);
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
