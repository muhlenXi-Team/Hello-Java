package com.muhlenxi.test;

import com.muhlenxi.dao.IStudentDao;
import com.muhlenxi.domain.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.apache.ibatis.io.Resources;

import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    @Test
    public void testFindAll() throws Exception{
        // 1、读取配置文件，生成字节输入流
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、获取 SQLSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        //3、获取 SQLSession 对象
        SqlSession sqlSession = factory.openSession();
        //4、获取 dao 的代理对象
        IStudentDao studentDao = sqlSession.getMapper(IStudentDao.class);
        //5、执行查询所有方法
        List<Student> students = studentDao.findAll();

        for (Student stu:
             students) {
            System.out.println(stu);
        }

        //6、释放资源
        sqlSession.close();
        inputStream.close();
    }
}
