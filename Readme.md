# Microservice training

## Git and CI/CD
Please use ```git clone https://github.com/richPurba/microservice-digital-focus-group.git``` to your local laptop. 
There are 3 different branches: Master, Release, and Feature. Please use the Feature branch as the Master branch is protected and there is a limited access to Release (regarding roles of these different branches, we can revisit this topic if you need the master). Afterwards, do the Pull Request in this github.

With convention, please use the following commit pattern ```git checkout -b feature_{MajorVersion}.{MinorVersion}/{YourFeatureName}```, thus please fill in the value in ```{}```

## How to use this project?
To run the project simply ```mvn spring-boot:run``` at each of the microservice folder. Basically you should run this command where there is a ```pom.xml``` file, where this ```pom.xml``` is the Project Object Model file (thus POM), or similar like ```package.json``` for ```npm``` installation.
For more detail, please go this [Springboot doc](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html#using-boot-running-with-the-maven-plugin)

For other project, such as NodeJs, please consult your own build system (such as ```npm start ``` for any build tool with npm.


