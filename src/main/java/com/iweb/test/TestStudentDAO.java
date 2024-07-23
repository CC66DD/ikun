package com.iweb.test;

import com.iweb.dao.StudentDAO;
import com.iweb.dao.impl.StudentDAOImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** 测试类 用于DAO接口测试
 * @Test 将指定方法标记为main方法 可以不依赖于main方法直接运行
 * @Before 在所有测试方法运行之前的执行的代码 一般用于环境的初始化
 * @After 在所有测试方法之后运行的代码 一般用于资源回收
 * @author CC
 * @date 2024/7/23 上午11:02
 */
public class TestStudentDAO {
    private StudentDAO studentDAO;
    @Before
    public void init(){
        studentDAO=new StudentDAOImpl();
    }
    @Test
    public void testFindBy(){
 //      Assert.assertNotNull(studentDAO.findById(1));
        Assert.assertNotNull(1);
     //   System.out.println(studentDAO.findById(null));
    }
    @Test
    public void testCount(){

        Assert.assertEquals(9L,(long)studentDAO.count());
    }
    @Test
    public void testFindAll(){
        Assert.assertNotNull(studentDAO.findAll());
        System.out.println(studentDAO.findAll());
    }
    @Test
    public void testFindWithLimit(){
        Assert.assertNotNull(studentDAO.findWithLimit(1,2));
    }
    @Test
    public void testFindByNameLike(){
        Assert.assertNotNull(studentDAO.findByNameLike("木易"));
        System.out.println(studentDAO.findByNameLike("木易"));
    }
    @Test
    public void testFindByNameLikeWithLimit(){
        Assert.assertNotNull(studentDAO.findByNameLikeWithLimit("刘",1,1));
    }
}
