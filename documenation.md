# Modul 295 - Dokumenation

**Autor:** Julian Schiller

**Datum:** 24.04.2026

## Inhaltsverzeichnis

## 1. Libraries und Frameworks
Library | Version | Zweck
--------|---------|------
Spring Boot| 4.0.5 | Application Framework
Spring Data JPA | - | DB-Zugriff
MySQL Connector | - | JDBC Driver
Lombok | - | Reduktion von Boilerplate
springdoc-openapi | 2.8.4 | API-Dokumentation via Swagger UI

Als IDE wurde VS Code verwendet und als Programmiersprache Java 21. Als Build Tool wurde Gradle 8.14.4 verwendet und Groovy als DSL
Die History wurde mit Git gemacht, welches ebenfalls direkt mit einem [Github Repo](https://github.com/julianschiller/m295) verbunden wurde um einen zweiten Speicherort zu haben

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

## 3. API Definition

## Medien
|URL|Methode|Anwendungsfall|Request Body|
|------|-------|---------|--------------|
/library/media|GET|Alle Medien abrufen|-
/library/media?title=|GET|Alle Medien mit bestimmtem Titel abrufen|-
/library/media/{id}|GET|Ein Medium mit bestimmter ID abrufen|-
/library/media|POST|Ein neues Medium erstellen|Das neue Medium als JSON
/library/media/{id}|PATCH|Ein bestehendes Medium bearbeiten|Das bearbeitete Medium als JSON
/library/media/{id}|DELETE|Ein Medium löschen|-

### JSON-Struktur
#### POST
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
Titel und Autor müssen gesendet werden, alles andere ist optional

#### PATCH
```json
{
    "genre":"",
    "minage":12,
    "isbn":"",
    "locationcode":""
}
```
Alle Attribute sind optional, aber nur diese dürfen bearbeitet werden

## Adresse
|URL|Methode|Anwendungsfall|Request Body|
|------|-------|---------|--------------|
/library/addresses|GET|Alle Adressen abrufen|-
/library/addresses?address=|GET|Alle Adressen mit bestimmter Straße und Hausnummer abrufen|-
/library/addresses?zip=|GET|Alle Adressen mit bestimmter Postleitzahl abrufen|-
/library/addresses|POST|Eine neue Adresse erstellen|Die neue Adresse als JSON
/library/addresses/{id}|DELETE|Eine Adresse löschen (schlägt fehl, wenn sie noch von einem Kunden referenziert wird)|-

### JSON-Struktur
#### POST
```json
{
    "address":"",
    "city":"",
    "zip":""
}
```
Alle Attribute müssen gesendet werden

## Kunde
|URL|Methode|Anwendungsfall|Request Body|
|------|-------|---------|--------------|
/library/customers/{id}|GET|Kunden mit bestimmter ID abrufen|-
/library/customers?name=|GET|Alle Kunden mit bestimmtem Namen abrufen|-
/library/customers?addressId=|GET|Alle Kunden mit bestimmter Adresse abrufen|-
/library/customers|POST|Einen neuen Kunden erstellen|Der neue Kunde als JSON
/library/customers/{id}|PATCH|Einen bestehenden Kunden bearbeiten|Der bearbeitete Kunde als JSON
/library/customers/{id}|DELETE|Einen Kunden löschen|-

### JSON-Struktur
#### POST
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
Alle Attribute müssen gesendet werden

#### PATCH
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
Alle Attribute sind optional, aber nur diese dürfen bearbeitet werden

## Ausleihe
|URL|Methode|Anwendungsfall|Request Body|
|------|-------|---------|--------------|
/library/borrowings|GET|Alle Ausleihen abrufen|-
/library/borrowings|POST|Eine neue Ausleihe erstellen|Die neue Ausleihe als JSON
/library/borrowings/{id}|PATCH|Eine bestehende Ausleihe verlängern|Die bearbeitete Ausleihe als JSON
/library/borrowings?mediaId=|DELETE|Eine Ausleihe beenden|-

### JSON-Struktur
#### POST
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
Dauer ist optional (hat einen Standardwert), Kunde und Medium müssen gesendet werden

#### PATCH
```json
{
    "duration":28
}
```
Dauer ist das einzige Attribut, das bearbeitet werden kann


## 4. REST API Dokumenation

Die API Dokumentation kann im zip File mit dem Namen [api-docs.json](./api-docs.json) gefunden werden

## 5. Testfälle

Die Tests sind alle im tests.http File zu finden, sie können daraus manuell in ein HTTP Request Tool wie YARC kopiert werden um die Fälle zu testen. Das erwartete Szenario steht jeweils im Kommentar über dem Request.