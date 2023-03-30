# REST Client Plugin
* see here: https://marketplace.visualstudio.com/items?itemName=humao.rest-client 
* Should replace the postman collection in the future
* Check also the **swagger ui documentation** for more infos about possible requests: 
    * ``https://dae5-raindrops-ws2022-test-1-production.up.railway.app/swagger-ui.html`` 

# Variables 
* Definition with @ and usage with {{...}}
@url = https://raindrops-ws2022-production-63fb.up.railway.app/

# Info - Controller
GET {{url}}/info HTTP/1.1

# Sensor Controller
## Add new sensor
POST {{url}}/sensor 
content-type: application/json

{
    "friendly_name": "Sensor #2 (VS Code, V5.0.0.2)",
    "employee_id": "100"
}

# Get all sensors
GET {{url}}/sensor HTTP/1.1 
* info: at first, the GET method on /sensor is not available - we need to implement this

# Humidity 
## Get Humidity by ID
GET {{url}}/humidity/1 HTTP/1.1

## Delete Humidity by ID
SQL-Injektion!!!! Die "id" wird als string übergeben und ausgeführt

DELETE {{url}}/humidity/1 OR 'x'='x' HTTP/1.1
DELETE {{url}}/humidity/1 HTTP/1.1

# Get Humidity values for a sensor by the sensor ID
GET {{url}}/sensor/2/humidity HTTP/1.1 

## Post Humidity for Sensor
POST {{url}}/sensor/2/humidity/1.18

## Get All Humidities
GET {{url}}/humidity HTTP/1.1

# Employees Controller
## Get All Employees
GET {{url}}/employee HTTP/1.1

