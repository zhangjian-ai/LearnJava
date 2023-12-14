## 项目准备

<img src='src/main/resources/static/management01.png'>

数据库表SQL：

```sql
create table if not exists dept
(
    id          int unsigned auto_increment comment '主键ID',
    name        varchar(10) not null unique comment '部门名称',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '更新时间',
    primary key (`id`)
) comment '部门表' engine InnoDB
                charset utf8mb4
                COLLATE utf8mb4_general_ci;

insert into dept(id, name, create_time, update_time)
values (1, '学工部', now(), now()),
       (2, '教研部', now(), now()),
       (3, '咨询部', now(), now());

create table if not exists emp
(
    id          int unsigned auto_increment comment '员工ID',
    username    varchar(20)      not null unique comment '用户名',
    password    varchar(20) default '123456' comment '密码',
    name        varchar(10)      not null comment '姓名',
    gender      tinyint unsigned not null comment '性别 0 女 1 男',
    image       varchar(300) comment '头像',
    job         tinyint unsigned comment '职位：1 班主任，2 讲师，3 学工主管，4 教研主任，5 咨询师',
    entry_date  date comment '入职时间',
    dept_id     int unsigned comment '部门id',
    create_time datetime         not null comment '创建时间',
    update_time datetime         not null comment '更新时间',
    primary key (`id`)
) comment '员工表' engine InnoDB
                charset utf8mb4
                collate utf8mb4_general_ci;

insert into emp(id, username, password, name, gender, image, job, entry_date, dept_id, create_time, update_time)
values (1, 'seeker', '123456', '追逐者', 1, '1.jpg', 4, '2018-12-09', 1, now(), now()),
       (2, 'faker', '123456', '欺诈者', 0, '2.jpg', 1, '2015-08-12', 3, now(), now()),
       (3, 'fiona', '123456', '无双剑姬', 0, '3.jpg', 2, '2017-2-19', 2, now(), now()),
       (4, 'griff', '123456', '格里芬', 1, '4.jpg', 5, '2008-11-03', 1, now(), now()),
       (5, 'lucky', '123456', '赏金猎人', 0, '5.jpg', 3, '2020-06-12', 1, now(), now()),
       (6, 'xander', '123456', '胖子', 1, '6.jpg', 2, '2020-07-21', 1, now(), now()),
       (7, '段正淳', '123456', '瘸子', 1, '1.jpg', 4, '2018-12-09', 1, now(), now()),
       (8, '叶二娘', '123456', '女的', 0, '2.jpg', 1, '2015-08-12', 3, now(), now()),
       (9, '洪七公', '123456', '丐帮', 1, '3.jpg', 2, '2017-2-19', 2, now(), now()),
       (10, '乔峰', '123456', '降龙十八掌', 1, '4.jpg', 5, '2008-11-03', 1, now(), now()),
       (11, '慕容复', '123456', '小人', 1, '5.jpg', 3, '2020-06-12', 1, now(), now()),
       (12, '天山童姥', '123456', '老人', 0, '6.jpg', 2, '2020-07-21', 1, now(), now());
```



## 开发细节

编码细节：

1. 在后续的开发中，不再使用 System.out.println 打印日志。在各个类上使用 注解 @Slf4j ，为当前类提供日志打印属性 log
2. 一个完整的请求路径，应该是类上 @RequestMapping 的value属性 + 方法上@RequestMapping 的value属性。因此，对于方法上公共部分的请求路径，可以统一放在类上



## 部门管理

部门管理主要有四部分功能，均已在代码中实现，定义 DeptController、DeptService、DeptMapper 三个结构来实现具体的开发，具体参见代码。

1. 部门列表
1. 删除部门
1. 新增部门
1. 修改部门



## 员工管理

定义 EmpController、EmpService、EmpMapper 三个结构来实现具体的开发。



### 分页查询

进行条件分页查询前，先学习两个新知识。

- 请求参数默认值

  @RequestParam 注解不仅可以手动映射请求参数到形参，也可以设置参数校验（是否必填），也可以为参数设置默认值

- PageHelper 插件

    为了简化分页查询的开发工作，使用这样一个插件。

    - 添加依赖

        ```xml
                <dependency>
                    <groupId>com.github.pagehelper</groupId>
                    <artifactId>pagehelper-spring-boot-starter</artifactId>
                    <version>1.4.2</version>
                </dependency>
        ```

    - 执行查询

        ```java
            public PageInfo findAllUser(int pageNum, int pageSize) {
                // 使用 PageHelper 分页
                PageHelper.startPage(pageNum, pageSize);
                
                // 调用 Mapper 中的方法
                List userList = userMapper.findAllUser();
              	Page<UserPO> empPOPage = (Page<UserPO>)userList;
                
                // 返回结果
                return new UserVO(empPOPage.getTotal(), empPOPage.getResult());
            }
        
        ```

  另外，在分页查询时，我们往往需要返回查询数据的总数以及当前页的数据信息。因此，这里新建一个 vo 实体类，用于封装某个查询页面的数据对象。

    ```java
    package com.zhangjian.de.vo;
    
    import com.zhangjian.de.po.EmpPO;
    
    import java.util.List;
    
    /**
     * 员工分页查询页面数据类
     */
    public class EmpPageVO {
        private long total; // 员工总数
        private List<EmpPO> rows; // 当前页的员工信息列表
    }
    ```

### 批量删除

单个删除本质就是批量删除的一个特例，因此针对删除功能，实现一个批量删除即可。



### 新增员工

新增时，id自增不用传；密码有默认值不用传；创建时间和更新时间由后端自动添加。



### 修改员工

修改员工分为两步：

- 根据 id 查询出员工信息
- 将修改后的信息提交给服务端



## 文件上传

文件上传主要使用到一个 接口类型 MultipartFile，该类型用于接收前端传递的文件对象。其 参数名 和 前端文件对象的key保持一致即可。当然也可以使用 @RequestParam 映射到别的形参上。

新增一个UploadController文件：

```java
package com.zhangjian.controller;

import com.zhangjian.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file){
        log.info("文件上传");
        log.info(String.valueOf(file.getSize())); // log.info 不能传入 long 类型，要转一下
        return Result.success();
    }
}
```



### 本地存储

本地存储就是直接将文件保存在服务器磁盘中，这里仅做演示，是不推荐使用的一种方式。

```java
package com.zhangjian.controller;

import com.zhangjian.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @PostMapping( "/upload" )
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("文件上传");

        // 1. 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 2. 使用 uuid 构建保存的文件名
        int index = originalFilename.lastIndexOf('.');
        String substring = originalFilename.substring(index);

        String newFilename = UUID.randomUUID().toString() + substring;

        // 3. 将文件保存到 static/images 目录下
        File dir = new File("oa-backend/src/main/resources/static/images");

        log.info(dir.getAbsolutePath());

        if (!dir.exists()) { // 创建目录
            dir.mkdirs();
        }

        file.transferTo(new File(dir.getAbsolutePath(), newFilename));

        // 4. 其他常用方法
        log.info(String.valueOf(file.getSize())); // 获取文件大小， 单位字节
//        log.info(Arrays.toString(file.getBytes())); // 获取文件内容的数组
//        file.getInputStream();  // 获取文件内容的输入流

        return Result.success();
    }
}
```



