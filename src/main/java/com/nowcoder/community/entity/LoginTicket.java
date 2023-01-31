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
 * @create: 2022-12-22 15:18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginTicket {

    private int id;
    private int userId;//用户id
    private String ticket;//登录凭证
    private int status;//登录状态  0-有效; 1-无效;
    private Date expired;//过期时间
}