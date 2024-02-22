FROM tomcat:latest
COPY /backend/*.jar /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
