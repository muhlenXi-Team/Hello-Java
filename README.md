# java-demos

- 1、mybatis 项目是使用 xml 映射方式的增删改查 demo。
- 2、myBatisAnnotationDemo 项目是 使用 注解 方式的增删改查 demo

## 备忘

1、pom.xml 文件用于配置项目的打包方式、依赖等。

2、resources 下的 SqlMapConfig.xml 文件用于配置数据库连接环境、映射等。如果有自定义的 properties 文件，需要在这里引入。

3、resources 下的 log4j.properties 存放的是日志打印相关配置。

4、resources 下的 jdbc.properties 存放的是数据库相关配置，比如 driver、url、username、password 等。

5、操作完记得提交事务。

```java
sqlSession.commit();
```

