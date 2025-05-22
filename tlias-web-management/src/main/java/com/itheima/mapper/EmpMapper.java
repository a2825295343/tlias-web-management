package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    //---------------------------------原始的分页查询---------------------------------
//    //查询总记录数
//    @Select("select count(*) from emp")
//    public Long count();
//
//    //分页查询
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    //---------------------------------PageHelper的分页查询---------------------------------
    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
            //"order by e.update_time desc") //结尾不能加分号
    //public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
    List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id") //获取到生成的主键--主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    //根据ID更新员工的基本信息
    void updateById(Emp emp);

    //统计员工职位人数
    @MapKey("pos")  //可删除，红波浪线是误报
    List<Map<String, Object>> countEmpJobData();

    //统计员工性别人数
    @MapKey("name") //可删除，红波浪线是误报
    List<Map<String, Object>> countEmpGenderData();

    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);

    @Select("select * from emp where job = 1") //可以修改前端代码实现按固定职业查找，还可以使用<where>来实现动态sql
    List<Emp> findByJob();
}
