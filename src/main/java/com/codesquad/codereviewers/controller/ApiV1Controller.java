package com.codesquad.codereviewers.controller;

import com.codesquad.codereviewers.controller.views.request.NewArticleRequest;
import com.codesquad.codereviewers.domain.Article;
import com.codesquad.codereviewers.domain.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiV1Controller {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/article/new")
    public void saveNewArticle(@RequestBody NewArticleRequest request) {
        Article article = request.toEntity();
        articleService.saveArticle(article);
    }
}
