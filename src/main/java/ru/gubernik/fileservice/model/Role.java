package ru.gubernik.fileservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Объект роли пользователя
 */
@Entity
@Table(name = "ROLE")
public class Role {

    /**
     * Идетнификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Название роли
     */
    @Column(name = "ROLE_NAME", length = 20)
    private String role;

    /**
     * Пользователи с данной ролью
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> users;

    public Role(){

    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
