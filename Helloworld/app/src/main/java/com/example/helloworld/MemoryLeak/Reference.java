package com.example.helloworld.MemoryLeak;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by hanchenghai on 2018/3/14.
 */

public class Reference {

    public static void main(String[] args) {
        //强引用
        String str = new String("abc");

        //gc 只会回收堆内存  如果str 是 字面量赋值那么就是放在了一个常量池中 Data区中不会被销毁
        //String str = "abc";


        //软引用 内存不足时会释放
        SoftReference<String> softReference = new SoftReference<String>(str);


        //弱引用 GC时候就会回收
        WeakReference<String> weakReference = new WeakReference<String>(str);

        str = null;
        System.out.println("强引用" + str);

        softReference.clear();
        System.out.println("软引用" + softReference.get());

        //weakReference.clear();
        System.gc();
        System.out.println("弱引用" + weakReference.get());


        //虚引用 用的不多 判断对象是否被回收
        //PhantomReference<String> phantomReference = new PhantomReference<>(str,q)

    }


    //常见的内存泄露
    /*
    * 1. 内部类会隐式持有外部类的引用
    *       解决方法：1.内部类声明为静态内部类
     *               2.改为外部类
     *               3.改为弱引用
     *2. 非静态内部类的静态实例 生命周期和Activity没关系，所以释放时机不同
    *
    * 3.Context 上下文引起的内存泄露 单例持有
    * */


    //heap 分析  hprof 文件的分析


}
