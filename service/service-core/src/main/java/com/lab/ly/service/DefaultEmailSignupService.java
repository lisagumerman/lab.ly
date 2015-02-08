package com.lab.ly.service;

import com.lab.ly.model.internal.EmailSignup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by haswell on 2/7/15.
 */
@Service
@Transactional
public class DefaultEmailSignupService implements EmailSignupService {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public String save(String emailAddress) {
        final EmailSignup signup = new EmailSignup();
        signup.setEmailAddress(emailAddress);
        entityManager.persist(signup);
        return "Successfully registered email address";
    }
}
