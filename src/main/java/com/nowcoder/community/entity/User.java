package com.nowcoder.community.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-13 21:51
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private int id;
    private String username;//用户名
    private String password;//用户密码
    private String salt;//盐
    private String email;//邮箱
    private int type;//类型(普通/管理员/版主)
    private int status;//状态(激活/未激活)
    private String activationCode;//激活码
    private String headerUrl;// 头像
    private Date createTime;//注册时间
}