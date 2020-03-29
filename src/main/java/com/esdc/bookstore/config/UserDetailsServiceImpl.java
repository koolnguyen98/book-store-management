package com.esdc.bookstore.config;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.esdc.bookstore.entity.Account;
import com.esdc.bookstore.entity.Role;
import com.esdc.bookstore.repository.AccountRepository;
import com.esdc.bookstore.repository.RoleRepository;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private AccountRepository accountRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByUserName(userName);
 
        if (account == null) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
        List<Role> roles = account.getRoles();
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roles != null) {
            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(account.getUserName(), //
        		account.getPassword(), grantList);
 
        return userDetails;
    }
 
}