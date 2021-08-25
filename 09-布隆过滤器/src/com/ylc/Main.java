package com.ylc;

public class Main {
    public static void main(String[] args) {
        BloomFilter<Integer> bf = new BloomFilter<>(1000000, 0.01);
        for (int i = 1; i <= 500; i++) {
            bf.put(i);
        }
        for (int i = 1; i <= 500; i++) {
            System.out.println(bf.contains(i));
        }
    }
}
