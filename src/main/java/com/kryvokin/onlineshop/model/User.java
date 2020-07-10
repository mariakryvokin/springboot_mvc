package com.kryvokin.onlineshop.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email
    private String email;
    private String password;
    @Lob
    private byte[] photo;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_has_roles", joinColumns = {
            @JoinColumn(name = "users_id")}, inverseJoinColumns = {
            @JoinColumn(name = "roles_name")})
    private Set<Role> roles;
    @OneToMany(mappedBy = "user")
    private List<Order> order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
