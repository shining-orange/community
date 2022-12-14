package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-13 00:48
 **/
@Repository
@Primary//优先级最高
public class AlphaDaoMybatisImpl implements AlphaDao {
    public String select() {
        return "Mybatis";
    }
}