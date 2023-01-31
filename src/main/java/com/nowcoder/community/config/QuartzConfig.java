package com.nowcoder.community.config;

import com.nowcoder.community.quartz.AlphaJob;
import com.nowcoder.community.quartz.PostScoreRefreshJob;
import com.nowcoder.community.quartz.WKImageDeleteJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2023-01-19 00:21
 **/
//此配置的作用是:在第一次初始化时将配置读取到数据库里
//配置 -> 数据库  -> 调用
@Configuration
public class QuartzConfig {
    //FactoryBean可以简化Bean实例化的过程
    //1.将FactoryBean装配到spring容器中
    //2.将FactoryBean注入给其他的bean
    //该bean得到的是FactoryBean对象所管理的实例

    //JobDetailFactoryBean这个类底层封装了JobDetail底层详细化的过程
    //配置JobDetail
    //@Bean
    public JobDetailFactoryBean alphaJobDetail(){
        JobDetailFactoryBean factoryBean =new JobDetailFactoryBean();
        factoryBean.setJobClass(AlphaJob.class);
        factoryBean.setName("alphaJob");//任务名称
        factoryBean.setGroup("alphaJobGroup");//组名称
        factoryBean.setDurability(true);//是否长久保存,连触发器都没有了也正常保存
        factoryBean.setRequestsRecovery(true);//任务是否能够回滚
        return factoryBean;
    }
    //配置Trigger(SimpleTriggerFactoryBean,CronTriggerFactorBean)
    // @Bean
    public SimpleTriggerFactoryBean alphaTrigger(JobDetail alphaJobDetail){
        SimpleTriggerFactoryBean factoryBean=new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(alphaJobDetail);//设置要操作的Job实例
        factoryBean.setName("alphaTrigger");
        factoryBean.setGroup("alphaTriggerGroup");
        factoryBean.setRepeatInterval(3000);//频率
        factoryBean.setJobDataMap(new JobDataMap());//指定存储状态的对象
        return factoryBean;
    }

    //刷新帖子分数的任务
    @Bean
    public JobDetailFactoryBean postScoreRefreshJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(PostScoreRefreshJob.class);
        factoryBean.setName("postScoreRefreshJob");//任务名称
        factoryBean.setGroup("communityJobGroup");//组名称
        factoryBean.setDurability(true);//是否长久保存,连触发器都没有了也正常保存
        factoryBean.setRequestsRecovery(true);//任务是否能够回滚
        return factoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean postScoreRefreshTrigger(JobDetail postScoreRefreshJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(postScoreRefreshJobDetail);//设置要操作的Job实例
        factoryBean.setName("postScoreRefreshTrigger");
        factoryBean.setGroup("communityTriggerGroup");
        factoryBean.setRepeatInterval(1000 * 60 * 5);//频率
        factoryBean.setJobDataMap(new JobDataMap());//指定存储状态的对象
        return factoryBean;
    }

    // 删除WK图片任务
    @Bean
    public JobDetailFactoryBean wkImageDeleteJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(WKImageDeleteJob.class);
        factoryBean.setName("wkImageDeleteJob");
        factoryBean.setGroup("communityJobGroup");
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        return factoryBean;
    }

    // 删除WK图片触发器
    @Bean
    public SimpleTriggerFactoryBean wkImageDeleteTrigger(JobDetail wkImageDeleteJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(wkImageDeleteJobDetail);
        factoryBean.setName("wkImageDeleteTrigger");
        factoryBean.setGroup("communityTriggerGroup");
        factoryBean.setRepeatInterval(1000 * 60 * 4);
        factoryBean.setJobDataMap(new JobDataMap());
        return factoryBean;
    }
}