### 对象存储

相关概念：

> SDK：Software Development Kit 的缩写，软件开发工具包。这包括 辅助软件开发的的依赖、示例代码 等。
>
> Bucket：存储空间。适用于存储对象（Object，就是文件）的容器，所有的对象都必须隶属于摸个存储空间。



对象存储是指将文件保存到专门的文件存储服务器上。在项目的实际开发中，几乎不会直接将文件保存在服务器上，这既不可靠，也不安全。现下比较流行的两种方式是 **公司自己搭建对象存储服务（webdav、minio）** 或 **使用第三方提供的云存储服务（阿里云、腾讯云 等）**。

下面演示将文件保存到阿里云OSS服务的过程：

1. 注册阿里云OSS，获取AccessKey

    ```shell
    host: http://oss-cn-chengdu.aliyuncs.com
    
    bucket: oa-backend
    
    AccessKey ID: LTAI5tLwceXsCyzpmLHv76xR
    
    AccessKey Secret: 40OBM9Ry7ZRs6DKz9H7HLAYhsr6VLD
    ```

2. 引入 Ali-OSS依赖到项目中

    ```xml
            <dependency>
                <groupId>com.aliyun.oss</groupId>
                <artifactId>aliyun-sdk-oss</artifactId>
                <version>3.15.1</version>
            </dependency>
            <dependency>
                <groupId>javax.xml.bind</groupId>
                <artifactId>jaxb-api</artifactId>
                <version>2.3.1</version>
            </dependency>
            <dependency>
                <groupId>javax.activation</groupId>
                <artifactId>activation</artifactId>
                <version>1.1.1</version>
            </dependency>
            <!-- no more than 2.3.3-->
            <dependency>
                <groupId>org.glassfish.jaxb</groupId>
                <artifactId>jaxb-runtime</artifactId>
                <version>2.3.3</version>
            </dependency>
    ```

3. 根据SDK实例文档，编写一个文件上传的工具类

    ```java
    package com.zhangjian.utils;
    
    import com.aliyun.oss.ClientException;
    import com.aliyun.oss.OSS;
    import com.aliyun.oss.OSSClientBuilder;
    import com.aliyun.oss.OSSException;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Component;
    import org.springframework.web.multipart.MultipartFile;
    
    import java.io.IOException;
    import java.util.UUID;
    
    /**
     * 文件存储类
     */
    @Slf4j
    @Component // 将文件存储类交给IOC容器
    public class FileStorage {
    
        private final String endpoint = "http://oss-cn-chengdu.aliyuncs.com";
    
        private final String bucketName = "oa-backend";
    
        private final String accessKeyId = "LTAI5tLwceXsCyzpmLHv76xR";
    
        private final String accessKeySecret = "40OBM9Ry7ZRs6DKz9H7HLAYhsr6VLD";
      
        private OSS getOSS() {
            return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        }
    
        public boolean bucketIsExist(String bucketName) {
            OSS oss = getOSS();
    
            return oss.doesBucketExist(bucketName);
        }
    
        public void createBucket(String bucketName) {
            OSS oss = getOSS();
    
            try {
                oss.createBucket(bucketName);
            } catch (OSSException | ClientException e) {
                e.printStackTrace();
            } finally {
                oss.shutdown();
            }
        }
    
        public String getBucketEndpoint(){
            StringBuilder builder = new StringBuilder(endpoint);
            builder.insert(builder.indexOf("//") + 2, bucketName + ".");
    
            return builder.toString();
        }
    
        public String upload(MultipartFile file) throws IOException {
            OSS oss = getOSS();
    
            String originalFilename = file.getOriginalFilename();
    
            int index = originalFilename.lastIndexOf('.');
            String substring = originalFilename.substring(index);
    
            String newFilename = UUID.randomUUID() + substring;
    
            try {
                oss.putObject(bucketName, newFilename, file.getInputStream());
                return getBucketEndpoint() + "/" + newFilename;
            } catch (OSSException | ClientException e) {
                e.printStackTrace();
                return null;
            } finally {
                oss.shutdown();
            }
        }
    }
    ```

4. 在UploadController中实现接口

    ```java
        @Autowired
        private FileStorage fileStorage;    
    
    		@PostMapping( "/upload/ali/oss" )
        public Result upload(MultipartFile file) throws IOException {
            log.info("上传文件到OSS");
            String url = fileStorage.upload(file);
            log.info("文件上传成功，访问地址: {}", url);
            return Result.success(url);
        }
    ```

    



## 配置文件

在前面接口开发中，实现文件上传工具类时，是直接将阿里云相关的信息硬编码在了代码中，这很明显不符合编码的规范，且很不利于后续的维护。



### 参数配置化

对于这种参数信息，通常将其放入 *.properties 配置文件中，然后使用 Properties 类加载配置文件，来获取具体的配置项。

但是，我们目前使用的是springboot项目，这些配置已经提前加载好了，只需在对应属性上使用 @Value 注解引入对应的配置即可，形式如下：

```java
// 假设 properties 中有一个配置key为 username
@Value("${username}")
public String name; // 这就将配置文件中 username 的值，给到了属性 name
```



在默认配置文件 application.properties 中增加阿里云OSS相关的配置：

> 配置名通常建议取得有意义，且有较高的辨识度

```properties
# ali oss 配置
ali.oss.endpoint=oss-cn-chengdu.aliyuncs.com
ali.oss.bucket-name=oa-backend
ali.oss.access-key-id=LTAI5tLwceXsCyzpmLHv76xR
ali.oss.access-key-secret=40OBM9Ry7ZRs6DKz9H7HLAYhsr6VLD
```



改造文件上传工具类：

```java
    // 要使用@Value来注入配置的值时，属性就不能是 final

    @Value( "${ali.oss.endpoint}" )
    private String endpoint;

    @Value( "${ali.oss.bucket-name}" )
    private String bucketName;

    @Value( "${ali.oss.access-key-id}" )
    private String accessKeyId;

    @Value( "${ali.oss.access-key-secret}" )
    private String accessKeySecret;
```



### yml/yaml 配置文件

在前面使用的 *.properties 时，可以明显的感觉到，到配置信息变多时，配置信息将变得臃肿，层级结构也不清晰。再此引入 *.yml（等价于 *.yaml） 配置文件。

下面将 applicaton.properties 转为 application.yml：

