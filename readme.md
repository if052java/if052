Build status: [![Build Status](https://buildhive.cloudbees.com/job/if052java/job/if052/badge/icon)](https://buildhive.cloudbees.com/job/if052java/job/if052/)

To run these 2 projects you'll need:
  - Maven 3.0+
  - internet connection to resolve dependencies
  - jdk7
  - tomcat 7

How to run: step by step
  (making war's)
  1. cd to if052/if052-restful-service
  2. run: "mvn clean package"
  3. cd to if052/if052-webapp
  4. run: "mvn clean package"
  
  (deploying war`s)
  5. run tomcat
  6. in browser: http://localhost:8080/manager/
  7. find "Select WAR file to upload" and choose 
	  if052/if052-restful-service/target/restful.war
  8. click "Deploy" button
  9. perform last 2 steps with
	  if052/if052-webapp/target/webapp.war
Finish!

Links to check how it works:
  (should return JSON)
  http://localhost:8080/restful/greeting
  http://localhost:8080/restful/greeting?name=<your name>
  
  (should return "Hello .. !")
  http://localhost:8080/webapp/ 	
  http://localhost:8080/webapp/greeting 
  http://localhost:8080/webapp/greeting?name=<your name>
  

    Used guides:
https://spring.io/guides/gs/rest-hateoas/
https://spring.io/guides/gs/consuming-rest/
https://spring.io/guides/gs/serving-web-content/
https://spring.io/guides/gs/convert-jar-to-war-maven/
  
At this moment this is an extremely simple project, i hope it will grow up)

