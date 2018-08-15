package com.codesquad.codereviewers.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class RegisteredUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String username;
    private String nickname;
    private String profileHref;

    private String githubCode;
    private String email;
    private String bio;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Article> articles = newArrayList();

    public boolean isRegistered() {
        return true;
    }

}
