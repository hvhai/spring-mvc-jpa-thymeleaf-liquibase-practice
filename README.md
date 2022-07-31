# Sample application with spring mvc, JPA, Thymeleaf and Liquibase

## Init database

1. Get mysql image:
    ``` shell
    docker run --name demo-mvc -p 3306:3306 -e MYSQL_ROOT_PASSWORD=pw -d mysql
    ```
2. [Config DBeaver for connect to DB](https://stackoverflow.com/a/61939827/18859462)

   Add two properties: "useSSL" and "allowPublicKeyRetrieval"

   jdbc:mysql://localhost:3306/forum?allowPublicKeyRetrieval=true&useSSL=false


3. Update **[application.properties](src/main/resources/application.properties)**
   ``` properties 
   spring.datasource.url=jdbc:mysql://localhost:3306/forum?allowPublicKeyRetrieval=true&useSSL=false
   spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
   spring.datasource.username=root
   spring.datasource.password=pw

   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
   spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
   spring.jpa.hibernate.ddl-auto=none

   spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
   ```
## Config for liquibase

1. Add liquibase plugin
   ```xml
   <plugin>
       <groupId>org.liquibase</groupId>
       <artifactId>liquibase-maven-plugin</artifactId>
       <version>${liquibase.version}</version>
       <configuration>
           <changeLogFile>${project.basedir}/src/main/resources/db/changelog/db.changelog-master.xml</changeLogFile>
           <outputChangeLogFile>dbchangelog.xml</outputChangeLogFile>
           <diffChangeLogFile>${project.basedir}/src/main/resources/db/migrations/${maven.build.timestamp}_changelog.xml</diffChangeLogFile>
           <propertyFile>liquibase.properties</propertyFile>
           <logging>debug</logging>
           <contexts>!test</contexts>
       </configuration>
       <dependencies>
           <dependency>
               <groupId>org.liquibase</groupId>
               <artifactId>liquibase-core</artifactId>
               <version>${liquibase.version}</version>
           </dependency>
           <dependency>
               <groupId>org.liquibase.ext</groupId>
               <artifactId>liquibase-hibernate5</artifactId>
               <version>${liquibase-hibernate5.version}</version>
           </dependency>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-data-jpa</artifactId>
               <version>${spring-boot.version}</version>
           </dependency>
           <dependency>
               <groupId>javax.validation</groupId>
               <artifactId>validation-api</artifactId>
               <version>${validation-api.version}</version>
           </dependency>
           <dependency>
               <groupId>org.javassist</groupId>
               <artifactId>javassist</artifactId>
               <version>${javassist.version}</version>
           </dependency>
       </dependencies>
   </plugin>
   ```
2. Create liquibase.properties
   ```properties
   url=jdbc:mysql://localhost:3306/forum?allowPublicKeyRetrieval=true&useSSL=false
   driver=com.mysql.cj.jdbc.Driver
   username=root
   password=pw
   referenceDriver=liquibase.ext.hibernate.database.connection.HibernateDriver
   referenceUrl=hibernate:spring:com.codehunter.springmvcjpathymeleafliquibasepractice.domain?\
   dialect=org.hibernate.dialect.MySQL5InnoDBDialect&\
   hibernate.physical_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy&\
   hibernate.implicit_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
   ```
3. Create ``db.changelog-master.xml`` and empty migrations folder
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <databaseChangeLog
     xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext"
     xmlns:pro="http://www.liquibase.org/xml/ns/pro"
     xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
           http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.9.xsd
           http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd
           http://www.liquibase.org/xml/ns/pro http://www.liquibase.org/xml/ns/pro/liquibase-pro-latest.xsd">

   </databaseChangeLog>
   ```
4. Add domain, repository, service, controller, html page
5. Execute ``mvn liquibase:diff`` to generate the changelog
6. Import changelog to master changelog
   ```xml
   <include file="db/migrations/20220731042837_changelog.xml" relativeToChangelogFile="false"/>
   ```
7. Run the app and check.

## Add ``post`` to app
1. Create Post domain
   - Properties: id, contain, createdTime
   - Add relation with the User
2. Generate changelog diff and update change changelog master

## Update web UI for the posts

1. Create thymeleaf layout

   

