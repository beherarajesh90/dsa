package com.interviewprep.dsa.priorityQueues.designProblems;

import org.springframework.util.CollectionUtils;

import javax.swing.plaf.metal.MetalButtonUI;
import java.util.*;

//https://leetcode.com/problems/design-twitter/
public class DesignTwitter {

}

class Twitter {

    private static int timestamp = 0;
    private Map<Integer, Set<Integer>> follows;
    // {userId -> [[tweetId, timestamp], [tweetId, timestamp}
    private Map<Integer, List<int[]>> tweets;

    public Twitter() {
        follows = new HashMap<>();
        tweets = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, k -> new LinkedList<>()).addFirst(new int[]{tweetId,timestamp++});
    }

    public List<Integer> getNewsFeed(int userId) {

        // Each element in the heap: [timestamp, tweetId, userId, index in tweet list]
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> b[0] - a[0]);
        Set<Integer> followees = follows.getOrDefault(userId, new HashSet<>());
        //user is following himself
        followees.add(userId);

        List<Integer> result = new ArrayList<>();

        //add first tweet from each user
        int count = 0;
        for(int fId: followees){
            List<int[]> followeeTweets = tweets.getOrDefault(fId, new LinkedList<>());
            if(!followeeTweets.isEmpty() && count < 10){
                maxHeap.offer(new int[]{followeeTweets.getFirst()[1], followeeTweets.getFirst()[0], fId, 0});
                count++;
            }
            else if (count == 10) {
                //if count reached 10 return
                while (!maxHeap.isEmpty()){
                    result.add(maxHeap.poll()[1]);
                }
                return result;
            }
        }

        //if count less than 10, while res is not 10 or maxHeap is empty keep polling from heap and add next tweet
        while (result.size() < 10 && !maxHeap.isEmpty()){
            int[] tweet = maxHeap.poll();
            int indx = tweet[3];
            int uId = tweet[2];
            if (indx+1 < tweets.getOrDefault(uId, new LinkedList<>()).size()) maxHeap.offer(new int[]{tweets.get(uId).get(indx+1)[1], tweets.get(uId).get(indx+1)[0], uId, indx + 1});
            result.add(tweet[1]);
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId) return;
        follows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        follows.computeIfPresent(followerId, (k, v) -> {
            v.remove(followeeId);
            return v;
        });
    }
}
