package com.example.Backend.Model;

public class Project {

    private int id;

    private int managerId;

    private String name;

    private String description;

    private String members;

    public Project(int id, int managerId, String name, String description, String members){
        this.id = id;
        this.managerId = managerId;
        this.name = name;
        this.description = description;
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }




}
