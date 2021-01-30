package vn.jewel.shop.model;

import javax.persistence.*;

@Entity

@Table(name = "users_roles")

public class RolesUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private Long role_id;


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}
