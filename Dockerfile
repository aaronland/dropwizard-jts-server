# Copied from: https://github.com/carlossg/docker-maven/blob/master/openjdk-18-slim/Dockerfile

FROM openjdk:18-jdk-slim AS builder

RUN apt-get update \
  && apt-get install -y curl procps \
  && rm -rf /var/lib/apt/lists/*

ARG MAVEN_VERSION=3.8.5
ARG USER_HOME_DIR="/root"
ARG SHA=89ab8ece99292476447ef6a6800d9842bbb60787b9b8a45c103aa61d2f205a971d8c3ddfb8b03e514455b4173602bd015e82958c0b3ddc1728a57126f773c743
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

COPY . /usr/src/dropwizard-jts-server

WORKDIR /usr/src/dropwizard-jts-server

RUN mvn clean && mvn install

FROM openjdk:18-slim

RUN mkdir /usr/local/jar

# TO DO: derive version number from pom.xml...

COPY --from=builder /usr/src/dropwizard-jts-server/target/jts-server-0.0.2.jar /usr/local/jar/jts-server.jar
