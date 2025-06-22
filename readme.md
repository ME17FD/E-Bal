# 🛒 ebal — E-Commerce Microservices Platform

**ebal** is a modern e-commerce platform built with a microservices architecture using Spring Boot, Spring Cloud, and a React + TypeScript frontend.

## 📦 Architecture Overview

The system is decomposed into the following independently deployable services:

| Service               | Responsibilities                                                                 |
|-----------------------|---------------------------------------------------------------------------------|
| �‍💼 User Service       | User registration, login, JWT-based authentication, and role-based authorization |
| 📦 Product Service     | Product and category CRUD, image upload and filtering                           |
| 🛒 Order Service       | Order creation, status tracking, product availability via Product Service       |
| 💬 Chat Service        | Real-time messaging with WebSocket and image support                            |
| 🧠 Recommendation Service | Provides personalized product suggestions (based on views, categories, orders) |
| 🚪 API Gateway         | Central routing via Spring Cloud Gateway, CORS handling, auth filtering         |
| �‍ Eureka Discovery     | Service discovery and registration                                              |
| 🌐 Frontend (React)    | Built with Vite, React, and TypeScript; connects to microservices via REST      |

## 🛠️ Technologies Used

### Backend
- Spring Boot 3
- Spring Cloud (Eureka, Gateway, Config)
- Spring Security + JWT
- Spring Data JPA
- OpenFeign
- WebSocket (STOMP)
- Maven / Gradle

### Frontend
- React + TypeScript
- Vite
- Axios
- React Router

## 🗃️ Database Configuration

Each service uses its own dedicated database:
- `users_db`
- `products_db`
- `orders_db`
- `chat_db`
- (optional) `recommendation_db` or stateless logs
