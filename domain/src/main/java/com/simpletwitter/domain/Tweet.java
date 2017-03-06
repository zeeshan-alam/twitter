package com.simpletwitter.domain;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String tweetId;
    private String tweet;
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedAt;

    public Tweet(){}

    public Tweet(String tweet){
        this.tweet = tweet;
    }

    @ManyToOne
    @JoinTable(
            name="USER_TWEET",
            joinColumns = @JoinColumn( name="TWEET_ID"),
            inverseJoinColumns = @JoinColumn( name="USER_ID")
    )
    private User user;

    public String getTweetId() {
        return tweetId;
    }

    public String getTweet() {
        return tweet;
    }

    public String getPostedAt() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return  dateFormat.format(postedAt);
    }

    public String getPostedBy() {
        return  user.getUserId();
    }

    @PrePersist
    void polpulateDate(){
        postedAt = new Date();
    }

    @Override
    public String toString() {
        return "tweetId:"+tweetId+", tweet:"+tweet;
    }
}
