FROM openjdk:17

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the project source
COPY ./src ./src
COPY ./pom.xml ./pom.xml

# RUN chmod 755 mvnw

# RUN ./mvnw install
RUN ./mvnw install -DskipTests
# RUN ./mvnw dependency:go-offline -B
# RUN ./mvnw package -DskipTests

ENTRYPOINT ["java","-jar","./target/api-gateway-0.0.1-SNAPSHOT.jar"]
