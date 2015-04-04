Build status: [![Build Status](https://buildhive.cloudbees.com/job/if052java/job/if052/badge/icon)](https://buildhive.cloudbees.com/job/if052java/job/if052/)

To run these 2 projects you'll need:
  - Maven 3.0+
  - internet connection to resolve dependencies
  - jdk7
  - tomcat 7

How to run application with Intellij Idea 14 Ultimate Edition (II14):
1. Clone the project from Github.
2. Import project as a Maven project into II14.
3. Go to "Viev" in menu --> "Tool windows" --> select "Maven projects"
4. In "Maven projects" window:
   a) set profile "test" is active and profile "presentation" is not active;
   b) select module "if052-consumption-computation";
   c) in "Lifecycle" set marks on options:
      - clean
      -install;
   d) run Maven build.
5. Wait for "SUCCESS" status to all four modules.
6. Edit configuration of local Tomcat Server 7:
   a) add artifact "if052-restful-service" with application context "/restful";
   b) add artifact "if052-webapp" with application context "/".
7. Run Tomcat Server.

    Used guides:
https://spring.io/guides/gs/rest-hateoas/
https://spring.io/guides/gs/consuming-rest/
https://spring.io/guides/gs/serving-web-content/
https://spring.io/guides/gs/convert-jar-to-war-maven/
