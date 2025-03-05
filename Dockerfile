# Étape 1 : Construction de l'application avec Maven
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Copier uniquement le fichier pom.xml pour optimiser le cache Maven
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copier le reste du code source après pour éviter de retélécharger les dépendances à chaque changement de code
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Création de l'image finale plus légère avec JRE 21
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copier uniquement le fichier JAR compilé depuis l'étape précédente
COPY --from=builder /app/target/*.jar app.jar

# Exposer le port de l'application
EXPOSE 8080

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
