package com.codesquad.codereviewers.controller.views.request;

import com.codesquad.codereviewers.domain.Article;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class NewArticleRequest {

    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder().content(this.content).title(this.title).build();
    }
}
