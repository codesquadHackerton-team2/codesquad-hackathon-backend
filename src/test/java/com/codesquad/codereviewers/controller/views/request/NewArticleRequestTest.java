package com.codesquad.codereviewers.controller.views.request;

import com.codesquad.codereviewers.domain.Article;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NewArticleRequestTest {

    private NewArticleRequest request;

    @Before
    public void setUp() {
        this.request = NewArticleRequest.builder().title("test title").content("test content").build();
    }

    @Test
    public void test_toentity() {
        Article article = this.request.toEntity();

        assertThat(article.getContent(), is("test content"));
    }

}