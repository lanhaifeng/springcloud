#基础镜像
FROM openjdk:8-jdk-alpine

#镜像制作人
MAINTAINER lanhaifeng@gmail.com

#镜像元数据
LABEL version="1.0" description="spring cloud eureka server service" by="study"

#容器中的挂载点
VOLUME ["/data"]

#容器应该打开的端口
EXPOSE 8167

#定义参数
ARG JAR_FILE

#拷贝本地文件到镜像中
COPY ${JAR_FILE} eureka.jar

#指定运行用户
USER feng:feng

#指定工作目录
WORKDIR feng

#指定容器启动时要执行的命令，如果存在CMD指令，CMD中的参数会被附加到ENTRYPOINT指定的后面
ENTRYPOINT ["java","-jar","./eureka.jar"]
