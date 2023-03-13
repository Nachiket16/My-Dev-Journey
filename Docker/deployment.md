❯ sudo docker run -d --name mysqldb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=electronic_store mysql

1❯ sudo docker network create bootApp

2❯ sudo docker run -d --name mysqldb -p 3307:3306 --network=bootApp -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=agri_store mysql

3❯ sudo docker run -it --name store -p 9091:9090 --network=bootApp -e MYSQL_HOST=mysqldb -e MYSQL_PORT=3306 agristore
