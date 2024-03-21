## March 6th
-Renamed the project to "BiteSize"

This caused a lot of issues with Maven, so I had to take the time to learn how to redefine the project structure and how it is built. For a while,  starting the server would cause a 404 error as it was still using the previous war file name. It was difficult to find a solution for this online, but I was able to solve it on my own. In hindsight, it was a good exercise to get a better understanding on the inner workings of Maven and Tomcat.

## Spring break
- Finished Week 8 Work
- Configured database to work properly with hibernate and new database structure due to change
- Configured usual dependencies such as log4j and junit testing

## Week 9
- Created new database dump to be added to throughout the week
- Created generic dao to be used for all CRUD
- Tested user dao to ensure generic dao works
- Created test database to run tests on
- Adding many-to-many relationship for schedules and tasks