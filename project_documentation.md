# Project Dokumentation
## Crypto Game:

Wir haben ein kleines spiel erstellt, indem man sich von vier Coins eine aussuchen kann und in diese investieren.
Man kann also jede Runde eine Menge Geld wählen, die man gerne investieren möchte
und dann wird per zufall ein Event ausgelöst welches den Kurs entweder steigen oder fallen lässt.
Es gibt immer einen sogenannten EventActor also zum Beispiel Elon Musk, der dann ein Event ausführt.
Er tweeted vielleicht über eine der Coins und dann kann das einen entweder positiven oder negativen effekt haben.
Der Effekt ist in eine vorbestimmten range also gibt es Events, die sowohl negativ als auch positiv ausfallen können,
wenn man nur genug glück hat. 
**Das Ziel ist es dann möglichst viel Geld in kurzer Zeit zu machen,
wie es halt so üblich ist in einem kapitalistischen System.**


## Klassen Diagramm:

![Altes Klassen Diagramm!](./old_klassen_diagramm.png "Altes Klassen Diagramm")

Der Aufbau sieht zwar auf den ersten Blick relativ kompliziert aus aber das ist er eigentlich nicht.
Wir haben die CryptoCurrency also die Währung mit dem Namen dem Kurs und der anzahl die man besitzt.
Und dan drei Funktionen, zwei für das kaufen und verkaufen und eine um den Kurs zu ändern.
Im UserInterface werden die grössen des GUI's angegeben und die menge an Geld die der Spieler hat.
Das EventBrain ist verantwortlich für die auslösung eines neuen events.
Der Static Loader is sozusagen die Main Klasse welche statisch alle Daten die wir das Spiel brauchen.
Also die Währung die genauen Events usw. sind dort definiert.
Der EventActor ist dann die Person/Organisation welche "verantwortlich" ist für das Event.
Das EventInterface dient als eine Art Klasse, die wir in den anderen Klassen implementieren,
um die TriggerEvent funktion mehrmals brauchen zu können.
Im Event ist dann die genau Nachricht definiert und der Einfluss auf den Coin.
Und die EventCouple Klass als Letztes verbindet das Event mit der bestimmten Coin.

![Neues Klassen Diagramm!](./new_klassen_diagramm.png "Neues Klassen Diagramm")


## OO-Konzepte

Wir haben in diesem Projekt uns einmal quer durch alle möglichen OO Konzepte programmiert. Einige davon führen wir hier kurz auf.

### Encapsluation

Da wir mit der Libary Lombok gearbeitet haben, sieht encapsluation bei uns etwas anders aus. Ein gutes Beispiel ist aber die Klasse <code> CryptoCurrency </code>. Anstatt mit <code>@Data</code> für alle Attribute Getters und Setters zu generieren haben wir mit <code>@Getter</code> nur Getter definiert und die Currencies so vor äusseren einfüssen geschützt.

### Delegation

Delegation ist in diesem Projekt sehr stark verbreitet. Im <code>EventBrain</code> kann ein Event getriggert werden welches dann über <code>EventActor</code> (und allenfalls noch über <code>EventCouple</code>) an das <code>Event</code> weitergegeben wird. Die Generierete Message macht dann diesen Weg zurück.
Hier der Code dazu

    //EventBrain
    public void triggerEvent() {
        int currencyIndex = randomInt(0, currencies.size()-1);
        CryptoCurrency cryptoCurrency =  currencies.get(currencyIndex);
        int eventActorsIndex = randomInt(0, eventActors.size()-1);
        EventActor event = eventActors.get(eventActorsIndex);
        latestMessage = event.triggerEvent(cryptoCurrency); // <------- delegation here 
    }

    //EventActor
    public String triggerEvent(CryptoCurrency cryptoCurrency) {
        int index = randomInt(0, events.size()-1);
        EventInterface event = events.get(index);
        String message = event.triggerEvent(cryptoCurrency);  // <------- delegation here 
        return name + " " + message;
    }

    //EventCouple
        public String triggerEvent(CryptoCurrency cryptoCurrency) {
        Event event;
        //some code..
        return event.triggerEvent(cryptoCurrency);
    }

    //Event
    public String triggerEvent(CryptoCurrency cryptoCurrency){
        //some code
        return message + " " + cryptoCurrency.getName() + ". " + sign + df.format(currencyInfluence) + "%";
    }

### static

Static haben wir verwendet, um all unsere Events und Coins zu definieren. Den ganzen entsprechenden Code befindet sich in der Klasse <code>StaticLoader</code>.

## Selbstreflexion

Wir haben mit einem Kurzen Brainstorming begonnen und einige Ideen gesammelt.
Entschieden haben wir uns dann für das Crypto Game, weil es spassig klang es umzusetzen.
Da wir nicht das erste Mal zusammen an einem Projekt arbeiteten,
lief unsere Absprache sehr gut.
Wir sind dann ungefähr dem Plan gefolgt der im Projektarbeit.pdf definiert ist.
Auch wenn wir am Anfang nur sporadisch vorankamen vor allem mit den Diagrammen und Dokumentation,
welche uns beiden nicht sehr gefallen.
Schlussendlich kamen wir dann aber sehr gut voran, auch wenn uns das Kreieren des GUI's ein paar Probleme bereitet hat.
Das ganze ging dan aber wesentlich besser als wir die Funktion unserer IDE gefunden haben,
welche uns eine visuelle oberfläche gibt, um das Interface zu erstellen.
Dann ging es eigentlich nur noch darum einige Bugs in unserem Programm zu fixen und den letzten feinschliff zu machen.
Wir sind beide zufrieden mit dem Ergebnisse unserer Arbeit, auch wenn es vermutlich noch einig Funktionen gibt,
die wir beide noch gerne umsetzten, würden aber dafür fehlt uns leider die Zeit
und es sie würden vermutlich den Rahmen dieser Projektarbeit sprengen.

