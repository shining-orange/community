# <font color=red>ECHO</font>社区
<div align="center">
<a href="https://www.orangejun.cn/photos/12">
    项目截图（觉得好的请给个Starred吧）
需要数据库的可以直接加Q哦！（277160461）
  </a><br>
    <img src="https://blog-dm-01.oss-cn-hangzhou.aliyuncs.com/ZNS_MX%5BA_IOLOK%25V4OZN8US.png">
</div>

#### 简要介绍

社区论坛项目

基础框架：SpringBoot，Spring + SpringMVC + MyBatis，SpringSecurity

关系型数据库：MySQL
NoSQL数据库：Redis
分布式发布-订阅消息系统：Kafka
分布式可扩展的实时搜索和分析引擎：ElasticSearch
定时任务框架：Quartz
本地缓存库：Caffeine
端点监控工具：Actuator
Html转pdf工具：wkhtmltopdf
验证码生成工具：Kaptcha

#### 主要功能

##### 导航栏模块

- 导航栏有两种展现形式
    - 用户登录或者登录凭证生效中，导航栏显示**首页**、**注册**、**登录**
    - 用户退出登录或登录凭证已失效，导航栏显示**首页**、**消息**、**用户菜单**
    - 管理员的导航栏特别显示**网站数据统计**
- 使用拦截器进行拦截
    - 检查登录凭证有效期
    - SpringSecurity授予权限
    - 请求结束后在modelAndView添加用户对象

##### 注册模块

- 提交注册数据
    - 通过表单提交注册数据
    - 服务端验证注册信息是否合法
    - 添加注册用户信息到数据表
- 发送邮箱激活码
    - 向注册用户发送邮件
        - 配置服务器邮箱信息
        - 使用Spring封装的 JavaMailSender 发送邮件
        - 使用 Thymeleaf 模板引擎 传输HTML内容给 JavaMailSender
- 点击接收到的邮件中的链接，访问服务端的激活服务
    - 激活成功，跳转到主页
    - 激活码错误，激活失败无法登录
    - 不能重复激活

##### 登录模块

- 登录
    1. 生成验证码
        - 获取验证码文本数据text，图片数据
        - 随机生成字符串kaptchaOwner保存至Cookie
        - kaptchaOwner和text作为键值对保存至Redis
        - 图片数据输出到浏览器显示
    2. 生成登录凭证，以Cookie形式发送给客户端
    3. 点击记住我，登录凭证有效期时间延长
    4. 验证登录信息
        - 验证账号和密码
        - 验证验证码
            - 获取Cookie的kaptchaOwner对应的值，与用户输入的验证码比较
    5. 登录成功后，保存登录凭证
- 忘记密码
    1. 验证邮箱，不能为空且已注册
    2. 点击获取验证码，发送到用户邮箱
        - 创建Cookie保存至浏览器和Redis中
          通过浏览器保存的key值，获取Redis保存的value值，
          再把value值与用户输入的验证码作比较
        - 验证码十分钟后过期，密码重置后过期
    3. 密码不能为空，长度小于6

##### 用户菜单模块

- 个人主页
    - 个人信息
        - 个人头像，用户名，注册时间
            - 关注用户人数
                - 点击查看当前用户关注其他用户的列表
            - 关注者人数
                - 点击查看其他用户关注当前用户的列表
            - 获得的赞的数量
                - 获得的赞来自其他用户对当前用户的帖子和评论的点赞
        - 我的帖子
            - 用户发布的所有帖子列表
        - 我的回复
            - 对帖子的回复列表
        - 关注
            - 关注其他用户
- 账号设置
    - 上传头像
        - 验证文件格式、空值
        - 生成随机文件名（不能使用原有的文件名，会导致文件名重复）
        - 头像上传至qiniu云服务器，成功后更新数据库用户表保存的头像访问地址
    - 修改密码
        - 验证空值
        - 验证原密码和修改密码是否相等
        - 验证两次输入的修改密码是否相等
- 退出
    - 用户退出登录，登录凭证修改为失效状态
    - 导航栏状态改为 首页、注册、登录

##### 帖子模块

- 发布帖子
    - 点击**我要发布**，填写**标题**和**正文**进行新帖发布
    - 登录凭证失效或退出后不能访问发帖功能
    - 帖子内容处理：转义html标签、过滤敏感词汇
- 首页帖子列表
    - 左侧包含标题、作者信息、发布时间
    - 右侧显示该帖子的点赞数量和回帖数量
    - 标题右侧显示帖子类型和帖子状态
        - 帖子类型：普通、置顶
        - 帖子状态：正常、精华、拉黑
- 帖子列表排序，分页
    - 根据帖子发布时间生成最新帖子列表
    - 根据帖子的发布时间、点赞数、评论数，
      是否加精、是否置顶得出分数，生成最热帖子列表
