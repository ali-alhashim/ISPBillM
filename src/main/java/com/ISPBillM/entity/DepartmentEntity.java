package com.ISPBillM.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("department")
public class DepartmentEntity {

    @Id
    private String id;

    private String name;

    @DBRef
    private List<ServiceEntity> services;

    @DBRef
    private List<EmployeeEntity> employees;

    @DBRef
    private BranchEntity branch;

    private String status;
    private String description;
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public BranchEntity getBranch() {
        return branch;
    }

    public void setBranch(BranchEntity branch) {
        this.branch = branch;
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
