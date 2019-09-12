import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

public class MyBatisUtil {
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private MyBatisUtil() {}

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }

        return sqlSession;
    }

    public static void closeSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            sqlSession.close();
            threadLocal.remove();
        }
    }

    public static void main(String[] args) {
        Connection connection = MyBatisUtil.getSqlSession().getConnection();
        System.out.println(connection != null ? "succeed" : "failed");
    }
}
