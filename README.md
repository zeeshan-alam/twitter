Solution is developed using spring boot, Swagger, JPA and H2(in memory database).
Following are the Instructions to run the application.
Pre-requisite: Java, Git and Maven should already be installed on your machine.

1)	Open command prompt/console.
2)	Type “git clone https://github.com/zeeshan-alam/twitter”. 
3)	It will create a directory called “twitter”.
4)	Type “cd twitter”
5)	Type “mvn package”
6)	Type “java -jar api/target/api-0.0.1-SNAPSHOT.jar”
(It will start swagger UI on port 8080. Please make sure that 8080 is not already in use.If you want to change it please change it in api/src/main/resources/application.properties)

7)	Open browser and go to following URL.  Swagger UI is running on port 8080.
http://<your-machine-name>:8080/swagger-ui.html and click “List Operations”.
Please refer to ReadMe.docx for instruction with screenshots.