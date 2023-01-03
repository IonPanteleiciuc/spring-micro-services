#!/bin/bash

run(){
    docker rmi front
    docker build -t front:latest front-end/
    docker run -p 4000:8080 front:latest 
}

run