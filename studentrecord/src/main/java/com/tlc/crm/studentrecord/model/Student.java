package com.tlc.crm.studentrecord.model;

import com.tlc.crm.studentrecord.api.validation.Department;
import com.tlc.validator.TlcModel;
import com.tlc.validator.type.Group.Create;
import com.tlc.validator.type.Group.Update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Student implements TlcModel {

    private Long id;

    @NotNull(groups = {Create.class, Update.class})
    private String rollNumber;

    @Pattern(regexp = "^[A-Z][A-Za-z+\\s]*$", groups = {Create.class, Update.class})
    private String name;

    @Department(groups = {Create.class, Update.class})
    private String departmentName;

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-z]+$", groups = {Create.class, Update.class})
    private String email;

    public Student(Long id, String rollNumber, String name, String departmentName, String email) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.name = name;
        this.departmentName = departmentName;
        this.email =email;
    }

    public Student() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Object identity() {
        return null;
    }

    public String toString() {
        return String.format("Id:%s \n RollNumber:%s \n Name:%s \nDeptName:%s \nEmail:%s \n\n", id, rollNumber, name, departmentName, email);
    }
}
