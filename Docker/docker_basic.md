# Docker
**Official website**
> https://hub.docker.com/

## Docker Basic Commands

`Version`     

❯ docker -v     
Docker version 20.10.12, build 20.10.12-0ubuntu2~20.04.1

❯ docker --version      
Docker version 20.10.12, build 20.10.12-0ubuntu2~20.04.1

`Pull Image`

❯ docker pull < Image name >
>**❯ sudo docker pull hello-world**     
[sudo] password for password: 
Using default tag: latest
latest: Pulling from library/hello-world
Digest: sha256:6e8b6f026e0b9c419ea0fd02d3905dd0952ad1feea67543f525c73a0a790fefb
Status: Image is up to date for hello-world:latest
docker.io/library/hello-world:latest

❯ docker pull < Image name > : < tag >
> **❯ sudo docker pull amazoncorretto:17.0.6**                    
> 17.0.6: Pulling from library/amazoncorretto



`List of all images`

❯ sudo docker images                  
REPOSITORY  | TAG | IMAGE ID | CREATED | SIZE       
hello-world | latest | feb5d9fea6a5 | 17 months ago | 13.3kB

`Search images on the docker`

❯ sudo docker search < Parent Image Name >

> **❯ sudo docker search mysql**        
> NAME                            DESCRIPTION                                     STARS     OFFICIAL   AUTOMATED        
> mysql                           MySQL is a widely used, open-source relation…   13860     [OK]             

`Show all run container`

❯ sudo docker ps -a

`Docker run with custom name and in detached mode`

❯ sudo docker run --name javaContainer -d < CONTAINER ID >
❯ sudo docker run --name javaContainer --detach < CONTAINER ID >


`Docker run with interactive mode - This will keep the container running`

❯ sudo docker run --name javaContainer -it -d < Image name >

`Enter/Execute the container image`

❯ sudo docker exec -it < CONTAINER ID > python3




