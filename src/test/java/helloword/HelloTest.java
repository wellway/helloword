package helloword;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloTest {
public static void main(String[] args) {
	SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
	System.out.println(timeFormat.format(new Date()));
}
}
