package com.iweb.dao.impl;

import com.iweb.dao.StudentDAO;
import com.iweb.pojo.Student;
import com.iweb.test1.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CC
 * @date 2024/7/22 下午3:53
 */
public class StudentDAOImpl implements StudentDAO {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void insert(Student student) {
        try(
            Connection c= DBUtil.getConnection();
            Statement st=c.createStatement()
            // 缺点：1.传参麻烦
            // 2.性能较差
            // 3.存在sql注入攻击问题

            // PreparedStatement语句
            // 先编译 后传参 效率更高
            // 传参方便
            // 有效防止sql注入攻击的问题
        ){
            String sql="INSERT INTO student(name,gender,birthday,addr,qqnumber)" +
                    "VALUES('%s','%s','%s','%s','%d')";
            sql=String.format(sql,student.getName(),student.getGender(),
                    sdf.format(student.getBirthday()),student.getAddr(),student.getQqnumber());
            st.execute(sql);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql="DELETE FROM student WHERE id=?";
        Connection c=null;
        try{
            c= DBUtil.getConnection();
            // 关闭事务的自动提交
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            c.commit();
        }
        catch(SQLException e){
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(Student student) {
        String sql="update student set name=?,gender=?,birthday=?,addr=?,qqnumber=? where id=?";
        try(
                Connection c= DBUtil.getConnection();
                PreparedStatement ps=c.prepareStatement(sql)
                ){
            // 给sql语句进行参数传递
            ps.setString(1,student.getName());
            ps.setString(2,student.getGender());
            ps.setDate(3, new Date(student.getBirthday().getTime()));
            ps.setString(4,student.getAddr());
            ps.setLong(5,student.getQqnumber());
            ps.setInt(6,student.getId());
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer count() {
        String sql="select count(*) from student";
        try(
                Connection c= DBUtil.getConnection();
                PreparedStatement ps=c.prepareStatement(sql)

        ){
            // 结果集（查询结果每一行）
            ResultSet rs=ps.executeQuery();
            if(rs.next()){ // 一条数据 用if判断
                // 每次从结果集读取一行数据
                // 根据读取的字段数据不同 使用不同的get方法
                // 方法参数有两种 一种是获取的字段在查询结果中出现的顺序
                // 可以不写字段出现的顺序 而写字段的名称
                return rs.getInt(1);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student findById(Integer id) {
        String sql="select * from student where id=?";
        Student student=null;
        try(
                Connection c= DBUtil.getConnection();
                PreparedStatement ps=c.prepareStatement(sql)
                ){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                student=new Student();
                student.setId(id);
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        return findWithLimit(0,Integer.MAX_VALUE);
    }

    @Override
    public List<Student> findByNameLike(String name) {
        return findByNameLikeWithLimit(name ,0,Integer.MAX_VALUE);
    }

    @Override
    public List<Student> findByNameLikeWithLimit(String name, int start, int limit) {
        String sql="select * from student where name like concat('%',?,'%') limit?,?";
        List<Student> list=new ArrayList<>();
        try(
                Connection c= DBUtil.getConnection();
                PreparedStatement ps=c.prepareStatement(sql)
                ){
            ps.setString(1,name);
            ps.setInt(2,start);
            ps.setInt(3,limit);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Student student=new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
                list.add(student);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list.size()>0?null:list;
    }

    @Override
    public List<Student> findWithLimit(int start, int limit) {
        String sql="select * from student limit?";
        List<Student> list=new ArrayList<Student>();
        try(
                Connection c= DBUtil.getConnection();
                PreparedStatement ps=c.prepareStatement(sql)
        ){
            ps.setInt(1,start);
            ps.setInt(2,limit);
            ResultSet rs=ps.executeQuery();
            // 遍历结果集
            while(rs.next()){
                Student student=new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddr(rs.getString("addr"));
                student.setQqnumber(rs.getLong("qqnumber"));
                list.add(student);
            }
        }
        catch(SQLException e){
        e.printStackTrace();
        }
        return list;
    }
}
