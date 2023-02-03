package org.pm.mvc;

import org.pm.dao.AccountDao;
import org.pm.dao.SiteDao;
import org.pm.entity.Account;
import org.pm.entity.Site;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/test/site")
public class SiteFromController {

    private final SiteDao siteDao;

    private final AccountDao accountDao;

    public SiteFromController(SiteDao siteDao, AccountDao accountDao)
    {
        this.siteDao = siteDao;
        this.accountDao = accountDao;
    }

    @GetMapping("/add")
    public String siteAdd(Model model)
    {
        model.addAttribute("site", new Site());
        return "add-site";
    }

    @PostMapping("/add")
    public String siteAddNow(Site site)
    {
        site.setSite_date(LocalDate.now().toString());
        site.setAccount(accountDao.findById(1L));
        siteDao.save(site);

        return "ok";
    }

    @GetMapping("/listAll")
    public String siteList(Model model)
    {
        model.addAttribute("sites", siteDao.findSitesWithAccount(accountDao.findById(1L)));

        return "list-sites";
    }

}
