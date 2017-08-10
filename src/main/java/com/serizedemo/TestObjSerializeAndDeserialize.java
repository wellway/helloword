package com.serizedemo;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Arrays;

import com.sun.corba.se.impl.ior.ByteBuffer;

/** 
 * url: http://www.cnblogs.com/xdp-gacl/p/3777987.html
 * <p>ClassName: TestObjSerializeAndDeserialize<p>
 * <p>Description: 测试对象的序列化和反序列<p>
 * @author xudp
 * @version 1.0 V
 * @createTime 2014-6-9 下午03:17:25
 */
public class TestObjSerializeAndDeserialize {

    public static void main(String[] args) throws Exception {
    	printWriterTest();
    	
    	
    	
       /* 
        * SerializeByte();
        SerializePerson();//序列化Person对象
        Person p = DeserializePerson();//反序列Perons对象
        System.out.println(MessageFormat.format("name={0},age={1},sex={2}",
                                                 p.getName(), p.getAge(), p.getSex()));
        */
    }
    
    /**
     * MethodName: SerializePerson 
     * Description: 序列化Person对象
     * @author xudp
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void SerializePerson() throws FileNotFoundException,
            IOException {
        Person person = new Person();
        person.setName("gacl");
        person.setAge(25);
        person.setSex("男");
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("E:/Person.txt")));
        oo.writeObject(person);
        System.out.println("Person对象序列化成功！");
        oo.close();
    }

    /**
     * MethodName: DeserializePerson 
     * Description: 反序列Perons对象
     * @author xudp
     * @return
     * @throws Exception
     * @throws IOException
     */
    private static Person DeserializePerson() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("E:/Person.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        return person;
    }

    private static void SerializeByte() throws IOException{
    	FileOutputStream fos = new FileOutputStream( new File("E:/afile.txt"));
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeInt(3);
        dos.writeChar(1);
        dos.close();
        fos.close();
    }
    
    private static void printWriterTest(){
    	FileWriter writer;
    	
    	File file = new File("E:/file.txt");
    	if(file.exists())
    		file.delete();    	
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	try {
    		writer = new FileWriter(file,true);
			
			writer.write(addRightEmpty("HEADER",6));
			writer.write("|");
			writer.write(addRightEmpty("MTP1.00",8));
			writer.write("|");
			writer.write(addLeftEmpty("2298573",10));	
			writer.write("|");
			writer.write(addLeftEmpty("5879",5));	
			writer.write("|");
			writer.write(addLeftEmpty("",8));
			writer.write("|");
			writer.write(addLeftEmpty("XSHG01",6));	
			writer.write("|");
			writer.write(addLeftEmpty("20160722-11:22:33.920",21));
			writer.write("|");
			writer.write(addLeftEmpty("0",1));	
			writer.write(addLeftEmpty("",1));	
			writer.write("|");
			writer.write(addRightEmpty("T100",8));	
			writer.write("\r");
			
			writer.write(addLeftEmpty("MD001",5));
			writer.write("|");
			writer.write(addLeftEmpty("000001",6));
			writer.write("|");
			writer.write(addRightEmpty("*ST黑豹",8));
			writer.write("|");
			writer.write(addLeftEmpty("80569330",16));
			writer.write("|");
			writer.write(addLeftEmpty("100584729342.30",16));
			writer.write("|");
			writer.write(addLeftEmpty("3039.0091",11));
			writer.write("|");
			writer.write(addLeftEmpty("3038.1182",11));
			writer.write("|");
			writer.write(addLeftEmpty("3039.2700",11));
			writer.write("|");
			writer.write(addLeftEmpty("3021.2892",11));
			writer.write("|");
			writer.write(addLeftEmpty("3021.2892",11));
			writer.write("|");
			writer.write(addLeftEmpty("0.0000",11));
			writer.write("|");
			writer.write(addLeftEmpty("",8));
			
			writer.write("|");
			writer.write(addRightEmpty("11:22:27.570",12));
			writer.write("\r");
			
			writer.write(addLeftEmpty("TRAIL",5));

         writer.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static String addRightEmpty(String str,int n) throws UnsupportedEncodingException{
    	byte[] targert = str.getBytes("GBK");
    	byte[] dest = new byte[n];
    	Arrays.fill(dest, (byte)32);
    	
    	System.arraycopy(targert, 0, dest, 0, targert.length);
    	
    	return new String(dest,"GBK");
    }
    public static String addLeftEmpty(String str,int n) throws UnsupportedEncodingException{
    	byte[] targert = str.getBytes("GBK");
    	byte[] dest = new byte[n];
    	Arrays.fill(dest, (byte)32);
    	int len =  targert.length;
    	System.arraycopy(targert, 0, dest, n-len, len);
    	
    	return new String(dest,"GBK");
    }
    public static char byteToChar(byte[] b) { 
        char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF)); 
        return c; 
    }
}