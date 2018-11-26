# Lab 6
#
#### Requirement
>Perform the multiplication of 2 polynomials. Use both the regular O(n^2) algorithm and the Karatsuba algorithm, and each in both the sequencial form and a parallelized form. Compare the 4 variants.
#
#### Algorithms
*   regular polynom multiplication:
    * Complexity: O(n^2)
    * Step 1:	Distribute each term of the first polynomial to every term of the second polynomial. Remember that when you multiply two terms together you must multiply the coefficient (numbers) and add the exponents.
    * Step 2:	Combine like terms (if you can).
#    
* The Karatsuba algorithm:  
    * Complexity: O(n^log 30)
    * A fast multiplication algorithm that uses a divide and conquer approach to multiply two numbers.

#### Parallelization
Used Java's ThreadPool combined with custom Runnable objects to achieve parallelization;

### Results:  
note: by level 'x' i am reffering that the algorithms were used to multiply 2 polynomials of rank x * 100, with coefficients being random numbers of x * 10 digits.

| Tables                   | Level 0  | Level 1 | Level 2 | Level 3 | Level 4 | Level 5 | Level 6 | Level 7 | Level 8 | Level 20 |
| ------------------------ |:--------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|:-------:|
| regular sequential       | 54  ms | 72  ms | 144  ms | 168  ms | 236  ms | 348 ms | 372 ms | 631 ms | 1199 ms | 5995 ms |
| regular parallelized     | 20 ms | 28 ms | 46 ms | 85 ms |143 ms |209 ms |330 ms |558 ms |724 ms |6383 ms |
| karatsuba sequential     | 29 ms |29 ms |42 ms |67 ms |73 ms |190 ms |253 ms |352 ms |507 ms |5981 ms |
| karatsuba parallelized   | 5 ms |17 ms |39 ms |72 ms |159 ms |238 ms |341 ms |479 ms |679 ms |5079 ms |

Throught all the tests i've put those algorithms to, the results were for the most part as expected, although the results may vary quite a bit ( see that up to level 8 the parallelized version of the regular algorithm has the lead, but suddnly at level 20 it takes quite a bit more time than the sequential one). There are a lot of factors that can be responsible for those inconsistancies, like background processes, memory usage, and the implementation itself.

### Conclusions

1. For the most part, the parallelized versions of the algorithms run faster.
2. Karatsuba's is clearly supperior to the regular algorithm and for large numbers it would be preferred
