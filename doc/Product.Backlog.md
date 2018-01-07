# Product Backlog

## Epic 1 *Audio*

> Als Nutzer möchte ich Namen in einer App, die auf meinem Android-Smartphone läuft, aufnehmen, speichern und abspielen können. Die App soll mir helfen Namen richtig auszusprechen.

### Feature 1 *Audio aufnehmen*

> Als Nutzer möchte ich ausgesprochene Namen in Form einer Audiodatei aufnehmen, um die korrekte Aussprache des Namens später anzuhören.

- Aufwandsschätzung: [M]
- Akzeptanztests: 
	- eine Audiodatei wurde temporär erstellt
	- die erstellte Audiodatei enthält Daten
	- jede Aufnahme aktiviert das Mikrofon
	- jede Aufnahme ist maximal 20 Sekunden lang


##### Task 1 *Aufnahme starten*
> Als Nutzer möchte ich, dass die Aufnahme startet und das Mikrofon aktiviert wird.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Aufnahme stoppen*
> Als Nutzer möchte ich, dass das Mikrofon deaktiviert ist, sobald ich die Aufnahme beende.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

### Feature 2 *Audio speichern*

> Als Nutzer möchte ich die gerade aufgenommenen Audiodateien, oder die bearbeitete Datei speichern können, um später darauf zugreifen zu können.

- Aufwandsschätzung: [M]
- Akzeptanztests: 
	- Die Audiodatei wurde unter dem korrekten Pfad abgespeichert
	- Beim Speichern wird die bestehende Datei überschrieben 
	- Die neue/geänderte Audiodatei kann abgespielt werden und enthält die Änderungen bzw. das neu aufgenommene
	- Wird die App beendet und wieder geöffnet, ist die Audiodatei immernoch verfügbar
	- Zu der gespeicherten Audiodatei wurde auch eine Person gespeichert

##### Task 1 *Audiodatei persistent speichern*
> Als Nutzer möchte ich die Audiodateien dauerhaft speichern, damit sie auch nachdem die App geschlossen wurde, noch vorhanden sind.

- Aufwandsschätzung: [3] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Audiodatei einer Person zuordnen*
> Als Nutzer möchte ich die Audiodateien einer Person zuordnen, so dass diese unter der Person zu finden ist.

- Aufwandsschätzung: [3] Stunden
- Tatsächliche Zeit: [0-72] Stunden

### Feature 3 *Audio abspielen*

> Als Nutzer möchte ich die gespeicherten Audiodateien auf meinem Androidgerät abspielen können, um mir die richtige Aussprache anzuhören.

- Aufwandsschätzung: [M]
- Akzeptanztests: 
	- Es kann eine Audiodatei zum Abspielen ausgewählt werden 
	- es gibt eine grafische Möglichkeit in der App, die ausgewählte Audiodatei abzuspielen
	- Die Audiodatei wird über die Lautsprecher wiedergegeben
	- Das Apspielen kann unterbrochen werden
	- Die Ausgabe auf den Lautsprechern endet, sobald die Audiodatei abgespielt wurde

##### Task 1 *Audiodatei auswählen*
> Als Nutzer möchte ich eine gespeicherte Audiodatei auswählen können, um sie abzuspielen.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Audiodatei wiedergeben*
> Als Nutzer möchte ich die ausgewählte Audiodatei abspielen können.

- Aufwandsschätzung: [3] Stunden
- Tatsächliche Zeit: [0-72] Stunden

### Feature 4 *Audio bearbeiten*

> Als Nutzer möchte ich die gesprochene und gespeicherte Auidodatei bearbeiten können, sofern sie eine Bearbeitung benötigt.

- Aufwandsschätzung: [M]
- Akzeptanztests: 
	- die zu ändernde Audiodatei kann ausgewählt werden
	- es gibt grafische Möglichkeiten die Lautstärke und die Länge zu ändern
	- die Audiodatei mit den Änderungen kann angehört werden, bevor sie endgültig übernommen werden
	- nach einer Sicherheitsabfrage können die Änderungen übernommen werden
	- die Änderungen sind übernommen und sind gespeichert

##### Task 1 *Lautstärke ändern*
> Als Nutzer möchte ich die Lautstärke der Audiodatei ändern können.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Länge schneiden*
> Als Nutzer möchte ich die Audiodatei den Anfang und das Ende festlegen können, um eventuell nicht benötigte Aufnahmesequenzen zu löschen.

