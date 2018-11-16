package org.sergei.rest.service;

import org.sergei.rest.model.User;
import org.sergei.rest.model.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Sergei Visotsky, 2018
 * <pre>
 * This class creates a set of GrantedAuthority instances that represent roles that the user has in the system.
 * </pre>
 */
public class ApiUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;

    ApiUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = translateRoles(user.getUserRoles());
    }

    /**
     * Method to set 'ROLE_' prefix for each role for a particular user taken from the database
     *
     * @param userRoles Takes a list of user roles from constructor as a parameter
     * @return a collection of authorities
     */
    private Collection<? extends GrantedAuthority> translateRoles(List<UserRoles> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRoles role : userRoles) {
            String name = role.getRoleName().toUpperCase();
            if (!name.startsWith("ROLE_")) {
                name = "ROLE_" + name;
            }
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
