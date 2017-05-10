package pl.kkms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import pl.kkms.util.DateHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idtasks")
    private int idTask;
    private String name;
    @Type(type = "text")
    private String description;
    @Column(name = "addtime", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = DateHelper.JSON_DATE_PATTERN)
    private Date addTime;
    @Column(name = "begintime", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = DateHelper.JSON_DATE_PATTERN)
    private Date beginTime;
    @Column(name = "endtime", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = DateHelper.JSON_DATE_PATTERN)
    private Date endTime;
    @Column(columnDefinition = "TINYINT")
    private int priority;
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userstotasks",
            joinColumns = {@JoinColumn(name = "idtasks")},
            inverseJoinColumns = {@JoinColumn(name = "iduser")}
    )
    @JsonBackReference
    private List<User> users = new ArrayList<>();

    public Task() {
    }

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
