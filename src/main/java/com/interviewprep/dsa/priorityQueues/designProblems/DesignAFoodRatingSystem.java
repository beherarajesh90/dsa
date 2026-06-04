package com.interviewprep.dsa.priorityQueues.designProblems;

import java.util.*;

//https://leetcode.com/problems/design-a-food-rating-system/description/
public class DesignAFoodRatingSystem {
}
class FoodRatings {

    static class Food {
        String name;
        int rating;

        Food(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }
    }

    private Map<String, Integer> ratingMap;
    private Map<String, String> cuisineMap;
    private Map<String, PriorityQueue<Food>> cuisineHeap;

    public FoodRatings(
            String[] foods,
            String[] cuisines,
            int[] ratings) {

        ratingMap = new HashMap<>();
        cuisineMap = new HashMap<>();
        cuisineHeap = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {

            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            ratingMap.put(food, rating);
            cuisineMap.put(food, cuisine);

            cuisineHeap
                    .computeIfAbsent(
                            cuisine,
                            k -> new PriorityQueue<>(
                                    (a, b) -> {
                                        if (a.rating == b.rating) {
                                            return a.name.compareTo(b.name);
                                        }
                                        return b.rating - a.rating;
                                    }
                            ))
                    .offer(new Food(food, rating));
        }
    }

    public void changeRating(
            String food,
            int newRating) {

        ratingMap.put(food, newRating);

        String cuisine = cuisineMap.get(food);

        cuisineHeap.get(cuisine)
                .offer(new Food(food, newRating));
    }

    public String highestRated(String cuisine) {

        PriorityQueue<Food> heap =
                cuisineHeap.get(cuisine);

        while (true) {

            Food top = heap.peek();

            if (ratingMap.get(top.name) == top.rating) {
                return top.name;
            }

            heap.poll();
        }
    }
}
