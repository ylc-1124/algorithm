package com.ylc.ks;

import java.util.*;

/**
 * 0-1背包问题
 */
public class Knapsack {
    public static void main(String[] args) {
        //价值主导
        select("价值主导", ((o1, o2) -> o2.value - o1.value));  //价值从小到大排序
        select("重量主导", ((o1, o2) -> o1.weight - o2.weight));  //价值从小到大排序
        select("价值密度主导",((o1, o2) -> Double.compare(o2.valueDensity ,o1.valueDensity)));  //价值从小到大排序
    }

    static void select(String title,Comparator<Article> cmp) {
        Article[] articles = new Article[]{
                new Article(35, 10), new Article(30, 40),
                new Article(60, 30), new Article(50, 50),
                new Article(40, 35), new Article(10, 40),
                new Article(25, 30)
        };
        Arrays.sort(articles, cmp);
        int capacity = 150, weight = 0, value = 0;
        List<Article> selectedArticle = new LinkedList<>();
        for (int i = 0; i < articles.length && weight < capacity; i++) {
            int newWeight = weight + articles[i].weight;
            if (newWeight > capacity) continue;
            weight = newWeight;
            value += articles[i].value;
            selectedArticle.add(articles[i]);
        }
        System.out.println("【" + title + "】");
        System.out.println("总价值:" + value);
        System.out.println("总重量:" + weight);
        for (Article article : selectedArticle) {
            System.out.println(article);
        }

    }
}
