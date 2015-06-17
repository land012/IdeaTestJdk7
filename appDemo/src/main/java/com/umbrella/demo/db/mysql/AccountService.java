package com.umbrella.demo.db.mysql;

import com.umbrella.demo.java.lang.BooleanDemo;
import org.apache.log4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

/**
 * Created by 大洲 on 15-6-15.
 */
public class AccountService {
    private static final Logger log = Logger.getLogger(AccountService.class);
    private TransactionTemplate txTemplate;
    private AccountDao accountDao;

    public void setTxTemplate(TransactionTemplate txTemplate) {
        this.txTemplate = txTemplate;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Map<String, String> query(String sql) {
        return this.accountDao.query(sql);
    }

    public boolean insert(String sql) {
        return this.accountDao.insert(sql);
    }

    public boolean insertTow(final String sql1, final String sql2) {
        this.txTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                try {
                    accountDao.insert(sql1);
//                    if(true) {
//                        transactionStatus.setRollbackOnly();
//                        return false;
//                    }
                    accountDao.insert(sql2);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                }
                return false;
            }
        });
        return false;
    }

    public boolean update(final String sql) {
        return this.txTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                log.info("update begin");
                Boolean flag = accountDao.update(sql);
                log.info(flag);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return flag;
            }
        });
    }

    public boolean updateAccount(final int id, final String sql2) {
        Boolean flag = txTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                Map m1 = accountDao.query("select * from account t where t.id=" + id);
                int balanceOld = (Integer)m1.get("balance");
                int versionOld = (Integer)m1.get("version");
                int balanceNew = balanceOld + 10;
                int versionNew = versionOld + 1;
                log.info("balanceOld=" + balanceOld + ", versionOld=" + versionOld);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**
                 * 当开启事务时，update 操作会锁记录，直到退出事务，其它线程才能 update这条记录，所以可以使用 乐观锁的方式防止并发
                 */
//                String sql3 = "update account t set t.balance=" + balanceNew + ", t.version=" + versionNew + " where t.id=1 ";
                // 下面这句 sql 可以防并发
                String sql3 = "update account t set t.balance=" + balanceNew + ", t.version=" + versionNew + " where t.id=" + id + " and t.version=" + versionOld;
                boolean flag1 = accountDao.update(sql3);
                log.info("flag1=" + flag1);
                if(!flag1) {
                    transactionStatus.setRollbackOnly();
                    return false;
                }
                accountDao.insert(sql2);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        return flag;
    }
}
