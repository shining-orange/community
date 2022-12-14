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
 * @create: 2022-12-14 01:03
 **/



@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiscussPost {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int type;
    private int status;
    private Date createTime;
    private int commentCount;
    private double score;
}