package com.comic.auth;

import com.comic.auth.domain.Session;
import com.comic.core.domain.UserStatus;
import com.comic.core.entity.User;
import com.comic.core.mappers.UserMapper;
import com.comic.users.UserService;
import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.smallrye.jwt.build.Jwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.jwt.Claims;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

@ApplicationScoped
@JBossLog
@RequiredArgsConstructor
public class AuthService {

    private final IdentityProviderManager identityProviderManager;
    private final UserService userService;

    public Session authenticate(String email, String password) {
        SecurityIdentity identity = identityProviderManager.authenticateBlocking(
                new UsernamePasswordAuthenticationRequest(email, new PasswordCredential(password.toCharArray())));

        User user = userService.getByEmailAndStatus(identity.getPrincipal().getName(), UserStatus.ACTIVE);
        return createSession(identity.getRoles(), user);
    }

//    public void changePassword(String username, String currentPassword, String newPassword) throws AuthException {
//        try {
//            identityProviderManager.authenticateBlocking(
//                    new UsernamePasswordAuthenticationRequest(username, new PasswordCredential(currentPassword.toCharArray())));
//        } catch (AuthenticationFailedException e) {
//            throw new RuntimeException("abc");
//        }
//        userService.changePassword(username, newPassword);
//    }

    private Session createSession(Set<String> roles, User user) {
        return Session.builder()
                .authToken(Jwt.upn(user.username)
                        .groups(roles)
                        .claim(Claims.sub.name(), user.username)
                        .claim(Claims.email.name(), user.email)
                        .claim(Claims.given_name.name(), user.firstName)
                        .claim(Claims.family_name.name(), user.lastName)
                        .sign())
                .profile(UserMapper.INSTANCE.mapFromUserToUserProfile(user))
                .build();
    }

    private Set<String> getUserRoles(Set<String> userRoles) {
        return userRoles;
    }
}
