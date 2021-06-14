package spring.mycompany;

import spring.model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.service.AccountService;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) appCtx.getBean("accountService");
        List<Account> delinquentAccounts = accountService
                .findDeliquentAccounts();

        for (Account a : delinquentAccounts) {
            System.out.println("Niesolidny to " + a.getAccountNo());
        }
    }
}