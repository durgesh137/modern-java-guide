package com.modernjava.guide;

import com.modernjava.guide.dsa.linkedlist.LinkedListOperations;
import com.modernjava.guide.fundamentals.q1.JavaPassByValueOrReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModernJavaGuideApplication {

	public static void main(String[] args) {

		SpringApplication.run(ModernJavaGuideApplication.class, args);
		//uncommment lines to see the working
//		LinkedListOperations.simulateLinkedList();
		JavaPassByValueOrReference.conceptUnderstanding();
	}

}
