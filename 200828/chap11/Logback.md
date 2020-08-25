# Logback으로 자세한 에러 로그 출력하기

### pom.xml에 의존 추가

```xml
<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
	<version>1.7.25</version>
</dependency>

<dependency>
	<groupId>ch.qos.logback</groupId>
	<artifactId>logback-classic</artifactId>
	<version>1.2.3</version>
</dependency>
```

### src/main/resources 폴더 생성, logback.xml 파일

- 설정 후 Maven 프로젝트 업데이트 필수

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
		<encoder>
			<pattern>%d %5p %c{2} - %m%n</pattern>
		</encoder>
	</appender>
	<root level="INFO">
		<appender-ref ref="stdout" />
	</root>
	
	<logger name="org.springframework.web.servlet" level="DEBUG"/>
</configuration>
```

Seunghye Jung