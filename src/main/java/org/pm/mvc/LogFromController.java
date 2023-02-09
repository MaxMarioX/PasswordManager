package org.pm.mvc;

import org.hibernate.annotations.Parameter;
import org.pm.dao.AccountDao;
import org.pm.dao.LogDao;
import org.pm.entity.Account;
import org.pm.entity.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("log")
public class LogFromController {

    private final LogDao logDao;
    private final AccountDao accountDao;

    private HttpSession httpSession;

    public LogFromController(LogDao logDao, AccountDao accountDao)
    {
        this.logDao = logDao;
        this.accountDao = accountDao;
    }

    @GetMapping("/listAll")
    public String logListAll(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");

        model.addAttribute("logs",logDao.findAll());
        model.addAttribute("account", account);

        return "list-logs";
    }

    @GetMapping("/listAllAdm")
    public String logListAdmAll(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");

        model.addAttribute("logs", logDao.findAllAdm());
        model.addAttribute("account", account);

        return "list-logs-adm";
    }

    @GetMapping("/listById")
    public String logListById(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");

        model.addAttribute("logs",logDao.findLogsWithAccount(account));
        model.addAttribute("account", account);

        return "list-logs";
    }
}
