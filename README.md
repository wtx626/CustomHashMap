## 项目简介
最近看了各大互联网公司的面试经验贴，HashMap几乎是Java相关岗位的必考题，大部分题目都可以在源码的基础上找到答案，
本项目自己动手实现一个JDK7下的HashMap。
## 考虑方面
* HashMap的数据结构
* put get方法的实现
* Hash冲突的解决
* 红黑树（JDK8中用来处理HashMap冲突的实现）
* 高并发问题
## 技术路线
1. HashMap的3要素，Hash函数、数组和链表；
2. 对于一个key，迅速计算出数组中的index；
3. 要均匀分布，要较少碰撞。说白了，我们希望通过hash函数，让数据均匀分布在数组中，不希望大量数据发生碰撞，导致链表过长。那么怎么办到呢？也是利用位运算，通过对数据的二进制的位进行移动，让hash函数得到的数据散列开来，从而减低了碰撞的概率。