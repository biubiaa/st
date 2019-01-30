package com.sylg.st.dto;

public class ComList {
    private int id;
    private String name;
    private String fabuTime;
    private String type;
    private String generalization;

    public String getGeneralization() {
        return generalization;
    }

    public void setGeneralization(String generalization) {
        this.generalization = generalization;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFabuTime() {
        return fabuTime;
    }

    public void setFabuTime(String fabuTime) {
        this.fabuTime = fabuTime;
    }
}
