package org.pm.mvc;

import org.pm.dao.AccountDao;
import org.pm.dao.SiteDao;
import org.pm.entity.Account;
import org.pm.entity.Site;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/edit/{site_id}")
    public String siteEdit(HttpServletRequest httpServletRequest, Model model, @PathVariable("site_id") Long site_id)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");
        Site site = siteDao.findSiteById(site_id);

        model.addAttribute("account", account);
        model.addAttribute("site", site);

        return "edit-site";
    }

    @PostMapping("/edit")
    public String siteEditNow(HttpServletRequest httpServletRequest, Model model, Site site)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");

        site.setAccount(account);
        site.setSite_date(LocalDate.now().toString());

        siteDao.update(site);

        model.addAttribute("account", account);
        model.addAttribute("Message","Site data has been changed!");

        return "edit-site";
    }

    @GetMapping("/remove/{site_id}")
    public String siteRemoveNow(HttpServletRequest httpServletRequest, Model model, @PathVariable("site_id") Long site_id)
    {
        this.httpSession = httpServletRequest.getSession();
        Account account = (Account) httpSession.getAttribute("LoggedUser");

        Site site = siteDao.findSiteById(site_id);
        siteDao.remove(site);

        model.addAttribute("account", account);
        model.addAttribute("Message","Site has been removed!");

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
