package com.example.finalexamlibraryvar1kalzhigitovnurbol.service;



import com.example.finalexamlibraryvar1kalzhigitovnurbol.models.User;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.UserDetailsImpl;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.repository.UserRepository;
import com.example.finalexamlibraryvar1kalzhigitovnurbol.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private LoginAttemptService loginAttemptService;
    @Autowired
    public void setLoginAttemptService(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    private HttpServletRequest request;
    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
