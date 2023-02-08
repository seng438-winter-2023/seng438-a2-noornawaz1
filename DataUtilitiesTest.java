package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.*;
import org.junit.*;


public class DataUtilitiesTest {

	
	
	//START TEST CASES FOR METHOD 1 - calculateColumnTotal
	//testing using mocking
	@Test
	public void calculateColumnTotalFiveValues() {
	//setup for mocking 
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	         {
	        	 //creating table using mocked values, looping through arraysr 
	        	 double[] rows = {7.5,2.5,3.5,4.5,5.5};
		         allowing(values).getColumnCount();
		         will(returnValue(2));
		         for(int i = 0; i < rows.length; i++) {
		           	allowing(values).getValue(i, 0);
		           	will(returnValue(rows[i]));
		         }
		         //setting the amount of rows 
		         allowing(values).getRowCount();
		         will(returnValue(5));
	         }
	     });
	     //testing the result we get with the expected result
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals(result, 23.5, 0.000000001d);
 	}    
	//Testing using table that has null value
 	@Test (expected = InvalidParameterException.class)
 	public void calculateColumnTotalInvalidTable() {
	     final Values2D values = null;
	     double result = DataUtilities.calculateColumnTotal(values, 0);
 	}
	 
	 
 	 //TEST DOES NOT WORK BECAUSE OF 
	 //MOCKING -> Mocking Does not allow us to use this test, 
	 //When testing an invalid input, using mocking throws an error as it does not
	 //exist in the mocked table we created
// @Test
//	 public void calculateColumnTotalInvalidColumn() {
//	     Mockery mockingContext = new Mockery();
//	     final Values2D valuesNew = mockingContext.mock(Values2D.class);
//	     mockingContext.checking(new Expectations() {
//	         {
//	             allowing(valuesNew).getRowCount();
//	             will(returnValue(5));
//	             allowing(valuesNew).getValue(0, 0);
//	             will(returnValue(7.5));
//	             allowing(valuesNew).getValue(1, 0);
//	             will(returnValue(2.5));
//	         }
//	     });
//	     
//	     
//	     //    Sending a negative column number (invalid)        
//	     double result = DataUtilities.calculateColumnTotal(valuesNew, -5);
//	     
//	     //    Return Value should be 0
//	     assertEquals(result, 0, 0.000000001d);
// }
 
// END OF TEST CASES FOR METHOD 1
 
 //Start of Test Cases for METHOD 2
	
	//Using mocking to simulate to a table
	@Test
	public void  test1calculateRowTotal() {
		
		//create mocking table 
		Mockery mockingContext = new Mockery();
	    	final Values2D values = mockingContext.mock(Values2D.class);
	    	mockingContext.checking(new Expectations() {
		{
		//set amount of columns (2 columns, 1 row)
		    one(values).getColumnCount();
		    will(returnValue(2));
		    //create values within the table
		    one(values).getValue(0, 0);
		    will(returnValue(7.0));
		    one(values).getValue(0, 1);
		    will(returnValue(2.5));
		}
	    });
	    //send to function and verify
	    double result = DataUtilities.calculateRowTotal(values, 0);
	    assertEquals(result, 9.5, .000000001d);
	}
	
	//Test to check for correct exception thrown, 
	@Test (expected = InvalidParameterException.class)
    	public void test2calculateRowTotal() {
        	final Values2D values = null;
        	double result = DataUtilities.calculateRowTotal(values, 0);
    	}
	
//	@Test TEST DOES NOT WORK BECAUSE OF MOCKING -> Mocking Does not allow us to use
	//When testing an invalid input, using mocking throws an error as it does not
	//exist in the mocked table we created
//	public void test3calculateRowTotal() {
//		Mockery mockingContext = new Mockery();
//	    final Values2D valuesNew = mockingContext.mock(Values2D.class);
//	    mockingContext.checking(new Expectations() {
//	        {
//	        
//	        	double[] coloumns = {7.5,2.5};
//	            allowing(valuesNew).getColumnCount();
//	            will(returnValue(2));
//	            for(int i = 0; i < coloumns.length; i++) {
//	            	allowing(valuesNew).getValue(0, i);
//	            	will(returnValue(coloumns[i]));
//	            }
//	            allowing(valuesNew).getColumnCount();
//	            will(returnValue(2));
//	        }
//	    });
//	    double result = DataUtilities.calculateRowTotal(valuesNew, -1);
//	    // verify
//	    assertEquals(result, 0, .000000001d);
//	}
//	
	
//End of Test Cases for METHOD 2

// START TEST CASES FOR METHOD 3 - createNumberArray
	 
	//testing using valid double values within array
	 @Test
	 public void createNumberArrayValid() {
	     double[] data = {1.5, 2.5, 3.5, 4.5, 5.5};
	     java.lang.Number[] result = DataUtilities.createNumberArray(data);
	     
	     assertTrue(result instanceof java.lang.Number[]);        
	 }
	 
	 
	 //testing 
	 @Test (expected = InvalidParameterException.class)
	 public void createNumberArrayInvalidData() {
	     double[] data = null;
	     java.lang.Number[] result = DataUtilities.createNumberArray(data);
	 }
	 
//END of test cases for method 3
	 
//START of test cases for method 4
	//Test for number array with double values -> Number Array 
	@Test
	public void test1PasscreateNumberArray2D(){
		double[][] test =  {{1.0,1.0}, {1.0,1.0}};
		Number[][] result = DataUtilities.createNumberArray2D(test);
		assertTrue(result instanceof Number[][]);
	}
	
	
	//Test for number array using  NULL 
	@Test (expected = InvalidParameterException.class)
	public void test3secondcreateNumberArray2d() {
		double[][] test = null;
		Number[][] result = DataUtilities.createNumberArray2D(test);
		assertTrue(result instanceof Number[][]);
	}
	
//END of test cases for METHOD 4
	
//START of test cases for METHOD 5
	//Test cumulative percentage, using mocking
	@Test
	public void getCumulativePercentagesTest() {
		//before
		Mockery mockingContextKey = new Mockery();
		KeyedValues valuesNewKey = mockingContextKey.mock(KeyedValues.class);
		mockingContextKey.checking(new Expectations() {{
			//creating a KeyValue object, with two arrays
			String [] keyArray = {"Key1","Key2","Key3"};
			double[] values = {5,9,2};
			//loop through and create object using arrays from before
			for(int i= 0; i < values.length;i++) {
				allowing(valuesNewKey).getValue(i);
				will (returnValue(Double.valueOf(values[i])));
				allowing(valuesNewKey).getKey(i);
				will(returnValue(keyArray[i]));
			}
			//set item count amount, and Key list values 
			allowing(valuesNewKey).getItemCount();
			will(returnValue(3));
			allowing(valuesNewKey).getKeys();
			will(returnEnumeration(Arrays.asList(keyArray)));
		}
		});
		//testing using example given in the javadocs, comparing 
		//key value to the percentage that should be returned
		KeyedValues result = DataUtilities.getCumulativePercentages(valuesNewKey);
		assertEquals(result.getValue(0).doubleValue(), 0.3125, .000000001d);
		assertEquals(result.getValue(1).doubleValue(), 0.875, .000000001d);
		assertEquals(result.getValue(2).doubleValue(), 1.0, .000000001d);
	}
	
	//testing percentage using NULL object 
	@Test (expected = InvalidParameterException.class)
    	public void getCumulativePercentagesTestFail() {
		KeyedValues valuesKey = null;
		KeyedValues result = DataUtilities.getCumulativePercentages(valuesKey);
	}
	
//END of test cases for METHOD 5
}
