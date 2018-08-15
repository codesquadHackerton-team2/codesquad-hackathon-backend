package com.codesquad.codereviewers.domain.service;

import com.codesquad.codereviewers.domain.Article;
import com.codesquad.codereviewers.domain.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Article getSingleArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(supplyException());
    }

    @Transactional(readOnly = true)
    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Transactional
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    private Supplier<RuntimeException> supplyException() {
        return () -> new NoArticleFoundException("검색된 게시물이 없습니다.");
    }

    private class NoArticleFoundException extends RuntimeException {
        NoArticleFoundException(String msg) {
            super(msg);
        }
    }
}
