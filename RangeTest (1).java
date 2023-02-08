package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }

    //CONTAINS() TESTING

    // testing a positive number thats inside the range and checking that it returns true
    @Test
    public void testContainsPositiveInsideRange() {
    	assertTrue(exampleRange.contains(0.34));
    }
    
    // testing a negative number thats inside the range and checking that it returns true
    @Test
    public void testContainsNegativeInsideRange() {
    	assertTrue(exampleRange.contains(-0.34));
    }
    
    // testing a positive number thats outside the range and checking that it returns false
    @Test
    public void testContainsPositiveOutsideRange() {
    	assertFalse(exampleRange.contains(12.32));
    }
    
    // testing a negative number thats outside the range and checking that it returns false
    @Test
    public void testContainsNegativeOutsideRange() {
    	assertFalse(exampleRange.contains(-12.32));
    }
    
    //INTERSECTS() TESTING
    
    //testing a range that's within the range being tested completely and verifying that it returns true
    @Test
    public void testIntersectsOverlappingSmaller() {
    	assertTrue(exampleRange.intersects(-0.5, 0.5));
    }
    
    //testing a range that overlaps completely over the range being tested and verifying that it returns true
    @Test
    public void testIntersectsOverlappingBigger() {
    	assertTrue(exampleRange.intersects(-1.5, 1.5));
    }
    
    //testing a range that has only its upper bound within the range being tested and verifying that it returns true
    @Test
    public void testIntersectsOverlappingLower() {
    	assertTrue(exampleRange.intersects(-0.5, 1.5));
    }
    
    //testing a range that has only its lower bound within the range being tested and verifying that it returns true
    @Test
    public void testIntersectsOverlappingUpper() {
    	assertTrue(exampleRange.intersects(-1.5, 0.5));
    }
    
    //testing a range that doesn't intersect and verifying that it returns false
    @Test
    public void testIntersectsNotOverlapping() {
    	assertFalse(exampleRange.intersects(1, 1.5));
    }
    
    
    //COMBINE() TESTING
    
    //combining two ranges and verifying that the upper and lower bound of the new range are accurate
    @Test
    public void testCombineTwoRanges() {
    	Range range_one = new Range(-3, 3);
    	Range range_two = new Range(-4, 5);
    	
    	Range new_range = Range.combine(range_one, range_two);
    	
    	double lower_bound = new_range.getLowerBound();
    	double upper_bound = new_range.getUpperBound();

    	
    	assertEquals("Range 1 is (-3, 3), Range_2 is (-4, 5), the lower bound should be -4.0", -4.0, lower_bound, 0.00000001d);
    	assertEquals("Range 1 is (-3, 3), Range_2 is (-4, 5), the upper bound should be 5.0", 5.0, upper_bound, 0.00000001d);
    	
    }
    
    //combining one range with a null range and verifying that it returns the range
    @Test
    public void testCombineOneNull() {
    	Range range_one = new Range(-3, 3);
    	Range range_two = null;
    	
    	Range new_range = Range.combine(range_one, range_two);
    	
    	double lower_bound = new_range.getLowerBound();
    	double upper_bound = new_range.getUpperBound();

    	
    	assertEquals("Range 1 is (-3, 3), Range_2 is null, the lower bound should be -3.", -3.0, lower_bound, 0.00000001d);
    	assertEquals("Range 1 is (-3, 3), Range_2 is null, the upper bound should be 3.", 3.0, upper_bound, 0.00000001d);
    	
    }
    
    //combining two null ranges and verifying that it returns null
    @Test
    public void testCombineTwoNulls() {
    	Range range_one = null;
    	Range range_two = null;
    	
    	Range new_range = Range.combine(range_one, range_two);

    	
    	assertNull("Range 1 is null, Range_2 is null, new_range should be null.", new_range);
    	
    }
    
    //GETLOWERBOUND() TESTING
    //creating a range and verifying that the lower bound is the same value of the method
    @Test
    public void testLowerBound() {
    	assertEquals(-1, exampleRange.getLowerBound(), 0.00000001d);
    }
    
    //GETUPPERBOUND() TESTING
    //creating a range and verifying that the upper bound is the same value of the method
    @Test
    public void testUpperBound() {
    	assertEquals(1, exampleRange.getUpperBound(), 0.00000001d);
    }
    
    //CONSTRAIN() TESTING
    //entering a value close to the upper bound of the range and verifying it
    @Test
    public void constrainShouldBeOne() {
    	
        assertEquals("In the range -1 to 1, the closest value to 2 is 1.",
        1, exampleRange.constrain(2), .000000001d);
    }
    
    //entering a value close to the lower bound of the range and verifying it
    @Test
    public void constrainShouldBeNegOne() {
        assertEquals("In the range -1 to 1, the closest value to -2 is -1.",
        -1, exampleRange.constrain(-2), .000000001d);
    }
    
    //entering a value equal to the middle of the range and verifying that it returns the same value
    @Test
    public void constrainShouldBeZero() {
        assertEquals("In the range -1 to 1, the closest value to 0 is 0.",
        0, exampleRange.constrain(0), .000000001d);
    }
    
    //SHIFT() TESTING
    //shifting the range by 1 then verifying the lower bound of the range is correct
    @Test
    public void beginningShiftTest() {
        assertEquals("The beginning of the range [-1, 1] shifted right by 1 should be 0.",
        0, Range.shift(exampleRange, 1).getLowerBound(), .000000001d);
    }
    
    //shifting the range by 1 then verifying the upper bound of the range is correct
    @Test
    public void endShiftTest() {
        assertEquals("The end of the range [-1, 1] shifted right by 1 should be 2.",
        2, Range.shift(exampleRange, 1).getUpperBound(), .000000001d);
    }


    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}