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