```yml
spring:
  # 数据库连接配置
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://172.16.20.64:3307/mybatis
    username: root
    password: mybatis123

  # 文件上传文件大小配置
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 100MB

# mybatis 日志输出及命名转换配置
mybatis:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    map-underscore-to-camel-case: true

# 自定义 阿里云OSS 配置
ali:
  oss:
    endpoint: oss-cn-chengdu.aliyuncs.com
    bucket-name: oa-backend
    access-key-id: LTAI5tLwceXsCyzpmLHv76xR
    access-key-secret: 40OBM9Ry7ZRs6DKz9H7HLAYhsr6VLD
```



从上面不难看出，yml 形式的配置文件，其目录结构比 properties 清晰了很多。在实际项目中通常建议使用 yml 配置。（这里将 properties 文件重命名保存）



### @ConfigurationProperties

在参数配置化小节中，我们将参数抽离到配置文件后，使用 @Value 注解依次为每个属性注入参数值。可以发现，如果参数配置变多之后，挨个的使用 @Value 注解也将成为一件很麻烦事情。



#### 实现配置类

基于此，我们因此 配置类，类中的属性名和配置的key保持一致，他们之间可以自动完成驼峰和中划线的转换。按照如下步骤来实现：

1. 定义一个配置类

    ```java
    package com.zhangjian.pojo;
    
    public class AliOSSProperties {
        private String endpoint;
        private String bucketName;
        private String accessKeyId;
        private String accessKeySecret;
    }
    ```

2. 要为属性注入配置的值，就要为配置类提供 Getter、Setter 方法。使用 lombok 的 @Data 注解

    ```java
    package com.zhangjian.pojo;
    
    import lombok.Data;
    
    @Data
    public class AliOSSProperties {
        private String endpoint;
        private String bucketName;
        private String accessKeyId;
        private String accessKeySecret;
    }
    ```

3. 想要让spring帮忙完成自动属性的注入，还需要将配置类交给IOC容器管理，使其称为bean对象

    ```java
    package com.zhangjian.pojo;
    
    import lombok.Data;
    import org.springframework.stereotype.Component;
    
    @Data
    @Component
    public class AliOSSProperties {
        private String endpoint;
        private String bucketName;
        private String accessKeyId;
        private String accessKeySecret;
    }
    ```

4. 最后，因为在配置文件中配置繁多。我们需要使用 @ConfigurationProperties 注解，为配置类指定其所归属的配置前缀

    ```java
    package com.zhangjian.pojo;
    
    import lombok.Data;
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.stereotype.Component;
    
    @Data
    @Component
    @ConfigurationProperties(prefix = "ali.oss")
    public class AliOSSProperties {
        private String endpoint;
        private String bucketName;
        private String accessKeyId;
        private String accessKeySecret;
    }
    ```



#### 改造工具类

如此一来，配置类有了。我们在工具类中注入配置类，直接使用相关配置即可。工具类改造后代码：

```java
package com.zhangjian.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.zhangjian.pojo.AliOSSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 文件存储类
 */
@Slf4j
@Component // 将文件存储类交给IOC容器
public class FileStorage {

    @Autowired
    private AliOSSProperties aliOSSProperties;


    private OSS getOSS() {
        return new OSSClientBuilder().build(aliOSSProperties.getEndpoint(), aliOSSProperties.getAccessKeyId(), aliOSSProperties.getAccessKeySecret());
    }

    public boolean bucketIsExist(String bucketName) {
        OSS oss = getOSS();
        boolean exist = oss.doesBucketExist(bucketName);
        oss.shutdown();

        return exist;
    }

    public void createBucket(String bucketName) {
        OSS oss = getOSS();

        try {
            oss.createBucket(bucketName);
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
        } finally {
            oss.shutdown();
        }
    }

    public String getBucketEndpoint() {
        StringBuilder builder = new StringBuilder(aliOSSProperties.getEndpoint());

        if (aliOSSProperties.getEndpoint().startsWith("http:")) {
            builder.insert(builder.indexOf("//") + 2, aliOSSProperties.getBucketName() + ".");
        }else {
            builder.insert(0, "http://" + aliOSSProperties.getBucketName() + ".");
        }

        return builder.toString();
    }

    public String upload(MultipartFile file) throws IOException {
        OSS oss = getOSS();

        String originalFilename = file.getOriginalFilename();

        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));

        String newFilename = UUID.randomUUID() + suffix;

        try {
            oss.putObject(aliOSSProperties.getBucketName(), newFilename, file.getInputStream());
            return getBucketEndpoint() + "/" + newFilename;
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
            return null;
        } finally {
            oss.shutdown();
        }
    }
}
```



#### 使用配置提示

当我们使用 @ConfigurationProperties 注解定义配置类后，我们可以引入一个依赖，这个依赖可以根据代码中定义的配置类，在我们编辑配置文件时，给出像系统配置一样的提示，方便我们维护配置文件。

依赖信息：

```xml
        <!--自定义配置提示-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
        </dependency>
```



引入配置后，再次编辑配置文件，就可以得到相应的提示：

<img src='src/main/resources/static/management02.png'>



## 登录管理

在前面的开发中，我们已经分别定义了 DeptController、EmpController、UploadController 三个控制类，他们都不适合直接用来创建登录相关的接口，因此对于登录再单独创建一个 LoginController。

登录本质上就是员工的操作，通过账号密码检查某个员工是否存在，因此，service 和 mapper 都可以直接使用 EmpService 和 EmpMapper。

<img src='src/main/resources/static/management03.png'>



### 登录接口

controller定义：

```java
package com.zhangjian.controller;

import com.zhangjian.de.po.EmpPO;
import com.zhangjian.pojo.Result;
import com.zhangjian.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping( "/login" )
    public Result login(EmpPO empPO) {
        log.info("用户 {} 登录", empPO.getUsername());

        EmpPO emp = empService.login(empPO);

        // 如果查到用户表示登录成功；否则表示登录失败
        return emp == null ? Result.fail("用户名或密码错误") : Result.success("登录成功");
    }
}
```

其它代码已在项目中实现，此处不再展示。



### 登录校验

#### 会话技术

**会话：** 用户打开浏览器，访问web服务器资源，会话建立，直到有一方断开连接，会话结束。再一次绘画中可以包含 多次 请求和响应。

**会话跟踪：** 一种维护浏览器状态的方法，服务器需要识别多次请求是否来自于同一浏览器，以便在同一次会话的多次请求间 共享数据。

**会话跟踪方案：**

- 客户端会话跟踪技术：Cookie
- 服务端会话跟踪技术：Session
- 令牌技术



**会话跟踪技术方案对比**