- 帖子详细
    - 点击主页的某个帖子可以查看帖子的具体信息
        - 标题、作者信息、发布时间、帖子正文、其他用户回帖情况
    - 分页显示其他用户回帖
        - 显示每个用户回帖的所处楼层，用户信息，回帖内容，发布时间
        - 在每个回帖下方可以点赞，回复
- 帖子操作
    - 评论
        - 评论分为**对帖子的回帖**，**对用户评论的评论**
        - 评论内容过滤敏感词汇，不能为空
    - 点赞
        - **点赞实体和点赞数量作为键值对存入redis**
        - 支持对帖子，评论点赞
        - 第一次点赞，第二次取消点赞
    - 加精，置顶，删除
        - 使用SpringSecurity进行权限控制
            - 游客，普通用户没有任何操作权限
            - 版主拥有加精，置顶权限
            - 管理员拥有删除权限

##### 消息模块

- 点击导航栏的消息，可以查看**朋友私信**和**系统通知**，右上角是前两者未读消息数量的总和
- 点击导航栏的消息，可以查看朋友私信和系统通知，右上角是前两者未读消息数量的总和
- 朋友私信
    - 私信特征
        - 每条私信包含用户头像名称信息，会话数量，会话最新时间，会话最新内容
        - 点击用户名称查看用户个人主页
        - 点击私信下的内容可以查看私信的详细内容
            - 展示发送方和接收方的所有会话内容
            - 可以删除会话内容
            - 可以直接给发送方私信
- 发送私信
    - 点击发送私信，填写发送方和私信内容进行发送
        - 私信内容被过滤敏感词
- 私信状态
    - 私信有三种状态，未读、已读、删除
        - 未读的私信数量，会显示在**朋友私信**的右上角
        - 用户查看未读的私信并返回后，该私信状态转为已读，并更新**朋友私信**的右上角
        - 用户点击私信右上角×符号，将删除私信并改变私信状态为删除
- 系统通知
    - 显示其他用户对当前用户的评论，赞，关注的情况
        - 使用消息队列Kafka来实现
            - 其他用户对当前用户的操作为生产者，当前用户为消费者

#### 其他功能

##### 权限控制

- 功能需求：针对游客、普通用户、版主、管理员，授予不同权限，拦截请求；
- 实现方法：
    - 使用SpringSecurity
        - 创建配置类
        - 添加请求地址和授予用户访问权限
        - 权限不够时的处理
            - 没有登录，通过HttpServletResponse响应提示登录信息
            - 请求为Get请求，跳转到指定页面
            - 请求为Post请求，通过HttpServletResponse响应错误信息

##### 过滤敏感词

- 功能需求：用户的帖子和评论需要过滤敏感词，并用***替换敏感词
- 实现方法：
    - 使用前缀树算法
        - 特点：查找效率高，消耗内存大
        - 应用：字符串检索、词频统计、字符串排序等
    - 实现敏感词过滤器
        - 定义前缀树私有类
        - 加载敏感词文件，初始化前缀树
        - 编写过滤敏感词的方法

##### 统一处理异常

- 功能需求：服务器发送异常时，向用户提示友好的报错信息
- 实现方法：
    - 在使用ThymeLeaf模板时，SpringBoot会自动到src/main/resources/templates/error/ 文件夹下寻找404.html、500.html的错误提示页面。 错误提示页面的命名规则就是：错误码.html，如404是404.html，500是500.html。
    - @ControllerAdvice 配合 @ExceptionHandler 实现全局异常处理
        - 异常处理步骤
            - 捕捉到Controller层异常，打印错误信息
            - 判断该请求是异步请求还是同步请求
                - 异步请求，返回JSON字符串，js进行异步处理返回报错信息
                - 同步请求即普通请求，直接重定向到500.html页面
        - 注意这里只能捕获到Controller层的异常，在service层或者其他层面的异常都不能捕获

##### 统一记录日志

- 功能需求：记录日志并避免主业务与日志耦合
- 实现方法：
    - 使用AOP面向切面编程
        - 优势：利用 AOP 可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程 序的可重用性，同时提高了开发的效率。
        - 步骤：创建切面类，定义切入点表达式和连接点，增强目标对象

##### 消息队列

- 功能需求：消费者与生产者模式。用户的点赞评论关注为生产者，产生数据；用户获取这些信息并查看，使用数据；

- 实现方法：

    - Kafka消息队列

        - 一个分布式消息发布订阅系统

        - 使用模式：

          点对点模式

          模式

            - 两种角色：发送者（生产者），接收者（消费者）
            - 运行机制： 消息发送者生产消息发送到queue中，然后消息接收者从queue中取出并且消费消息。消息被消费以后，queue中不再有存储，所以消息接收者不可能消费到已经被消费的消息

