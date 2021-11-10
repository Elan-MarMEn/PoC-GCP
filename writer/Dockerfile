FROM openjdk:11

WORKDIR /writer/bin

ENV PORT 8080

COPY /target/classes /writer/bin/classes
COPY /target/dependency /writer/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","com.gcp.poc.writer.WriterApplication"]