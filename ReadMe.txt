##To Build a Docker Image Run:
docker build -t seleniumtest:latest .

## run docker container from image
docker run --rm -d --network host --name selenium seleniumtest:latest

##Copy files from docker container
docker cp <containerId>:/file/path/within/container /host/path/target
docker cp selenium:/usr/local/service/target/surefire-reports/emailable-report.html .


