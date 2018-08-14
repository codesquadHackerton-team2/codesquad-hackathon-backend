package com.codesquad.codereviewers.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @ManyToOne
    private Article article;

    private String title;
    private String content;

    @Embedded
    private Position position;

    @Embeddable
    @Getter
    @AllArgsConstructor
    private class Position {
        private long line;
    }
}
