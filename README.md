# Service Registry:-  
Service registry looks like a centralized database or directory where all information about registered service stored.
service registry contains the information about service and their location is stored and maintained.
Service Registry serves as a central hub for managing service discovery and communication in a microservices architecture. 
## How service registry works :- 
### 1. Service Registration
When we create a service then we can register that service into service registry.
In service registration process we need to include metadata such as Service name, Network location, health status and others 
### 2. Service Lookup
if client and other microservice want to communicate with particular service , 
in this case client and other microservice do not have idea about the location of that service and other info regarding to the required service.
So here service registry contains such kind of information regarding all registered services.
### 3. Health Monitoring
Service registry also perform health checkup machanism to monitored the status of registered service instance. 
service registry removes unhealthy and unavailable services to ensure that client only receive informatio about healthy and operational service.
### 4. Load Balancing
some service registry having capability to handle load balancing.
distribute incomming request among multiple instance of same service. this helps optimize resource utiliztion, improving performance, and enhancing fault taularance.


# API Gateway:- 
An API gateway accept request from client, process them based on defined policy and direct them to the appropriate service.
It acts as middle man between the client and groups of microservice.
### 1. Single point of interection 