- Aufwandsschätzung: [3] Stunden
- Tatsächliche Zeit: [0-72] Stunden

### Feature 5 *Audio löschen*

> Als Nutzer möchte ich die gesprochene und gespeicherte Audiodatei löschen können, sofern sie nicht mehr benötigt wird.

- Aufwandsschätzung: [S]
- Akzeptanztests: 
	- zu löschende Audiodatei kann ausgewählt werden
	- bevor endgültig gelöscht wird, kommt eine Sicherheitsabfrage
	- der Nutzer wird gefragt, ob er eine neue Audiodatei erstellen möchte
	- die ausgewählte Datei wird gelöscht und ist nichtmehr auf dem Speicher
	- der Datenbankeintrag mit Verweis auf die Datei wird gelöscht

##### Task 1 *Audiodatei komplett löschen*
> Als Nutzer möchte ich eine Audiodatei löschen können, um falsche Audiodateien aus dem Speicher entfernen zu können.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Audiodatei durch neue Aufnahme ersetzen*
> Als Nutzer möchte ich eine Audiodatei durch eine neue Aufnahme ersetzen können, um diese zu verbessern.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

### Feature 6 *Audio bewerten*

> Als Nutzer möchte ich eine Audioaufnahme bewerten können, um den Aufnahmen eine Qualität zuzuordnen.

- Aufwandsschätzung: [S]
- Akzeptanztests: 
	- Audiodatei kann ausgewählt werden
	- es gibt eine grafische Möglichkeit die Aufnahme und die Aussprache zu bewerten
	- die Bewertungen werden der Audiodatei in der Datenbank eindeutig zugeordnet
	- die Bewertungen werden gespeichert und sind beim erneutem Aufruf sichtbar


##### Task 1 *Aufnahmequalität angeben*
> Als Nutzer möchte ich die Aufnahme bewerten können, um sie später zum Beispiel zu wiederholen.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Aussprachequalität angeben*
> Als Nutzer möchte ich die Aussprache bewerten, um zu wissen, ob ich diese ohne Bedenken verwenden kann oder ob ich diese eventuell nochmals überprüfen muss.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

## Epic 2 *Personenverwaltung*

> Als Nutzer möchte ich die aufgenommenen Audiodaten verwalten können, sie gewissen Personen zuordnen können und sie in bestimmter Sortierung anzeigen lassen.
> Ebenso sollte es die Möglichkeit geben die Daten zu importieren und exportieren, sodass man als Nutzer die Möglichkeit eines Backups hat.


### Feature 1 *Daten exportieren*

> Als Nutzer möchte ich die Daten exportieren können. Dies soll vor allem zur Sicherung (Backup) der Daten dienen.

- Aufwandsschätzung: [M]
- Akzeptanztests: 
	- es gibt eine grafische Möglichkeit in der App, den Exportvorgang zu starten
	- die erstellte Datei muss alle notwendigen Daten beinhalten
	- ein direkter Import der BackupDatei darf keine Änderungen enthalten
	- die Datei muss unter dem festgelegten Pfad zu finden sein


##### Task 1 *Relevante Daten sammeln*
> Als Nutzer möchte ich, dass alle relevanten Daten in dem Backup gesichert werden.

- Aufwandsschätzung: [3] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Gesammelte Daten speichern*
> Als Nutzer möchte ich, dass alle relevanten Daten in dem Backup gespeichert werden.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 3 *Speicherort festlegen*
> Als Nutzer möchte ich, den Speicherort des Backups festlegen können.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden


### Feature 2 *Daten importieren*

> Als Nutzer möchte ich die exportierten Daten importieren können um so ein Backup einzuspielen. Auch soll mir als Nutzer die Möglichkeit gegeben werden die Daten auf einem externen Medium zu bearbeiten oder zu verwalten.

- Aufwandsschätzung: [M]
- Akzeptanztests: 
	- es gibt eine grafische Möglichkeit in der App den Importvorgang zu starten
	- den Pfad, in dem die Importdatei liegt kann ausgewählt werden
	- fehlen in der Importdatei notwendige Informationen wird dem Nutzer das mitgeteilt
	- nach erfolgreichem Import, stimmen die importierten Daten mit den Daten in der ImportDatei überein

