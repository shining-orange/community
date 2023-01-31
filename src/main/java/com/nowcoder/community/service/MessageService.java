package com.nowcoder.community.service;

import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.entity.Message;
import com.nowcoder.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-28 11:40
 **/

@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    //注入过滤敏感词工具
    @Autowired
    private SensitiveFilter sensitiveFilter;

    //查询会话
    public List<Message> findConversations(int userId,int offset,int limit){
       return messageMapper.selectConversations(userId,offset,limit);
    }

    // 查询当前用户的会话数量.
    public int findConversationCount(int userId){
        return messageMapper.selectConversationCount(userId);
    }

    // 查询某个会话所包含的私信列表.
    public List<Message> findLetters(String conversationId,int offset,int limit){
        return messageMapper.selectLetters(conversationId,offset,limit);
    }

    // 查询某个会话所包含的私信数量.
    public int findLetterCount(String conversationId){
        return messageMapper.selectLetterCount(conversationId);
    }

    // 查询未读私信的数量
    public int findLetterUnreadCount(int userId,String conversationId){
        return messageMapper.selectLetterUnreadCount(userId,conversationId);
    }
    //添加一条消息
    public int addMessage(Message message){
        message.setContent(HtmlUtils.htmlEscape(message.getContent()));
        message.setContent(sensitiveFilter.filter(message.getContent()));
        return messageMapper.insertMessage(message);
    }

    //将消息变成已读消息
    // 将集合中的多条消息的状态变为已读
    public int readMessage(List<Integer> ids) {
        return messageMapper.updateStatus(ids, 1);
    }

    //删除私信
    public int deleteMessage(int id) {
        return messageMapper.updateStatus(Arrays.asList(new Integer[]{id}), 2);
    }

    //查询最新的通知
    public Message findLatestNotice(int userId,String topic){
        return messageMapper.selectLatestNotice(userId,topic);
    }
    public int findNoticeCount(int userId,String topic){
        return messageMapper.selectNoticeCount(userId,topic);
    }

    public int findNoticeUnreadCount(int userId,String topic){
        return messageMapper.selectNoticeUnreadCount(userId,topic);
    }
    public List<Message> findNotices(int userId, String topic, int offset, int limit){
        return messageMapper.selectNotices(userId,topic,offset,limit);
    }

}