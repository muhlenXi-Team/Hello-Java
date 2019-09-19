import com.muhlenxi.dao.IUserDao;
import com.muhlenxi.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest {

    private InputStream inputStream;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private IUserDao userDao;


    @Before
    public void setup() throws Exception{
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void clean() throws Exception {
        sqlSession.commit();

        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for(User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal() {
        int total = userDao.findTotal();
        System.out.println(total);
    }

    @Test
    public void testFindUserByName() {
        List<User> users = userDao.findUserByName("%张%");
        for(User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindUserById() {
       User user = userDao.findUserById(3);
       System.out.println(user);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("张三丰");
        user.setBirthday(new Date());
        user.setAddress("武当山");
        user.setSex('男');

        userDao.saveUser(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(5);
        user.setName("张三丰");
        user.setBirthday(new Date());
        user.setAddress("武当山");
        user.setSex('M');

        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        userDao.deleteUser(5);
    }
}
