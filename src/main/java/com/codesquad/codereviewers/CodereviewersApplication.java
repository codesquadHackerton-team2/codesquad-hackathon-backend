package com.codesquad.codereviewers;

import com.codesquad.codereviewers.domain.Article;
import com.codesquad.codereviewers.domain.RegisteredUser;
import com.codesquad.codereviewers.domain.repositories.ArticleRepository;
import com.codesquad.codereviewers.domain.repositories.RegisteredUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodereviewersApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodereviewersApplication.class, args);
    }

    @Bean
    CommandLineRunner bootstrapData(ArticleRepository articleRepository, RegisteredUserRepository registeredUserRepository) {
        return args -> {
            RegisteredUser postRegisterUser = RegisteredUser.builder().nickname("seulgi")
                    .email("seulgi@smtown.com")
                    .profileHref("https://pbs.twimg.com/profile_images/897825729563443204/vjhZBPi8.jpg")
                    .username("강슬기")
                    .build();

            Article postArticle = Article.builder()
                    .title("test title")
                    .content("test content").build();

            RegisteredUser savedUser = registeredUserRepository.save(postRegisterUser);
            postArticle.setUser(savedUser);

            articleRepository.save(postArticle);
        };
    }
}
