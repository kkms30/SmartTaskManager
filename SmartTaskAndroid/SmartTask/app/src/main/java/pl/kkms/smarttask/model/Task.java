
package pl.kkms.smarttask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pl.kkms.smarttask.database.IDatabaseObject;

public class Task implements IDatabaseObject{

    @SerializedName("idTask")
    private int idTask;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("addTime")
    private String addTime;
    @SerializedName("beginTime")
    private String beginTime;
    @SerializedName("endTime")
    private String endTime;
    @SerializedName("priority")
    private int priority;
    @SerializedName("status")
    private String status;

    private long id;

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
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

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
