Problem statement:

Implement an application for:

      ⦁ arithmetic operations: addition, subtraction, multiplication and division by one digit, in a base p belongs to {2,3,...,9,10,16}

      ⦁ conversions of natural numbers between two bases p, q belongs to {2,3,...,9,10,16} using the substitution method or successive divisions and rapid conversions between two bases p, q belongs to {2, 4, 8, 16}.



Calculator - Feature list

F1. Add two real numbers in a certain base

F2. Substract two real numbers in a certain base

F3. Multiply a real number with a one digit number

F4. Divide a real number with a one digit number

F5. Convert a real number from base b into base h



Iteration planned features

I1	

      F1. Add two real numbers in a certain base
      
      F2. Substract two real numbers in a certain base
      
      F3. Multiply a real number with a one digit number
      
      F4. Divide a real number with a one digit number
      
I2	

      F5. Convert a real number from base b into base h
      
            1. Substitution method

            2. Successive divisions and multiplications

            3. Rapid conversions

Iteration 1
Running scenario

First run of application:

![start](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/start.png)

1. Add two numbers in base b:

      ⦁ Both numbers are positive:
      
      ![add positive](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/add_both_positive.png)

      ⦁ Both numbers are negative: 
      
      ![add negative](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/add_both_negative.png)

      ⦁ First number is positive and second number is negative:  
            
      ![add](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/add_second_negative.png)

      ⦁ First number is negative and second number is positive:  
            
      ![](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/add_first_negative.png)


2. Substract two numbers in base b:

      ⦁ Both numbers are positive: 
      
      ![substract poz](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/sub_both_pozitive.png)

      ⦁ Both numbers are negative: 
      
      ![substract neg](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/sub_both_negative.png)

      ⦁ First number is positive and second number is negative: 
      
      ![sub1](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/sub_second_negative.png)

      ⦁ First number is negative and second number is positive:  
      
      ![sub2](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/sub_first_negative.png)


3. Multiplication of a number with another one digit number in base b:

      ⦁ Both numbers are positive: 
      
      ![mult poz](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/mul_both_positive.png)

      ⦁ Both numbers are negative:
      
      ![mult neg](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/mul_both_negative.png)
 
      ⦁ One number is positive and the other number is negative:  
      
      ![mult1](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/mul_one_number_negative.png)


4. Division of a number with another one digit number in base b:

      ⦁ Both numbers are positive:  
      
      ![div poz](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/div_both_positive.png)

      ⦁ Both numbers are negative: 
      
      ![div neg](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/div_both_negative.png)
      
      ⦁ One number is positive and the other number is negative:  
      
      ![div 1](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/div_one_number_negative.png)

5. Conversion of a number from a base b into a base h:

      ⦁ Base b < h and we use substitution method as follows:  
      
      ![conv1](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/conversion_substitution.png)
      
      ⦁ Base b > h and we use successive divisions and multiplications method as follows:  
      
      ![conv2](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/conversion_sccessive_divisions_and_multiplications.png)

      ⦁ Base b, h are from the following list {2, 4, 8, 16} and we can also use rapid conversions: 
      
      ![conv3](https://github.com/Pufcorina/UBB/blob/master/Semester1/ComputationalLogic/Poze/conversion_raid_conversion.png)


Work items/tasks:

T1	Implement the arithmetic operation: addition, substraction, multiplication, division

T2	Implement conversion of any number in base 2

T3	Implement conversion of any number using following methods: substitution method or successive divisions or rapid conversions between two bases p, q belongs to {2, 4, 8, 16}.

