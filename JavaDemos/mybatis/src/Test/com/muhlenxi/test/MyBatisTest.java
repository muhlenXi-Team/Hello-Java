package com.muhlenxi.test;

import com.muhlenxi.dao.IStudentDao;
import com.muhlenxi.domain.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.ibatis.io.Resources;

import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private IStudentDao studentDao;

    @Before
    public void setup() throws Exception{
        // 1、读取配置文件，生成字节输入流
        inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2、获取 SQLSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        //3、获取 SQLSession 对象
        sqlSession = factory.openSession();
        //4、获取 dao 的代理对象
        studentDao = sqlSession.getMapper(IStudentDao.class);
    }

    @After
    public void clean() throws Exception{
        sqlSession.commit();

        //6、释放资源
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testFindAll(){
        //5、执行查询所有方法
        List<Student> students = studentDao.findAll();

        for (Student stu:
             students) {
            System.out.println(stu);
        }
    }

    @Test
    public void testSaveStudent(){
        Student student = new Student();
        student.setName("李思思");
        student.setGender("man");
        student.setAge(11);
        student.setMath(100);
        student.setEnglish(99);
        student.setSchool_id(400);

        System.out.println("保存前：" + student);
        studentDao.saveStudent(student);
        System.out.println("保存后：" + student);
    }

    @Test
    public void testDeleteStudent() {
        studentDao.deleteStudent(9);
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setId(6);
        student.setName("胡六六");
        studentDao.updateStudent(student);
    }

    @Test
    public void testFindStudentById() {
        Student student = studentDao.findStudentById(3);
        System.out.println(student);
    }

    @Test
    public void testFindStudentsByName() {
        List<Student> students = studentDao.findStudentsByName("%五%");
        for (Student student: students) {
            System.out.println(student);
        }
    }

    @Test
    public void testFindTotal() {
        System.out.println(studentDao.findTotal());
    }

    @Test
    public void testHelloJava(){
        System.out.println("Hello, Java!");
    }
}
