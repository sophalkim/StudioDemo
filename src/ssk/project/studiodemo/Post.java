package ssk.project.studiodemo;


public class Post {
    
    String subreddit;
    String title;
    String author;
    int points;
    int numComments;
    String permalink;
    String url;    
    String domain;
    String id;
    String thumbnail;
     
    String getDetails(){
        String details=author
                       +" posted this and got "
                       +numComments
                       +" replies";
        return details;    
    }
     
    String getTitle(){
        return title;
    }
     
    String getScore(){
        return Integer.toString(points);
    }
    
    String getthumbnail() {
    	return thumbnail;
    }
}
