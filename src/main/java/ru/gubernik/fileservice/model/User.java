package ru.gubernik.fileservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

/**
 * Пользователь
 */
@Entity
@Table(name = "USERS")
public class User {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Имя
     */
    @NotEmpty(message = "Введите имя пользователя")
    @Column(name = "NAME", length = 50)
    private String userName;

    /**
     * Электронная почта
     */
    @NotEmpty(message = "Введите электронную почту")
    @Column(name = "EMAIL", length = 50)
    private String email;

    /**
     * Пароль
     */
    @NotEmpty(message = "Введите пароль")
    @Column(name = "PASSWORD" , length = 50)
    private String password;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    /**
     * Роль пользователя
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    public User(){

    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}