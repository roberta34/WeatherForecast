# PrognozaMeteo – Frontend

Interfața web (React) pentru proiectul **PrognozaMeteo**.

## Rulare (dezvoltare)

Prerechizite: **Node.js** + **npm**.

```powershell
cd .\frontend
npm install
npm start
```

Aplicația pornește pe `http://localhost:3000`.

## Configurare API

Frontend-ul comunică cu backend-ul Spring Boot folosind Axios.

Fișier: [`src/services/api.js`](./src/services/api.js)

În mod implicit, API-ul este:

- `http://localhost:8085/api`

Dacă schimbi portul backend‑ului, actualizează `baseURL` din `api.js`.

## Scripturi disponibile (Create React App)

### `npm start`

Rulează aplicația în modul development.

### `npm test`

Lansează test runner-ul.

### `npm run build`

Build pentru producție în folderul `build`.

### `npm run eject`

Eject (ireversibil) din Create React App.

## Documentație

Pentru setup complet (DB + backend + endpoints), vezi README-ul din rădăcina repo‑ului: [`../README.md`](../README.md).
