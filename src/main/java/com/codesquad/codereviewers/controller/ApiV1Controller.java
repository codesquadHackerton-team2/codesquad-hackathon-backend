package com.codesquad.codereviewers.controller;

import com.codesquad.codereviewers.configuration.security.LoggedOnToken;
import com.codesquad.codereviewers.controller.views.request.NewArticleRequest;
import com.codesquad.codereviewers.domain.Article;
import com.codesquad.codereviewers.domain.RegisteredUser;
import com.codesquad.codereviewers.domain.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiV1Controller {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/article/new")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void saveNewArticle(@RequestBody NewArticleRequest request, @AuthenticationPrincipal LoggedOnToken token) {
        Article article = request.toEntity();
        RegisteredUser user = token.getPrincipal();
        article.setUser(user);

        articleService.saveArticle(article);
    }

    @GetMapping("/article/get/all")
    public List<Article> getAllArticles() {
        return articleService.getAllArticle();
    }

    @GetMapping("/article/get/featured")
    public List<Article> getFeaturedArticles() {
        return articleService.getAllFeatured();
    }
}
