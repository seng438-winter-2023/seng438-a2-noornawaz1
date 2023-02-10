**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

|   Group \#:      |  UCID  |
| ---------------- | --- |
|   Student Names: |     |
|    Sajan Hayer   |  30114632   |
|   Manraj Singh   |  30115660   |
|    Noor Nawaz    |  30118937   |
| Ahmad Elshiltawi |  30123883   |

# 1 Introduction
In this lab, we were asked to develop tests in the JUnit framework for two given classes: Range and DataUtilties. By doing so, we were able to develop a good understanding of automated unit testing through JUnit. Since we were only provided with JAR files and no source code, we had to execute black-box testing methods such as equivalence testing when designing a test suite. For the Range class, we tested the methods constrain(), contains(), shift(), intersects(), combine(), getUpperBound(), and getLowerBound(). We began testing by first documenting the functionalities of each method and then constructing tests that would verify that the value returned by the methods was equivalent to the one that we hardcoded. For testing the DataUtilties() class, we tested the methods calculateColumnTotal(), calculateRowTotal(), createNumberArray(), createNumberArray2D(), and getCumilativePercentages(). These methods use the interfaces Values2D and KeyedValues so we had to utilize mocking objects in order to complete thorough and accurate testing. We examined the methods functionalities and hardcoded values that the methods would return if they were operating correctly. Once we ran the method, we compared it to the values produced by the test. Additionally, we constructed tests to ensure that the correct exceptions were thrown. The results of all the tests were recorded in Figure 1 and Figure 2. 

# 2 Detailed description of unit test strategy
To test the Range and DataUtilities classes using the black-box testing method, we further used equivalence testing to develop test cases. Equivalence testing allows us to test a single unit (generally a class) by separating the possible inputs into subgroups that have the same/similar input. Equivalence classes are evaluated based on the input conditions. 

All group members initially familiarized themselves with the SUT, and read the provided Javadoc documentation about the classes that were to be tested. All members read the documentation for each method in a class and identified the return type, parameters and description of the method to understand functionality. We also noted if the function threw any exceptions based on certain input. By determining the function parameters, we designed test cases based on input values that were valid (correct data/object type) and invalid (null, incorrect type etc.). When valid input was provided, we tested if the methods had the appropriate return type, along with appropriate values for results. When invalid input was provided, we verified if the methods threw the correct exceptions and if they handled errors correctly as outlined in the documentation.  

The test suite that targeted the methods of the Range class also consisted of test cases that were developed using Boundary Value Testing. By developing test cases using this method, we were able to identify a range of inputs that were valid for the methods, which further allowed us to identify boundary values that are not acceptable by the methods being tested. Boundary Value Testing also allowed us to reduce the number of test cases required, as it was easy to identify a range of valid input, and their associated invalid boundaries. 

Black box testing allowed us to focus on the core functionality of the system without thoroughly considering the internal code and inner works of the SUT. This allowed us to test a system from end-to-end and put us in the shoes of regular users that use the application.

# 3 Test cases developed
Figure 1: Results of Testing Range Class
| Class | Function | Test description | Pass/Fail |
|---------|-------|---------|---------|
| org.jfree.Range | contains | Testing a positive number that is inside the range| Pass |
| org.jfree.Range | contains | Testing a negative number that is inside the range| Pass |
| org.jfree.Range | contains | Testing a positive number that is outside the range| Pass |
| org.jfree.Range | contains | Testing a negative number that is outside the range| Pass |
| org.jfree.Range | intersects | Testing a smaller range that is within the original range completely | Pass |
| org.jfree.Range | intersects | Testing a range that is within the range from the upper bound’s side | Pass |
| org.jfree.Range | intersects | Testing a range that is within the range from the lower bound’s side | Failed:java.lang.AssertionError |
| org.jfree.Range | intersects | Testing a bigger range that encapsulates the original range completely | Pass |
| org.jfree.Range | intersects | Testing a range that does not intersect the original range | Pass |
| org.jfree.Range | combine | Testing combining two initialized ranges | Failed:java.lang.AssertionError: Range 1 is (-3, 3), Range_2 is (-4, 5), the upper bound should be 5.0 expected:<5.0> but was:<-4.0> |
| org.jfree.Range | combine |Testing combining one initialized range and null range | Failed:java.lang.AssertionError: Range 1 is (-3, 3), Range_2 is null, the upper bound should be 3. expected:<3.0> but was:<-3.0> |
| org.jfree.Range | combine | Testing combining two null ranges | Pass |
| org.jfree.Range | getLowerBound | Testing the value returned| Pass |
| org.jfree.Range | getUpperBound | Testing the value returned| Failed:java.lang.AssertionError: expected:<1.0> but was:<-1.0> |


