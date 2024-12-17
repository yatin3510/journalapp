package com.LinkApp.journalapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.LinkApp.journalapp.entity.User;
import com.LinkApp.journalapp.repository.UserRepo;

@Service
public class userDetailServiceimpl implements UserDetailsService{

    @Autowired
    private UserRepo userrepo;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user=userrepo.findByUsername(username);

        if(user!=null)
        {
            UserDetails userdetials=org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getRoles().toArray(new String[0])).build();
            return userdetials;
        }
        return null;
    }
    
}
