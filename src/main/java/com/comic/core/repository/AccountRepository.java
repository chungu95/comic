package com.comic.core.repository;

import com.comic.core.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class AccountRepository extends BaseRepository<Account, Long> {
    public Optional<Account> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
}
