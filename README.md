![fog_carport](https://i.ibb.co/PFfyyWD/fog-logo-banner.png)

## Om projektet
Fog carport eksamensprojekt. Bygge en web-applikation i Java, MySql, HTML, CSS, JS og Bootstrap og køre det på en Tomcat webcontainer. Implementeret med et command-pattern og en front-controller.
Vores rapport omkring projektet kan tilgås og læses her '[dokumentation/rapport]()'. <br> <br>
Projektet er udført af:
- [Andreas Fritzbøger](https://github.com/Wolfgang1235)
- [Mads Herlevsen](https://github.com/Mads725)
- [Owais Dashti](https://github.com/OwaisAD)
- [Thomas Fritzbøger](https://github.com/thomasfritzboger)

## Logbog
Her er et link til vores logbog
- [LOGBOG]()



## Deployment på Droplet
T.B.D.

## Hvordan køres projektet?
1. Først skal du clone projektet eller downloade en zip-fil med projektet til din arbejdsstation.
2. Åbn Workbench og kør sql-filen `FogDB.sql`, som ligger under mappen `resources`. Den opretter de nødvendige databaser. 
3. Du skal nu lave en Tomcat konfiguration. Dvs, 
   1. klik på "Add Configuration ..."
   2. Klik på "+" og vælg "Tomcat Server Local".
   3. Klik på "Fix knappen"
   4. Vælg war-exploded som deployment type
   5. Nu kan du klikke på den grønne play-knap for at bygge og køre projektet.
4. Du skal nok ændre kodeord til databasen i projektet, så du kan tilgå den lokalt. Det gøres under model-delen i filerne: `/persistence/ConnectionPool` i linie 13 og 14. Du kan evt. klikke på Configuration i top-menuen -> Edit configurations -> Startup/Connection og lave to environment variabler. Den ene skal under Name hedde db_un og ud fra Value taster du din MySQL brugernavn. Den anden skal hedde db_up under navn og skal have din MySQL kode under value. På den måde kan du tilgå scriptet.

## Bemærkninger
- Strukturering er i passende packages for overblik (MVC). Noget af strukturen er også givet af Maven, og kan ikke laves om. F.eks. opdelingen i `/java` og `/webapp`.

Funktionelt kan applikationen:
- Vise hhv. sider for brugeren og administrator.
- Kunden kan bestille en carport efter egne ønskede dimensioner. Bekræftes denne kan en stykliste fås i profilen.
- Administratoren kan bekræfte forespørgsler, fremsøge kunder, tjekke ordre og ændre på lagerholdning.
- Du kan oprette en bruger eller logge på en følgende roler:
    1. `kunde1@fog.dk` med password: `1234` (rolle: `kunde`)
    2. `admin@fog.dk` med password: `1234` (rolle: `admin`)
- Hvis man indtaster ugyldige data under indlogning, bliver man sendt til en en fejlside.

## MVC arkitektur med front-controller

![](documentation/frontcontroller.jpg)
