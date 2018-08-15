# JMyCalc Java sample app

The sample application with the following idea behind:
if we have a lot of complicated calculations it is not a bad idea to keep 
calculation results in memory cash for use them later as result of the same next calculation.
Of course,it is useful only if every calculation will take more time 
as put/get values into/from the cash. This is Java realization, but similarly it
can be done in C++ (see project MyCalc).

Different SimpleCash realizations  are considered:

 - cash as synchronized HashMap (Collections.synchronizedMap), and synhronization is not
   required in SimpleCash methods.
 - cash as not synchronized HashMap/ArrayList and with required thread synchronization
    in SimpleCash methods using 'synchronized' or conditional locking mechanisms.
