FROM openjdk:17-jdk-alpine as build
WORKDIR /workspace/app

COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY config config
COPY src src

RUN apk update && apk add dos2unix
RUN dos2unix gradlew
RUN chmod +x gradlew

RUN ./gradlew build -x test -x detekt
RUN mkdir -p build/libs/dependency && (cd build/libs/dependency; jar -xf ../*.jar)

FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/libs/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","rubber.dutch.hat.HatUserBackApplication"]
