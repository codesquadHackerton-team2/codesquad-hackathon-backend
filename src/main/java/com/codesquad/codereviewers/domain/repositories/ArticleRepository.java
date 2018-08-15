package com.codesquad.codereviewers.domain.repositories;

import com.codesquad.codereviewers.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByFeatured(boolean featured);
}
