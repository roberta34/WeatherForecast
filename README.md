# PrognozaMeteo
# Sistem de Prognoză Meteo

## Descriere generală

Acest proiect reprezintă o aplicație web completă pentru gestionarea și analiza prognozelor meteo pentru orașe din Europa și din lume. Sistemul permite generarea de rapoarte meteo zilnice, statistici complexe și previziuni pe termen scurt, utilizând o bază de date relațională și algoritmi implementați la nivel de server.

Aplicația este construită folosind:

* **Backend:** Spring Boot (Java)
* **Bază de date:** PostgreSQL (cu PL/pgSQL)
* **Frontend:** React


---

## Funcționalități principale

### Prognoză Meteo

* Generarea prognozei meteo pentru fiecare zi dintr-un an calendaristic
* Vizualizarea datelor pentru fiecare oraș:

  * temperatură minimă și maximă
  * viteză vânt
  * umiditate
  * index UV
  * tip de vreme (pictograme: soare, ploaie, ninsoare etc.)
* Organizarea datelor pe țări și orașe

---

### Alerte Meteo Inteligente

* Generare automată de alerte în funcție de condiții extreme:

  * vânt puternic
  * umiditate ridicată
  * temperaturi extreme
* Recomandări pentru utilizatori:

  * evitarea deplasărilor
  * utilizarea umbrelei
  * alte sugestii utile

---

### Statistici și Analize

* Calculul temperaturilor medii (zilnic, lunar, anual)
* Identificarea valorilor extreme
* Detectarea anomaliilor meteorologice
* Analiza evoluției vremii pentru un oraș

---

### Funcționalități Avansate

* Previziuni pe termen scurt (7–10 zile)
* Compararea prognozelor:

  * între ani diferiți
  * între sezoane
* Clasificarea orașelor în funcție de condițiile meteo
* Generarea de grafice pentru evoluția temperaturii

---

### Interacțiune utilizatori

* Comentarii asupra prognozelor
* Evaluarea acurateței prognozelor (rating)
* Sistem de scor bazat pe feedback-ul utilizatorilor

---

### Interfață grafică

* Vizualizarea prognozelor într-o interfață modernă
* Hartă cu temperaturi minime și maxime pentru orașe importante
* Grafice interactive pentru analiza datelor

---

## Tehnologii utilizate

### Backend (Spring Boot)

* Java
* Spring Web
* Spring JDBC (fără ORM)
* Expunere API REST

### Bază de date (PostgreSQL)

* SQL + PL/pgSQL
* Proceduri stocate
* Funcții
* Triggere
* Secvențe

### Frontend (React)

* React.js
* Axios pentru API calls
* CSS / Bootstrap / Material UI (opțional)

---

## 🗄️ Structura bazei de date

Aplicația include următoarele entități principale:

* `countries` – lista țărilor
* `cities` – orașe asociate țărilor
* `weather_forecasts` – prognoze zilnice
* `weather_alerts` – alerte meteo
* `users` – utilizatori
* `comments` – comentarii utilizatori
* `ratings` – evaluări ale prognozelor
* `forecast_history` – istoric prognoze (pentru comparații)

---

## Setup și instalare

### Configurarea bazei de date

Asigurați-vă că PostgreSQL este instalat și pornit.

Creați baza de date:

```sql id="db_create"
CREATE DATABASE weather_db;
```

Rulați scripturile:

```sql id="db_schema"
\i schema.sql
```

```sql id="db_populate"
\i populate.sql
```

---

### Configurarea backend-ului (Spring Boot)

```bash id="backend_run"
cd backend
./mvnw spring-boot:run
```

Configurați conexiunea în `application.properties`:

```properties id="props"
spring.datasource.url=jdbc:postgresql://localhost:5432/weather_db
spring.datasource.username=postgres
spring.datasource.password=parola
```

---

### Configurarea frontend-ului (React)

```bash id="frontend_run"
cd frontend
npm install
npm start
```

---

## Rulare aplicație

1. Porniți PostgreSQL
2. Rulați backend-ul Spring Boot
3. Rulați frontend-ul React
4. Accesați aplicația la:

```
http://localhost:3000
```

---

## API Endpoints (exemple)

### Forecast

* `GET /api/forecast/{cityId}`
* `POST /api/forecast`

### Cities

* `GET /api/cities`

### Alerts

* `GET /api/alerts/{cityId}`

### Statistics

* `GET /api/statistics/{cityId}`

---

## Funcționalități avansate în baza de date

* Calcul automat al indexului UV
* Generare automată de alerte (triggere)
* Funcții pentru:

  * temperatură medie
  * clasificare orașe
  * detectare anomalii
* Proceduri pentru prognoze pe mai multe zile

---

## Tratarea excepțiilor

Aplicația tratează:

* erori de conexiune la baza de date
* date invalide
* excepții generate de proceduri PL/pgSQL

---