##### Task 1 *Auswahl der Backupdatei*
> Als Nutzer möchte ich, falls es meherer Datenstände gibt, eine Backupdatei auswählen können.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Überprüfen der Datei*
> Als Nutzer möchte ich, dass die Backupdatei gelesen werden kann und keine Schäden an der App anrichtet.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 3 *Einlesen des Backups*
> Als Nutzer möchte ich, dass die in der Backupdatei gespeicherten Informationen eingespielt werden.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

### Feature 3 *Person erstellen*

> Als Nutzer möchte ich eine Person anlegen können, die mit bestimmten Attributen gespeichert wird. Der Person wird eine/mehere Audiodatei/en zugeordnet.

- Aufwandsschätzung: [M]
- Akzeptanztests: 
	- es gibt eine grafische Möglichkeit in der App, eine Person hinzuzufügen
	- in einer grafischen Oberfläche können Daten zu der Person angegeben werden
	- die angegebenen Daten werden gespeichert und sind bei erneutem Öffnen vorhanden
	- in der Datenbank wurde ein entsprechender eindeutiger Eintrag erstellt

##### Task 1 *Nachname festlegen*
> Als Nutzer möchte ich den Nachnamen der Person eingeben.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Vorname festlegen*
> Als Nutzer möchte ich den Vornamen der Person eingeben.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 3 *Firma festlegen*
> Als Nutzer möchte ich die Firma, bei der die Person arbeitet, eingeben.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 4 *Geschlecht festlegen*
> Als Nutzer möchte ich der Person ein Geschlecht zuordnen, um später zu wissen ob sie männlich oder weiblich ist.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 5 *Nationalität festlegen*
> Als Nutzer möchte ich der Person eine Nationalität zuordnen können.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 6 *Person speichern*
> Als Nutzer möchte ich, dass die Person mit den eingegebenen Attributen gespeichert werden kann.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

### Feature 4 *Person bearbeiten*

> Als Nutzer möchte ich eine Person und ihre zugehörigen Attribute bearbeiten können.

- Aufwandsschätzung: [M]
- Akzeptanztests: 
	- die zu bearbeitende Person kann ausgewählt werden
	- man kann in einen Bearbeitungsmodus wechseln. Erst in diesem können die Daten bearbeitet werden
	- bearbeitete Daten werden gespeichert und sind bei erneutem Aufrufen der Person noch vorhanden
	- die Einträge in der Datenbank werden entsprechend ebenfalls geändert

##### Task 1 *Person auswählen*
> Als Nutzer möchte ich eine Person auswählen können, um mir ihre Daten anzuschauen.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Personendaten bearbeiten*
> Als Nutzer möchte ich die Möglichkeit haben, in einen Bearbeitungsmodus wechseln zu können, um die Personendaten zu ändern.

- Aufwandsschätzung: [3] Stunden
- Tatsächliche Zeit: [0-72] Stunden

### Feature 5 *Person löschen*

> Als Nutzer möchte ich eine Person löschen können.

- Aufwandsschätzung: [S]
- Akzeptanztests: 
	- es gibt eine grafische Möglichkeit den Löschvorgang zu starten
	- Sicherheitsabfrage, ob wirklich gelöscht werden soll
	- Gelöschte Person und ihre Metadaten sind nichtmehr in der App zu finden
	- der Eintrag in der Datenbank und alle Verweise zu diesem, werden gelöscht

##### Task 1 *Person aus Speicher entfernen*
> Als Nutzer möchte ich, dass die Person, die ich lösche, aus dem Speicher entfernt wird.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Metadaten löschen*
> Als Nutzer möchte ich, dass auch alle Metadaten zu der Person gelöscht werden.

- Aufwandsschätzung: [1] Stunden
- Tatsächliche Zeit: [0-72] Stunden

### Feature 6 *Person suchen*

> Als Nutzer möchte ich eine Person nach ihren Attributen suchen können, um sie schneller finden zu können.

- Aufwandsschätzung: [L]
- Akzeptanztests: 
	- es gibt ein Suchfeld, in dem der Suchtext eingetragen werden kann
	- Liste wird bei Eingabe in das Suchfeld live mit Treffern aktualisiert
	- gibt es die Person in der App, so wird diese auch gefunden und angezeigt
	- gibt es eine Person nicht in der App, nach der gesucht wird, wird dies dem Nutzer angezeigt
	- Gesuchte Personen werden innerhalb fünf Sekunden gefunden und angezeigt

##### Task 1 *Suchalgorithmus*
> Als Nutzer möchte ich, dass meine Suche mindestens in fünf Sekunden ein Ergebnis liefert.

