# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
#ADD mysql-connector-java-5.1.44.jar jdbc/mysql-connector-java-5.1.44.jar
FROM payara/micro
#WORKDIR /opt
# EXPOSE 8080
ADD domain.xml $PAYARA_PATH
ADD mysql-connector-java-5.1.44.jar $PAYARA_PATH/database-connector.jar
ADD target/arq-app-productos-1.0-SNAPSHOT.war  $PAYARA_PATH
ENTRYPOINT ["java", "-jar", "/opt/payara/payara-micro.jar", "--addJars", "/opt/payara/database-connector.jar", "--deploy", "/opt/payara/arq-app-productos-1.0-SNAPSHOT.war","--domainConfig","/opt/payara/domain.xml"]
#ENTRYPOINT java -jar -cp "/opt/jdbc/mysql-connector-java-5.1.44.jar:/opt/payara/micro.jar" /opt/payara/payara-micro.jar --deploy /opt/app/arq-app-productos-1.0-SNAPSHOT.war --domainConfig domain.xml 