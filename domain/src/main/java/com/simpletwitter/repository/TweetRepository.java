package com.simpletwitter.repository;

import com.simpletwitter.domain.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<Tweet,String> {
}
