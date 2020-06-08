package com.spring.cs.country;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.spring.cs.common.configuration.ApplicationCommonConfiguration;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Import(value = ApplicationCommonConfiguration.class)
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
        //System.out.println( "Hello World!" );
    }
}
