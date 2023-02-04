package org.pm.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long log_id;

    @Column(nullable = false, length = 255)
    private String log_msg;

    @Column(nullable = false)
    private String log_date;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    public Long getLog_id() {
        return log_id;
    }

    public void setLog_msg(String log_msg) {
        this.log_msg = log_msg;
    }

    public String getLog_msg() {
        return log_msg;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
