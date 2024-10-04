# $${\color{green}Service Registry:-}$$  
* Service registry looks like a centralized database or directory where all information about registered service stored.
* service registry contains the information about service and their location is stored and maintained.
* Service Registry serves as a central hub for managing service discovery and communication in a microservices architecture. 
* Service Registory helps up to remember all the service url  and their ports and etc....
## How service registry works :- 
### 1. Service Registration
>When we create a service then we can register that service into service registry.
>In service registration process we need to include metadata such as Service name, Network location, health status and others 
### 2. Service Lookup
>if client and other microservice want to communicate with particular service , 
>in this case client and other microservice do not have idea about the location of that service and other info regarding to the required service.
>So here service registry contains such kind of information regarding all registered services.
### 3. Health Monitoring
>Service registry also perform health checkup machanism to monitored the status of registered service instance. 
>service registry removes unhealthy and unavailable services to ensure that client only receive informatio about healthy and operational service.
### 4. Load Balancing
>some service registry having capability to handle load balancing.
>distribute incomming request among multiple instance of same service. this helps optimize resource utiliztion, improving performance, and enhancing fault taularance.
## How to integrate service registry:- 

#### 1. Eureka Server Configuration
* Go to start.spring.io and dependency **Eureka Server (Spring Cloud Discovery)**
![image](https://github.com/user-attachments/assets/77cf6062-143e-42fc-bc85-58b256de5bab)

* Click on Generate and extract zip file and open it into your IDE
* Create an application.yml file and write some configuration
* ```javascripts
      server:
        port: 8761
       eureka:
        client:
          register-with-eureka: false
          fetch-registry: false  
  ```

  ![image](https://github.com/user-attachments/assets/fa0739c2-96a2-4a71-a2c8-02797fc4a151)

* Anotate main class with **@EnableEurekaServer** annotation
  
![image](https://github.com/user-attachments/assets/89d13061-3418-48ff-8d46-7966ae704644)

#### 2. Eureka Client Configuration
* In Eureka Client configuration we need to update our eisting services with Eureka Client configuration
* Add Eureka client dependency into services pom file
  ```javascripts
        <properties>
        		<java.version>17</java.version>
        		 <spring-cloud.version>2023.0.3</spring-cloud.version>
	      </properties>
  
       <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
      </dependency>


     <dependencyManagement>
        <dependencies>
          <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
          </dependency>
        </dependencies>
  </dependencyManagement>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
  ```
![image](https://github.com/user-attachments/assets/6c79eb8b-34fc-457c-8cf1-da2eac847b3c)

* Update application.yml file with the below configuration 
   ```javascripts
      eureka:
        client:
          register-with-eureka: true
          fetch-registry: true  
          service-url:
              defaultZone: http://localhost:8761/eureka/
        instance:
          hostname: localhost 
   ```
   ![image](https://github.com/user-attachments/assets/c1c8290f-8b24-48e2-b073-977aeb65632c)


* Build and run the application ( All instance is down in the below screen sort)
  ![image](https://github.com/user-attachments/assets/ff4c953f-e2ca-44f7-a877-40cd1e4d1b67)
  
* Anotate the service main class using **@EnableDiscoveryClient** 
![image](https://github.com/user-attachments/assets/cbdb1417-1b77-4b14-ad15-852bf5138c96)

* First start the **service-registry** 
* start both the service named **department and user**
* open the **http://localhost:8761/** URL into browser

  ![image](https://github.com/user-attachments/assets/0398cb41-82f3-498a-a040-89b20a22da17)

  
#### 2. Other Configurations
* we specified service name into application.yml file
```javascripts
	spring:
         application:
           name: DEPARTMENT-SERVICE
```
![image](https://github.com/user-attachments/assets/0d67f593-458c-4983-820f-59ae75af9670)

* This will helps us we can use the same **service name** instead of **localhost:port**
  
**Before Service Registry**
  ```javascripts
	Department department = restTemplate.getForObject("http://localhost:9001/department/"+user.getDeptId()
		, Department.class);
		
  ```
  **After Service Registry Configuration**

  ```javascripts
	Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/"+user.getDeptId()
		, Department.class);
  ```
![image](https://github.com/user-attachments/assets/fcf9f65d-de7c-46e2-ba45-391260e6a3ee)


* **Another intested thing** after configuration of Service Registry, we are calling department service using the service name (as displayed in the above screen sort) as per result **user service will not work** because it is internally calling department service using restTemplate.
  ![image](https://github.com/user-attachments/assets/2ee832ed-ebbf-46cd-9b64-28133febf40e)

* Solution :- we need to anotate RestTemplate bean using **@LoadBalanced**
  ![image](https://github.com/user-attachments/assets/522efd2f-7dd8-4b24-88b5-f7555c07a26a)

We can see now user service returning response
![image](https://github.com/user-attachments/assets/cada5bf2-753d-40e4-bb11-bea47051469b)


# $${\color{green}API Gateway:-}$$ 
>An API gateway accept request from client, process them based on defined policy and direct them to the appropriate service.
>It acts as middle man between the client and groups of microservice.
>API Gateway encapsulates internal system architecture.
>It also has other responsibilities such as authentication, monitoring, load balancing, caching, request shaping and management, and static response handling.
>API Gateway is a service that sits between clients and backend services.
>it serves as a single entry point for clients to access multiple services, providing a unified interface and abstracting the complexities of the underlying architecture
###  How does API Gateway Works
>API gateway acts as single entry point for client and client can communicate with multiple services using the single entry point.
>API Gateway abstracting the underline artitecture and provide unified interface for client.
### 1. Routing 
>when a client send request to API Gateway, API Gateway will identify which microservice will serve the commig request.
>This routing can be based on various criteria such as the URL path, HTTP method, or headers.
### 2. Protocol Translation
>API Gateway has capability to translate request from one protocol to another.
>It can accept http request from client and convert then gRPC and websocket request for backend service.
### 3. Authentication and Authorization 
>API gateway can handle Authentication and authorization for incomming request.it can check the necesarray permission to access the request.
### 3. Set the rate limit
### 4. Load Balancing 
>The API Gateway can distribute incoming requests across multiple instances of a service to ensure high availability and scalability.
### 5. Caching 
>To improve performance, the API Gateway can cache responses from backend services and serve them directly to clients for subsequent identical requests.
### 5. Monitoring and loging 
>The API Gateway can collect metrics and logs for incoming requests, providing insights into the usage and performance of the system
# $${\color{red} Challenges Using API Gateway:-}$$:-
we can face following chalangages into API Gateway 
### 1. Performance bottlenecks:-
> As we know API gateay provide single entry point to client so we need to configure it very carefully so that it can handle the load.
### 2. Security :-
> Misconfiguration API can lead security vulranibility risk such as improper authentication, authorization, or exposure of sensitive information
> Regular security audits and updates are essential to mitigate these risks.
### 3. Monitoring and logging :-
>Monitoring and logging the API Gateway’s performance and behavior can be complex, especially when dealing with a large number of requests and services
### 3. Complexity :-
> Managing and configuring an API Gateway can be complex, especially in environments with a large number of services and endpoints
> Proper documentation and automation tools can help reduce this complexity.

## How to integrate API Gateway:- 

#### 1. API Gateway Configuration
* Go to start.spring.io and dependency **Eureka discovery Client, Actuator and Gateway**
* Generate application and import it into your ide
  ![image](https://github.com/user-attachments/assets/d49ac57f-c9ac-42df-8ffa-615e17632c5f)

* Create an **application.yml** file and Write the below configuration
```javascripts
	server:
         port: 9191
  
       spring:
         application:
           name: API-GATEWAY
         cloud:
          gateway:
           routes:
            - id: USER-SERVICE
              uri: lb://USER-SERVICE
              predicates:
               - Path=/user/**
            - id: DEPARTMENT-SERVICE
              uri: lb://DEPARTMENT-SERVICE
              predicates:
               - Path=/department/**
	eureka:
         client:
          register-with-eureka: true
          fetch-registry: true  
	  service-url:
            defaultZone: http://localhost:8761/eureka/
        instance:
          hostname: localhost 
```
![image](https://github.com/user-attachments/assets/05f6e181-ff17-4466-aeb6-5b2eafa159ee)


* Run All the services user-service, department-service, service-registry and apigateway and open service registry url into browser **http://localhost:8761/**. you can see the service up and running .
 ![image](https://github.com/user-attachments/assets/cea426f6-d955-45e5-891d-451d55ff4154)

* Now we can use **one centralized end point** that is **http://localhost:9191**
* we do not take bother about host and port of each service .
* we configured routing, **id:-** refers service name and **path :-** in which controller our requet will lend
* we can see in the below screen sort both service(either user-service OR department-service) are working with common end point.
  ![image](https://github.com/user-attachments/assets/af46f4d7-6914-4cdc-8564-4e8cab53601a)
  ![image](https://github.com/user-attachments/assets/3040b16a-40b3-4d02-836b-b7f9bda61e8c)
  ![image](https://github.com/user-attachments/assets/72c49c0f-5e69-45d5-82ad-807ffe25b7dd)
  ![image](https://github.com/user-attachments/assets/46e8552c-01b8-4955-9ecc-17bbb6ef67ad)


# $${\color{green}Circuit Breaker:-}$$ 
> Circuit Breaker pattern acts as safeguard against service failure
> IT is a fault tolerance machanism that monitor and control interaction between services.
> it dyanamicaly manage service availability and it also inturpt request for failing service to prevent syatem overload.
## Characteristics of circuit breaker pattern
  ### Fault tolerance :- 
  > Fault tolerance refer system capacity to sustain its functionality in presence of software and hardware failure.
  ### Monitoring :-
  > Countinuously monitor interaction between service to detect issue in real time.
  ### Failure Isolation :- 
  > Temporarily stop sending request to failure service.
  ### Fallback Machanism :-
  > Due service failure, we can pass either error message or we can use cached data to endure graceful termination.
  ### Automatic Recovery :- 
  > Automatic transition back to normal operation when the failure service recover.
## States of circuit breaker pattern
  ### Close State :- 
  > In CLose state the circuit breaker pattern operates normally and allowing request to flow throw different service.
  > Monitor health of the downstream service by collecting and analysing metrics such as response time, error rate or timeout.
  > if mentioned metrix remain within acceptable threshold indicating that downstream service is healthy and the circuit breaker stay in CLOSE state and continue to forward request.
  ### Open State :-
  > When the monitored metrix not in the acceptable threshold, it is signalling potential issue within downstream service then Circuit breaker moves the transition into OPEN state.
  > IN OPEN state circuit breaker immediately stop forwading request to failing service.
  > In this case circuit breaker provides a predefined fallback response or an error message
  ### Half Open State :-
  > After a duration of time circuit breaker goes into half-open state. In this state circuit breaker will allow a limited number of test request to failed service and if request got success then circuit breaker reset state with CLOSE
## When to use circuit breaker patter
  ### Remote service call :-
  > When some service try to call other service via network then it may be it encounted failure such as network downtime, service unavailable, slow response ...
  > This is the case where we can go with the circuit breaker concept
  ### High availability requirement :-
  ### Scaling and load handling
  ### Fault isolation