1. Cookie

    Cookie 方案的实现是通过在首次访问服务器时，服务器通过 Set-Cookie将信息自动保存到浏览器。浏览器下次访问时，会自动带上设置的Cookie信息，以证明当前请求来自哪里。 

    <img src='src/main/resources/static/management04.png'>

    演示代码：

    ```java
    package com.zhangjian.controller;
    
    import com.zhangjian.pojo.Result;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;
    
    import javax.servlet.http.Cookie;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    @Slf4j
    @RestController
    public class CookieController {
    
        /**
         * 设置Cookie
         * @param response
         * @return
         */
        @GetMapping("/c1")
        public Result cookie1(HttpServletResponse response){
            response.addCookie(new Cookie("login_user", "tom"));
            return Result.success();
        }
    
        /**
         * 获取cookie
         * @param request
         * @return
         */
        @GetMapping("/c2")
        public Result cookie2(HttpServletRequest request){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
            return Result.success();
        }
    }
    ```

2. Seesion

    Session会话保持，本质上还是依赖Cookie来实现的。Session会话信息保存在服务端，在服务端设置Session后，服务端以Set-Cookie的方式将SessionID保存到客户端，客户端下次访问时，带上SessionID，服务端就可以知道这个请求属于哪个会话了。

    <img src='src/main/resources/static/management05.png'>

    演示代码：

    ```java
    package com.zhangjian.controller;
    
    import com.zhangjian.pojo.Result;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;
    
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpSession;
    
    @Slf4j
    @RestController
    public class SessionController {
    
        /**
         * 通过 HttpSession 获取session对象
         * @return
         */
        @GetMapping("/s1")
        public Result session01(HttpSession session){
            // 同一个浏览器访问服务时，session 同上是一样的
            log.info("s1 session: " + session.hashCode());
    
            // 在session中设置值
            session.setAttribute("queen", "jane");
    
            return Result.success();
        }
    
        /**
         * 通过 HttpServletRequest 也可以获取到session对象
         * @param request
         * @return
         */
        @GetMapping("/s2")
        public Result session02(HttpServletRequest request){
            // session 的 hashCode 和上面的接口应该是一样的
            HttpSession session = request.getSession();
    
            log.info("s2 session: " + session.hashCode());
    
            // 获取session的值
            Object queen = session.getAttribute("queen");
            log.info(queen.toString());
    
            return Result.success();
        }
    }
    ```

3. 令牌技术

    令牌技术需要前后端配合实现，将认证信息等发送到客户端，客户端每次访问都带上令牌。然后服务端通过对令牌解析验证，来判断请求是否有效，以及属于哪一个会话。

    <img src='src/main/resources/static/management06.png'>



#### 令牌技术(JWT)

- JWT介绍

    <img src='src/main/resources/static/management07.png'>

    JWT 令牌的 Header 和 Payload 部分，就是将原json数据进行 Base64编码后得到的字符串。而数字签名部分，是根据Header中的加密算法，结合秘钥和Payload得到的数字签名。

    

    <img src='src/main/resources/static/management08.png'>

- JWT生成和校验

    > - JWT在校验时所使用的签名秘钥必须是和生成令牌时的秘钥是配套的
    > - 如果JWT令牌在解析校验时报错，则说明JWT令牌被篡改 或 过期了，令牌非法

    1. 引入依赖

        ```xml
            <!--jwt-->
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt</artifactId>
                <version>0.9.1</version>
            </dependency>
        ```

    2. jwt生成和校验代码演示

        ```java
        package com.zhangjian;
        
        import io.jsonwebtoken.Claims;
        import io.jsonwebtoken.Jwts;
        import io.jsonwebtoken.SignatureAlgorithm;
        import org.junit.jupiter.api.Test;
        import org.springframework.boot.test.context.SpringBootTest;
        
        import java.util.Date;
        import java.util.HashMap;
        
        // 测试内容与 spring无关时，为了更快的执行测试，可以注释掉 spring默认的测试注解
        //@SpringBootTest
        class OaBackendApplicationTests {
        
        
            @Test
            void contextLoads() {
            }
        
            @Test
            void generateJwt(){
                // payload
                HashMap<String, Object> payload = new HashMap<>();
                payload.put("name", "jack");
                payload.put("province", "dekesasi");
        
                String jwt = Jwts.builder()
                        .addClaims(payload) // 添加载荷
                        .signWith(SignatureAlgorithm.HS256, "secretKey") // 设置 加密算法 和 秘钥
                        .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间为 1 小时
                        .compact();
        
                System.out.println(jwt); // eyJhbGciOiJIUzI1NiJ9.eyJwcm92aW5jZSI6ImRla2VzYXNpIiwibmFtZSI6ImphY2siLCJleHAiOjE3MDAxNTM0NjB9.02D-A7QBUJtd6C9lSR3mo7MEyiRINutdSCfNKPnwwh8
        
            }
        
            @Test
            void parseJwt(){
                String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJwcm92aW5jZSI6ImRla2VzYXNpIiwibmFtZSI6ImphY2siLCJleHAiOjE3MDAxNTM0NjB9.02D-A7QBUJtd6C9lSR3mo7MEyiRINutdSCfNKPnwwh8";
                Claims claims = Jwts.parser()
                        .setSigningKey("secretKey")
                        .parseClaimsJws(jwt)
                        .getBody();
                System.out.println(claims);
            }
        
        }
        ```
    
        

#### 登录接口生成JWT

1. 将前面JWT代码编写成一个工具类

    ```java
    package com.zhangjian.utils;
    
    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.SignatureAlgorithm;
    
    import java.util.Date;
    import java.util.HashMap;
    
    public class JwtUtils {
    
        private static String secretKey = "your_secret";
        private static long expire = 3600 * 1000L;
    
        /**
         * 生成 JWT
         * @param payload
         * @return
         */
        public String generateJwt(HashMap<String, Object> payload) {
            return Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .addClaims(payload)
                    .setExpiration(new Date(System.currentTimeMillis() + expire))
                    .compact();
        }
    
        /**
         * 解析JWT
         * @param jwt
         * @return
         */
        public Claims parseJwt(String jwt) {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();
        }
    }
    ```

2. 改写 LoginController

    ```java
    package com.zhangjian.controller;
    
    import com.zhangjian.de.po.EmpPO;
    import com.zhangjian.pojo.Result;
    import com.zhangjian.service.EmpService;
    import com.zhangjian.utils.JwtUtils;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
    
    import java.util.HashMap;
    
    @Slf4j
    @RestController
    public class LoginController {
    
        @Autowired
        private EmpService empService;
    
        @PostMapping("/login")
        public Result login(@RequestBody EmpPO empPO){
            log.info("用户 {} 登录", empPO.getUsername());
    
            EmpPO emp = empService.login(empPO);
    
            // 登录成功则生成JWT返回
            if (emp != null){
                // 准备payload
                HashMap<String, Object> payload = new HashMap<>();
                payload.put("id", emp.getId());
                payload.put("name", emp.getName());
                payload.put("username", emp.getUsername());
    
                String jwt = JwtUtils.generateJwt(payload);
                
                return Result.success(jwt);
            }
    
            // 如果查到用户表示登录成功；否则表示登录失败
            return Result.fail("用户名或密码错误");
        }
    }
    ```

    

