package com.knits.sbs.course.test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPasswordEncoder {

	public static void main(String[] args) {
		String encoded=new BCryptPasswordEncoder().encode("stefan0");
		System.out.println(encoded);
	}

}
