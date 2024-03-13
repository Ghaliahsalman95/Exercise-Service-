package com.example.exerciseservice.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data@AllArgsConstructor
public class NewsArticle {
    @NotNull(message = "ID can not be null")
    private String ID;
    //------------------------------
    @NotNull(message = "Title can not be null")
    @Size(max = 100,message = "Title length maximum 100")
    private String title;
    //---------------------------------
    @Size(min = 4,max = 20,message = "author length between 4-20")
    @NotNull(message = "author can not be null")
    private String author;
    //------------------------------
    @NotNull(message = "content can not be null")
    @Size(min = 100,message = "content must be more than 200 characters.")
    private String content;
    //--------------------------------------
    @NotNull(message = "Category Cannot be null")
    @Pattern(regexp = "politics|sports|technology")
    private String category;
    //-----------------------------
    @NotNull(message = "Image URL - Cannot be null.")
    private String imageUrl;
    //--------------------------------
    @AssertFalse(message = "isPublished Default false")
    private boolean isPublished;
   @JsonFormat(pattern = "yyyy-dd-mm")
    private Date publishDate;

}
