package com.unsafe;

import java.lang.reflect.Field;

import com.serizedemo.Person;

public class UnsafeMain {

	public static void main(String[] args) throws Exception {
		Field theUnsafe = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafe.setAccessible(true);
		sun.misc.Unsafe unsafe = (sun.misc.Unsafe) theUnsafe.get(null);
		Class<Person> personClass = Person.class;
		Field name = personClass.getField("name");
		Field age = personClass.getField("age");
		try {
			System.out.println("objectFieldOffset name -->" + unsafe.objectFieldOffset(name));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("objectFieldOffset age -->" + unsafe.objectFieldOffset(age));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("staticFieldOffset name -->" + unsafe.staticFieldOffset(name));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("staticFieldOffset age -->" + unsafe.staticFieldOffset(age));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
