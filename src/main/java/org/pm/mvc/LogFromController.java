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

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("test/log")
public class LogFromController {

    private final LogDao logDao;
    private final AccountDao accountDao;

    public LogFromController(LogDao logDao, AccountDao accountDao)
    {
        this.logDao = logDao;
        this.accountDao = accountDao;
    }

    @GetMapping("/add")
    public String logAdd(Log log, Account account, @RequestParam String message)
    {
        account = accountDao.findById(2L);

        log.setLog_msg(message);
        log.setLog_date(LocalDate.now().toString());
        log.setAccount(account);

        logDao.save(log);

        return "ok";
    }

    @GetMapping("/listAll")
    public String logListAll(Model model)
    {
        model.addAttribute("logs",logDao.findAll());

        return "list-logs";
    }

    @GetMapping("/listById")
    public String logListById(Model model, Account account)
    {
        account = accountDao.findById(1L);

        model.addAttribute("logs",logDao.findLogsWithAccount(account));

        return "list-logs";
    }
}
