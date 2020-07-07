package com.annotation;

/**
 * 测试运行时注解
 */
@ClassInfo("Test Class")
public class TestRuntimeAnnotation {

	@FieldInfo(value = { 1, 2 })
	public String	fieldInfo	= "FiledInfo";

	@FieldInfo(value = { 10086 })
	public int		i			= 100;
	
	
	public int		y			= 100;

	@MethodInfo(name = "BlueBird", data = "Big")
	public static String getMethodInfo() {
		return TestRuntimeAnnotation.class.getSimpleName();
	}
}
