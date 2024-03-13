package com.example.exerciseservice.Service;

import com.example.exerciseservice.Model.NewsArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Service
public class NewsArticleService {//business layer
    ArrayList<NewsArticle> newsArticles=new ArrayList<>();

    public ArrayList<NewsArticle>getAll(){
        return newsArticles;
    }
    //--------------------------
    public void add(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }
    public boolean upade(String ID,NewsArticle newsArticle){
        for (int i=0;i<newsArticles.size();i++){
            if (newsArticles.get(i).getID().equalsIgnoreCase(ID)){
                newsArticles.set(i,newsArticle);
                return true;
            }
        }return false;

    }

    public boolean delete(String ID){
        for (NewsArticle newsArticle:newsArticles){
            if (newsArticle.getID().equalsIgnoreCase(ID)){
                newsArticles.remove(newsArticle);
                return true;
            }
        }return false;
    }

    //---------------------5. Publish NewsArticles.

    public NewsArticle pulish(String ID){
        for (NewsArticle newsArticle:newsArticles){
            if (newsArticle.getID().equalsIgnoreCase(ID)){
                newsArticle.setPublished(true);
                long currentTimeInMillis = System.currentTimeMillis();
//new Date(currentTimeInMillis)
                newsArticle.setPublishDate(new Date(currentTimeInMillis));
               // newsArticle.setPublishDate(LocalDate.now());//start publish
                return newsArticle;
            }
        }return null;
    }

    //--------------6. Get all Published NewsArticles.

    public ArrayList<NewsArticle>getPublished(){
        ArrayList<NewsArticle>publishList=new ArrayList<>();
        for (NewsArticle newsArticle:newsArticles){
            if (newsArticle.isPublished()){
                publishList.add(newsArticle);
            }
        }return publishList;
    }

    //------------7. Get NewsArticles by Category.

    public ArrayList<NewsArticle> getArticlesByCategory(String category ) {
        ArrayList <NewsArticle>categoryList=new ArrayList<>();
        for (NewsArticle newsArticle:newsArticles){
            if (newsArticle.getCategory().equalsIgnoreCase(category)){
                categoryList.add(newsArticle);
            }
        }return categoryList;
    }
}
