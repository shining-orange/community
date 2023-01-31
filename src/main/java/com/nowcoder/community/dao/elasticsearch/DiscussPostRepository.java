package com.nowcoder.community.dao.elasticsearch;

import com.nowcoder.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

//@Mapper mybatis独有的
@Repository
//@Field(type = FieldType.Date,format = DateFormat.basic_date_time)
//spring提供的针对数据访问层的注解
//声明实体类型和主键的类型
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost,Integer> {

}
