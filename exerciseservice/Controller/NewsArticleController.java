package com.example.exerciseservice.Controller;

import com.example.exerciseservice.ApiResponse.ApiResponse;
import com.example.exerciseservice.Model.NewsArticle;
import com.example.exerciseservice.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/newsArticle")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;
    //-----------1. Get all NewsArticles.
@GetMapping("getAll")
    public ResponseEntity Getall(){
    if (newsArticleService.getAll().isEmpty()){
        return ResponseEntity.status(400).body(new ApiResponse("Array Empty"));
    }
        return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getAll());
    }
//---2. Add a NewsArticle---------------------------
    @PostMapping("/addArticle")
public ResponseEntity addArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors){
    if (errors.hasErrors()){
        return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
    }
    newsArticleService.add(newsArticle);
    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(newsArticle.getTitle()+" Added Successfully"));
}
//---------------3. Update a NewsArticle.---------------
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id,@RequestBody NewsArticle newsArticle,Errors errors){
    if (errors.hasErrors()){
        return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
    }
    if (newsArticleService.upade(id,newsArticle)){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(" NewsArticle ID "+id+" updated Successfully"));
    }
    return ResponseEntity.status(400).body(new ApiResponse("No ID "+id+"found"));
    }
    //------------------4. Delete a NewsArticle.------------------------
@DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
    if (newsArticleService.delete(id)){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("NewsArticle with "+id+" Deleted Successfully"));
    }
    return ResponseEntity.status(400).body(new ApiResponse("No ID "+id+"found to delete it "));

}
//---------------------5. Publish NewsArticles.
    @PutMapping("/publish/{id}")
public ResponseEntity publish(@PathVariable String id){
    if (newsArticleService.pulish(id)==null){
        return ResponseEntity.status(400).body(new ApiResponse("No ID "+id+"Found"));
    }
    return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.pulish(id));
}//----------------6. Get all Published NewsArticles.

    @GetMapping("/getPublish")
    public ResponseEntity getPublish(){
    if (newsArticleService.getPublished().isEmpty()){
        return ResponseEntity.status(400).body(new ApiResponse("there is No article published"));
    }return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getPublished());
    }

    //--------------7. Get NewsArticles by Category.
    @GetMapping("/getCategory/{category}")
    public ResponseEntity getCategory(@PathVariable String category){
    if (newsArticleService.getArticlesByCategory(category).isEmpty()){
        return ResponseEntity.status(400).body(new ApiResponse("No Article from this category "+category));
    }
    return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getArticlesByCategory(category));
    }
}