## 过滤器 Filter

<img src='src/main/resources/static/filter01.png'>

### 快速入门

<img src='src/main/resources/static/filter02.png'>

自定义filter：

```java
package com.zhangjian.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*") // 过滤的请求路径。此处表示所有请求
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化 filter"); // 仅在程序启动时调用一次
    }

    /**
     * 只有这个方法必须实现。其他两个是 default 方法，不常用
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 放行前的操作，通常用于处理 request 相关内容，比如登录校验、访问权限验证等
        log.info("放行前的操作");

        // 放行。让请求访问后端资源
        filterChain.doFilter(servletRequest, servletResponse);

        // 放行后的操作。通常用于处理 response 相关内容，比如 响应改写等等
        log.info("放行后的操作");
    }

    @Override
    public void destroy() {
        log.info("销毁 filter"); // 仅在程序退出时调用一次
    }
}
```

启动类上添加Servlet组件扫描注解：

```java
package com.zhangjian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class OaBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(OaBackendApplication.class, args);
    }
}
```



### 详细介绍

1. 执行流程

    <img src='src/main/resources/static/filter03.png'>

2. 拦截路径

    <img src='src/main/resources/static/filter05.png'>

3. 过滤器链

    <img src='src/main/resources/static/filter04.png'>

### 登录校验-Filter

<img src='src/main/resources/static/filter06.png'>

登录校验过滤器实现：

> 此处使用到 fastjson 依赖，用于将对象转换为 json串
>
> ```xml
>         <!--fastjson-->
>         <dependency>
>             <groupId>com.alibaba</groupId>
>             <artifactId>fastjson</artifactId>
>             <version>1.2.83</version>
>         </dependency>
> ```

```java
package com.zhangjian.filter;

import com.alibaba.fastjson.JSONObject;
import com.zhangjian.pojo.Result;
import com.zhangjian.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*") 
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 请求和相应类型强转，向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 回去请求url
        String uri = request.getRequestURI();
        log.info("请求后端资源: {}", uri);

        // 2.判断是否是登录接口，如果是直接放行
        if (uri.contains("login")){
            log.info("用户登录");
            filterChain.doFilter(request, response);
            return;
        }

        // 3. 获取请求头中的jwt令牌（token字段）
        String jwt = request.getHeader("token");

        // 4. 判断令牌是否存在不存在则返回错误响应
        if (!StringUtils.hasLength(jwt)){
            log.error("token 信息为空");
            Result err = Result.builder(-1, "INVALID_TOKEN", null);

            // 在controller中，因为 Controller 注解的存在，自动帮我们将Result对象，转成了 JSON串
            // 然而在 Filter 中，就需要我们自己转换并写入到响应中
            String noLogin = JSONObject.toJSONString(err);
            response.getWriter().write(noLogin);
            return;
        }

        // 5. 判断令牌是否合法，不合法也返回错误响应
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            e.printStackTrace();

            log.error("token 校验失败");
            Result err = Result.builder(-1, "INVALID_TOKEN", null);
            String noLogin = JSONObject.toJSONString(err);
            response.getWriter().write(noLogin);
            return;
        }

        // 6. 如果校验通过，则放行
        filterChain.doFilter(request, response);
    }
}
```



## 拦截器 Interceptor

<img src='src/main/resources/static/interceptor01.png'>

 

### 快速入门

拦截器的所有方法，在每次拦截到请求时，都会调用一次。

<img src='src/main/resources/static/interceptor02.png'>

 实现拦截器：

```java
package com.zhangjian.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class DemoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("资源访问前的操作~");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("资源访问后的操作~");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("视图渲染后的操作~ 最后执行");
    }
}
```

注册拦截器：

```java
package com.zhangjian.config;

import com.zhangjian.interceptor.DemoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private DemoInterceptor demoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
    }
}
```



### 详细介绍

<img src='src/main/resources/static/interceptor03.png'>

<img src='src/main/resources/static/interceptor04.png'>



### 登录校验-Interceptor

定义拦截器：

```java
package com.zhangjian.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zhangjian.pojo.Result;
import com.zhangjian.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    /**
     * 登录校验重写这一个方法即可
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求url
        String uri = request.getRequestURI();
        log.info("请求后端资源: {}", uri);

        // 2.判断是否是登录接口。拦截器配置时直接配置排除 /login 不拦截即可

        // 3. 获取请求头中的jwt令牌（token字段）
        String jwt = request.getHeader("token");

        // 4. 判断令牌是否存在不存在则返回错误响应
        if (!StringUtils.hasLength(jwt)){
            log.error("token 信息为空");
            Result err = Result.builder(-1, "INVALID_TOKEN", null);

            // 在controller中，因为 Controller 注解的存在，自动帮我们将Result对象，转成了 JSON串
            // 然而在 Interceptor 中，就需要我们自己转换并写入到响应中
            String noLogin = JSONObject.toJSONString(err);
            response.getWriter().write(noLogin);
            return false;
        }

        // 5. 判断令牌是否合法，不合法也返回错误响应
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            e.printStackTrace();

            log.error("token 校验失败");
            Result err = Result.builder(-1, "INVALID_TOKEN", null);
            String noLogin = JSONObject.toJSONString(err);
            response.getWriter().write(noLogin);
            return false;
        }

        // 6. 如果校验通过，则放行
        return true;
    }
}
```

配置拦截器：

```java
package com.zhangjian.config;

import com.zhangjian.interceptor.DemoInterceptor;
import com.zhangjian.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private DemoInterceptor demoInterceptor;

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
```



## 异常处理

在此之前，我们的代码中几乎没有对异常进行过处理。如果一旦程序出错，异常将会被框架捕获，然后返回错误信息到前端。出现这样的情况时，由于框架返回的数据结构并不是我们统一的Result，导致前端将无法正常处理响应结果。

基于此，我们通常需要手动的处理一下全局的异常。

<img src='src/main/resources/static/exception01.png'>

全局异常处理器实现：

```java
package com.zhangjian.exception;

import com.zhangjian.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 捕获的异常类型。这里捕获所有 Exception
    public Result ex(Exception exception){
        exception.printStackTrace();
        return Result.builder(-1, exception.getMessage(), "系统异常");
    }
}
```



## 事务管理

<img src='src/main/resources/static/transaction01.png'>

spring 事务管理，主要使用 @Transactional 注解来自动开启、提交 或 回滚 事务。



### 事务入门

现在实现一个需求，删除部门的同时，删除部门下的所有员工。

