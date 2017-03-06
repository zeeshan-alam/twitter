package com.simpletwitter.api;

import com.simpletwitter.domain.Tweet;
import com.simpletwitter.service.TweetService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "/api", description = "Twitter rest api")
@RequestMapping(path="/api")
public class TwitterController {


    private TweetService tweetService;

    @Autowired
    public TwitterController(TweetService tweetService){
        this.tweetService = tweetService;
    }

    @ApiOperation(value = "Create tweet", notes = "Create tweet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Tweet has been created"),
            @ApiResponse(code = 400, message = "The server cannot process the request due to a client error, eg missing details in the request."),
            @ApiResponse(code = 500, message = "Something is wrong with services")
    })
    @RequestMapping(method = RequestMethod.POST , path = "/users/{userId}/tweets", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTweet(@ApiParam(value = "user id") @PathVariable String userId, @ApiParam(value = "tweet") @RequestBody String message){

            if (message.length() > 140) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            tweetService.createTweet(userId, message);
            return  ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @ApiOperation(value = "Follow user", notes = "Follow user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Follow user operation is successful"),
            @ApiResponse(code = 400, message = "The server cannot process the request due to a client error, eg missing details in the request."),
            @ApiResponse(code = 500, message = "Something is wrong with services")
    })
    @RequestMapping(method = RequestMethod.POST , path = "/users/{userId}/leaders")
    public ResponseEntity<?> followUser(@ApiParam(value = "user id")  @PathVariable String userId, @ApiParam(value = "leader id")  @RequestBody String leaderId){
        tweetService.followUser(leaderId,userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @ApiOperation(value = "Get tweets for user", notes = "Get tweets for user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get user's tweet operation is successful"),
            @ApiResponse(code = 400, message = "The server cannot process the request due to a client error, eg missing details in the request."),
            @ApiResponse(code = 500, message = "Something is wrong with services")
    })
    @RequestMapping(method = RequestMethod.GET , path = "/users/{userId}/tweets", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tweet>> getTweetByUser(@ApiParam(value = "user id")  @PathVariable String userId){
        List<Tweet> tweets = tweetService.getAllTweetForUser(userId);
        return ResponseEntity.ok().body(tweets);
    }


    @ApiOperation(value = "Get tweets of leaders who are being followed", notes = "Get tweets of leaders who are being followed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get leader's tweets operation is successful"),
            @ApiResponse(code = 400, message = "The server cannot process the request due to a client error, eg missing details in the request."),
            @ApiResponse(code = 500, message = "Something is wrong with services")
    })
    @RequestMapping(method = RequestMethod.GET , path = "/users/{userId}/leaders/tweets", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tweet>> getLeadersTweets(@ApiParam(value = "user id") @PathVariable String userId){
        List<Tweet> tweets = tweetService.getLeadersTweetsForUser(userId);
        return ResponseEntity.ok().body(tweets);
    }

}
