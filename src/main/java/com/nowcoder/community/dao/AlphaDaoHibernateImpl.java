package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-13 00:48
 **/
@Repository("alphaHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao {
    public String select() {
        return "Hibernate";
    }
}