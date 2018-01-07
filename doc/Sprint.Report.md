# Sprint Report (1)

In diesem Sprint wurden bis zum [Meilenstein 4](https://sopra.informatik.uni-stuttgart.de/sopra-ws1617/sopra-team-8/milestones/1) erledigten User Stories abgearbeitet.

## Verbesserte Dokumente

[Produkt Backlog](ProduktBacklog.md)
[Entwurf](Entwurf.md)

## Geplante Features
> Die bis zum Meilenstein 4 geplanten Features

### Erledigte Features inkl. impl.Stories und Tasks
- Audio aufnehmen
	- Aufnahme starten
	- Aufnahme stoppen
- Audio speichern
	- Audiodatei persistent speichern
	- Audiodatei einer Person zuordnen
- Audio abspielen
	- Audiodatei auswählen
	- Audodatei wiedergeben
- Person erstellen
	- Nachname festlegen
	- Vorname festlegen
	- Firma festlegen
	- Geschlecht festlegen
	- Nationalität festlegen
	- Person speichern
- Person bearbeiten
	- Person auswählen
	- Personendaten bearbeiten
- Person suchen
	- Suchfeld
	- Suchergebnisse anzeigen
- Person löschen
	- Person aus Speicher entfernen
	- Metadaten löschen

### Nicht erledigte Features
>alle geplanten Features wurden implementiert


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
	- Personendaten ändern (Anrede, Vor-Nachname, Region, Firma)
	- Änderungen speichern
	- Person löschen
- Buttons des Mediaplayers
	- alle Buttons sind klickbar
	- wenn "Aufnehmen" geklickt, beendet der nächste Klick die Aufnahme
	- wenn "Aufnehmen" geklickt, werden die anderen Buttons ausgegraut
- Suche
	- Suchergebnisse anzeigen für keine Daten
	- Suchergebnisse anzeigen für einen gefundenen Datensatz

### Zugehörige Testprotokolle
- Klasse PersonTestCases:
>Running tests
$ adb shell am instrument -w -r   -e debug false -e class de.uni_stuttgart.sopra_ws1617_team8.tong.PersonTestCases de.uni_stuttgart.sopra_ws1617_team8.tong.test/android.support.test.runner.AndroidJUnitRunner
Client not ready yet..
Started running tests
Tests ran to completion.

- KLasse MainActivityTest:
>Running tests
$ adb shell am instrument -w -r   -e debug false -e class de.uni_stuttgart.sopra_ws1617_team8.tong.MainActivityTest de.uni_stuttgart.sopra_ws1617_team8.tong.test/android.support.test.runner.AndroidJUnitRunner
Client not ready yet..
Started running tests
Tests ran to completion.

- Klasse AudioTestCases:
>Running tests
$ adb shell am instrument -w -r   -e debug false -e class de.uni_stuttgart.sopra_ws1617_team8.tong.AudioTestCases de.uni_stuttgart.sopra_ws1617_team8.tong.test/android.support.test.runner.AndroidJUnitRunner
Client not ready yet..
Started running tests
Tests ran to completion.

- SearchTest
>Running tests
$ adb shell am instrument -w -r   -e debug false -e class de.uni_stuttgart.sopra_ws1617_team8.tong.SearchTest de.uni_stuttgart.sopra_ws1617_team8.tong.test/android.support.test.runner.AndroidJUnitRunner
Client not ready yet..
Started running tests
Tests ran to completion.