1. EmpMapper 实现根据部门id删除员工的方法

    ```java
        /**
         * 根据部门id删除员工
         * @param deptId
         */
        @Delete("delete from emp where dept_id = #{deptId}")
        void deleteByDeptId(Integer deptId);
    ```

2. DeptService 中注入 empMapper

    ```java
        @Autowired
        private EmpMapper empMapper;
    ```

3. 在DeptService-delete 方法中，补全删除员工的逻辑

    ```java
    @Override
    public void delete(Integer id) {
        // 删除部门
        deptMapper.deleteById(id);
    
        // 删除员工
        empMapper.deleteByDeptId(id);
    }
    ```

此时，在无异常的情况下，可以正常删除部门和与其相关的员工信息了。

但是，如果在删除部门和删除员工之间出现了异常，那么就会导致，仅删除了部门，而员工信息还在的情况。这是 事务的作用就显示出来了，delete 方法改造如下：

```java
    @Transactional
    @Override
    public void delete(Integer id) {
        // 删除部门
        deptMapper.deleteById(id);
        
        int a = 1/0;
        
        // 删除员工
        empMapper.deleteByDeptId(id);
    }
```

改造后，上述的问题将不存在了。这里在配置中，打开 spring的事务日志，在console中可以看到事物的回滚信息：

```yml
# 配置事务日志等级为debug
logging:
  level:
    org.springframework.jdbc.support.JdbcTransactionManager: debug
```



### 事务细节

1. 事务回滚

    <img src='src/main/resources/static/transaction02.png'>

    上面直接 new Exception 不是 RuntimeException，事务将不会回滚，使用事务注解 rollbackFor 来指定出现何种异常时回滚事务。

    ```java
        @Transactional(rollbackFor = Exception.class)
        @Override
        public void delete(Integer id) throws Exception {
            // 删除部门
            deptMapper.deleteById(id);
    
    //        int a = 1/0;
            if(true){
                throw new Exception("运行出错啦~");
            }
    
            // 删除员工
            empMapper.deleteByDeptId(id);
        }
    ```

2. 事务传播行为

    事务传播行为：指的是一个事务方法被另一个事务方法调用时，被调用的事务方法应该如何进行事务控制。

    事务传播行为，通过事务注解的 propagation 属性来控制，它有以下可配置的值：

    <img src='src/main/resources/static/transaction03.png'>

    基于前面已有的需求，现在要求在 删除部门 时，无论删除失败还是成功，都记录一条日志到数据库。

    1. 创建日志记录表

        ```sql
        create table if not exists record(
            id int unsigned auto_increment comment '主键ID',
            operate text not null comment '操作的详细信息',
            create_time DATETIME not null comment '创建时间',
            primary key (id)
        ) engine InnoDB charset utf8mb4 collate utf8mb4_general_ci;
        ```

    2. 创建RecordPO

        ```java
        package com.zhangjian.de.po;
        
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        
        import java.time.LocalDateTime;
        
        /**
         * 日志记录
         */
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public class RecordPO {
            private int id;
            private String operate;
            private LocalDateTime createTime;
        }
        ```

    3. 创建 RecordService 接口

        ```java
        package com.zhangjian.service;
        
        import com.zhangjian.de.po.RecordPO;
        
        public interface RecordService {
        
            /**
             * 新增日志记录
             * @param recordPO
             */
            void add(RecordPO recordPO);
        }
        ```

    4. 实现日志新增方法

        ```java
        package com.zhangjian.service.impl;
        
        import com.zhangjian.mapper.RecordMapper;
        import com.zhangjian.de.po.RecordPO;
        import com.zhangjian.service.RecordService;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Propagation;
        import org.springframework.transaction.annotation.Transactional;
        
        @Slf4j
        @Service
        public class RecordServiceImpl implements RecordService {
        
            @Autowired
            private RecordMapper recordMapper;
        
            // 新增日志记录方法被其他事物方法调用时，自己单独开一个事物，不受调用方事务影响
            @Transactional(propagation = Propagation.REQUIRES_NEW)
            @Override
            public void add(RecordPO recordPO) {
                Integer recordId = recordMapper.insert(recordPO);
                log.info("新增日志记录成功，id = {}", recordId);
            }
        }
        ```

    5. 创建 RecordMapper 并创建新增记录的方法

        ```java
        package com.zhangjian.mapper;
        
        import com.zhangjian.de.po.RecordPO;
        import org.apache.ibatis.annotations.Insert;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Options;
        
        @Mapper
        public interface RecordMapper {
        
            /**
             * 插入数据记录
             */
            @Options(useGeneratedKeys = true, keyProperty = "id")
            @Insert("insert into record(operate, create_time) values (#{operate}, #{createTime})")
            Integer insert(RecordPO recordPO);
        }
        ```

    6. 在 DeptService 中注入 RecordSercice并改造 delete 方法

        ```java
            @Autowired
            private RecordService recordService;
        
        ...
          
          	// 当前事务回滚时，不会影响日志记录的事务。因为它的 propagation = Propagation.REQUIRES_NEW
            @Transactional(rollbackFor = Exception.class) 
            @Override
            public void delete(Integer id) throws Exception {
                try {
                    // 删除部门
                    deptMapper.deleteById(id);
        
        //        int a = 1/0;
                    if(true){
                        throw new Exception("运行出错啦~");
                    }
        
                    // 删除员工
                    empMapper.deleteByDeptId(id);
                } finally { // 将日志记录放在 finally 中，无论如何都会执行记录操作。且记录日志的事务不受当前事务影响
                    RecordPO recordPO = new RecordPO();
                    recordPO.setCreateTime(LocalDateTime.now());
                    recordPO.setOperate("执行了删除部门的操作，被删除的部门ID为 " + id);
                    recordService.add(recordPO);
                }
            }
        ```



## AOP

AOP：Aspect Oriented Programming（面向切面编程、面向方面编程），其实就是面向特定方法编程。

动态代理是面向切面编程最主流的实现方式。而SpringAOP是Spring框架的高级技术，旨在管理bean对象的过程中，主要通过底层的动态代理机制，对特定的方法进行编程。



### 快速入门

现在通过实现一个统计当前已有方法的请求耗时的切面，来初步了解AOP编程。

1. 在 pom.xml 中，导入 AOP 的依赖

    ```xml
            <!-- aop -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-aop</artifactId>
            </dependency>
    ```

