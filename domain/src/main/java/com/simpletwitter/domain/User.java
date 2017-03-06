package com.simpletwitter.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    public User() {}

    public User(String userId) {
        this.userId = userId;
    }

    @Id
    private String userId;

    public List<Tweet> getTweets() {
        return tweets;
    }

    public Set<User> getFollowers() {
        return followers;
    }
    public Set<User> getLeaders() {
        return leaders;
    }

    @ManyToMany
    @JoinTable(
            name="USER_TWEET",
            joinColumns = @JoinColumn( name="USER_ID"),
            inverseJoinColumns = @JoinColumn( name="TWEET_ID")
    )
    private List<Tweet> tweets = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="USER_FOLLOWER",
            joinColumns = @JoinColumn( name="USER_ID"),
            inverseJoinColumns = @JoinColumn( name="FOLLOWER_ID")
    )
    private Set<User> leaders = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name="USER_FOLLOWER",
            joinColumns = @JoinColumn( name="FOLLOWER_ID"),
            inverseJoinColumns = @JoinColumn( name="USER_ID")
    )
    private Set<User> followers = new HashSet<>();

    @Override
    public String toString() {
        return "userId:"+userId;
    }

    public String getUserId() {
        return userId;
    }
}
