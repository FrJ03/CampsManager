# Camps Manager
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)
![EclipseJava](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)
![Tomcat](https://img.shields.io/badge/Apache-D22128?style=for-the-badge&logo=Apache&logoColor=white)
![HTML](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JS](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)
## Index
1. Purpose
1. Requeriments
1. Technologies
1. Configuration
1. Contributions

## Purpose
The purpose of this program is to manage the camps of a Camps agency.

## Requeriments
- The JAR program must be located in the project directory
- To run this program you need Java 1.8 JDK
- The application runs in Apache Tomcat 8.5
- The application database runs in a Docker container

## Technologies
- **Database**: MariaDB
- **Back-end**: Java J2EE
- **Front-end**: HTML, CSS and JavaScript
- **Web Server**: Apache
- **Application Server**: Apache Tomcat
- **Containers**: Docker

## Configuration
1. Download a branch.
1. (Optional) Modify the current database user and password.

    1. Modify "MYSQL_USER" and "MYSQL_PASSWORD" in `db/docker-compose.yml`.
    1. Modify "user" and "password" in `src/main/webapp/WEB-INF/classes/config.properties`.
1. Run the database,.

    In the `db/` directory use:

    ```docker compose up```
1. Regenerate the WAR file if something has been modified such as the database password.
1. Copy the WAR file to the `webapps/` directory of Tomcat.
1. Run `startup.sh` (Linux) or `startup.bat` (Windows) located in the Tomcat `bin/` directory.
1. Open a browser tab and find `localhost:8080/CampsManager`.

## Contributions
Contributions to this project are welcomed. If you have a feature request or find a bug, please open an issue. If you wish to contribute code, please open a pull request.