- Aufwandsschätzung: [3] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Suchkriterien festlegen*
> Als Nutzer möchte ich explizit nach Personen mit bestimmten Attributen suchen können.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 3 *Suchergebnisse anzeigen*
> Als Nutzer möchte ich, dass die Suchergebnisse in einer Liste angezeigt werden, um aus diesen auswählen zu können.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 4 *Suchfeld*
> Als Nutzer möchte ich die Möglichkeit haben, eine schnelle Suche durchzuführen, indem ich in einem Suchfeld etwas eintrage und mir live eine Liste mit Treffern angezeigt wird.

- Aufwandsschätzung: [4] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 5 *Letzte Suchen speichern*
> Als Nutzer möchte ich, dass meine letzten Suchen gespeichert werden, um diese nicht erneut eingeben zu müssen.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden


### Feature 7 *Personen sortieren*

> Als Nutzer möchte ich die Personen nach ihren Attributen sortieren und anzeigen lassen können um einen besseren Überblick zu bekommen.

- Aufwandsschätzung: [L]
- Akzeptanztests: 
	- es gibt eine grafische Oberfläche in dem die Sortierkriterien ausgewählt werden können
	- es können mehrere Sortierkriterien ausgewählt werden
	- Personenliste wird nach ausgewählten Sortierkriterien sortiert
    - alle Personen sind in der Liste noch vorhanden, nur die Reihenfolge passt sich an

##### Task 1 *Sortierkriterien festlegen*
> Als Nutzer möchte ich mir die Personenliste nach gewissen Kriterien sortieren lassen können.

- Aufwandsschätzung: [3] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Favoritensortierungen*
> Als Nutzer möchte ich Lieblingssortierungen erstellen und auswählen können,.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 3 *Speichern Sortiereinstellung*
> Als Nutzer möchte ich, dass meine letzte Sortiereinstellung gespeichert wird und beim nächsten Aufruf der App noch vorhanden ist.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden


## Epic 3 *Design und Settings*

> Als Nutzer erwarte ich ein ansprechendes Design, welches stimmig zu den Firmenfarben ist und in dem ich mich schnell zurecht finde. Ich möchte gewisse Einstellungen an der App vornehmen können, um diese zu personalisieren.

### Feature 1 *Design*

> Als Nutzer möchte ich ein stimmiges Design-Kozept haben, welches ein gewogenen Ausgleich zwischen dem Firmendesign und der Usability darstellt. Zusätzlich kann ich das Design manuell anpassen.

- Aufwandsschätzung: [XL]
- Akzeptanztests: 
	- Alle Texte sind gut lesbar
	- Alle Features können mit der Benutzeroberfläche benutzt werden
	- es gibt keine Sackgassen, d.h. es gibt immer einen Weg zurück bzw. weiter


##### Task 1 *Allgemeines Design erstellen*
> Als Nutzer möchte ich mich auf einer ansprechenden Nutzeroberfläche bewegen, die leicht zu verstehen ist, angenehme Farben benutzt und gut zu lesen ist.

- Aufwandsschätzung: [10] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Farbeinstellungen*
> Als Nutzer möchte ich die Intensität der Farben und den Kontrast anpassen können.

- Aufwandsschätzung: [3] Stunden
- Tatsächliche Zeit: [0-72] Stunden

### Feature 2 *Sprache*

> Als Nutzer möchte ich, dass die Sprache an meine allgemeine Handyeinstellung gekoppelt ist. Zusätzlich kann ich diese manuell verändern.

- Aufwandsschätzung: [M]
- Akzeptanztests: 
	- Bei Installation wird die App in der Systemsprache angezeigt
	- es gibt in den Einstellungen eine Möglichkeit die App-Sprache zu ändern
	- die Änderung wird für die ganze App übernommen
	- bei Auswahl einer neuen Sprache, werden alle sichtbaren Strings in dieser Sprache korrekt angezeigt
	- die Übersetzung der Strings ist korrekt

##### Task 1 *Systemsprache erkennen und übernehmen*
> Als Nutzer möchte ich, dass meine App in der Systemsprache installiert wird.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden

##### Task 2 *Sprache ändern*
> Als Nutzer möchte ich die Wahl haben meine App in Englisch anzeigen zu lassen.

- Aufwandsschätzung: [2] Stunden
- Tatsächliche Zeit: [0-72] Stunden
