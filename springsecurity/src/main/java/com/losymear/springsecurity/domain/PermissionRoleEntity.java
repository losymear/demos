package com.losymear.springsecurity.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: springsecurity
 * @description:
 * @author: losymear
 * @create: 2018-11-03 10:44
 */
@Entity
@Table(name = "permission_role", schema = "demo", catalog = "")
public class PermissionRoleEntity {

    private int id;
    private int urlId;
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
    @Column(name = "urlId")
    public int getUrlId() {
        return urlId;
    }

    public void setUrlId(int urlId) {
        this.urlId = urlId;
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
                urlId == that.urlId &&
                roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlId, roleId);
    }
}
