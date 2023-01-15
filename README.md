# Welcome to Comanda!

Hi! in this file i show how do initial configuration the project Comanda


# Application Properties
In the src/main/resources directory you will find applicationtemplate.properties this file and an example file to be replicated to a file called application.properties with the same template parameters.

## JAVA
Java Version: 19
Spring-Boot Version: 3.0.1
OpenJDK: 19

  
If you don't know how to install java, I advise you to follow the steps given on this site:
[Tutorial](https://www.educba.com/install-jdk/)

## Initial Comands
  
The project uses maven as package manager and so that we have all the packages used by the projects that can be found in the pom.xml we will need to run the command:

    mvn install
if you don't have maven installed, install it from the following link
[Maven](https://maven.apache.org/download.cgi)

  
after installation it will be necessary to configure the location where the packages downloaded by the projects will be stored, the file to be changed is in the path:

    C:\Users\<USER>\.m2
inside that directory we will create the settings.xml file and add the following configuration:

    <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <localRepository>{your directory for packages}</localRepository>
    <interactiveMode>true</interactiveMode>
    <offline>false</offline>
</settings>
where this is {your directory for packages} we will replace it with the directory you want, but attention needs to be a short directory so we don't have a problem with the error: CreateProcess error=206.

[Example](https://stackoverflow.com/questions/10519558/createprocess-error-206-the-filename-or-extension-is-too-long-when-running-main)



## Database

The chosen database was Postgres for several reasons, it will not be necessary to create the tables by hand, hibernate will update everything based on the entities according to the property signed in the application:

    spring.jpa.hibernate.ddl-auto=update

## Docker
  
For development purposes, a docker-compose was created where a postgres database will be uploaded ready to be connected and the database created, just run the following code:

    docker compose up -d


## AWS

To configure aws keys you will need to find the .ws folder that will be inside your user folder:
[Credentials Documentation](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html)

 - Linux

    ~/.aws/credentials

 - Windows
C:\Users\USERNAME\\.aws\credentials

Archive credentials

    [default]  
    aws_access_key_id = your_access_key_id 
    aws_secret_access_key = your_secret_access_key

-- Using Amazon S3 to save product images


## Contributors

| Name | Linkedin | GitHub|
|--|--| --|
| João Victor Ramos de Sousa |[Linkedin](https://www.linkedin.com/in/jvrs2812/)|[GitHub](https://github.com/jvrs2812)|
