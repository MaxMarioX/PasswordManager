package org.pm.entity;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long account_id;

    @Column(nullable = false, unique = true)
    private Long account_number;

    @Column(length = 100, nullable = false)
    private String account_name;

    @Column(length = 100, nullable = false)
    private String account_surname;

    @Column(length = 150, unique = true)
    private String account_email;

    @Column(length = 12)
    private String account_phone;

    @Column(length = 255, nullable = false)
    private String account_password;

    @Column(nullable = false)
    private boolean account_password_blk;

    @Column(nullable = false)
    private boolean account_strong_auth;

    @OneToMany(mappedBy = "account")
    private List<Site> siteList;

    @OneToMany(mappedBy = "account")
    private List<Log> logList;

    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
    private List<Role> roleList;

    public String hashPassword(String password)
    {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public void setAccount_id(Long account_id)
    {
        this.account_id = account_id;
    }

    public Long getAccount_id()
    {
        return account_id;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public Long getAccount_number()
    {
        return account_number;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_name()
    {
        return account_name;
    }

    public void setAccount_surname(String account_surname) {
        this.account_surname = account_surname;
    }

    public String getAccount_surname()
    {
        return account_surname;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAccount_email()
    {
        return account_email;
    }

    public void setAccount_phone(String account_phone) {
        this.account_phone = account_phone;
    }

    public String getAccount_phone() {
        return account_phone;
    }

    public void setAccount_password(String account_password) {
        this.account_password = hashPassword(account_password);
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password_blk(Boolean account_password_blk) {
        this.account_password_blk = account_password_blk;
    }

    public boolean getAccount_password_blk()
    {
        return account_password_blk;
    }

    public void setAccount_strong_auth(Boolean account_strong_auth) {
        this.account_strong_auth = account_strong_auth;
    }

    public boolean getAccount_strong_auth()
    {
        return account_strong_auth;
    }

    public void setSiteList(List<Site> siteList)
    {
        this.siteList = siteList;
    }

    public List<Site> getSiteList()
    {
        return siteList;
    }

    public void setLogList(List<Log> logList)
    {
        this.logList = logList;
    }

    public List<Log> getLogList()
    {
        return logList;
    }
    public void setRoleList(List<Role> roleList)
    {
        this.roleList = roleList;
    }

    public List<Role> getRoleList()
    {
        return roleList;
    }
}
