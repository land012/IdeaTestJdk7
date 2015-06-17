package com.umbrella.demo.db.mysql;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * Created by 大洲 on 15-6-15.
 */
public class AccountDao {
    private JdbcTemplate jtTest;

    public void setJtTest(JdbcTemplate jtTest) {
        this.jtTest = jtTest;
    }

    public Map query(String sql) {
        return this.jtTest.queryForMap(sql);
    }

    public boolean insert(String sql) {
        int i = this.jtTest.update(sql);
        if(i==1) return true;
        return false;
    }

    public boolean update(String sql) {
        int i = this.jtTest.update(sql);
        if(i==1) return true;
        return false;
    }
}
