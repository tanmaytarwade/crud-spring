//package com.museum.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.museum.entity.Article;
//import com.museum.service.ArticleService;
//
//@RestController
//@RequestMapping("/article")
//public class ArticleController {
//	
//	@Autowired
//	private ArticleService articleService;
//
//	//http://localhost:8085/article/add
//	@PostMapping("/add")
//	public ResponseEntity<?> addArtcle(@RequestBody Article article){
//		try {
//			Article savedArticle = articleService.addArticle(article);
//			return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//}
package com.museum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.museum.entity.Article;
import com.museum.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // Add a new article
    @PostMapping("/add")
    public ResponseEntity<?> addArticle(@RequestBody Article article) {
        try {
            Article savedArticle = articleService.addArticle(article);
            return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Retrieve all articles
    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    // Retrieve an article by ID
 // Retrieve an article by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Integer id) {
        try {
            Optional<Article> article = articleService.getArticleById(id);
            if (article.isPresent()) {
                return new ResponseEntity<>(article.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Article not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Update an article
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Integer id, @RequestBody Article articleDetails) {
        try {
            Article updatedArticle = articleService.updateArticle(id, articleDetails);
            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete an article
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Integer id) {
        try {
            articleService.deleteArticle(id);
            return new ResponseEntity<>("Article deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

