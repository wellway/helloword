package com.classloaderdemo;


/**
 * 演示类加载器的树状组织结构
 * @author qiqi
 *
 */
public class ClassLoaderTree {
	public static void main(String[] args) {
		ClassLoader loader = ClassLoaderTree.class.getClassLoader(); 
        while (loader != null) { 
            System.out.println(loader.toString()); 
            loader = loader.getParent(); 
        } 
	}
}
