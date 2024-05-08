package sftp.integration.models;

import java.util.Map;

public class Company {
    public Address address;
    public String department;
    public String name;
    public String title;

    public Company() {
    }

    public Company(Address address, String department, String name, String title) {
        this.address = address;
        this.department = department;
        this.name = name;
        this.title = title;
    }
    public Company(Map<String, Object> companyMapped, Map<String, Object> address, Map<String, Object> coordinates) {

        this.address = new Address(address, coordinates);
        this.department = (String) companyMapped.get("department");
        this.name = (String) companyMapped.get("name");
        this.title = (String) companyMapped.get("title");
    }
    
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
