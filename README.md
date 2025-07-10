# 🏋️‍♂️ MyGym — Application de gestion de salle de sport en microservices EN COURS DE DEV

**MyGym** est une application web full-stack développée en Java 21 et Angular 20 permettant la **gestion des équipements sportifs** et la **réservation de créneaux par les utilisateurs**, selon leur état (disponible, en entretien, cassé). Ce projet repose sur une architecture **microservices** moderne, conteneurisée avec **Docker**.

---

## 🚀 Fonctionnalités principales

### 👨‍💼 Côté Administrateur
- Visualisation de l’état des machines (Disponible / En entretien / Cassée)
- Mise à jour de l’état d’une machine
- Ajout ou suppression d’équipements

### 🧑‍🤝‍🧑 Côté Utilisateur
- Consultation des machines disponibles
- Réservation d’un créneau horaire sur une machine
- Annulation d’une réservation

---

## 🧱 Stack technique

### Backend
- **Java 21**
- **Spring Boot** (Web, JPA, Validation)
- **Spring Cloud Eureka** – service discovery
- **Spring Cloud Gateway** – API Gateway
- **Spring Security** (à venir)
- **PostgreSQL** – base de données
- **Docker** – conteneurisation des services

### Frontend
- **Angular 20**
- **Angular Material** – UI moderne et responsive
- Communication avec l’API Gateway via HTTP

## 🛠️ Lancer le projet localement

### 1. Prérequis

- Java 21+
- Node.js / Angular CLI
- Docker & Docker Compose
- PostgreSQL (ou utilisez Docker)

### 2. Lancement rapide

```bash
# À la racine du projet
docker-compose up --build

# Ou utilisation du lancement.bat qui lancera les différents services avec des TO afin d'éviter les conflits de démarrage
```

### 3. Accès à l'application

Frontend : http://localhost:4200

API Gateway : http://localhost:8080

Eureka Dashboard : http://localhost:8761

 ## 📌 TODO / Améliorations prévues
✅ Authentification / Sécurité (JWT)

🔄 Gestion des conflits de réservation

📊 Tableau de bord pour les admins

🔒 Rôles (Admin / Utilisateur)

📱 Responsive mobile

🧪 Tests unitaires & d'intégration

🚀 Pipeline CI/CD GitHub Actions

## 🙋‍♂️ Auteur
👤 Fabien Lautru
🎯 Développeur Full Stack Java & Angular | Passionné par les architectures distribuées et les bonnes pratiques DevOps.
📫 github.com/flautru
