# 🎵 Sistema di Gestione Artisti e Canzoni (API + Database)

## 📖 Descrizione del Progetto

Questo progetto implementa un **sistema di gestione musicale** che permette di esplorare un catalogo di **artisti e canzoni**, recuperati da un’**API esterna**, e di gestire una **collezione personale** salvata in un **database locale**.

L’applicazione funziona tramite **menu testuale in console**, offrendo funzionalità di consultazione, aggiunta, modifica e rimozione di artisti.

Il sistema comunica con un servizio RESTful (API SparkJava) in esecuzione su `http://localhost:4567/api`.

---

## 🧩 Struttura del Progetto

Il progetto è organizzato in quattro componenti principali:

### 1. `Main`
Gestisce l’interfaccia utente da console e offre un menu interattivo con le seguenti opzioni:

- Esplorare il catalogo completo di canzoni disponibili
- Cercare una canzone per ID
- Consultare l’elenco degli artisti
- Visualizzare i dettagli di un artista e salvarlo nel database locale
- Aggiungere un nuovo artista
- Modificare le informazioni di un artista esistente
- Eliminare un artista dal sistema
- Consultare la propria collezione personale salvata in locale

---

### 2. `ApiClient`
Classe dedicata alla comunicazione con l’API esterna.  
Utilizza `HttpClient` di Java per inviare richieste HTTP ai vari endpoint e gestisce le operazioni seguenti:

- Ottenere tutti gli artisti o le canzoni
- Cercare artista o canzone per ID
- Creare, aggiornare o eliminare un artista
- Ottenere le canzoni associate a un artista

📡 **Base URL:** `http://localhost:4567/api`

---

### 3. `Database`
Classe che gestisce la connessione al database locale, implementando il **pattern Singleton** per garantire una sola connessione attiva.

Utilizzato per:
- Salvare artisti nella collezione personale
- Recuperare la lista di artisti salvati
- Eliminare o modificare artisti nella collezione

---

### 4. `Entità`
Classi che rappresentano le entità principali del dominio:

#### `Artista`
- `id`
- `nome`
- `paese`
- `genere`
- `List<Canzone> canzoni`

#### `Canzone`
- `id`
- `titolo`
- `durata` (in secondi)
- `annoPubblicazione`
- `Artista artista`

#ChiVuoleEssereFucilato

---
### `Proposta Didattica`
Controllerei l'esercizio svolto di `Zanfardini Liam`, in quanto è appassionato di musica
