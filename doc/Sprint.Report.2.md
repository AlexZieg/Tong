# Sprint Report (2)

In diesem Sprint wurden bis zum [Meilenstein 5](https://sopra.informatik.uni-stuttgart.de/sopra-ws1617/sopra-team-8/milestones/2) erledigten User Stories abgearbeitet.

## Verbesserte Dokumente


[Entwurf](Entwurf.md)
[Begründung](Begruendung.md)

## Geplante Features
> Die bis zum Meilenstein 5 geplanten Features

### Erledigte Features inkl. impl.Stories und Tasks
- Audio löschen
	- Audiodatei komplett löschen
- Audio bewerten
	- Aufnahmequalität angeben
- Vorgeschlagene Aussprache abspielen
- Daten exportieren
	- Relevanten Daten sammeln
	- Gesammelte Daten speichern
- Person suchen
	- Suchkriterien festlegen
- Sprache
	- Systemsprache erkennen und übernehmen
	- Sprache ändern


### Nicht erledigte Features
- Daten importieren
	- Auswahl der Backupdatei
	- Überpüfen der Datei
	- Einlesen des Backups
- Personen sortieren
	- Sortierkriterien gestlegen
	- Favoritensortierung
	- Speichern sortiereinstellungen
- Design
	- Allgemeines Design erstellen
	- Farbeinstellungen

## Tests
### Getestete Funktionen
- Aufnahme erstellen
- Aufnahme abspielen
- Aufnahme speichern (zu Person zuordnen)
	- Personendaten eingeben
	- Person erstellen
- Überprüfen ob Person in Liste angezeigt wird
- Person auswählen
	- Personendaten anzeigen
	- Die Aufnahme zur Person abspielen
	- Die Aufnahme zur Person pausieren
	- Die Aufnahme zur Person stoppen
	- Eine Vorsgeschlagene Aussprache abspielen
	- Personendaten ändern (Anrede, Vor-Nachname, Region, Firma)
	- Änderungen speichern
	- Person löschen
- Buttons des Mediaplayers
	- alle Buttons sind klickbar
	- wenn "Aufnehmen" geklickt, beendet der nächste Klick die Aufnahme
	- wenn "Aufnehmen" geklickt, werden die anderen Buttons ausgegraut
	- wenn "Reset" geklickt, wird die Aufnahme resettet
	- wenn "Speichern" geklickt, wird die Aufnahme gespeichert	
- Suche
	- Suchergebnisse anzeigen für keine Daten
	- Suchergebnisse anzeigen für einen gefundenen Datensatz
	- Suchergebnisse in einer bestimmten Kategorie angeben

### Zugehörige Testprotokolle
- Klasse CreateAndDeletePersonTest:

	[Protokoll](app/build/reports/androidTests/connected/de.uni_stuttgart.sopra_ws1617_team8.tong.CreateAndDeletePersonTest.html)

- KLasse EditPersonTest:

	[Protokoll](app/build/reports/androidTests/connected/de.uni_stuttgart.sopra_ws1617_team8.tong.EditPersonTest.html)

- Klasse ButtonTest:

	[Protokoll](app/build/reports/androidTests/connected/de.uni_stuttgart.sopra_ws1617_team8.tong.ButtonTest.html)

- Klasse SearchTest:

	[Protokoll](app/build/reports/androidTests/connected/de.uni_stuttgart.sopra_ws1617_team8.tong.SearchTest.html)


### Testabdeckung

[Abdeckung](app/build/reports/coverage/debug/index.html)