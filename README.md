spring-cloud-without-netflix-samples
====================================

Netflix OSSを使わないSpring Cloudによるマイクロサービスのサンプルです。

# 必要な環境
- JDK 11
- Maven 3.x
- Docker

# 技術スタック

| パターン | ライブラリ |
|--------|-----------|
| Service Discovery | Spring Cloud Consul |
| Client-Side Load Balancing | Spring Cloud LoadBalancer |
| Circuit Breaker | Resilience4j |

# DockerによるConsul起動

```bash
$ ./docker-run-consul.sh
```

# マイクロサービスの起動

```bash
$ cd frontend-service
$ mvn clean package
$ java -jar target/frontend-service-0.0.1-SNAPSHOT.jar
```

```bash
$ cd backend-service
$ mvn clean package
$ java -jar target/backend-service-0.0.1-SNAPSHOT.jar
```

```bash
$ cd backend-service
$ java -jar target/backend-service-0.0.1-SNAPSHOT.jar --server.port=9011
```

# Consulからサービス一覧を取得

```bash
$ curl -v localhost:8500/v1/health/service/frontend-service
$ curl -v localhost:8500/v1/health/service/backend-service
```