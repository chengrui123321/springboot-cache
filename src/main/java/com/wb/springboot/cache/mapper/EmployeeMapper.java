package com.wb.springboot.cache.mapper;

import com.wb.springboot.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

/**
 * EmployeeMapper
 */
@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee WHERE id = #{id} ")
    Employee getEmp(Integer id);

    @Insert("INSERT INTO employee (last_name, email, gender, d_id) VALUES (#{lastName}, #{email}, #{gender}, #{dId})")
    void insert(Employee employee);

    @Update("UPDATE employee SET last_name = #{lastName}, email = #{email}, gender = #{gender}, d_id = #{dId} WHERE id = #{id} ")
    void update(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id} ")
    void delete(Integer id);

}
