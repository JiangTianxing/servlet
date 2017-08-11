package org.redrock.template.data.mapper;

import org.apache.ibatis.annotations.Select;
import org.redrock.template.data.entity.Student;

public interface StudentMapper {
    @Select("SELECT * FROM student WHERE id = #{id}")
    Student selectStudent(int id);
}