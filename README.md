### Spring Boot项目部署到Docker

#### spring boot + mybatis + HikariCP + websocket + docker

> 从今天开始深入学习java后端的开发，停止除了工作以外的所有android的研究。
打好基础功底，为日后发力奠定基础。学好微服务解决现有公司服务器部署运维的痛点。

- 工程打包
```shell
mvn package
```
- 部署到Docker
```shell
mvn package docker:build
```

### Docker相关的命令

- 查看镜像
```shell
docker images
```
- 以特定image运行一个new container
```shell
docker run [imageId]
```
- 查看 all container
```shell
docker ps -a
```
- 运行特定的docker
```shell
docker start [containerId]
```