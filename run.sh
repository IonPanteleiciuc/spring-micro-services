#!/bin/bash

run(){
    docker rm -f $(docker ps -a -q)
    docker rmi -f $(docker image ls -q)
    docker volume rm $(docker volume ls -q)
    docker compose up -d
}

run