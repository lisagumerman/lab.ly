package com.lab.ly.service;

import com.lab.ly.model.internal.EmailSignup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 * Created by haswell on 2/7/15.
 */
@Service
@Transactional
public class DefaultEmailSignupService implements EmailSignupService {
    @PersistenceContext
    private EntityManager entityManager;

    static final String password = "stop-looking-at-my-signups!";


    @Override
    public String save(String emailAddress) {
        final EmailSignup signup = new EmailSignup();
        signup.setEmailAddress(emailAddress);
        entityManager.persist(signup);
        return "Successfully registered email address";
    }

    @Override
    public List<EmailSignup> getSignups(String password) {
        if(!DefaultEmailSignupService.password.equals(password)) {
            return Collections.emptyList();
        }
        return entityManager.createQuery(
                "select signup from EmailSignup signup",
                EmailSignup.class).getResultList();
    }
}
