package com.ylc.ks;

/**
 * 物品类
 */
public class Article {
    public int weight;
    public int value;
    public double valueDensity; //价值密度

    public Article(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.valueDensity = ((double) value) / weight;
    }

    @Override
    public String toString() {
        return "Article{" +
                "weight=" + weight +
                ", value=" + value +
                ", valueDensity=" + valueDensity +
                '}';
    }
}
