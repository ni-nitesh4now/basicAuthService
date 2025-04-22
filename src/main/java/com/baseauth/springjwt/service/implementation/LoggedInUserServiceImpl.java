package com.baseauth.springjwt.service.implementation;

import com.baseauth.springjwt.entity.Credentials;
import com.baseauth.springjwt.payload.enums.UserType;
import com.baseauth.springjwt.repository.CustomerRepository;
import com.baseauth.springjwt.repository.UserProfileRepository;
import com.baseauth.springjwt.service.CredentialsService;
import com.baseauth.springjwt.service.LoggedInUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggedInUserServiceImpl implements LoggedInUserService {
    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Object getLoggedInUser() {
        Credentials loggedInUser = credentialsService.getLoggedInUser();
        if (loggedInUser.getUserType() == UserType.INTERNAL) {
            return userProfileRepository.findByCredentials(loggedInUser)
                    .orElseThrow(() -> new RuntimeException("User profile not found"));
        } else if (loggedInUser.getUserType() == UserType.CLIENT) {
            return customerRepository.findByCredentials(loggedInUser)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
        }
        return null;
    }
}
