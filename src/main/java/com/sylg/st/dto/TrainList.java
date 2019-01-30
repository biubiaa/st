package com.sylg.st.dto;

public class TrainList {
    private  int trainId;
    private String trainName;
    private String location;
    private String fabuTime;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getFabuTime() {
        return fabuTime;
    }

    public void setFabuTime(String fabuTime) {
        this.fabuTime = fabuTime;
    }
}
