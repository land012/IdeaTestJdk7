package com.umbrella.demo.atomikos.mysql.dao;

import com.umbrella.demo.atomikos.mysql.vo.Disk;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by 大洲 on 15-2-27.
 */
@Repository("diskDao")
public class DiskDaoImpl implements DiskDao {
    @Resource
    private JdbcTemplate jtDiskmgmt;

    @Override
    public void save(Disk d) {
        this.jtDiskmgmt.update("INSERT INTO diskmgmt.disk(name, cid, pid, remark) VALUES(?, ?, ?, ?)",
                d.getName(),
                d.getCid(),
                d.getPid(),
                d.getRemark());
    }
}
