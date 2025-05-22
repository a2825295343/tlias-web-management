package com.itheima.mapper;

import com.itheima.pojo.*;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {
    @Select("select * from clazz")
    List<Clazz> findAll();

    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    @Insert("insert into clazz values(null,#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    //@Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id=#{id}")
    Clazz getById(Integer id);

    //@Update("update clazz set name=#{name}, room=#{room}, begin_date=#{beginDate}, end_date=#{endDate}, master_id=#{masterId}, subject=#{subject}, update_time=#{updateTime} where id=#{id}")
    void updateById(Clazz clazz);
}
