docker build -t jenkins-mvn .
docker run -p 8082:8080 -p 50000:50000 --name jenkins-mvn jenkins-mvn
