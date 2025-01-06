//package com.museum.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.museum.dao.ArticleRepository;
//import com.museum.entity.Article;
//
//@Service
//public class ArticleService {
//	
//	@Autowired
//	private ArticleRepository articleRepo;
//
//	public Article addArticle(Article article) {
//		Article savedArticle = articleRepo.save(article);
//		return savedArticle;
//	}
//}
package com.museum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museum.dao.ArticleRepository;
import com.museum.entity.Article;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepo;

    // Create or Add Article
    public Article addArticle(Article article) {
        return articleRepo.save(article);
    }

    // Retrieve all articles
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    // Retrieve article by ID
    public Optional<Article> getArticleById(Integer id) {
        return articleRepo.findById(id);
    }

    // Update an article
    public Article updateArticle(Integer id, Article articleDetails) {
        Article article = articleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));

        article.setName(articleDetails.getName());
        article.setCategory(articleDetails.getCategory());
        article.setCreatedDate(articleDetails.getCreatedDate());
        article.setCreatedName(articleDetails.getCreatedName());
        return articleRepo.save(article);
    }

    // Delete an article
    public void deleteArticle(Integer id) {
        Article article = articleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        articleRepo.delete(article);
    }
}
