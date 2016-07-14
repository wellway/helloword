package com.filedemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamDemo {
	public static void main(String[] args) {

		int i = 319874;
		try {

			// create a new file with an ObjectOutputStream
			FileOutputStream out = new FileOutputStream("D:/test.txt");
			ObjectOutputStream oout = new ObjectOutputStream(out);

			// write something in the file
			oout.writeInt(i);
			oout.writeInt(1653984);

			// flush the stream
			oout.flush();

			// close the stream
			oout.close();

			// create an ObjectInputStream for the file we created before
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					"d:/test.txt"));

			// read and print an int
			System.out.println("" + ois.readInt());

			// read and print an int
			System.out.println("" + ois.readInt());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
