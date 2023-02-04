package org.pm.mvc;

import org.mindrot.jbcrypt.BCrypt;
import org.pm.dao.AccountDao;
import org.pm.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AccountDao accountDao;

    public AuthenticationController(AccountDao accountDao)
    {
        this.accountDao = accountDao;
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


        if(LogInLock(accountID)) {
            if (LogInAuth(accountID, password)) {
                request.setAttribute("Message", "Podane hasło jest prawidłowe");
            } else {
                request.setAttribute("Message", "Podane ID lub hasło jest nieprawidłowe!");
            }
        } else {
            request.setAttribute("Message", "Konto zablokowane!");
        }
        return "authentication";
    }

    public boolean hashPassword(String password, String hpassword)
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
