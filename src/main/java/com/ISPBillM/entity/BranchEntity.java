package com.ISPBillM.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("branch")
public class BranchEntity {

    @Id
    private String id;

    private String name;

    private String address;

    @DBRef
    private List<ServiceEntity> services;

    @DBRef
    private List<DepartmentEntity> departments;

    @DBRef
    private List<EmployeeEntity> employees;

    private String status;
    private String description;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

    public List<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentEntity> departments) {
        this.departments = departments;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public int employeeCount()
    {
        if(this.getEmployees() != null)
        {
            return this.getEmployees().size();
        }
        else
        {
            return 0;
        }
    }

    public int serviceCount()
    {
        if(this.getServices() !=null)
        {
            return this.getServices().size();
        }
        else
        {
            return 0;
        }
    }
}
