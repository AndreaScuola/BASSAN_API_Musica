# API Documentazione – MANIGLIO_sparkAPI_spotify

Questa API espone risorse per gestire Artisti e Canzoni di una piattaforma musicale tipo Spotify.

- Runtime: Java 21, SparkJava, JSON via Gson
- Formato: application/json per richieste e risposte
- Base URL: http://localhost:4567/api

Nota: gli anni di pubblicazione sono numeri interi (es. 1975, 2023).



## Riepilogo Endpoints

| Metodo | Endpoint                | Descrizione                   | Dettagli                                                        |
|--------|-------------------------|-------------------------------|-----------------------------------------------------------------|
| GET    | `/health`               | Stato di salute API           | [Health check](#health-check)                                   |
| GET    | `/artisti`              | Lista tutti gli artisti       | [Elenco artisti](#elenco-artisti)                               |
| GET    | `/artisti/{id}`         | Dettaglio singolo artista     | [Dettaglio artista](#dettaglio-artista)                         |
| GET    | `/artisti/{id}/canzoni` | Canzoni di un artista         | [Dettaglio artista con canzoni](#dettaglio-artista-con-canzoni) |
| POST   | `/artisti`              | Crea nuovo artista            | [Creazione artista](#creazione-artista)                         |
| PUT    | `/artisti/{id}`         | Aggiorna artista esistente    | [Aggiornamento artista](#aggiornamento-artista)                 |
| DELETE | `/artisti/{id}`         | Elimina artista               | [Eliminazione artista](#eliminazione-artista)                   |
| GET    | `/canzoni`              | Lista tutte le canzoni        | [Elenco canzoni](#elenco-canzoni)                               |
| GET    | `/canzoni/{id}`         | Dettaglio singola canzone     | [Dettaglio canzone](#dettaglio-canzone)                         |

## Modelli dati (JSON)

- Artista

```json
{
  "id": 1,
  "nome": "Queen",
  "paese": "UK",
  "genere": "Rock",
  "canzoni": [
    {
      "id": 1,
      "titolo": "Bohemian Rhapsody",
      "durata": 354,
      "annoPubblicazione": 1975
    },
    {
      "id": 2,
      "titolo": "We Will Rock You",
      "durata": 122,
      "annoPubblicazione": 1977
    },
    {
      "id": 3,
      "titolo": "Another One Bites the Dust",
      "durata": 216,
      "annoPubblicazione": 1980
    }
  ]
}
```

- Nuovo/Aggiorna Artista (body richiesta)

```json
{
  "nome": "Nome Artista",
  "paese": "Italia",
  "genere": "Pop"
}
```

- Canzone

```json
{
  "id": 10,
  "titolo": "Bohemian Rhapsody",
  "durata": 354,
  "annoPubblicazione": 1975,
  "artista": {
    "id": 1,
    "nome": "Queen",
    "paese": "UK",
    "genere": "Rock"
  }
}
```

- Errore

```json
{
  "error": "Messaggio descrittivo dell'errore"
}
  ```

## Convenzioni di risposta

- 200 OK: richiesta andata a buon fine (GET/PUT/DELETE)
- 201 Created: risorsa creata (POST); l'oggetto creato viene restituito nel body
- 400 Bad Request: input non valido (es. id non numerico, JSON malformato, campi obbligatori mancanti)
- 404 Not Found: risorsa non trovata
- 409 Conflict: vincoli violati o stato non coerente (se applicabile)
- 500 Internal Server Error: errore imprevisto

Tutte le risposte sono JSON e impostano Content-Type: application/json.

---

## Endpoints: Artisti

### Health check

- Metodo e Path: GET /health
- Risposta 200 (stato operativo):

```json
{
  "status": "ok",
  "timestamp": "2025-11-02T10:30:00"
}
  ```

- Nessun parametro o body richiesto
- Usato per verificare che l'API sia attiva e risponda correttamente

### Elenco artisti

- Metodo e Path: GET /artisti
- Query opzionali: nessuna specifica
- Risposta 200 (array di Artisti con le loro canzoni):

```json
[
  {
    "id": 1,
    "nome": "Queen",
    "paese": "UK",
    "genere": "Rock",
    "canzoni": [
      {
        "id": 1,
        "titolo": "Bohemian Rhapsody",
        "durata": 354,
        "annoPubblicazione": 1975
      },
      {
        "id": 2,
        "titolo": "We Will Rock You",
        "durata": 122,
        "annoPubblicazione": 1977
      }
    ]
  },
  {
    "id": 2,
    "nome": "Michael Jackson",
    "paese": "USA",
    "genere": "Pop",
    "canzoni": [
      {
        "id": 4,
        "titolo": "Billie Jean",
        "durata": 294,
        "annoPubblicazione": 1983
      },
      {
        "id": 5,
        "titolo": "Thriller",
        "durata": 357,
        "annoPubblicazione": 1982
      }
    ]
  }
]
```

### Dettaglio artista

- Metodo e Path: GET /artisti/{id}
- Parametri: id (numero intero)
- Risposta 200 (Artista con canzoni):

```json
{
  "id": 1,
  "nome": "Queen",
  "paese": "UK",
  "genere": "Rock",
  "canzoni": [
    {
      "id": 1,
      "titolo": "Bohemian Rhapsody",
      "durata": 354,
      "annoPubblicazione": 1975
    },
    {
      "id": 2,
      "titolo": "We Will Rock You",
      "durata": 122,
      "annoPubblicazione": 1977
    },
    {
      "id": 3,
      "titolo": "Another One Bites the Dust",
      "durata": 216,
      "annoPubblicazione": 1980
    }
  ]
}
```

- 404 se non esiste

### Dettaglio artista con canzoni

- Metodo e Path: GET /artisti/{id}/canzoni
- Parametri: id (numero intero)
- Risposta 200 (array di Canzoni con l'artista annidato):

```json
[
  {
    "id": 1,
    "titolo": "Bohemian Rhapsody",
    "durata": 354,
    "annoPubblicazione": 1975,
    "artista": {
      "id": 1,
      "nome": "Queen",
      "paese": "UK",
      "genere": "Rock"
    }
  },
  {
    "id": 2,
    "titolo": "We Will Rock You",
    "durata": 122,
    "annoPubblicazione": 1977,
    "artista": {
      "id": 1,
      "nome": "Queen",
      "paese": "UK",
      "genere": "Rock"
    }
  },
  {
    "id": 3,
    "titolo": "Another One Bites the Dust",
    "durata": 216,
    "annoPubblicazione": 1980,
    "artista": {
      "id": 1,
      "nome": "Queen",
      "paese": "UK",
      "genere": "Rock"
    }
  }
]
```

- 404 se l'artista non esiste

### Creazione artista

- Metodo e Path: POST /artisti
- Body JSON **(obbligatorio)**:
```json
{
  "nome": "Nome Artista",
  "paese": "Italia",
  "genere": "Pop"
}
```

- Risposta 201 (Artista creato con id):
```json
{
  "id": 123,
  "nome": "Nome Artista",
  "paese": "Italia",
  "genere": "Pop"
}
```

- 400 se campi mancanti o non validi

### Aggiornamento artista

- Metodo e Path: PUT /artisti/{id}
- Parametri: id (numero intero)
- Body JSON (come creazione):
```json
{
  "nome": "Nuovo Nome",
  "paese": "Francia",
  "genere": "Jazz"
}
```
- Risposta 200 (Artista aggiornato):
```json
{
  "id": 123,
  "nome": "Nuovo Nome",
  "paese": "Francia",
  "genere": "Jazz"
}
```

- 400 input non valido, 404 se non esiste

### Eliminazione artista

- Metodo e Path: DELETE /artisti/{id}
- Parametri: id (numero intero)
- Risposta 204 senza body
- 404 se non esiste

Nota: le canzoni dell'artista vengono eliminate automaticamente in cascata.

---

## Endpoints: Canzoni

### Elenco canzoni

- Metodo e Path: GET /canzoni
- Risposta 200 (array di Canzoni con artista annidato):
```json
[
  {
    "id": 10,
    "titolo": "Bohemian Rhapsody",
    "durata": 354,
    "annoPubblicazione": 1975,
    "artista": {
      "id": 1,
      "nome": "Queen",
      "paese": "UK",
      "genere": "Rock"
    }
  },
  {
    "id": 11,
    "titolo": "Billie Jean",
    "durata": 294,
    "annoPubblicazione": 1983,
    "artista": {
      "id": 2,
      "nome": "Michael Jackson",
      "paese": "USA",
      "genere": "Pop"
    }
  }
]
```

### Dettaglio canzone

- Metodo e Path: GET /canzoni/{id}
- Parametri: id (numero intero)
- Risposta 200 (Canzone con artista annidato):
```json 
{
  "id": 10,
  "titolo": "Bohemian Rhapsody",
  "durata": 354,
  "annoPubblicazione": 1975,
  "artista": {
    "id": 1,
    "nome": "Queen",
    "paese": "UK",
    "genere": "Rock"
  }
}
  ```
- 404 se non esiste

---

## Note aggiuntive

- Intestazioni suggerite per le richieste con body:
  - Content-Type: application/json
  - Accept: application/json
- I campi numerici devono essere interi positivi; i testi non vuoti.
- La durata è espressa in secondi.
- L'anno di pubblicazione deve essere un numero intero valido.
- In caso di eliminazione di un artista, le relative canzoni vengono rimosse automaticamente grazie al vincolo CASCADE.
- Il database contiene artisti precaricati come Queen, Michael Jackson, Madonna, The Beatles e Bob Marley con le loro canzoni più famose.

---

## Esempi di utilizzo

### Ottenere tutti gli artisti
```bash
curl -X GET http://localhost:4567/api/artisti
```

### Ottenere un artista specifico
```bash
curl -X GET http://localhost:4567/api/artisti/1
```

### Creare un nuovo artista
```bash
curl -X POST http://localhost:4567/api/artisti \
  -H "Content-Type: application/json" \
  -d '{"nome": "Lucio Battisti", "paese": "Italia", "genere": "Pop"}'
```

### Ottenere tutte le canzoni
```bash
curl -X GET http://localhost:4567/api/canzoni
```

### Ottenere le canzoni di un artista specifico
```bash
curl -X GET http://localhost:4567/api/artisti/1/canzoni
```
```
