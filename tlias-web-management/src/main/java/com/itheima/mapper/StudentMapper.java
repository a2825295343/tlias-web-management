package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(String name, Integer degree, Integer clazzId);

    void deleteByIds(List<Integer> ids);

    @Insert("insert into student(name, no, gender, phone, degree, clazz_id, id_card, is_college, address, graduation_date, create_time, update_time) values(#{name}, #{no}, #{gender}, #{phone}, #{degree}, #{clazzId}, #{idCard}, #{isCollege}, #{address}, #{graduationDate}, #{createTime}, #{updateTime})")
    void insert(Student student);

    @Select("select id, name, no, phone, gender, degree, id_card, is_college, address, graduation_date, violation_count, violation_score, clazz_id, create_time, update_time from student where id=#{id}")
    Student getById(Integer id);

    void updateById(Student student);

    @Update("update student set violation_count=violation_count+1, violation_score=violation_score+#{score} where id=#{id}")
    void violation(Integer id, Integer score);

    @Select("select c.name cname , count(s.id) scount from clazz c  left join student s on s.clazz_id = c.id group by c.name order by count(s.id)")
    List<Map<String,Object>> getStudentCount();

    /**
     * 统计学员学历
     */
    @MapKey("name")
    List<Map> countStudentDegreeData();

    @Select("select count(*) from student where clazz_id = #{id}")
    Integer countByClazzId(Integer id);

}
