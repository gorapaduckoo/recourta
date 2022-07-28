package com.ssafy.recourta.domain.user.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column
    private  String name;

    @Column(unique = true)
    private  String email;

    @Column
    private String password;

    @Column
    private int isStudent;

    @Column
    private String userImg;

}