2. 编写aop程序

    ```java
    package com.zhangjian.aop;
    
    import lombok.extern.slf4j.Slf4j;
    import org.aspectj.lang.ProceedingJoinPoint;
    import org.aspectj.lang.annotation.Around;
    import org.aspectj.lang.annotation.Aspect;
    import org.springframework.stereotype.Component;
    
    @Slf4j
    @Component // 切面类都要交给IOC容器管理
    @Aspect // 申明当前类是一个aop类
    public class TimeRecord {
    
        @Around("execution(* com.zhangjian.controller.*.*(..))") // 切入点表达式。此处表示 controller 中所有类的所有方法都将走这个切面
        public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
            // 记录开始时间
            long begin = System.currentTimeMillis();
    
            // 执行原始方法。通过代理调用时，返回类型统一为 Object
            Object result = joinPoint.proceed();
    
            // 记录结束时间
            long end = System.currentTimeMillis();
    
            log.info(joinPoint.getSignature() + " 方法耗时: {} ms", end - begin);
    
            return result;
        }
    }
    ```



### 使用场景和优势

<img src='src/main/resources/static/aop01.png'>



### 核心概念

<img src='src/main/resources/static/aop02.png'>

对以上概念做一个解释：

连接点：可以被AOP控制的方法，项目中几乎所有的方法都可以被AOP控制，因此几乎所有方法都是连接点。

切入点：实际被AOP控制的方法。通常使用 切入点表达式 来指定切面要控制的方法。

通知：封装了那些公共代码逻辑的方法，就叫通知。上面的 recordTime 方法就是一个通知。

切面：切面就是 通知 和 切入点 之间的对应关系。

目标对象：就是 通知 作用在哪写对象上，这些对象就是目标对象。



### 执行流程

AOP的执行流程，本质上就是在目标对象前后，增加一系列操作逻辑后得到一个代理对象。有了代理对象IOC在注入时，使用的就是 代理类的bean对象。

<img src='src/main/resources/static/aop03.png'>



### 通知类型

通知类型，就是作用在通知上的注解类型，有以下几种：

<img src='src/main/resources/static/aop04.png'>



抽取公共部分的切点表达式：

<img src='src/main/resources/static/aop05.png'>



### 执行顺序

当有多个切面的切入点都匹配到了目标方法，目标方法运行时，多个通知都会被执行。

<img src='src/main/resources/static/aop06.png' style='width: 60%; float:left'>



### 切入点表达式

<img src='src/main/resources/static/aop07.png'>



- **execution**

    <img src='src/main/resources/static/aop08.png'>

    <img src='src/main/resources/static/aop09.png'>

    <img src='src/main/resources/static/aop10.png'>

    <img src='src/main/resources/static/aop11.png'>

    实现一个需求：

    使用 execution 表达式，匹配 DeptService 中 list 和 delete 方法，在两个方法执行前打印日志信息。

    ```java
    package com.zhangjian.aop;
    
    import lombok.extern.slf4j.Slf4j;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Before;
    import org.aspectj.lang.annotation.Pointcut;
    import org.springframework.stereotype.Component;
    
    @Slf4j
    @Component
    @Aspect
    public class ShowLog {
        // 同时使用多个表达式
        @Before("execution(* com.zhangjian.service.DeptService.delete(*)) || execution(* com.zhangjian..DeptService.list())")
        public void printLog(){
            log.info(" 被调用了~");
        }
    }
    ```

- **annotation**

    <img src='src/main/resources/static/aop121.png'>

    使用annotation不用自己书写表达式，对于特定的切面功能，使用annotation的方式会更方便维护。

    下面将前面的需求，由 execution 改为annotaion实现，这次同时演示 @PointCat 注解的用法。

    1. 定义一个注解

        ```java
        package com.zhangjian.aop;
        
        import java.lang.annotation.ElementType;
        import java.lang.annotation.Retention;
        import java.lang.annotation.RetentionPolicy;
        import java.lang.annotation.Target;
        
        @Retention( RetentionPolicy.RUNTIME)
        @Target( ElementType.METHOD)
        public @interface PrintLog {
        }
        ```
    
    2. 在 DeptServiceImpl实现类中，给需要打印日志的两个方法上增加注解
    
        ```java
            @PrintLog 
            @Override
            public List<DeptPO> list() {
                return deptMapper.list();
            }
        
            @PrintLog
            // 当前事务回滚时，不会影响日志记录的事务。因为它的 propagation = Propagation.REQUIRES_NEW
            @Transactional(rollbackFor = Exception.class)
            @Override
            public void delete(Integer id) throws Exception {
                try {
                    // 删除部门
                    deptMapper.deleteById(id);
        
        //        int a = 1/0;
                    if(true){
                        throw new Exception("运行出错啦~");
                    }
        
                    // 删除员工
                    empMapper.deleteByDeptId(id);
                } finally { // 将日志记录放在 finally 中，无论如何都会执行记录操作。且记录日志的事务不受当前事务影响
                    RecordPO recordPO = new RecordPO();
                    recordPO.setCreateTime(LocalDateTime.now());
                    recordPO.setOperate("执行了删除部门的操作，被删除的部门ID为 " + id);
                    recordService.add(recordPO);
                }
            }
        ```
    
    3. 在 切面类中定义 抽取表达式的 PointCat 并 改写通知
    
        ```java
        package com.zhangjian.aop;
        
        import lombok.extern.slf4j.Slf4j;
        import org.aspectj.lang.annotation.Aspect;
        import org.aspectj.lang.annotation.Before;
        import org.aspectj.lang.annotation.Pointcut;
        import org.springframework.stereotype.Component;
        
        @Slf4j
        @Component
        @Aspect
        public class ShowLog {
        
            @Pointcut("@annotation(com.zhangjian.aop.PrintLog)")
            public void pt(){}
        
            // 同时使用多个表达式
        //    @Before("execution(* com.zhangjian.service.DeptService.delete(*)) || execution(* com.zhangjian..DeptService.list())")
            @Before("pt()")
            public void printLog(){
                log.info(" 被调用了~");
            }
        }
        ```



### 连接点

<img src='src/main/resources/static/aop110.png'>

改造前面编写的切面方法，获取target的相关信息。

1. 改写 TimeRecord

    ```java
    package com.zhangjian.aop;
    
    import lombok.extern.slf4j.Slf4j;
    import org.aspectj.lang.ProceedingJoinPoint;
    import org.aspectj.lang.annotation.Around;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Before;
    import org.aspectj.lang.annotation.Pointcut;
    import org.springframework.stereotype.Component;
    
    @Slf4j
    @Component // 切面类都要交给IOC容器管理
    @Aspect // 申明当前类是一个aop类
    public class TimeRecord {
    
        // 切入点表达式。此处表示 controller 中所有类的所有方法都将走这个切面
    //    @Around("execution(* com.zhangjian.controller.*.*(..))")
        @Around("com.zhangjian.aop.ShowLog.pt()")
        public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
            // 记录开始时间
            long begin = System.currentTimeMillis();
    
            // 获取目标类名
            log.info("连接点类名: {}", joinPoint.getTarget().getClass().getName());
    
            // 获取目标方法
            log.info("当前方法名: {}", joinPoint.getSignature().getName());
    
            // 获取方法运行时的参数
            log.info("参数信息: {}", joinPoint.getArgs());
    
            // 执行原始方法。通过代理调用时，返回类型统一为 Object
            Object result = joinPoint.proceed();
    
            // 记录结束时间
            long end = System.currentTimeMillis();
    
            log.info(joinPoint.getSignature() + " 方法耗时: {} ms", end - begin);
    
            return result;
        }
    }
    ```

