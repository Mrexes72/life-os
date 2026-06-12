# --- Trinn 1: Byggefasen (Build stage) ---
FROM gradle:8-jdk21 AS builder
WORKDIR /app

# Kopier Gradle-konfigurasjonen for å cache avhengigheter (dependencies)
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Last ned avhengigheter uten å bygge hele koden (for raskere påfølgende bygg)
RUN ./gradlew dependencies --no-daemon || true

# Kopier kildekoden og bygg den kjørbare JAR-filen
COPY src src
RUN ./gradlew bootJar --no-daemon -x test

# --- Trinn 2: Kjørefasen (Runtime stage) ---
FROM eclipse-temurin:21-jre
WORKDIR /app

# Opprett en ikke-priviligert bruker av sikkerhetshensyn
RUN useradd -m springuser
USER springuser

# Kopier den ferdige JAR-filen fra byggefasen
COPY --from=builder /app/build/libs/*.jar app.jar

# Definer nettverksporten applikasjonen lytter på
EXPOSE 8080

# Kommando for å kjøre Spring Boot-applikasjonen
ENTRYPOINT ["java", "-jar", "app.jar"]