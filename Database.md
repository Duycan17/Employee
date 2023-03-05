# Database
![image](https://user-images.githubusercontent.com/117552281/222960891-64dac2d3-2ff0-4ae4-a888-8d31dbbcc4d2.png)

In this design, the employees table holds information about each employee, including their ID, name, address, gender, job position, email address, and date of birth.

The salary table has a foreign key to the employees table and holds the monthly salary for each employee. This allows for the possibility of storing historical salary data for each employee if needed.

Finally, the attendances table also has a foreign key to the employees table and holds information about the attendance of each employee. This could include the date and time of attendance, any absences, or any other attendance-related information that may be useful for tracking employee performance.
