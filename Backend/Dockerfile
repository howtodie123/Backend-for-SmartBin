######

FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /server
COPY --chmod=500 .mvn .mvn
COPY --chmod=500 mvnw .
COPY pom.xml .
# bộ nhớ đệm được lưu ở .m2/repository
RUN --mount=type=cache,sharing=locked,target=~/.m2/repository/ \
    ./mvnw dependency:resolve-plugins dependency:resolve

FROM builder AS app-builder
COPY ./src/main ./src/main
ARG VERSION=0.0.1-SNAPSHOT
ARG SERVICE_NAME=demo1
# file jar build xong sẽ nằm trong target
ENV JAR_PATH=/server/target/${SERVICE_NAME}-${VERSION}.jar
WORKDIR /server


## tạo file jar và chia file jar thành nhiều
RUN  ./mvnw clean package -q -DskipTests -Drevision=${VERSION} -Dservice_name=${SERVICE_NAME}
RUN ls -lah /server/target/
RUN  java -Djarmode=tools -jar "$JAR_PATH" extract --layers --launcher --destination /server/target/layers
RUN ls -lah /server/target/

FROM eclipse-temurin:17-jdk-alpine AS jre-builder
RUN jlink \
    --add-modules ALL-MODULE-PATH \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /jre/


FROM alpine:latest
ENV JAVA_HOME=/jre
ENV PATH="$JAVA_HOME/bin:$PATH"
COPY --from=jre-builder /jre $JAVA_HOME

WORKDIR /opt/app

ENV LAYER_PATH=/server/target/layers
COPY --from=app-builder  $LAYER_PATH/dependencies/ ./
COPY --from=app-builder  $LAYER_PATH/spring-boot-loader/ ./
COPY --from=app-builder  $LAYER_PATH/snapshot-dependencies ./
COPY --from=app-builder  $LAYER_PATH/application ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]