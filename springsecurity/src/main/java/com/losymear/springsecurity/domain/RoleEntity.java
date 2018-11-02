package com.losymear.springsecurity.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-02 13:35
 */
@Entity
@Table(name = "role", schema = "demo", catalog = "")
public class RoleEntity {

    private int id;
    private String role;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