##### 社区搜索功能

- 功能需求：用户在导航栏输入关键字可以**模糊**搜索帖子，搜索到的帖子中的关键字标红
- 实现方法：
    - Elasticsearch搜索引擎
        - 一个分布式的，RestFul风格的搜索引擎
        - 支持对各种类型的数据的检索
        - 搜索速度快，可以提供**实时**的搜索服务
    - 实现步骤
        - 发布帖子时，将贴子异步地提交到Elasticsearch服务器
        - 删除帖子时，将贴子异步地在Elasticsearch服务器删除
        - 搜索帖子时，根据关键词从Elasticsearch服务器获取数据

##### 网站统计数据

- 功能需求：管理员可以查看**网站访问次数**和**用户活跃状态**
- 实现方法：
    1. 使用redis高级数据类型
        - HyperLogLog（超级日志）
            - 采用一种基数算法，用于完成独立总数（独立访客）的统计
            - 占据空间小，我无论统计多少个数据，只占有12k的内存空间
            - 不精确的统计算法，标准误差为0.81%
        - Bitmap（位图）
            - 不是一种独立的数据结构，实际上就是字符串
            - 支持按位存取数据，可以将其看成是byte数据
            - 适合存储所大量的连续的数据的布尔值（签到）
    2. 统计网站访问次数
        - UV 统计时间点网站访问次数
            - 独立访客，需要通过用户IP排重统计数据（可以统计游客，在拦截器中实现）
            - 每次访问都要进行统计
            - HyperLogLog,性能好，且存储空间小
        - 区间UV 统计时间段网站访问次数
            - 把区间内的UV相加
    3. 统计网站用户活跃状态
        - DAU 统计时间点用户活跃状态
            - 日活跃用户，需要通过用户ID排重统计数据（排除游客，需要精确）
            - 访问一次，则认为其活跃
            - Bitmap，性能好，且可以统计精确的结果
        - 区间DAU 统计时间段用户活跃状态
            - - 把区间内的DAU相加

##### 任务执行和调度

- 功能需求：网站帖子由于用户和版主操作造成分数不断更新，避免服务器时刻随着帖子分数的变化
  去更新数据库和elastic服务器并更新帖子列表排序，实现定时任务去完成。
- 实现方法：使用定时任务框架Quartz
    - Quartz的基本组成部分
        - 调度器：Scheduler
        - 任务：JobDetail
        - 触发器：Trigger，包括SimpleTrigger和CronTrigger
    - Job和JobDetail
        - Job是Quartz中的一个接口，接口下只有execute方法，在这个方法中编写**业务逻辑**
        - JobDetail绑定指定的Job，每次Scheduler调度执行一个Job的时候，首先会拿到对应的Job，
          然后创建该Job实例，再去执行Job中的execute()的内容，任务执行结束后，关联的Job对象实例会被释放，且会被JVM GC清除。
    - SimpleTrigger可以实现在一个指定时间段内执行一次作业任务或一个时间段内多次执行作业任务。

##### 生成长图

- 功能需求：生成指定页面，上传至服务器
- 实现方法：
    - wkhtmltopdf，是一个可以把HTML转换为图片和pdf的工具
        1. 配置wkhtmltopdf的图片生成路径，不存在则自动创建文件夹
        2. 发布生成长图事件，异步生成
        3. 启用定时器,监视该图片生成事件,一旦生成了,则上传至七牛云

##### 本地缓存库

- 功能需求：优化网站性能，避免数据量大造成redis缓存雪崩，创建本地缓存库
- 实现方法：
    - 使用Caffeine缓存工具，将数据库缓存在应用服务器
    - 多级缓存形成：一级缓存（本地缓存）> 二级缓存（redis分布式缓存）> 数据库（MySQl）
        - 先找本地缓存，找不到去分布式缓存找，找不到去数据库找
          最后本地缓存或Redis更新数据，接着数据返回给客户端

##### 项目监控

- 功能需求：监控服务器某个结点的状态，例如所有的bean，数据库连接
- 实现方法
    - 使用Spring Boot Actuator
        - Endpoints:监控应用的入口，Spring Boot内置了很多端点，也支持自定义端点
        - 监控方式：HTTP或JMX
        - 访问路径：例如"/actuator/health"
        - 注意事项：按需配置暴露的端点，并对所有端点进行权限控制

### 项目常见问题

- 本地连接远程服务器的redis服务失败
    - 查看配置文件的redis连接地址、端口、密码是否正确
    - 查看远程服务器redis服务是否打开、防火墙是否关闭
    - 重启redis可能会造成之前密码失效，需要重新设置密码
- @Autowired注解 和 html页面上的thymeleaf 报错标红，不影响项目正常运行
