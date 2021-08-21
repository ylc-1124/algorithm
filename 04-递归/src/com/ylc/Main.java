package com.ylc;

public class Main {
    public static void main(String[] args) {
        testFactorial();
    }

    static void testFactorial() {
        System.out.println(new Factorial().factorial2(4));
    }

    static void testHanoi() {
        new Hanoi().hanoi(3, "A", "B", "C");
    }

    static void testFib() {
        int n = 40;
        Fib fib = new Fib();
        Times.test("fib0",()->{
            System.out.println(fib.fib0(n));
        });
        Times.test("fib1",()->{
            System.out.println(fib.fib1(n));
        });
        Times.test("fib2",()->{
            System.out.println(fib.fib2(n));
        });
        Times.test("fib3",()->{
            System.out.println(fib.fib3(n));
        });
    }
    /**
     * 求 1+2+3+。。。。+n的和
     */
    static int sum(int n) {
        if (n <= 1) return n;

        return n + sum(n - 1);
    }
}
