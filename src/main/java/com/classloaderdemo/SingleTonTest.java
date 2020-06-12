package com.classloaderdemo;

/**
 * 先给类static 属性初始化值，有父类先初始化父类的static，然后子类，所以1.首先得到singleTon=null，count1=0，count2=0；int默认值为0 2.初始化对象，执行构造函数，3从上到先属性赋值。
 * 
 * tips: 将private static SingleTon singleTon = new SingleTon(); public static int count1; public static int count2 = 2;
 * 
 * 改为 public static int count1; public static int count2 = 2; private static SingleTon singleTon = new SingleTon(); 执行结果就不一样了
 * 
 * @ClassName: SingleTon
 * @Description:
 * @author yalonz
 * @date 2020年5月6日
 *
 */
class SingleTon {
	//	static {
	//		System.out.println("------start-----");
	//		count3 = 3;
	//		System.out.println("------end-----");
	//	}

	private static SingleTon	singleTon	= new SingleTon();
	public int					count0		= 9;
	public static int			count1 ;
	public static int			count2		= 2;
	public static int			count3;

	static {
		System.out.println("------start-----");
		count3 = 3;
		System.out.println("------end-----");
	}

	private SingleTon() {
		System.out.println("count0=" + count0);
		System.out.println("count1=" + count1);
		System.out.println("count2=" + count2);
		System.out.println("count3=" + count3);
		System.out.println("---------1----------");
		count0++;
		count1++;
		count2++;
		count3++;
		System.out.println("count0=" + count0);
		System.out.println("count1=" + count1);
		System.out.println("count2=" + count2);
		System.out.println("count3=" + count3);
		System.out.println("----------2---------");
	}

	public static SingleTon getInstance() {
		return singleTon;
	}
}

public class SingleTonTest {
	public static void main(String[] args) {
		SingleTon singleTon = SingleTon.getInstance();
		System.out.println("count0=" + singleTon.count0);
		System.out.println("count1=" + singleTon.count1);
		System.out.println("count2=" + singleTon.count2);
		System.out.println("count3=" + singleTon.count3);

	}
}