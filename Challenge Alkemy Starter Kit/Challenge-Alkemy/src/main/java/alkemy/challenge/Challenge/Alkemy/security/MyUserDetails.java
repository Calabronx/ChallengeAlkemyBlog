package alkemy.challenge.Challenge.Alkemy.security;

import alkemy.challenge.Challenge.Alkemy.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {

    private String login;
    private String password;
    //granted auhtoritiez using Collection class with <type not known subclass from GrantedAuthority>
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    //the details from the user login and password
    public static MyUserDetails fromUserEntityToCustomUserDetails(User user) {
        MyUserDetails my = new MyUserDetails();
        my.login = user.getUsername();
        my.password = user.getPassword();
        //passing the granted authorities
        my.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getUsername()));
        return my;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
