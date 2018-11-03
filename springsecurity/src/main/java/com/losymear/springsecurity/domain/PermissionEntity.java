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
@Table(name = "permission", schema = "demo", catalog = "")
public class PermissionEntity {

    private int id;
    private String urlPattern;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "urlPattern")
    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionEntity that = (PermissionEntity) o;
        return id == that.id &&
                Objects.equals(urlPattern, that.urlPattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlPattern);
    }
}
