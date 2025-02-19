# RedCollar
This project was developed as part of the NYU CSCI-GA 3812 course, instructed by Prof. Franchitti and Joanna Gilberti. The project was led by Shiwei He, with team members Nikhil Pujari and Aditya Deshpande.

**There're 3 fundamental folders in the root directory:** 
- business-application-model
    This is to store the POJO files that map the data model in java with that in the JBPM process, I understand it as an ORM.

- business-application-service
    This is the folder where most of the project service is created, such as process controll, task controll, Java Spring Boot application config and starter, etc. As one can see in the pom.xml under this directory, the service is dependent on the business-application-model, and also dependent on `Evaluation-1.0.0-SNAPSHOT.jar` from business-central-kjar.

- business-central-kjar
    This is where all the business logic lies, such as Clothing_Process.bpmn, and every custom tasks' wid(work item handler) files. The mapping between the wid and the java handler class need to be mapped carefully using `kie-deployment-descriptor.xml` under business-central-kjar/src/main/resources/META-INF.

