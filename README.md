# Eureka Server

## 개요

Eureka Server는 Netflix에서 개발한 서비스 디스커버리 패턴을 구현한 서버입니다. 마이크로서비스 아키텍처에서 서비스들이 서로를 찾고 통신할 수 있도록 중앙 레지스트리 역할을 합니다.

## 주요 기능

### 클라이언트 사이드 서비스 디스커버리

-   서비스들이 호스트명과 포트를 하드코딩하지 않고 서로를 찾고 통신할 수 있습니다
-   각 서비스는 중앙 서비스 레지스트리에 등록되어야 합니다

### Netflix Eureka의 특징

-   각 클라이언트가 동시에 서버 역할을 하여 피어 복제가 가능합니다
-   클라이언트는 서비스 레지스트리의 모든 피어 목록을 가져옵니다
-   로드 밸런싱 알고리즘을 통해 다른 서비스에 요청을 보냅니다
-   하트비트 신호를 통해 서비스 상태를 관리합니다

## 프로젝트 구조

```
src/
├── main/
│   ├── java/
│   │   └── kr/co/lunasoft/
│   │       ├── EurekaServerApplication.java
│   │       ├── config/
│   │       │   ├── AspectConfig.java
│   │       │   └── ZuulFilterConfig.java
│   │       └── controller/
│   │           └── CustomErrorController.java
│   └── resources/
│       └── application.yml
└── test/
    └── java/
        └── kr/co/lunasoft/
            └── EurekaServerApplicationTests.java
```

## 시작하기

### 필요 조건

-   Java 8 이상
-   Maven 3.x

### 실행 방법

1. **Maven을 사용한 실행**

    ```bash
    ./mvnw spring-boot:run
    ```

2. **JAR 파일 빌드 및 실행**
    ```bash
    ./mvnw clean package
    java -jar target/eureka-server-*.jar
    ```

### 접속 정보

-   Eureka Dashboard: http://localhost:8761
-   기본 포트: 8761

## 설정

### application.yml 주요 설정

```yaml
server:
    port: 8761

eureka:
    instance:
        hostname: localhost
    client:
        register-with-eureka: false
        fetch-registry: false
        service-url:
            defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

## 마이크로서비스 아키텍처 구성

이 프로젝트는 다음과 같은 마이크로서비스 생태계의 일부로 동작합니다:

1. **서비스 레지스트리 (Eureka Server)** - 현재 프로젝트
2. **REST 서비스 (Eureka Client)** - 레지스트리에 자신을 등록하는 서비스
3. **웹 애플리케이션** - Spring Cloud Netflix Feign Client를 사용하는 클라이언트

## 모니터링 및 관리

### Eureka Dashboard

-   등록된 모든 서비스 인스턴스 조회
-   서비스 상태 및 헬스체크 정보 확인
-   서비스 디스커버리 통계 모니터링

### 헬스체크

서비스들은 주기적으로 하트비트를 전송하여 활성 상태를 유지합니다:

-   기본 하트비트 주기: 30초
-   서비스 제거 임계값: 90초

## 개발 및 테스트

### 테스트 실행

```bash
./mvnw test
```

### 개발 모드 실행

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## 기여하기

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 `LICENSE` 파일을 참조하세요.

## 문의사항

프로젝트에 대한 문의사항이나 이슈가 있으시면 GitHub Issues를 통해 연락주세요.
