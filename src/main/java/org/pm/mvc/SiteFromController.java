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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/site")
public class SiteFromController {

    private final SiteDao siteDao;

    private HttpSession httpSession;

    public SiteFromController(SiteDao siteDao)
    {
        this.siteDao = siteDao;
    }

    @GetMapping("/add")
    public String siteAdd(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");

        model.addAttribute("site", new Site());
        model.addAttribute("account", account);

        return "add-site";
    }

    @PostMapping("/add")
    public String siteAddNow(HttpServletRequest httpServletRequest, Site site)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");

        site.setSite_date(LocalDate.now().toString());
        site.setAccount(account);

        siteDao.save(site);

        return "redirect:/site/listById";
    }

    @GetMapping("/listById")
    public String site(HttpServletRequest httpServletRequest, Model model)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");

        List<Site> siteList = siteDao.findSitesWithAccount(account);

        model.addAttribute("sites", siteList);
        model.addAttribute("account", account);

        return "list-sites";
    }

}
