package com.comic.core.repository;

import com.comic.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class RoleRepository extends BaseRepository<Role, Long> {
    public Optional<Role> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public Set<Role> findByNameIn(Set<String> name) {
        return findIn("name", name);
    }

}
