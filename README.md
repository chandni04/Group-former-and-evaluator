# Group-former-and-evaluator
This is an java based desktop application to help organization create project groups.
In our college, every semester it was a problemtic task to create project groups,
so we decided to come up with a solution which will automate the process of creating project groups.

Project is built using following technologies :
1. Java (both for front-end and back-end),
2. MongoDB database for storing student information.

The department administrator needs to first upload the list of students in excel format with their information like marks, roll no, etc.
Then they need to provide the criteria based on which groups will be formed, critea includes number of students per group.
After that application devides the students in 3 groups namely group of average student, group of below average students and
group of above average students, and then the application picks the student from each group to form the project groups.

Use of this application also makes sure that all the groups are normalised and no group will contain all the clever students
of the class.
Once the groups are formed, application creates another excel sheet with information of projects created.

There are some enhancements like to send emails to each students regaring their group information.
