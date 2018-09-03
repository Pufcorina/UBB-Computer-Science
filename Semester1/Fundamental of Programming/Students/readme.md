Problem statement:

A faculty stores information about: students, disciplines and grades. 

Create an application which allows to: 

⦁	Manage the list of students and available disciplines. The application must allow the user to add, remove, update, and list both students and disciplines.

⦁	Grade students at a given discipline. The program must allow only those students who are enrolled at a given discipline to receive any number of grades. Deleting a student also removes their list of grades. Deleting a discipline deletes all the grades at that discipline.

⦁	Search for disciplines/students based on their ID or name/title. The search must work using case-insensitive, partial string matching, and must return all matching disciplines/students.

⦁	Create statistics: 

  ⦁	All students enrolled at a given discipline, sorted alphabetically or by descending order of average grade.

  ⦁	All students failing at one or more (students having an average  <5 for a discipline are considered to be failing)
  
  ⦁	Students with the best school situation, sorted in descending order of their aggregated average (the average between their average grades per discipline).
  
  ⦁	All disciplines at which there is at least one grade, sorted in descending order of the average grade received by all students enrolled at that discipline.
  
⦁	Unlimited undo/redo functionality. Each step will undo/redo the previous operation that modified the data structure.




To show statistic functionalities I’ll use file repository and graphical user interface and some pictures:

  ![](https://github.com/Pufcorina/GitUtilities/blob/master/Photos/FP%20Students/p1.png)

⦁	Statistics - Sort students at a given discipline - Sort alphabetically all students:

  ![](https://github.com/Pufcorina/GitUtilities/blob/master/Photos/FP%20Students/p2.png)

 
⦁	Statistics - Sort students at a given discipline - Sort by descending order of average grade all students:

  ![](https://github.com/Pufcorina/GitUtilities/blob/master/Photos/FP%20Students/p3.png)
 

⦁	Statistics -  All students failing at one or more disciplines:

  ![](https://github.com/Pufcorina/GitUtilities/blob/master/Photos/FP%20Students/p4.png)
 

⦁	Statistics -  Students with the best school situation:

  ![](https://github.com/Pufcorina/GitUtilities/blob/master/Photos/FP%20Students/p5.png)
 
 
⦁	Statistics -  Sort all disciplines by average:

  ![](https://github.com/Pufcorina/GitUtilities/blob/master/Photos/FP%20Students/p6.png)
  
  
  

Work items/tasks:

T1	Interpret a command given by the user using an interface based on menu based

T2	Check the validity of the arguments that were interpreted

T3	Perform add/remove/update/ list operations on the repository based on the arguments

T4	Perform statistics based on information from repositories

T5	Unlimited undo/redo functionality.

T6	Create a graphic user interface
