package pl.kkms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pl.kkms.util.DateHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iduser")
    private int idUser;
    private String login;
    private String password;
    private String name;
    private String surname;
    @Column(name = "usercode")
    private String userCode;  @Column(name = "addtime", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = DateHelper.JSON_DATE_PATTERN)
    private Date addTime;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();

    @Transient
    private String qrCodeUrl;

    // KLASA MUSI MIEC DOMYSLNY KONSTRUKTOR
    public User() {
    }

    public User(int idUser, String login, String password, String name, String surname, String userCode) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userCode = userCode;
    }

    public User(String login, String password, String name, String surname, String userCode) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userCode = userCode;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String usercode) {
        this.userCode = usercode;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }
}
