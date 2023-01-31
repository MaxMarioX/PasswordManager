package org.pm.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Date;

@Entity
@Table(name="sites")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long site_id;

    @Column(nullable = false, length = 50)
    private String site_name;

    @Column(length = 250)
    private String site_address;

    @Column(length = 50)
    private String site_login;

    @Column(length = 50)
    private String site_password;

    @Column(nullable = false)
    private String site_date;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public void setSite_id(Long site_id)
    {
        this.site_id = site_id;
    }

    public Long getSite_id()
    {
        return site_id;
    }

    public void setSite_name(String site_name)
    {
        this.site_name = site_name;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_address(String site_address)
    {
        this.site_address = site_address;
    }

    public String getSite_address() {
        return site_address;
    }

    public void setSite_login(String site_login)
    {
        this.site_login = site_login;
    }

    public String getSite_login()
    {
        return site_login;
    }

    public void setSite_password(String site_password)
    {
        this.site_password = site_password;
    }

    public String getSite_password()
    {
        return site_password;
    }

    public void setSite_date(String site_date)
    {
        this.site_date = site_date;
    }

    public String getSite_date()
    {
        return site_date;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public Account getAccount()
    {
        return account;
    }


}
