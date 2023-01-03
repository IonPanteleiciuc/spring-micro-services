run(){
    docker compose down
    docker rm -f $(docker ps -a -q)
    docker rmi -f $(docker image ls -q)
    docker volume rm $(docker volume ls -q)
    # docker build -t product-service:latest product-service
    # docker build -t api-gateway:latest api-gateway
    docker build -t discovery-server:latest discovery-server

    # docker run -d product-service -p 8080:8080
    # docker run -d api-gateway -p 8082:8082
    docker run -d discovery-server -p 8760:8760
}

run