# V1

---
## Pre Coaching
### Geschatte Progress (21%)
### Status
Het opzetten van het project (dependencies) verliep reddelijk vlot (mede dankzij de lessen).  
Het instellen van de JPA database daarentegen, ging wat moeizaam. De connectie aanmaken zelf verliep relatief vlot. Het aanpassen van het domein aan de bestaande database had wel wat problemen (Vooral de kolomtypes die niet overeenkwamen, JoinColumn en ForeignKeys). We hadden ons eerst gebasseerd op het relationeel schema en dat bleek achteraf niet overeen te komen.  
Het verschil tussen de Get en Request mapping, hadden we even door elkaar gehaald. De andere endpoints aanmaken was niet zo moeilijk. Eens we met de eerste weg waren, volgende de *rest* ook. We konden geen rideEvents maken omdat rides geen primaryKey hebben, we gaan op een andere manier moeten bijhouden of de ride ongoing is (bv endtime checken op null).
Messaging tussen beide projecten was snel geconfigureerd met cloudAMQP. De Globalproperty wouw niet van de eerste keer inladen en hebben we eerst een keer moeten hardcoden.  
### Stories
We hebben na het importen van de issues met de CSV ook priorities toegevoegd. Dit vinden we handiger om ons eerst tot de kern te focussen.   
We beperken ons eerst tot een paar issues en proberen deze volledig af te werken, dit is voor enkele al gelukt.    
### Vragen
- Moeten we voor unit tests de controllers met rest aanspreken of rechtstreeks via de functie.
- Is er een rede dat Rides geen PK heeft.
## Post Coaching
### Feedback
# V2

---
## Pre Coaching
### Geschatte Progress ()
### Status
### Stories
### Vragen
## Post Coaching
### Feedback
# Eindoplevering
