# Используем официальный образ Tomcat
FROM tomcat:10.1.33

# Копируем WAR файл в каталог для приложений Tomcat /usr/local/tomcat/webapps/
COPY target/ROOT.war /Program Files/apache-tomcat-10.1.33/webapps/

# Открываем порт 8080
EXPOSE 8080