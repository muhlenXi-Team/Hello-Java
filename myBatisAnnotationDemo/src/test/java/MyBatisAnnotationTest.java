import com.muhlenxi.dao.IUserDao;
import com.muhlenxi.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MyBatisAnnotationTest {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = factory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> users = userDao.findAll();

        for (User user:
             users) {
            System.out.println(user);
        }

        sqlSession.close();
        inputStream.close();
    }
}
