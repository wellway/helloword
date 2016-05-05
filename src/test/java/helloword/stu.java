package helloword;

public class stu {
	public  static final long str=getStr();
	
	private static long getStr(){
		long s= System.currentTimeMillis();
		System.out.println(s);		
		return s;
	}
}
