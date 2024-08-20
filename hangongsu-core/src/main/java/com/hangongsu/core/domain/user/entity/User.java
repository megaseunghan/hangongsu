package com.hangongsu.core.domain.user.entity;

import com.hangongsu.core.domain.user.vo.Platform;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private String email;
    private String name;
    private String nickname;
    private long birthYear;
}