2. 丰富 ShowLog

    ```java
    package com.zhangjian.aop;
    
    import lombok.extern.slf4j.Slf4j;
    import org.aspectj.lang.JoinPoint;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Before;
    import org.aspectj.lang.annotation.Pointcut;
    import org.springframework.stereotype.Component;
    
    @Slf4j
    @Component
    @Aspect
    public class ShowLog {
    
        @Pointcut("@annotation(com.zhangjian.aop.PrintLog)")
        public void pt(){}
    
        // 同时使用多个表达式
    //    @Before("execution(* com.zhangjian.service.DeptService.delete(*)) || execution(* com.zhangjian..DeptService.list())")
        @Before("pt()")
        public void printLog(JoinPoint joinPoint){
            log.info(joinPoint.getSignature().getName() + " 被调用了~");
            
            // 获取 连接点 资源路径
            log.info(joinPoint.getSourceLocation().toString());
            
            // 获取 连接点 类型
            log.info(joinPoint.getKind());
            
            // 获取 当前 连接点对象
            log.info(joinPoint.getThis().toString());
        }
    }
    ```



### 案例-记录操作日志

**需求**

将 部门管理 和 员工管理 两个模块中 增、删、改 的操作记录到日志。

需要记录的内容有：操作人信息（ID、名字）、操作时间、执行方法的全类名、执行方法名、方法运行时参数、返回值、方法执行时长



**说明**

本案例按照从无到有的过程，演示所有步骤。



**编码**

1. 引入pom依赖。这个过程将用到 aop 和 fastjson 两个依赖

    ```xml
            <!--fastjson-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>1.2.83</version>
            </dependency>
    
            <!-- aop -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-aop</artifactId>
            </dependency>
    ```

2. 创建操作日志表

    ```sql
    create table if not exists operate_log(
        id int unsigned auto_increment primary key comment '主键ID',
        operate_user varchar(100) not null comment '操作人信息',
        operate_time datetime comment '操作时间',
        class_name varchar(200) comment '操作全类名',
        method_name varchar(100) comment '操作方法名',
        method_params varchar(150) comment '操作方法的参数',
        return_value varchar(500) comment '返回值',
        cost_time int comment '执行耗时'
    ) comment '操作日志表' engine InnoDB charset utf8mb4 collate utf8mb4_general_ci;
    ```

3. 创建日志表对应的实体类

    ```java
    package com.zhangjian.de.po;
    
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.AllArgsConstructor;
    
    import java.time.LocalDateTime;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class OperateLogPO {
        private Integer id;
        private String operateUser;
        private LocalDateTime operateTime;
        private String className;
        private String methodName;
        private String methodParams;
        private String returnValue;
        private Integer costTime;
    }
    ```

4. 创建操作日志的mapper和对应的xml

    ```java
    package com.zhangjian.mapper;
    
    import com.zhangjian.de.po.OperateLogPO;
    import org.apache.ibatis.annotations.Mapper;
    
    @Mapper
    public interface OperateLogMapper {
        /**
         * 新增数据
         * @param operateLogPO
         */
        void insert(OperateLogPO operateLogPO);
    }
    ```

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="com.zhangjian.mapper.OperateLogMapper">
    
        <insert id="insert" useGeneratedKeys="true" keyProperty="id">
            insert into operate_log(operate_user, operate_time, class_name, method_name, method_params, return_value,
                                    cost_time)
            VALUES (#{operateUser}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue},
                    #{costTime})
        </insert>
    </mapper>
    ```

5. 创建一个注解，用于标记要使用切面方法的方法

    ```java
    package com.zhangjian.aop;
    
    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;
    
    @Retention( RetentionPolicy.RUNTIME)
    @Target( ElementType.METHOD)
    public @interface OperateLog {
    }
    ```

6. 实现切面类

    ```java
    package com.zhangjian.aop;
    
    import com.alibaba.fastjson.JSON;
    import com.zhangjian.mapper.OperateLogMapper;
    import com.zhangjian.de.po.OperateLogPO;
    import com.zhangjian.utils.JwtUtils;
    import io.jsonwebtoken.Claims;
    import lombok.extern.slf4j.Slf4j;
    import org.aspectj.lang.ProceedingJoinPoint;
    import org.aspectj.lang.annotation.Around;
    import org.aspectj.lang.annotation.Aspect;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;
    
    import javax.servlet.http.HttpServletRequest;
    import java.time.LocalDateTime;
    import java.util.Arrays;
    
    @Slf4j
    @Component
    @Aspect
    public class OperateLogAspect {
    
        @Autowired
        private HttpServletRequest httpServletRequest; // 在spring中，请求对象也是被IOC容器管理的
    
        @Autowired
        private OperateLogMapper operateLogMapper;
    
        @Around( "@annotation(com.zhangjian.aop.OperateLog)" )
        public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
            // 1. 通过请求头获取jwt，解析得到操作人信息
            String jwt = httpServletRequest.getHeader("token");
            Claims claims = JwtUtils.parseJwt(jwt);
            Object id = claims.get("id");
            Object name = claims.get("name");
    
            String operateUser = name + "(id=" + id + ")";
    
            // 2. 操作时间
            LocalDateTime operateTime = LocalDateTime.now();
    
            // 3. 执行方法的全类名
            String className = joinPoint.getTarget().getClass().getName();
    
            // 4. 执行的方法名
            String methodName = joinPoint.getSignature().getName();
    
            // 5. 调用方法时，使用到的参数
            Object[] args = joinPoint.getArgs();
            String methodParams = Arrays.toString(args);
    
            // 6. 执行方法回去返回值
            long begin = System.currentTimeMillis();
          
            Object result = joinPoint.proceed();
            String returnValue = JSON.toJSONString(result);
          
            long end = System.currentTimeMillis();
    
            // 7. 获取执行时长
            Integer costTime = (int) (end - begin);
    
            // 记录日志
            OperateLogPO operateLogPO = new OperateLogPO(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
            operateLogMapper.insert(operateLogPO);
            
            log.info("新增一条操作日志: {}", operateLogPO);
            
            // 把原始方法的返回值返回回去
            return result;
        }
    }
    ```

7. 为 部门管理、员工管理 中的 增删改方法添加 @OperateLog 注解。

8. 测试。发现一个问题。method_params 出现了文本超长的问题，这提醒我们，插入数据时，通常要控制文本上限，以满足数据库字段的限制。









