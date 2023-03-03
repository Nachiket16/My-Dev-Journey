# Docker
**Official website**      
> https://hub.docker.com/       
> https://docs.docker.com/engine/reference/commandline/container_ls/    

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

❯ sudo docker run --name javaContainer -it -d < Image ID >
> ❯ sudo docker run --name javaContainer -it -d 5e28ba2b4cdb


`Run / Execute the container image`

❯ sudo docker exec -it < CONTAINER ID / Name > jshell
> ❯ sudo docker exec -it javaContainer jshell

`Inspect the container`

❯ sudo docker inspect < CONTAINER ID >
>**❯ sudo docker inspect 93e6190b0a84**

`Stop the docker container`
> docker stop my_container

________________________________________________________
`Mysql On Docker`                 
❯ sudo docker pull mysql              
* Set The Password              
❯ sudo docker run --name mysqlDb -e MYSQL_ROOT_PASSWORD=root -d mysql             
* Run the Mysql       
❯ sudo docker exec -it mysqlDb bash  (Enter into the bash)      
bash-4.4# : mysql -p        
Enter Password: #### (Enter the set password)       
mysql > (Hurrey !!!! You have installed the mysql and it's up and runnig)       

![Docker_Mysql](https://user-images.githubusercontent.com/84851340/222643723-6744524c-71aa-457e-a7fb-d0b9e6445915.png)

________________________________________________________
`How to check and modify Image status`      
* This will show you running images       
❯ sudo docker ps      
   * This will Stop the running images      
   ❯ sudo docker stop mysqlDb           
   ❯ sudo docker stop < Container name / ID >       

* This will show you previously running but now stopped images      
❯ sudo docker ps -a     
  * This will remove stopped container image/images         
  ❯ sudo docker rm 93e6190b0a84 dbbe9123d5f4      
  ❯ sudo docker rm < Conatainer Id, Id, Id >    

* This will Restart the stopped container     
 ❯ sudo docker restart javaContainer
 ❯ sudo docker restart < Conatainer Name >

* List of Installed Images        
❯ sudo docker images      
  * This will remove image        
❯ sudo docker rmi hello-world         
❯ sudo docker rmi < Image name >   


![docker_delet](https://user-images.githubusercontent.com/84851340/222647613-63640bcd-7064-43da-9a08-fada83a236b1.png)


