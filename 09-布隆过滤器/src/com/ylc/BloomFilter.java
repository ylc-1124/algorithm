package com.ylc;

public class BloomFilter<T> {
    /**
     * 二进制位的个数
     */
    private int bitSize;
    /**
     * 二进制向量
     */
    private long[] bits;
    /**
     * 哈希函数的个数
     */
    private int hashSize;

    /**
     * @param n 数据规模
     * @param p 误判率，取值范围(0,1)
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("argument is wrong");
        }
        double ln2 = Math.log(2);
        bitSize = (int) (-(n * Math.log(p)) / (ln2 * ln2));
        hashSize = (int) (bitSize * ln2 / n);
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];
    }

    /**
     * 添加元素
     */
    public boolean put(T value) {
        nullCheck(value);
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        boolean result = false;
        for (int i = 1; i <= hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            //生成索引
            int index = combinedHash % bitSize;
            //设置index位置为1
            if (set(index)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 设置index位置的二进制位为1
     */
    private boolean set(int index) {
        //对应long值
        long value = bits[index / Long.SIZE];
        int bitValue = 1 << (index % Long.SIZE);
        bits[index / Long.SIZE] = value | bitValue;
        return (value & bitValue) == 0;
    }

    /**
     * 查询index位置的二进制位值
     *
     * @return true 为 1  false 为 0
     */
    private boolean get(int index) {
        //对应long值
        long value = bits[index / Long.SIZE];
        return (value & (1 << (index % Long.SIZE))) != 0;
    }

    /**
     * 判断一个元素是否存在
     */
    public boolean contains(T value) {
        nullCheck(value);
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        for (int i = 1; i <= hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            //生成索引
            int index = combinedHash % bitSize;
            //查询index位置是否为0
            if (!get(index)) return false;

        }
        return true;
    }

    private void nullCheck(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null");
        }
    }
}
