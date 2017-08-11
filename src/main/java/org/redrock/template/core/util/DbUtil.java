package org.redrock.template.core.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.redrock.template.data.entity.Student;
import org.redrock.template.data.mapper.StudentMapper;

import java.util.Properties;

public class DbUtil {

    /**
     * 内存持久化
     */
    private static SqlSessionFactory sqlSessionFactory;
    /**
     * 单例获取SqlSessionFactory
     * 1.数据源(数据库连接池)
     * 2.环境(id,事务处理工厂,数据源)
     * 3.配置类:添加映射类、配置相关参数
     * 4.创建sqlSessionFactory实例
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            synchronized (DbUtil.class) {
                if (sqlSessionFactory == null) {
                    //设置数据源
                    Properties properties = PropertiesUtil.getProperties("database.properties");
                    DruidDataSource dataSource =  new DruidDataSource();
                    dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
                    dataSource.setUrl(properties.getProperty("jdbc.url"));
                    dataSource.setUsername(properties.getProperty("jdbc.username"));
                    dataSource.setPassword(properties.getProperty("jdbc.password"));
                    dataSource.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initialSize")));
                    dataSource.setMaxActive(Integer.parseInt(properties.getProperty("jdbc.maxActive")));
                    dataSource.setMaxWait(Long.parseLong(properties.getProperty("jdbc.maxWait")));
                    dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(properties.getProperty("jdbc.minEvictableIdleTimeMills")));
                    //设置事务工厂
                    TransactionFactory transactionFactory = new JdbcTransactionFactory();
                    //设置环境
                    Environment environment = new Environment("deployment", transactionFactory, dataSource);
                    //设置配置类
                    Configuration configuration = new Configuration();
                    configuration.setEnvironment(environment);
                    //添加Mapper
                    configuration.addMapper(StudentMapper.class);
                    //创建sqlSession创建工厂
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
                }
            }
        }
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student student = studentMapper.selectStudent(10);
        System.out.println(student.getName());
        session.close();
    }

}
