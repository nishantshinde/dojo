package nishantshinde.dojo.core;

import java.io.ByteArrayOutputStream;

public class ArraysTest {

	public static void main(String[] args) {

		int[] array1 = new int[10];
		ByteArrayOutputStream bos1 = new ByteArrayOutputStream(10);  
		System.out.println(bos1.size());
		bos1.write(10);
		System.out.println(bos1.size());

	}

}
