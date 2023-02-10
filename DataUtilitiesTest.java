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
	//testing that the correct value is returned using positive numbers
	@Test
	public void test1ColumnTotal() {
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
	     assertEquals(23.5, result, 0.000000001d);
 	}   
	
	//testing that the correct value is returned using negative numbers
	@Test
	public void test2ColumnTotal() {
	//setup for mocking 
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	         {
	        	 //creating table using mocked values, looping through arraysr 
	        	 double[] rows = {-1.0, -1.0, -1.0, -1.0, -1.0};
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
	     assertEquals(-5.0, result, 0.000000001d);
 	}

	//testing that the correct value is returned using zero's
	@Test
	public void test3ColumnTotal() {
	//setup for mocking 
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	         {
	        	 //creating table using mocked values, looping through arraysr 
	        	 double[] rows = {0.0, 0.0, 0.0, 0.0, 0.0};
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
	     assertEquals(0.0, result, 0.000000001d);
 	}
	
	//Testing that the correct exception is thrown
 	@Test (expected = InvalidParameterException.class)
 	public void test4ColumnTotal() {
	     final Values2D values = null;
	     DataUtilities.calculateColumnTotal(values, 0);
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
	
	//testing the row totals when the values are positive numbers
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
	    assertEquals(9.5, result, .000000001d);
	}
	
	//Test to check for correct exception thrown, 
	@Test (expected = InvalidParameterException.class)
    	public void test2calculateRowTotal() {
        	final Values2D values = null;
        	DataUtilities.calculateRowTotal(values, 0);
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
	//	        	double[] columns = {7.5,2.5};
	//	            allowing(valuesNew).getColumnCount();
	//	            will(returnValue(2));
	//	            for(int i = 0; i < columns.length; i++) {
	//	            	allowing(valuesNew).getValue(0, i);
	//	            	will(returnValue(columns[i]));
	//	            }
	//	            allowing(valuesNew).getColumnCount();
	//	            will(returnValue(2));
	//	        }
	//	    });
	//	    double result = DataUtilities.calculateRowTotal(valuesNew, -1);
	//	    // verify
	//	    assertEquals(result, 0, .000000001d);
	//	}
	
	//Testing when the values are negative
	@Test
	public void test4calculateRowTotal() {
		Mockery mockingContext = new Mockery();
		final Values2D values2 = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {{
			//set amount of columns (2 columns, 1 row)
			one(values2).getColumnCount();
			will(returnValue(3));
			//create values within the table (all negative numbers)
			one(values2).getValue(0, 0);
			will(returnValue(-1));
			one(values2).getValue(0, 1);
			will(returnValue(-3));
			one(values2).getValue(0, 2);
			will(returnValue(-4));
		}
		});
		double result = DataUtilities.calculateRowTotal(values2, 0);
		// verify
		assertEquals(-8, result, .000000001d);
		}
		
		
	//Testing when the values are 0
	@Test
	public void test5calculateRowTotal() {
		Mockery mockingContext = new Mockery();
		final Values2D values2 = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {{
			//set amount of columns (2 columns, 1 row)
			one(values2).getColumnCount();
			will(returnValue(2));
			//create values within the table (all equal to 0)
			one(values2).getValue(0, 0);
			will(returnValue(0));
			one(values2).getValue(0, 1);
			will(returnValue(0));
		}
		});
		double result = DataUtilities.calculateRowTotal(values2, 0);
		// verify
		assertEquals(0, result, .000000001d);
		}
	
	//End of Test Cases for METHOD 2

	// START TEST CASES FOR METHOD 3 - createNumberArray
	 
	//testing that using valid double values returns Number[] array
	 @Test
	 public void test1CreateNumberArray() {
	     double[] data = {1.5, 2.5, 3.5, 4.5, 5.5};
	     java.lang.Number[] result = DataUtilities.createNumberArray(data);
	     
	     assertTrue(result instanceof java.lang.Number[]);        
	 }
	 
	 //Testing that the method returns a Number[] array returns correct values using positive numbers
	 @Test
	 public void test2CreateNumberArray() {
	     double[] data = {1.5, 2.5, 3.5, 4.5, 5.5};
	     java.lang.Number[] result = DataUtilities.createNumberArray(data);
	     
	     Assert.assertArrayEquals(new Number[]{1.5, 2.5, 3.5, 4.5, 5.5}, result);
	 }
	 
	//Testing that the method returns a Number[] array returns correct values using negative numbers
	@Test
	public void test3CreateNumberArray() {
		double[] data = {-1.0, -1.0, -1.0};
		java.lang.Number[] result = DataUtilities.createNumberArray(data);
		     
		Assert.assertArrayEquals(new Number[]{-1.0, -1.0, -1.0}, result);
	}
	
	//Testing that the method returns a Number[] array returns correct values using zero's
	@Test
	public void test4CreateNumberArray() {
		double[] data = {0.0, 0.0, 0.0};
		java.lang.Number[] result = DataUtilities.createNumberArray(data);
			     
		Assert.assertArrayEquals(new Number[]{0.0, 0.0, 0.0}, result);
	}
	
	//testing that correct exception is thrown
	@Test (expected = InvalidParameterException.class)
	public void test5CreateNumberArray() {
		double[] data = null;
	    DataUtilities.createNumberArray(data);
	}
	 
	//END of test cases for method 3

	//START of test cases for method 4
	//Testing that the method returns a Number[][] array 
	@Test
	public void test1CreateNumberArray2D(){
		double[][] test =  {{1.0,1.0}, {1.0,1.0}};
		Number[][] result = DataUtilities.createNumberArray2D(test);
		assertTrue(result instanceof Number[][]);
	}
	
	//Testing that the method returns a Number[][] array returns correct values using positive numbers
	@Test
	public void test2CreateNumberArray2D(){
		double[][] test =  {{1.0,1.0}, {1.0,1.0}};
		Number[][] result = DataUtilities.createNumberArray2D(test);
		Assert.assertArrayEquals(new Number[][]{{1.0,1.0}, {1.0,1.0}}, result);
	}
	
	//Testing that the method returns a Number[][] array returns correct values using negative numbers
	@Test
	public void test3CreateNumberArray2D(){
		double[][] test =  {{-1.0,-1.0}, {-1.0, -1.0}};
		Number[][] result = DataUtilities.createNumberArray2D(test);
		Assert.assertArrayEquals(new Number[][]{{-1.0, -1.0}, {-1.0, -1.0}}, result);
	}
		
	//Testing that the method returns a Number[][] array returns correct values using zero's
	@Test
	public void test4CreateNumberArray2D(){
		double[][] test =  {{0.0,0.0}, {0.0, 0.0}};
		Number[][] result = DataUtilities.createNumberArray2D(test);
		Assert.assertArrayEquals(new Number[][]{{0.0,0.0}, {0.0, 0.0}}, result);
	}
	
	//Test for number array using  NULL 
	@Test (expected = InvalidParameterException.class)
	public void test5CreateNumberArray2D() {
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
		assertEquals(0.3125, result.getValue(0).doubleValue(), .000000001d);
	}
	
	//Test cumulative percentage, using 0 as one of the values
	@Test
	public void getCumulativePercentagesTest2() {
		//before
		Mockery mockingContextKey = new Mockery();
		KeyedValues valuesNewKey = mockingContextKey.mock(KeyedValues.class);
		mockingContextKey.checking(new Expectations() {{
			//creating a KeyValue object, with two arrays
			String [] keyArray = {"Key1","Key2","Key3"};
			double[] values = {0,9,2};
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
		KeyedValues result = DataUtilities.getCumulativePercentages(valuesNewKey);
		assertEquals(0, result.getValue(0).doubleValue(), .000000001d);
		
	}
	
	//testing percentage using NULL object 
	@Test (expected = InvalidParameterException.class)
    	public void getCumulativePercentagesTestFail() {
		KeyedValues valuesKey = null;
		KeyedValues result = DataUtilities.getCumulativePercentages(valuesKey);
	}
	
	//END of test cases for METHOD 5
}
