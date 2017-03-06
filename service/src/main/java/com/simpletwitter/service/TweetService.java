package com.simpletwitter.service;


import com.simpletwitter.domain.Tweet;
import com.simpletwitter.domain.User;
import com.simpletwitter.repository.TweetRepository;
import com.simpletwitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetService {


    private UserRepository userRepository;
    private TweetRepository tweetRepository;
    public TweetService(){}

    @Autowired
    public TweetService(UserRepository userRepository, TweetRepository tweetRepository){
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public void createTweet(String userId, String message){

        Tweet tweet = new Tweet(message);
        tweetRepository.save(tweet);

        User user = userRepository.findOne(userId);
        if (user == null){
            user = new User(userId);
            userRepository.save(user);
        }

        user.getTweets().add(tweet);
        userRepository.save(user);
    }

    public List<Tweet> getAllTweetForUser(String userId) {

        List<Tweet> tweetList = new ArrayList<>();
        User user = userRepository.findOne(userId);
        if (user != null){
            tweetList = user.getTweets();
        }
        tweetList.sort((e2,e1)-> e1.getPostedAt().compareTo(e2.getPostedAt()));
        return tweetList;
    }

    public void followUser(String userId, String followerUserId){
        User user = userRepository.findOne(userId);
        if (user == null){
            user = new User(userId);
            userRepository.save(user);
        }

        User followerUser = userRepository.findOne(followerUserId);
        if (followerUser == null){
            followerUser = new User(followerUserId);
            userRepository.save(followerUser);
        }
        user.getFollowers().add(followerUser);
        userRepository.save(user);
    }

    public List<Tweet> getLeadersTweetsForUser(String userId) {

        final List<Tweet> tweetList = new ArrayList<>();
        User user = userRepository.findOne(userId);
        user.getLeaders().stream().forEach(e->e.getTweets().stream().forEach(tweetList::add));
        tweetList.sort((e2,e1)-> e1.getPostedAt().compareTo(e2.getPostedAt()));
        return tweetList;
    }
}
