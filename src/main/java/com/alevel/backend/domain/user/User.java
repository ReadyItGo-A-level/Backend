package com.alevel.backend.domain.user;

import com.alevel.backend.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    private int status = 1;

    @Builder
    public User(String email, String password, String username, int status){
        this.email = email;
        this.password = password;
        this.username = username;
        this.status = status;
    }
}