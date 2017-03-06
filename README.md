Solution is developed using spring boot, Swagger, JPA and H2(in memory database).<br />
Following are the Instructions to run the application.<br />
Pre-requisite: Java, Git and Maven should already be installed on your machine.<br />
<br />
1)	Open command prompt/console.<br />
2)	Type “git clone https://github.com/zeeshan-alam/twitter”. <br />
3)	It will create a directory called “twitter”.<br />
4)	Type “cd twitter”.<br />
5)	Type “mvn package”.<br />
6)	Type “java -jar api/target/api-0.0.1-SNAPSHOT.jar”.<br />
(It will start swagger UI on port 8080. Please make sure that 8080 is not already in use.If you want to change it please change it in api/src/main/resources/application.properties)<br />
<br />
7)	Open browser and go to following URL.  Swagger UI is running on port 8080.<br />
http://<your-machine-name>:8080/swagger-ui.html and click “List Operations”.<br />
Please refer to ReadMe.docx for instruction with screenshots.<br />