package com.iweb.test1;

import com.iweb.dao.StudentDAO;
import com.iweb.dao.impl.StudentDAOImpl;
import com.iweb.pojo.Student;

import java.util.Date;

/** JDBC java database connection
 * @author CC
 * @date 2024/7/22 下午2:43
 */
public class Test {
    public static void main(String[] args) {
//        // 驱动加载
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("驱动加载成功");
//        // 获取jdbc连接
//        String url = "jdbc:mysql://localhost:3306/nuist?characterEncoding=utf8";
//        String username = "root";
//        String password = "a12345";
//        Connection connection  = null;
//        try {
//            connection =
//                    DriverManager.getConnection(url,username,password);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("获取连接成功");
//
//        try {
//            Statement s = connection.createStatement();
//            // 准备一个sql语句
//            String sql ="INSERT INTO stu(NAME,gender,birthday,addr,qqnumber)" +
//                    "VALUES" +
//                    "('福气','女','2003-02-24','江苏南京','3124086825')";
//            // 运行sql语句
//            s.execute(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        // 驱动加载 链接获取 创建编译语句对象 执行语句
        // 驱动加载 链接获取 创建编译语句对象 执行语句 获取查询结果
//        Student s1 = new Student(0,"吴畏","男",new Date(0),"南京",110);
        StudentDAO studentDAO = new StudentDAOImpl();
//        studentDAO.insert(s1);
        Student s2 = new Student(6,"哈哈哈哈","女",new Date(),"江苏",119l);
        studentDAO.update(s2);
    }
}
