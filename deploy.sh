sudo docker stop arq-productos
sudo docker rm arq-productos
sudo docker build --no-cache -t arq/app-productos:$1 -t arq/app-productos:latest .
sudo docker run -d --net red1 --ip 172.19.0.4 -p 9002:8080 --name arq-productos arq/app-productos:$1
