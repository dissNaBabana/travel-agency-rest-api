# ✈️ Moldova Travel Agency API

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/Spring_Security-6.x-6DB33F?style=for-the-badge&logo=springsecurity" />
  <img src="https://img.shields.io/badge/JWT-Authentication-black?style=for-the-badge&logo=jsonwebtokens" />
  <img src="https://img.shields.io/badge/Hibernate-ORM-59666C?style=for-the-badge&logo=hibernate" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql" />
  <img src="https://img.shields.io/badge/Docker-Containerization-2496ED?style=for-the-badge&logo=docker" />
  <img src="https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven" />
</p>

<p align="center">
  <a href="https://www.oracle.com/java/">Java</a> •
  <a href="https://spring.io/projects/spring-boot">Spring Boot</a> •
  <a href="https://spring.io/projects/spring-security">Spring Security</a> •
  <a href="https://jwt.io/">JWT</a> •
  <a href="https://hibernate.org/">Hibernate</a> •
  <a href="https://www.postgresql.org/">PostgreSQL</a> •
  <a href="https://www.docker.com/">Docker</a>
</p>

---

## 📖 About The Project

Moldova Travel Agency API is a RESTful backend application built with Spring Boot for managing travel tours, hotels, bookings, destinations, and users.

The application implements secure authentication and authorization using JWT tokens and Role-Based Access Control (RBAC).

### Key Features

* 🔐 JWT Authentication & Authorization
* 👥 Role-Based Access Control
* ✈️ Tour Management
* 🏨 Hotel Management
* 🌍 Country Management
* 🏙 City Management
* ❤️ Favorites System
* 🧳 Booking Management
* 📊 Administrative Analytics
* 🐳 Docker Support
* 🗄 PostgreSQL Database
* ⚡ Hibernate ORM

---

## 🏛 Architecture

The project follows a layered architecture:

```text
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
PostgreSQL Database
```

### Project Structure

```text
src/main/java

├── controller
├── service
├── repository
├── entity
├── dto
├── dto/request
├── security
├── config
└── exception
```

---

## 🔐 Security

Authentication is based on JWT tokens.

Protected endpoints require:

```http
Authorization: Bearer <jwt_token>
```

### Roles

| Role        | Description          |
| ----------- | -------------------- |
| CLIENT      | Regular user         |
| ADMIN       | Content manager      |
| SUPER_ADMIN | System administrator |

---

# 🌐 API Base URL

```http
/api/v1
```

---

# 🔑 Authentication

| Method | Endpoint       | Access     |
| ------ | -------------- | ---------- |
| POST   | /auth/register | Public     |
| POST   | /auth/sign-in  | Public     |
| POST   | /auth/logout   | Authorized |

---

# 🌍 Countries

| Method | Endpoint        |
| ------ | --------------- |
| GET    | /countries      |
| GET    | /countries/{id} |
| POST   | /countries      |
| PUT    | /countries/{id} |

---

# 🏙 Cities

| Method | Endpoint     |
| ------ | ------------ |
| GET    | /cities      |
| GET    | /cities/{id} |
| POST   | /cities      |
| PUT    | /cities/{id} |

---

# 🏨 Hotels

| Method | Endpoint     |
| ------ | ------------ |
| GET    | /hotels      |
| GET    | /hotels/{id} |
| POST   | /hotels      |
| PUT    | /hotels/{id} |
| DELETE | /hotels/{id} |

---

# ✈️ Tours

| Method | Endpoint                                   |
| ------ | ------------------------------------------ |
| GET    | /tours                                     |
| GET    | /tours/{id}                                |
| GET    | /tours/filter?country=&minPrice=&maxPrice= |
| GET    | /tours/is_hot                              |
| GET    | /tours/new                                 |
| POST   | /tours                                     |
| PUT    | /tours/{id}                                |
| DELETE | /tours/{id}                                |

---

# 👤 Client Profile

| Method | Endpoint             |
| ------ | -------------------- |
| GET    | /clients/me          |
| PUT    | /clients/me          |
| DELETE | /clients/me          |
| GET    | /clients/me/bookings |

---

# ❤️ Favorites

| Method | Endpoint                       |
| ------ | ------------------------------ |
| GET    | /clients/me/favorites          |
| POST   | /clients/me/favorites/{tourId} |
| DELETE | /clients/me/favorites/{tourId} |

---

# 🧳 Bookings

| Method | Endpoint              |
| ------ | --------------------- |
| POST   | /bookings             |
| GET    | /bookings/{id}        |
| GET    | /bookings/tours/{id}  |
| PATCH  | /bookings/{id}/cancel |
| PATCH  | /bookings/{id}/paid   |

### Booking Features

* Automatic price calculation
* Available places validation
* Tour capacity control
* Payment status management
* Booking cancellation support

---

# 👨‍💼 Super Admin

| Method | Endpoint          |
| ------ | ----------------- |
| GET    | /admins           |
| GET    | /admins/{id}      |
| PATCH  | /admins/{id}/role |
| DELETE | /admins/{id}      |

---

# 📊 Analytics

| Method | Endpoint                             |
| ------ | ------------------------------------ |
| GET    | /admin/stats/bookings                |
| GET    | /admin/stats/bookings/filter?status= |
| GET    | /admin/clients                       |
| GET    | /admin/clients/{id}                  |
| GET    | /admin/clients/{id}/bookings         |

---

# 🗄 Database Schema

The application uses PostgreSQL with the following main entities:

* Users
* Countries
* Cities
* Hotels
* Hotel Images
* Tours
* Tour Images
* Bookings
* Favorites

### Relationships

```text
Country ──► City
City ──► Hotel
Country ──► Tour
Hotel ──► Tour
User ──► Booking
Tour ──► Booking
User ──► Favorite ◄── Tour
```

---

# 🐳 Docker

Build image:

```bash
docker build -t moldova-travel-agency .
```

Run container:

```bash
docker run -p 8080:8080 moldova-travel-agency
```

---


## 👩‍💻 Author

Backend REST API project developed with Spring Boot demonstrating:

* Clean Architecture
* JWT Authentication
* Spring Security
* Hibernate ORM
* PostgreSQL
* Docker
* REST API Design
* Role-Based Access Control
