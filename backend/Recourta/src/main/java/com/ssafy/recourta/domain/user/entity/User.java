package com.ssafy.recourta.domain.user.entity;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Column
    private  String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email
    @Column(unique = true)
    private  String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{8,20}", message = "비밀번호는 8~20자리수여야 합니다. 영문, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    @Column
    private String password;

    @NotBlank(message = "타입을 선택해주세요.")
    @Column
    private int isStudent;

    @Column
    private String userImg;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default        // Builder 사용시 기본값 지정
    private List<String> roles = new ArrayList<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
