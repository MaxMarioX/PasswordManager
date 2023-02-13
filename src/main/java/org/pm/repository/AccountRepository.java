package org.pm.repository;

import org.pm.entity.Account;
import org.pm.entity.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.RoleList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

}
