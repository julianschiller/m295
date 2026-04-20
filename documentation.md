# Modul 295 - Dokumentation

**Autor:** Julian Schiller

**Datum:** 24.04.2026

## Inhaltsverzeichnis

1. [Libraries und Frameworks](#1-libraries-und-frameworks)
2. [API Analyse](#2-api-analyse)
3. [API Definition](#3-api-definition)
4. [REST API Dokumentation](#4-rest-api-dokumentation)
5. [Testfälle](#5-testfälle)
   - 5.1 [Adressen](#51-adressen)
   - 5.2 [Medien](#52-medien)
   - 5.3 [Kunden](#53-kunden)
   - 5.4 [Ausleihen](#54-ausleihen)

<div style="page-break-after: always;"></div>

## 1. Libraries und Frameworks

Library | Version | Zweck
--------|---------|------
Spring Boot| 4.0.5 | Application Framework
Spring Data JPA | - | DB-Zugriff
MySQL Connector | - | JDBC Driver
Lombok | - | Reduktion von Boilerplate
springdoc-openapi | 2.8.4 | API-Dokumentation via Swagger UI

Als IDE wurde VS Code verwendet und als Programmiersprache Java 21. Als Build Tool wurde Gradle 8.14.4 verwendet und Groovy als DSL.
Die History wurde mit Git gemacht, welches ebenfalls direkt mit einem [Github Repo](https://github.com/julianschiller/m295) verbunden wurde um einen zweiten Speicherort zu haben.

<div style="page-break-after: always;"></div>

## 2. API Analyse

Wer | Anforderung | HTTP-Methode
----|-------------|------------
Der Benutzer | kann alle Medienobjekte auflisten| GET
Der Benutzer | kann Medien nach Titel suchen | GET
Der Benutzer | kann ein Medienobjekt anhand der ID abrufen | GET
Der Benutzer | kann ein neues Medienobjekt erstellen | POST
Der Benutzer | kann ein bestehendes Medienobjekt aktualisieren | PATCH
Der Benutzer | kann ein Medienobjekt löschen | DELETE
Der Benutzer | kann alle Adressen auflisten | GET
Der Benutzer | kann Adressen nach Strasse und Hausnummer suchen| GET
Der Benutzer | kann Adressen nach Postleitzahl suchen | GET
Der Benutzer | kann eine neue Adresse erstellen | POST
Der Benutzer | kann eine Adresse löschen (nur wenn sie nicht referenziert wird) | DELETE
Der Benutzer | kann einen Kunden anhand der ID abrufen | GET
Der Benutzer | kann Kunden nach Namen suchen | GET
Der Benutzer | kann Kunden nach Adress-ID suchen | GET
Der Benutzer | kann einen neuen Kunden erstellen | POST
Der Benutzer | kann einen bestehenden Kunden aktualisieren | PATCH
Der Benutzer | kann einen Kunden löschen | DELETE
Der Benutzer | kann alle Ausleihen auflisten | GET
Der Benutzer | kann eine neue Ausleihe erstellen | POST
Der Benutzer | kann eine bestehende Ausleihe verlängern | PATCH
Der Benutzer | kann eine Ausleihe beenden (über mediaId) | DELETE

<div style="page-break-after: always;"></div>

## 3. API Definition

In diesem Abschnitt wird die gesamte API Definition aufgezeigt, es wird gezeigt welche URL's es gibt, der jeweilige Anwendungsfall und der Request Body.

### Medien
|URL|Methode|Anwendungsfall|Request Body|
|------|-------|---------|--------------|
/library/media|GET|Alle Medien abrufen|-
/library/media?title=|GET|Alle Medien mit bestimmtem Titel abrufen|-
/library/media/{id}|GET|Ein Medium mit bestimmter ID abrufen|-
/library/media|POST|Ein neues Medium erstellen|Das neue Medium als JSON
/library/media/{id}|PATCH|Ein bestehendes Medium bearbeiten|Das bearbeitete Medium als JSON
/library/media/{id}|DELETE|Ein Medium löschen|-

#### JSON-Struktur POST
```json
{
    "title":"",
    "author":"",
    "genre":"",
    "minage":12,
    "isbn":"",
    "locationcode":""
}
```
Titel und Autor müssen gesendet werden, alles andere ist optional.

#### JSON-Struktur PATCH
```json
{
    "genre":"",
    "minage":12,
    "isbn":"",
    "locationcode":""
}
```
Alle Attribute sind optional, aber nur diese dürfen bearbeitet werden.

<div style="page-break-after: always;"></div>

### Adresse
|URL|Methode|Anwendungsfall|Request Body|
|------|-------|---------|--------------|
/library/addresses|GET|Alle Adressen abrufen|-
/library/addresses?address=|GET|Alle Adressen mit bestimmter Strasse und Hausnummer abrufen|-
/library/addresses?zip=|GET|Alle Adressen mit bestimmter Postleitzahl abrufen|-
/library/addresses|POST|Eine neue Adresse erstellen|Die neue Adresse als JSON
/library/addresses/{id}|DELETE|Eine Adresse löschen (schlägt fehl, wenn sie noch von einem Kunden referenziert wird)|-

#### JSON-Struktur POST
```json
{
    "address":"",
    "city":"",
    "zip":""
}
```
Alle Attribute müssen gesendet werden.

### Kunde
|URL|Methode|Anwendungsfall|Request Body|
|------|-------|---------|--------------|
/library/customers/{id}|GET|Kunden mit bestimmter ID abrufen|-
/library/customers?name=|GET|Alle Kunden mit bestimmtem Namen abrufen|-
/library/customers?addressId=|GET|Alle Kunden mit bestimmter Adresse abrufen|-
/library/customers|POST|Einen neuen Kunden erstellen|Der neue Kunde als JSON
/library/customers/{id}|PATCH|Einen bestehenden Kunden bearbeiten|Der bearbeitete Kunde als JSON
/library/customers/{id}|DELETE|Einen Kunden löschen|-

#### JSON-Struktur POST
```json
{
    "firstname":"",
    "lastname":"",
    "birthdate":"YYYY-MM-DD",
    "email":"",
    "address":{
        "address":"",
        "city":"",
        "zip":""
    }
}
```
Alle Attribute müssen gesendet werden.

#### JSON-Struktur PATCH
```json
{
    "email":"",
    "address":{
        "address":"",
        "city":"",
        "zip":""
    }
}
```
Alle Attribute sind optional, aber nur diese dürfen bearbeitet werden.

<div style="page-break-after: always;"></div>

### Ausleihe
|URL|Methode|Anwendungsfall|Request Body|
|------|-------|---------|--------------|
/library/borrowings|GET|Alle Ausleihen abrufen|-
/library/borrowings|POST|Eine neue Ausleihe erstellen|Die neue Ausleihe als JSON
/library/borrowings/{id}|PATCH|Eine bestehende Ausleihe verlängern|Die bearbeitete Ausleihe als JSON
/library/borrowings?mediaId=|DELETE|Eine Ausleihe beenden|-

#### JSON-Struktur POST
```json
{
    "duration":14,
    "customer":{
        "id":3
    },
    "media":{
        "id":12
    }
}
```
Dauer ist optional (hat einen Standardwert), Kunde und Medium müssen gesendet werden.

#### JSON-Struktur PATCH
```json
{
    "duration":28
}
```
Dauer ist das einzige Attribut, das bearbeitet werden kann.

<div style="page-break-after: always;"></div>

## 4. REST API Dokumentation

Die API Dokumentation kann im zip File mit dem Namen [api-docs.json](./api-docs.json) gefunden werden.

<div style="page-break-after: always;"></div>

## 5. Testfälle

Die Tests wurden mit der VS Code REST Client Extension manuell ausgeführt. Jeder Test zeigt den gesendeten Request und die erhaltene Response.

### 5.1 Adressen

#### Alle Adressen abrufen
**Request:** `GET /library/addresses`
**Erwartet:** 200 OK, JSON Array mit allen Adressen
![Get all addresses](./screenshots/01-get-all-addresses.png)

#### Adressen nach Postleitzahl filtern
**Request:** `GET /library/addresses?zip=4090`
**Erwartet:** 200 OK, JSON Array mit Adressen mit PLZ 4090
![Get addresses by zip](./screenshots/02-get-address-with-specific-zip.png)

#### Adressen nach Strasse filtern
**Request:** `GET /library/addresses?address=Hochwalderstrasse 23`
**Erwartet:** 200 OK, JSON Array mit passenden Adressen
![Get addresses by address](./screenshots/03-get-all-addresses-with-specific-address.png)

#### Neue Adresse erstellen
**Request:** `POST /library/addresses`
**Erwartet:** 201 Created, Adresse wird in der Datenbank gespeichert
![Create new address](./screenshots/04-create-new-address.png)

#### Adresse löschen
**Request:** `DELETE /library/addresses/2`
**Erwartet:** 204 No Content, Adresse wird gelöscht
![Delete address](./screenshots/05-delete-address.png)

#### Verifikation: GET nach dem Löschen
![Get all after delete](./screenshots/05b-get-all-after-delete.png)

#### Referenzierte Adresse löschen
**Request:** `DELETE /library/addresses/4`
**Erwartet:** 400 Bad Request, Adresse wird noch von einem Kunden referenziert
![Delete referenced address](./screenshots/06-try-to-delete-referenced-address.png)

#### Nicht existierende Adresse löschen
**Request:** `DELETE /library/addresses/9999`
**Erwartet:** 404 Not Found
![Delete non-existing address](./screenshots/07-delete-non-existing-address.png)

<div style="page-break-after: always;"></div>

### 5.2 Medien

#### Alle Medien abrufen
**Request:** `GET /library/media`
**Erwartet:** 200 OK, JSON Array mit allen Medien
![Get all media](./screenshots/08-get-all-medias.png)

#### Medien nach Titel filtern
**Request:** `GET /library/media?title=Harry Potter`
**Erwartet:** 200 OK, JSON Array mit Medien mit Titel "Harry Potter"
![Get media by title](./screenshots/09-get-all-medias-with-specific-title.png)

#### Medium nach ID abrufen
**Request:** `GET /library/media/2`
**Erwartet:** 200 OK, JSON Objekt des Mediums
![Get media by id](./screenshots/10-get-media-by-id.png)

#### Nicht existierendes Medium abrufen
**Request:** `GET /library/media/9999`
**Erwartet:** 404 Not Found
![Get non-existing media](./screenshots/11-get-non-existing-media-by-id.png)

#### Neues Medium erstellen
**Request:** `POST /library/media`
**Erwartet:** 201 Created, JSON Objekt des erstellten Mediums
![Create new media](./screenshots/12-create-new-media.png)

#### Medium bearbeiten
**Request:** `PATCH /library/media/5`
**Erwartet:** 200 OK, Genre und Mindestalter werden aktualisiert
![Edit media](./screenshots/13-edit-media.png)

#### Nicht existierendes Medium bearbeiten
**Request:** `PATCH /library/media/9999`
**Erwartet:** 404 Not Found
![Update non-existing media](./screenshots/14-update-non-existing-media.png)

#### Medium löschen
**Request:** `DELETE /library/media/4`
**Erwartet:** 204 No Content, Medium wird gelöscht
![Delete media](./screenshots/15-deleted-media.png)

#### Verifikation: GET nach dem Löschen
![Get all after delete](./screenshots/15b-get-all-after-delete.png)

#### Ausgeliehenes Medium löschen
**Request:** `DELETE /library/media/2`
**Erwartet:** 409 Conflict, Medium ist noch ausgeliehen
![Delete borrowed media](./screenshots/16-try-to-delete-borrowed-media.png)

#### Nicht existierendes Medium löschen
**Request:** `DELETE /library/media/9999`
**Erwartet:** 404 Not Found
![Delete non-existing media](./screenshots/17-delete-media-with-non-existing-id.png)

<div style="page-break-after: always;"></div>

### 5.3 Kunden

#### Kunde nach ID abrufen
**Request:** `GET /library/customers/1`
**Erwartet:** 200 OK, JSON Objekt des Kunden
![Get customer by id](./screenshots/18-get-customer-with-specific-id.png)

#### Nicht existierenden Kunden abrufen
**Request:** `GET /library/customers/9999`
**Erwartet:** 404 Not Found
![Get non-existing customer](./screenshots/19-get-customer-with-non-existing-id.png)

#### Kunden nach Nachname filtern
**Request:** `GET /library/customers?name=Test`
**Erwartet:** 200 OK, JSON Array mit passenden Kunden
![Get customers by name](./screenshots/20-get-customers-by-lastname.png)

#### Kunden nach Adress-ID filtern
**Request:** `GET /library/customers?addressId=3`
**Erwartet:** 200 OK, JSON Array mit Kunden an dieser Adresse
![Get customers by address](./screenshots/21-get-customers-by-address-id.png)

#### Neuen Kunden mit neuer Adresse erstellen
**Request:** `POST /library/customers`
**Erwartet:** 201 Created, Kunde und Adresse werden erstellt
![Create customer with new address](./screenshots/22-create-new-customer-with-new-address.png)

#### Neuen Kunden mit bestehender Adresse erstellen
**Request:** `POST /library/customers`
**Erwartet:** 201 Created, Kunde wird mit bestehender Adresse verknüpft
![Create customer with existing address](./screenshots/23-create-new-customer-with-existing-address.png)

#### Kunden mit nicht existierender Adresse erstellen
**Request:** `POST /library/customers`
**Erwartet:** 404 Not Found, Adresse existiert nicht
![Create customer with non-existing address](./screenshots/24-create-new-customer-with-non-existing-address.png)

#### Kunden ohne Adresse erstellen
**Request:** `POST /library/customers`
**Erwartet:** 400 Bad Request, Adresse ist erforderlich
![Create customer without address](./screenshots/25-create-new-customer-with-no-address.png)

#### Kunden bearbeiten
**Request:** `PATCH /library/customers/1`
**Erwartet:** 200 OK, E-Mail wird aktualisiert
![Edit customer](./screenshots/26-edit-customer.png)

#### Nicht existierenden Kunden bearbeiten
**Request:** `PATCH /library/customers/9999`
**Erwartet:** 404 Not Found
![Edit non-existing customer](./screenshots/27-edit-non-existing-customer.png)

#### Kunden löschen
**Request:** `DELETE /library/customers/1`
**Erwartet:** 204 No Content, Kunde wird gelöscht
![Delete customer](./screenshots/28-delete-customer.png)

#### Verifikation: GET nach dem Löschen
![Get customer after delete](./screenshots/28b-get-customer-after-delete.png)

#### Kunden mit offener Ausleihe löschen
**Request:** `DELETE /library/customers/3`
**Erwartet:** 409 Conflict, Kunde hat noch offene Ausleihen
![Delete customer with open borrowing](./screenshots/29-try-to-delete-customer-with-open-borrowing.png)

#### Nicht existierenden Kunden löschen
**Request:** `DELETE /library/customers/9999`
**Erwartet:** 404 Not Found
![Delete non-existing customer](./screenshots/30-try-to-delete-non-existing-customer.png)

<div style="page-break-after: always;"></div>

### 5.4 Ausleihen

#### Alle Ausleihen abrufen
**Request:** `GET /library/borrowings`
**Erwartet:** 200 OK, JSON Array mit allen aktiven Ausleihen
![Get all borrowings](./screenshots/31-get-all-borrowings.png)

#### Neue Ausleihe erstellen
**Request:** `POST /library/borrowings`
**Erwartet:** 201 Created, Ausleihe wird mit heutigem Datum erstellt
![Create borrowing](./screenshots/32-create-new-borrowing.png)

#### Ausleihe mit nicht existierendem Kunden erstellen
**Request:** `POST /library/borrowings`
**Erwartet:** 404 Not Found, Kunde existiert nicht
![Create borrowing with non-existing customer](./screenshots/33-create-new-borrowing-with-non-existing-customer.png)

#### Ausleihe mit nicht existierendem Medium erstellen
**Request:** `POST /library/borrowings`
**Erwartet:** 404 Not Found, Medium existiert nicht
![Create borrowing with non-existing media](./screenshots/34-create-new-borrowing-with-non-existing-media.png)

#### Ausleihe mit bereits ausgeliehenem Medium erstellen
**Request:** `POST /library/borrowings`
**Erwartet:** 409 Conflict, Medium ist bereits ausgeliehen
![Create borrowing with already borrowed media](./screenshots/35-create-borrowing-with-already-borrowed-media.png)

#### Ausleihe verlängern
**Request:** `PATCH /library/borrowings/6`
**Erwartet:** 200 OK, Dauer wird auf 28 Tage aktualisiert
![Extend borrowing](./screenshots/36-edit-borrowing.png)

#### Nicht existierende Ausleihe verlängern
**Request:** `PATCH /library/borrowings/9999`
**Erwartet:** 404 Not Found
![Extend non-existing borrowing](./screenshots/37-edit-non-existing-borrowing.png)

#### Ausleihe beenden
**Request:** `DELETE /library/borrowings?mediaId=5`
**Erwartet:** 204 No Content, Ausleihe wird gelöscht
![End borrowing](./screenshots/38-delete-borrowing.png)

#### Verifikation: GET nach dem Beenden
![Get all after delete](./screenshots/38b-get-all-after-delete.png)

#### Ausleihe mit nicht existierender Media-ID beenden
**Request:** `DELETE /library/borrowings?mediaId=9999`
**Erwartet:** 404 Not Found
![End non-existing borrowing](./screenshots/39-delete-non-existing-borrowing.png)
