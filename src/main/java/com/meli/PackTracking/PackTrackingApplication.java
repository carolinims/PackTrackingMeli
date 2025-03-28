package com.meli.PackTracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PackTrackingApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(PackTrackingApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PackTrackingApplication.class, args);
		
		String intro = """
				\n
				    *******         *******     ****************     ****                 ****
				   *******         *******     ****************     ****                 ****
				  *******         *******     ****************     ****                 ****
				 *******         *******     ****************     ****                 ****
				*******         *******     ****************     ****                 ****
				****   **     **   ****     ****                 ****                 ****
				****    **   **    ****     ****                 ****                 ****
				****     ** **     ****     ****                 ****                 ****
				****      **       ****     ************         ****                 ****
				****               ****     ************         ****                 ****
				****               ****     ****                 ****                 ****
				****               ****     ****                 ****                 ****
				****               ****     ****************     ****************     ****
				****               ****     ****************     ****************     **** 



                ------------------------------------------------
                          INITIALIZING Pack Tracking SYSTEM...
                ------------------------------------------------
                
                A long time ago, in a warehouse far, far away...
                
                The galaxy of logistics was in chaos. Packages were lost, 
                tracking numbers were scattered, and delivery times were 
                a mystery. But then, a new hope emerged...
                
                The Pack Tracking MELI SYSTEM, the ultimate force in shipment 
                tracking, is now ONLINE. May the logistics be with you!
                
                
                ------------------------------------------------
                          STARTED...
                ------------------------------------------------
                
                """;

        logger.info(intro);
	}
	 
}
