FROM openjdk:11

WORKDIR /reader/bin

ENV PORT 8080

COPY /target/classes /reader/bin/classes
COPY /target/dependency /reader/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","com.gcp.poc.reader.ReaderApplication"]