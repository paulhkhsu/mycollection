package com.myrest.mockito;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;




import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTester {
	
   //@InjectMocks annotation is used to create and inject the mock object
   @InjectMocks 
   MathApplication mathApplication = new MathApplication();

   //@Mock annotation is used to create the mock object to be injected
   @Mock
   CalculatorService calcService;

   @Test
   public void testAdd(){
      //add the behavior of calc service to add two numbers
		when(calcService.add(10.0, 20.0)).thenReturn(30.00);

      //test the add functionality
      Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
   }
   
   @Test
   public void iterator_will_return_hello_world(){
    //arrange
    Iterator i=(Iterator) mock(Iterator.class);
    when(i.next()).thenReturn("Hello").thenReturn("World");
    //act
    String result=i.next()+" "+i.next();
    //assert
    assertEquals("Hello World", result);
   }
}