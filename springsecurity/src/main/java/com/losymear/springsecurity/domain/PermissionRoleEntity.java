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
@Table(name = "permission_role", schema = "demo", catalog = "")
public class PermissionRoleEntity {

    private int id;
    private String url;
    private int roleId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "roleId")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionRoleEntity that = (PermissionRoleEntity) o;
        return id == that.id &&
                roleId == that.roleId &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, roleId);
    }
}
