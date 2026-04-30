# PROGNOZA METEO
## Sprint Plan Complet — Backend (Spring Boot) + Frontend (React) + PostgreSQL

---

## OVERVIEW GENERAL

Aplicația este un sistem web pentru gestionarea și analiza prognozelor meteo pentru orașe din Europa și din lume.

Sistemul permite:

- generarea prognozelor meteo zilnice
- stocarea datelor meteorologice pe termen lung
- generarea automată de alerte meteo
- analiza statistică a datelor
- compararea prognozelor istorice
- clasificarea orașelor
- interacțiune utilizatori (comentarii + rating)
- vizualizare grafică și hartă

---

## ARHITECTURĂ OBLIGATORIE

### Backend (Spring Boot — fără ORM)
```
backend/
│
├── controller/
│ ├── ForecastController.java
│ ├── CityController.java
│ ├── AlertController.java
│ ├── StatisticsController.java
│
├── service/
│ ├── ForecastService.java
│ ├── CityService.java
│ ├── AlertService.java
│ ├── StatisticsService.java
│
├── repository/
│ ├── ForecastRepository.java
│ ├── CityRepository.java
│ ├── AlertRepository.java
│
├── model/
│ ├── Forecast.java
│ ├── City.java
│ ├── Alert.java
│ ├── User.java
│
├── dto/
├── config/
└── WeatherApplication.java
```

### Frontend (React)
```
frontend/
│
├── pages/
│ ├── Dashboard.jsx
│ ├── Forecast.jsx
│ ├── CityDetails.jsx
│ ├── Statistics.jsx
│
├── components/
│ ├── ForecastCard.jsx
│ ├── Chart.jsx
│ ├── Navbar.jsx
│
├── services/
│ └── api.js
│
└── App.js
```

## STRUCTURA BAZEI DE DATE

Tabele principale:

- `countries` – țări
- `cities` – orașe
- `weather_forecasts` – prognoze meteo
- `weather_alerts` – alerte meteo
- `users` – utilizatori
- `comments` – comentarii
- `ratings` – evaluări

---

## SITEMAP APLICAȚIE

| Pagină | Descriere |
|------|--------|
| / | Dashboard |
| /forecast | Prognoze |
| /city/{id} | Detalii oraș |
| /statistics | Statistici |
| /map | Hartă |


## SPRINT 1 — FOUNDATION + CORE SYSTEM

### Obiectiv

Construirea bazei aplicației:
- bază de date completă
- backend funcțional
- frontend minimal

---

### 1. Database Design

#### Ce trebuie implementat:

- modelează entitățile:
  - countries
  - cities
  - weather_forecasts
  - weather_alerts
  - users

#### Ce trebuie urmărit:

- relații corecte (FK)
- normalizare (evit redundanța)
- fiecare tabel are date relevante

---

### 2. Scripturi SQL

#### Ce trebuie să conțină:

- script de creare tabele
- script de populare automată
- minim 15 înregistrări/tabel

---


### 3. Logică în PL/pgSQL

#### Trebuie implementate:

- funcții:
  - calcul temperatură medie
  - calcul index UV
  - detectare anomalie

- proceduri:
  - generare prognoză pe 7 zile
  - comparare date istorice

- triggere:
  - generare alertă automată
  - log modificări

---

### 🔹 4. Backend Core (Spring)

#### Ce trebuie implementat:

- structură:
  - Controller
  - Service
  - Repository

- endpoint-uri:
  - cities
  - forecast
  - alerts

---

### 5. API Design

Toate răspunsurile trebuie să fie JSON:

```json
{
  "status": "success",
  "data": {},
  "message": ""
}
```


### 6. Frontend Core
- Pagina Forecast
- selectare oraș
- listă prognoze
- afișare temperaturi
UI
- carduri
- loading
- erori

---

## Sprint 1 Outcome

- DB completă
- backend funcțional
- frontend minimal
- PL/pgSQL implementat

---

## SPRINT 2 — ADVANCED + STATISTICS + FINAL

### Obiectiv

Transformarea aplicației într-un sistem complet.

---

### 1. Extindere Database

#### Adaugă:

**Funcții:**
- temperatură medie pe perioadă
- temperatură maximă/minimă
- ranking orașe

**Proceduri:**
- clasificare orașe
- analiză sezonieră

---

### 2. Backend Advanced

#### Endpoint-uri noi:

- statistics
- ranking
- anomalies
- comments
- rating

---

### 3. Statistics System

#### Trebuie să calculezi:

- medii temperaturi
- extreme
- evoluții
- comparații între ani

---

### 4. Frontend Dashboard

#### Conține:

- overview temperaturi
- alerte
- top orașe

---

### 5. Pagina Statistics

#### Conține:

- grafice (Chart.js)
- comparații
- analize

---

### 6. Pagina City Details

#### Conține:

- istoric temperaturi
- evoluție grafică
- detalii meteo

---

### 7. Hartă (IMPORTANT)

- afișare orașe pe hartă
- temperaturi min/max

---

### 8. UI & UX

#### Trebuie:

- design responsive
- componente reutilizabile
- feedback vizual (loading, error, success)

---

### 9. Integrare finală

- conectare frontend-backend
- testare completă

---

## ✅ Sprint 2 Outcome

- aplicație completă
- UI modern
- logică avansată
- statistici funcționale

---
