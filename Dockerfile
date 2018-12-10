FROM getzephyr/java8

MAINTAINER aliaksei.matsarski@sjsu.edu

RUN echo "*				soft	nofile			65536" >> /etc/security/limits.conf
RUN echo "*				hard	nofile			65536" >> /etc/security/limits.conf

#install log stash


RUN echo "networkaddress.cache.ttl=60" >> /usr/lib/jvm/java-8-oracle/jre/lib/security/java.security

RUN echo "America/Los_Angeles" > /etc/timezone && dpkg-reconfigure --frontend noninteractive tzdata

ADD deploy/simulated-station.jar /

#defaults
#ENV SERVER_PORT 8080

#EXPOSE ${SERVER_PORT}

# Start Tomcat

CMD java -jar /simulated-station.jar