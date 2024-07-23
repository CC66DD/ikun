package com.iweb.dao;

import com.iweb.pojo.Student;

import java.util.List;

/**
 * @author CC
 * @date 2024/7/22 下午3:34
 */
public interface StudentDAO {

    /** 插入一个学生信息
     * @param student 要插入的学生信息 主键无需提供
     */
     void insert(Student student);

    /** 根据主键删除学生信息
     * @param id 要删除的学生信息的id
     */
     void delete(Integer id);

    /** 根据学生id 修改学生所有信息
     * @param student 要修改的学生id 和 需要被修改的学生字段
     */
     void update(Student student);

    /** 统计表记录总数
     * @return 记录总数 如果为0 没有数据
     */
     Integer count();

    /**根据id查找对应的学生信息
     * @param id 要获取的学生信息
     * @return id 对应的学生信息 学生不存在返回null
     */
     Student findById(Integer id);

    /** 获取所有学生信息
     * @return  所有学生对应集合
     */
     List<Student> findAll();

    /** 根据名称模糊查询学生
     * @param name 模糊查询关键字
     * @return 模糊查询的学生结果
     */
     List<Student> findByNameLike(String name);

    /** 根据学生名称进行模糊查询的分页的查询
     * @param name  模糊查询关键字
     * @param start limit起始参数
     * @param limit 要分页截取的行数
     * @return 分页模糊查询的数据 没有数据 返回null
     */
     List<Student> findByNameLikeWithLimit(String name,int start,int limit);

    /** 分页查询学生
     * @param start limit起始参数
     * @param limit 分页截取的行数
     * @return 分页查询的学生数据 没有数据 返回null
     */
     List<Student> findWithLimit(int start,int limit);
}
