package com.comic.core.repository;

import com.comic.core.domain.UserStatus;
import com.comic.core.entity.User;
import io.quarkus.panache.common.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository extends BaseRepository<User, Long> {
    public Optional<User> findByEmailAndStatus(String email, UserStatus status) {
        Parameters parameters = Parameters.with("email", email).and("status", status);
        return find("email = :email and status = :status", parameters).firstResultOptional();
    }
    public Boolean existsByEmail(String email) {
        return exists("email", email);
    }
}
