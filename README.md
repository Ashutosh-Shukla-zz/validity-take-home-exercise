# validity-take-home-exercise
Take home exercise - Detect possible duplicates in data


## Personal Information

| Name | Email Address |
| --- | --- |
| Mitali Salvi | salvi.mi@husky.neu.edu|


## Technology Stack
- Java SpringBoot

## Build Instructions
1. Clone repository
2. Import maven project in Eclipse
3. Run mvn build command to compile the project 

## Deploy Instructions
Make sure docker is installed in the system
1. Go to project base directory (/take-home-test)
2. Run the following command to build a docker image:
	docker build -t 'repo:imageTag' .
3. Run the built docker image with the following commandL
	docker run -d -p 8080:8080 repo:imageTag

## jUnit Tests
1. Run the project imported in Eclipse as **JUnit Test**

## Web Application Guidelines

**Upload CSV File**
1. Go to localhost:8080/ <br>
2. Upload CSV file
3. Hit Submit