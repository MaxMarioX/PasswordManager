package org.pm.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;

    @Column(unique = true, nullable = false, length = 255)
    private String role_name;

    @Column(nullable = false)
    private boolean role_active;

    @Column(length = 300)
    private String role_description;

    @ManyToMany
    @JoinTable(name = "accounts_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Account> accounts = new ArrayList<>();

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_active(boolean role_active) {
        this.role_active = role_active;
    }

    public boolean getRole_active()
    {
        return role_active;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }

    public String getRole_description()
    {
        return role_description;
    }

    public void setAccounts(List<Account> accounts) { this.accounts = accounts; }

    public List<Account> getAccounts() { return accounts; }
}
