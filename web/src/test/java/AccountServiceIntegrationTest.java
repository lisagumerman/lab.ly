import com.lab.ly.AccountService;
import com.lab.ly.model.Account;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by haswell on 2/2/15.
 */
@Ignore
public class AccountServiceIntegrationTest {

    AccountService service;

    @Before
    public void setUp() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/web/rest");
        service = target.proxy(AccountService.class);
    }

    @Test
    public void ensureAccountCanBeSaved() {

        Account account = new Account();
        account.setName("new account");
        Long id = service.save(account);
        assertThat(id, is(not(nullValue())));
    }
}
