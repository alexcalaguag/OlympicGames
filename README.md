# Olympic Games Rest API

API  RESTful, to manage data from the competitions of the Tokyo 2020 Olympic Games.
The goal is to create the following endpoints:
1. Registration of the competitions that will take place at the Tokyo 2020 Olympic Games.
2. Consultations of competitions registered
 
# Requeriment
1. Java 8 
2. Maven 3.0

# Installation
1. Clone de project OlympicGames
2. Execute mvn clean install into folder OlympicGames 
3. After compiling successfully enter the target folder and run the project jar
   ```
   java -jar OlympicGames-1.0.0.jar
   ```
4. Services RESTful
  
   1. Create Competition 
   Url : 
   ```
	http://localhost:8080/olympicgames/rest/competition/
   ```
   Body : 
   ```
   {
	"modality":"Futebol",
	"local" : "Estadio Maracana",
	"dateOfCompetition" :"2017-10-10",
	"startTimeCompetition" :"15:30:00",
	"endTimeCompetition" :"16:00:00",
	"team" : "Peru",
	"opponent" : "Brasil",
	"stage" : "Final"
   }
   ```
   
   2. Get All Competition
   ```
   http://localhost:8080/olympicgames/rest/competitions/
   ```
   
   3. Get Competition by Modality
   ```
   http://localhost:8080/olympicgames/rest/competitions/?modality=Futebol
   ``` 

5. After starting the project, the rest services will be available	

# Testing
A collection file OlympicGames.postman_collection.json has been created to import into postman.
After importing the file it will be possible to test the rest services of the Olympic games.

