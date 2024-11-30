# DEPI-DevOps-GP

This app shows a simple user profile app set up using 
- index.html with pure js and css styles
- nodejs backend with express module
- mongodb for data storage

### To start the app using docker

Step 1: start mongodb 

    docker run -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin123 --name mongodb --net mongo-network mongo

Step 2: start mongo-express
    
    docker run -d -p 8081:8081 -e ME_CONFIG_MONGODB_ADMINUSERNAME=admin -e ME_CONFIG_MONGODB_ADMINPASSWORD=admin123 --net mongo-network --name mongo-express -e ME_CONFIG_MONGODB_SERVER=mongodb mongo-express   

Step 3: open mongo-express from browser

    http://localhost:8081

Step 4: create `user-account` _db_ and `users` _collection_ in mongo-express

Step 5: Start your nodejs application locally - go to `app` directory of project 

    cd app
    npm install 
    node server.js
    
Step 6: Access you nodejs application UI from browser

    http://localhost:3000


### To start the app using docker-compose

Step 1: start mongodb and mongo-express

    docker-compose -f docker-compose.yaml up
    
_You can access the mongo-express under localhost:8080 from your browser_
    
Step 2: in mongo-express UI - create a new database "my-db"

Step 3: in mongo-express UI - create a new collection "users" in the database "my-db"       
    
Step 4: start node server 

    cd app
    npm install
    node server.js
    
Step 5: access the nodejs application from browser 

    http://localhost:3000

#### To build a docker image from the application

    docker build -t my-app:1.0 .

### Jenkins CI/CD Pipeline describtion: 
   - Load my groovy script.
   - Build my docker image.
   - Push the image to DockerHub (docker registry).
   - Deploy the application to EKS (Amazon Elastic Kubernetes Service) using my mainfast files.
