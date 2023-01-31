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
* @create: 2022-12-27 20:38
**/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    private int id;//Id
    private int userId;//评论id
    private int entityType;//评论类型
    private int entityId;//评论实体id
    private int targetId;//被评论目标id
    private String content;//评论内容
    private int status;//评论状态
    private Date createTime;//评论时间
}