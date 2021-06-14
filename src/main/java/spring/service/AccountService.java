package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.dao.csv.AccountDao;
import spring.model.Account;

import java.math.BigDecimal;
import java.util.*;
@Component
public class AccountService {


    private AccountDao accountDao;
    @Autowired
    public AccountService(@Qualifier("accountServiceImp") AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    public List<Account> findDeliquentAccounts() throws Exception {
        List<Account> delinquentAccounts = new ArrayList<Account>();
        List<Account> accounts = accountDao.findAll();
        Date thirtyDaysAgo = daysAgo(30);
        for (Account account : accounts) {
            boolean owesMoney = account.getBalance().compareTo(BigDecimal.ZERO) >0;
            boolean thirtyDaysLate = account.getLastPaidOn().compareTo(thirtyDaysAgo) <=0;
            if (owesMoney && thirtyDaysLate) {
                delinquentAccounts.add(account);
            }
        }
        return delinquentAccounts;
    }
    private static Date daysAgo(int days) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, -days);
        return gc.getTime();
    }
}