Figure 2: Results of Testing DataUtilities Class
| Class | Function | Test description | Pass/Fail |
|---------|-------|---------|---------|
| org.jfree.DataUtilities | calculateColumnTotal | Using Mocking, testing if a valid column within a table will return the correct amount (sum of all column values) | Pass |
| org.jfree.DataUtilities | calculateColumnTotal | Testing using a null object | Failed: Expected a InvalidParemeterException function returned a NullPointerException |
| org.jfree.DataUtilities | calculateRowTotal | Using Mocking testing if the sum of valid row within a table returns the correct value | Failed: Expected 9.5 but function returned 7.0|
| org.jfree.DataUtilities | calculateRowTotal | Testing using a null object | Failed:  Expected a InvalidParemeterException function returned a NullPointerException|
| org.jfree.DataUtilities | calculateRowTotal | Using Mocking testing if the sum of row with all negative data entries within a table returns the correct value | Failed:  Expected -8, but the value returned was -4.|
| org.jfree.DataUtilities | calculateRowTotal | Using Mocking testing if the sum of row with all data entries equal to zero within a table returns the correct value|Pass|
| org.jfree.DataUtilities | createNumberArray | Testing if a valid double array will be returned as Number Object Array| Pass |
| org.jfree.DataUtilities | createNumberArray2D | Testing that the method returns a Number[] array with correct values using positive numbers | Fail: arrays first differed at element[4]; expected 5.5 but was null|
| org.jfree.DataUtilities | createNumberArray2D | Testing that the method returns a Number[] array with correct values using negative numbers | Fail: arrays first differed at element[2]; expected -1.0 but was null|
| org.jfree.DataUtilities | createNumberArray2D | Testing that the method returns a Number[] array with correct values using zero's | Fail: arrays first differed at element[2]; expected 0.0 but was null|
| org.jfree.DataUtilities | createNumberArray | Testing that the correct exception is thrown when using null | Failed:  Expected a InvalidParameterException, function returned an IllegalArgumentException |
| org.jfree.DataUtilities | createNumberArray2D | Testing if valid 2D double array will be returned as a 2D Number object array | Pass |
| org.jfree.DataUtilities | createNumberArray2D | Testing that the method returns a Number[][] array with correct values using positive numbers | Fail: arrays first differed at element[0][1]; expected 1.0 but was null|
| org.jfree.DataUtilities | createNumberArray2D | Testing that the method returns a Number[][] array with correct values using negative numbers | Fail: arrays first differed at element[0][1]; expected -1.0 but was null |
| org.jfree.DataUtilities | createNumberArray2D | Testing that the method returns a Number[][] array with correct values using zero's | Fail: arrays first differed at element[0][1]; expected 0.0 but was null |
| org.jfree.DataUtilities | createNumberArray2D | Testing that the correct exception is thrown | Failed: Expected a InvalidParameterException function returned an IllegalArgumentException|
| org.jfree.DataUtilities | getCumulativePercentages | Testing using mocking a valid KeyedValued object with valid inputs | Failed: Expected 0.3125 but received 0.454545 |
| org.jfree.DataUtilities | getCumulativePercentages | Testing using a null object | Failed:  Expected a InvalidParameterException function returned an IllegalArgumentException|


# 4 How the team work/effort was divided and managed
Initially, all group members worked together to read documentation and gain a deeper understanding of the SUT. After familiarizing themselves, Noor and Ahmad developed test cases for the Range class and Sajan and Manraj wrote test cases for the DataUtilities class. All group members talked about test results after execution to analyze results.

# 5 Difficulties encountered, challenges overcome, and lessons learned
A challenge that was encountered during this lab was we realized that mocking would not allow us to fully implement some of the tests we had thought of. This was because when we tried to send a invalid value to the function, the code would throw an error stating that the value did not exist within our mocked table. The solution would be to add the specified value (row or column) within our table but that would result in the test passing. This resulted in our group having to remove these tests (these are commented out in our code). 

# 6 Comments/feedback on the lab itself
This lab was more enjoyable than the previous lab. It was interesting to learn about automated testing using code, and how the Junit framework allows testers to develop and execute a test suite fairly efficiently. It was also interesting to learn about Mocking and how the jMock framework can be used to mock objects of different classes. The instructions were very thorough and allowed us to gain a good understanding of how to design unit tests, along with package structure. 

