package com.comic.users;

import com.comic.common.exception.RoleException;
import com.comic.common.exception.UserException;
import com.comic.core.constants.Roles;
import com.comic.core.domain.UserStatus;
import com.comic.core.entity.Account;
import com.comic.core.entity.AccountRole;
import com.comic.core.entity.Role;
import com.comic.core.entity.User;
import com.comic.core.mappers.AccountMapper;
import com.comic.core.mappers.UserMapper;
import com.comic.core.query.Pageable;
import com.comic.core.query.PagingQuery;
import com.comic.core.repository.AccountRepository;
import com.comic.core.repository.AccountRoleRepository;
import com.comic.core.repository.RoleRepository;
import com.comic.core.repository.UserRepository;
import com.comic.security.SecurityIdentityHolder;
import com.comic.users.domain.GrantRoleRQ;
import com.comic.users.domain.RegisterRequest;
import com.comic.users.domain.UpdateProfileRequest;
import com.comic.users.domain.UserDto;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.identity.SecurityIdentity;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@RequiredArgsConstructor
public class UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;

    public Pageable<UserDto> search(PagingQuery pagingQuery) {
        return userRepository.search(pagingQuery, UserMapper.INSTANCE::mapFromUserToUserDto);
    }

    public User getById(Long id) {
        return userRepository.findByIdOptional(id).orElseThrow(UserException::userNotFound);
    }

    public User getByEmailAndStatus(String email, UserStatus status) {
        return userRepository.findByEmailAndStatus(email, status).orElseThrow(UserException::userNotFound);
    }

    @Transactional
    public UserDto createUser(RegisterRequest registerRequest) {
        validateRegisterRequest(registerRequest);
        Role role = roleRepository.findByName(Roles.USER).orElseThrow(RoleException::roleNotFound);
        createAccount(registerRequest, role);
        User user = UserMapper.INSTANCE.mapFromRegisterRequestToUser(registerRequest);
        return UserMapper.INSTANCE.mapFromUserToUserDto(userRepository.save(user));
    }

    public UserDto getCurrentLoginUser() {
        SecurityIdentity identity = SecurityIdentityHolder.getIdentity();
        User user = userRepository.findByEmailAndStatus(identity.getPrincipal().getName(), UserStatus.ACTIVE)
                .orElseThrow(UserException::userNotFound);
        return UserMapper.INSTANCE.mapFromUserToUserDto(user);
    }

    @Transactional
    public UserDto updateMyProfile(UpdateProfileRequest updateProfileRequest) {
        SecurityIdentity identity = SecurityIdentityHolder.getIdentity();
        User user = userRepository.findByEmailAndStatus(identity.getPrincipal().getName(), UserStatus.ACTIVE)
                .orElseThrow(UserException::userNotFound);
        UserMapper.INSTANCE.merge(user, updateProfileRequest);
        return UserMapper.INSTANCE.mapFromUserToUserDto(userRepository.save(user));
    }

    @Transactional
    public UserDto grantRoleForUser(Long id, GrantRoleRQ grantRoleRQ) {
        User user = userRepository.findByIdOptional(id).orElseThrow(UserException::userNotFound);
        Account account = accountRepository.findByEmail(user.getEmail()).orElseThrow(UserException::userNotFound);
        Set<Role> roles = roleRepository.findByNameIn(grantRoleRQ.getRoles());
        List<AccountRole> accountRoles = new ArrayList<>();
        accountRoleRepository.deleteByAccountId(account.getId());
        for (Role role : roles) {
            AccountRole accountRole = new AccountRole();
            accountRole.setRole(role);
            accountRole.setAccount(account);
            accountRoles.add(accountRole);
        }
        accountRoleRepository.persist(accountRoles);
        return UserMapper.INSTANCE.mapFromUserToUserDto(user);
    }

    public UserDto getUserRSById(Long id) {
        User user = userRepository.findByIdOptional(id).orElseThrow(UserException::userNotFound);
        return UserMapper.INSTANCE.mapFromUserToUserDto(user);
    }

    private void createAccount(RegisterRequest registerRequest, Role role) {
        Account account = AccountMapper.INSTANCE.mapFromRegisterRequestToAccount(registerRequest);
        account.setPassword(BcryptUtil.bcryptHash(registerRequest.getPassword()));
        accountRepository.save(account);
        AccountRole accountRole = new AccountRole();
        accountRole.setAccount(account);
        accountRole.setRole(role);
        accountRoleRepository.save(accountRole);
        accountRoleRepository.findAll();
    }

    private void validateRegisterRequest(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw UserException.emailHasBeenInUsed();
        }
    }
}
