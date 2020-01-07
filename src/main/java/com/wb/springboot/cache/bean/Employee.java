package com.wb.springboot.cache.bean;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 员工
 */
@Data
@Accessors(chain = true)
public class Employee implements Serializable {

    private Integer id;

    private String lastName;

    private String email;

    private Integer gender;

    private Integer dId;

}
