# Force AMD64 platform to match Docker build environment
FROM --platform=linux/amd64 maven:latest

# Set working directory inside the container
WORKDIR /app

# Copy the pom.xml to download dependencies first (caching optimization)
COPY pom.xml /app/

# Copy the entire project to the container
COPY . /app/

# Package the application using Maven
RUN mvn package

# Run the main class from the built JAR
CMD ["java", "-jar", "target/shoppingcartapp.jar"]
