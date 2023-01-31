package com.nowcoder.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-14 01:03
 **/

@Document(indexName = "discusspost", type = "_doc", shards = 6, replicas = 3)
@Data
public class DiscussPost {
    /*private int id;
    private int userId;//发帖用户id
    private String title;//标题
    private String content;//帖子内容
    private int type;//类型  0-普通; 1-置顶;
    private int status;//状态 0-正常; 1-精华; 2-拉黑;
    private Date createTime;//发帖时间
    private int commentCount;//评论数量
    private double score;//得分*/

    @Id
    private int id;

    @Field(type = FieldType.Integer)
    private int userId;

    // 互联网开发

    //文本的类型     存储的时候的解析器(最多的单词的分词器)       搜索的时候的解析器(ik_smart聪明的分词器 洞察分词器)
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content;

    @Field(type = FieldType.Integer)
    private int type;

    @Field(type = FieldType.Integer)
    private int status;

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Integer)
    private int commentCount;

    @Field(type = FieldType.Double)
    private double score;
}