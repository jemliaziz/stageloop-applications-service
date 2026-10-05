# StageLoop — Students & Applications Microservice

Spring Boot microservice of **StageLoop**, a team project at ESPRIT: a microservices platform that digitizes the internship lifecycle (final-year and summer internships) for students, companies and administrators.

This repository contains **my part of the platform: the Students & Applications service**. It manages student profiles and internship applications, from submission to acceptance, rejection or expiry.

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4-brightgreen) ![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-Eureka%20%7C%20Config-blue) ![MySQL](https://img.shields.io/badge/MySQL-8-blue) ![Docker](https://img.shields.io/badge/Docker-ready-2496ED)

---

## ✨ Features

- **Student profiles** — CRUD and keyword search
- **Internship applications** — CRUD, keyword search and a status workflow: `EN_ATTENTE` (pending) → `ACCEPTEE` (accepted) / `REFUSEE` (rejected) / `EXPIREE` (expired)
- **CV management** — CV upload and download, plus automatic generation of a CV summary and cover letter from student data
- **Scheduled jobs** (`@Scheduled`) — daily check that marks expired applications, monthly cleanup of old applications
- **Real-time notifications** — WebSocket (STOMP / SockJS) broadcast on `/topic/etudiantNotifications`
- **Cross-cutting concerns with AOP** — logging and execution-time monitoring of the service layer
- **API documentation** — Swagger UI (springdoc-openapi)
- **Observability** — Spring Boot Actuator health endpoint

## 🏗️ Architecture

The service is one of the StageLoop microservices. It registers with **Netflix Eureka** for service discovery and can load its configuration from **Spring Cloud Config**. The Angular 17 front-end of the platform consumes its REST API and subscribes to its WebSocket notifications.

```
Angular 17 front-end ──REST──▶  Applications service (this repo) ──JPA──▶ MySQL
        ▲                              │
        └──── WebSocket (STOMP) ───────┘
                                       │ registers with
                                       ▼
                         Eureka · Spring Cloud Config
```

Code layout — layered architecture:

```
src/main/java/.../
├── controllers/   REST + WebSocket endpoints
├── services/      business logic (IServices / ServicesImpl), scheduled jobs
├── repositories/  Spring Data JPA repositories
├── entities/      JPA entities: Etudiant, Candidature, Stage, StatutCandidature
├── aspects/       AOP: LoggingAspect, PerformanceAspect
└── Config/        CORS and WebSocket configuration
```

## 🔌 REST API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/etudiants` | List students |
| GET | `/api/etudiants/{id}` | Get a student |
| GET | `/api/etudiants/search?keyword=` | Search students |
| POST | `/api/etudiants/create` | Create a student |
| PUT | `/api/etudiants/update/{id}` | Update a student |
| DELETE | `/api/etudiants/delete/{id}` | Delete a student |
| GET | `/api/candidatures` | List applications |
| GET | `/api/candidatures/{id}` | Get an application |
| GET | `/api/candidatures/search?keyword=` | Search applications |
| POST | `/api/candidatures/create` | Create an application |
| PUT | `/api/candidatures/update/{id}` | Update an application |
| DELETE | `/api/candidatures/delete/{id}` | Delete an application |
| POST | `/api/candidatures/upload` | Upload a CV |
| GET | `/api/candidatures/cv/{filename}` | Download a CV |
| GET | `/api/candidatures/verifier-expirees` | Mark expired applications |
| DELETE | `/api/candidatures/supprimer-anciennes` | Delete old applications |

Full interactive documentation: **`http://localhost:8089/swagger-ui.html`**

## 🚀 Run it

### With Docker (recommended)

```bash
cp .env.example .env   # then set your own values
docker compose up --build
```

The API starts on `http://localhost:8089` with a MySQL 8 database.

### Locally

Requirements: Java 17 and a running MySQL server.

```bash
export DB_PASSWORD=your-mysql-password
./mvnw spring-boot:run
```

### Configuration

All settings come from environment variables — **no credentials are stored in the code**.

| Variable | Default | Purpose |
|---|---|---|
| `DB_URL` | `jdbc:mysql://localhost:3306/platformestage` | Database URL |
| `DB_USERNAME` / `DB_PASSWORD` | `root` / *(empty)* | Database credentials |
| `MAIL_USERNAME` / `MAIL_PASSWORD` | *(empty)* | SMTP credentials for email features |
| `EUREKA_ENABLED` / `EUREKA_URL` | `false` / `http://localhost:8761/eureka/` | Service discovery |
| `CONFIG_ENABLED` | `false` | Spring Cloud Config client |

## 🛠️ Tech stack

Java 17 · Spring Boot 3.4 · Spring Data JPA / Hibernate · Spring Cloud (Eureka client, Config client) · Spring WebSocket (STOMP) · Spring AOP · springdoc-openapi · Spring Boot Actuator · MySQL 8 · Lombok · Maven · Docker

## 👥 Context

Team project — 4th year Software Engineering, ESPRIT (2025). Each team member owned one functional module of StageLoop. **I owned the Students & Applications module end-to-end**: this Spring Boot microservice and its Angular screens (applications, students, CV display, statistics). The other modules and the final integrated version of the platform were built by my teammates.

---

**Mohamed Aziz Jemli** — Full-Stack Software Engineer
[Portfolio](https://jemliaziz.github.io) · [LinkedIn](https://www.linkedin.com/in/mohamed-aziz-jemli)
