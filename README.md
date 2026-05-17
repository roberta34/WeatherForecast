# PrognozaMeteo

Aplicație web (full‑stack) pentru prognoze meteo la nivel de oraș, statistici/rapoarte și funcționalități de analiză (alerte, anomalii, clasamente), construită cu **Spring Boot + PostgreSQL + React**.

## Cuprins

- [Stack tehnologic](#stack-tehnologic)
- [Funcționalități](#funcționalități)
- [Cerințe](#cerințe)
- [Configurare bază de date](#configurare-bază-de-date)
- [Rulare aplicație (Windows / PowerShell)](#rulare-aplicație-windows--powershell)
- [Configurare](#configurare)
- [API (endpoints)](#api-endpoints)
- [Structura proiectului](#structura-proiectului)
- [Troubleshooting](#troubleshooting)

## Stack tehnologic

- **Backend:** Spring Boot (Java), API REST
- **Bază de date:** PostgreSQL (SQL + PL/pgSQL: funcții/proceduri/triggere)
- **Frontend:** React (Create React App) + Axios

## Funcționalități

- Prognoze meteo per oraș (min/max, umiditate, vânt, UV, tip de vreme)
- Alerte meteo (generate pe baza condițiilor extreme)
- Statistici pe oraș + statistici globale
- Detectare anomalii meteo
- Comentarii și rating-uri (feedback utilizatori)
- Clasamente / ranking (inclusiv top cele mai calde/reci orașe)

## Cerințe

- **Java** (recomandat: LTS instalat) + Maven Wrapper (inclus în proiect)
- **Node.js** + **npm** (pentru frontend)
- **PostgreSQL** (local)

Porturi implicite în acest proiect:

- Backend: `http://localhost:8085`
- Frontend: `http://localhost:3000`

## Configurare bază de date

Scripturile DB sunt în folderul [`database/`](./database):

- `schema.sql`
- `populate.sql`
- `functions.sql`
- `triggers.sql`

1) Creează baza de date:

```sql
CREATE DATABASE weather_db;
```

2) Rulează scripturile (în `psql`, din rădăcina repo‑ului):

```sql
\i database/schema.sql
\i database/functions.sql
\i database/triggers.sql
\i database/populate.sql
```

## Rulare aplicație (Windows / PowerShell)

### 1) Backend (Spring Boot)

Din rădăcina repo‑ului:

```powershell
cd .\backend
.\mvnw.cmd spring-boot:run
```

Backend-ul pornește pe `http://localhost:8085` (configurat în `application.properties`).

### 2) Frontend (React)

În alt terminal:

```powershell
cd .\frontend
npm install
npm start
```

Aplicația este disponibilă la `http://localhost:3000`.

## Configurare

### Backend

Fișier: [`backend/src/main/resources/application.properties`](./backend/src/main/resources/application.properties)

Valorile curente (development local):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/weather_db
spring.datasource.username=postgres
spring.datasource.password=roberta
server.port=8085
```

### Frontend

Fișier: [`frontend/src/services/api.js`](./frontend/src/services/api.js)

Aplicatia folosește:

- `baseURL: http://localhost:8085/api`

Dacă schimbi portul backend‑ului, actualizează `baseURL`.

## API Endpoints

Prefix comun: `http://localhost:8085/api`

### Orașe

- `GET /cities` – listă orașe

### Prognoză

- `GET /forecast/{cityId}` – prognoză pentru un oraș

### Alerte

- `GET /alerts/{cityId}` – alerte pentru un oraș

### Statistici

- `GET /statistics` – toate statisticile
- `GET /statistics/{cityId}` – statistici pentru un oraș

### Anomalii

- `POST /anomalies/detect` – rulează detectarea anomaliilor
- `GET /anomalies` – listă anomalii

### Comentarii

- `POST /comments` – adaugă comentariu
- `GET /comments` – listă comentarii

### Rating-uri

- `POST /ratings` – adaugă rating
- `GET /ratings` – listă rating-uri

### Ranking (clasamente)

- `POST /ranking/generate` – generează clasamente
- `GET /ranking` – listă clasamente
- `GET /ranking/hottest` – cele mai calde orașe
- `GET /ranking/coldest` – cele mai reci orașe
- `GET /ranking/top/{limit}` – top N

### Swagger / OpenAPI

Proiectul folosește adnotări OpenAPI (ex. `@Tag`, `@Operation`). Dacă dependența Swagger/OpenAPI este activă în backend, interfața este de obicei disponibilă la una dintre adrese:

- `http://localhost:8085/swagger-ui.html`
- `http://localhost:8085/swagger-ui/index.html`

## Structura proiectului

```
PrognozaMeteo/
  backend/    # Spring Boot (API REST)
  frontend/   # React (UI)
  database/   # schema + populate + functions + triggers
  docs/       # documentație (dacă există)
```

## Troubleshooting

- **Eroare CORS / nu se conectează frontend-ul la backend:** verifică portul backend (`8085`) și `baseURL` din `frontend/src/services/api.js`.
- **Port ocupat:** schimbă `server.port` în `backend/.../application.properties` și actualizează `baseURL` în frontend.
- **DB auth failed:** actualizează `spring.datasource.username/password` sau crează user-ul în PostgreSQL.
- **Baza de date goală:** rulează și `database/populate.sql` după `schema.sql`.
