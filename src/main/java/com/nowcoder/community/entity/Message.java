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
 * @create: 2022-12-28 10:32
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    private int id;
    private int fromId;//发消息id
    private int toId;//收消息id
    private String conversationId;//会话id
    private String content;//内容
    private int status;//评论状态  0-未读  1-已读  2-删除
    private Date createTime;// 评论时间
}