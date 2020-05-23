# Developer Guide
This project is want to monitor the fluctuation of price product in Fabelio Website.
To run this project, you needed to install some required softwares in your local machine.

## Required software for project dependency
  - Java 1.8 (https://java.com/en/download/help/windows_manual_download.xml)
  - Gradle 2.4.x (https://docs.gradle.org/current/userguide/installation.html)
  - Postgres.app (https://postgresapp.com/downloads.html) for Mac
  
## Required software as Tools
  - IntelliJ IDEA Community Edition (https://www.jetbrains.com/idea/download)
  - Git Client (https://git-scm.com/downloads)
  
## How to run
You can run this project on some environments are **local mode**  
 
## Run on local mode
After install all required dependency softwares and ensured that all tools are already running, the next steps are as below:
  - Clone this project into your local machine
  - Run the DB schema (price_monitor_db.sql) in your local postgreSQL. Remember to change some attribut like user and password.
  - Configure the database  pointing them to your local DB connection
  - Simply run the project by execute this command on CLI:
        gradlew clean bootRun 
  - Or run it directly on Intelij by clicking button green triangle
        
        
