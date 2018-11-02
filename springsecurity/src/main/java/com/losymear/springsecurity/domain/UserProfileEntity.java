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
@Table(name = "user_profile", schema = "demo", catalog = "")
public class UserProfileEntity {

    private int id;
    private int userId;
    private String avatar;
    private String email;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileEntity that = (UserProfileEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, avatar, email);
    }
}
