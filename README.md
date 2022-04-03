# microservice-orders
Projekat za predmet Konstrukcije softvera. Mikroservis za narduzbenice.

Aplikacija predstavlja deo mikroservisne arhitekture zaduzene za rad sa narudzbenicama.

Informacije o izlozenim REST API pozivima mogu se pronaci odlaskom na ${putanjaServera:9099}/api-docs.html ili ${putanjaServera:9099}/api-docs-json

Za bazu podataka koriscena je relaciona MySql baza podataka kojoj se moze pristupiti 
na linku https://phpmyadmin-c005.cloudclusters.net/signon.php?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InJhc3Rrb21pdHJvdmljQGdtYWlsLmNvbSIsInVzZXJfaWQiOjI4MjY2LCJ1c2VybmFtZSI6InJhc3Rrb21pdHJvdmljQGdtYWlsLmNvbSIsImV4cCI6MTY0OTAzNDcxOH0.08kKglbtPBpkppNA4Go8DCpx-2cvKlsyCi8YOiLgyZE&uuid=ae744ac7ba42496d829c1c2c25d5d26a
sa korisnickim imenom MicroserviceOrdersUser i lozinkom Konstrukcije22!

Za stack tehnologija koriscjene su sledece tehnologije:
- Spring Boot
- Spring WEB za kreiranje REST kontrolera koji primaju i obradjuju zahteve
- OpenAPI i Swagger za dokumentaciju
- Logback za logovanje informacija
- Hibernate Validator i Jackson za konverziju i validaciju JSON objekata u Java POJO objekte
- Hibernate kao ORM alat
- Spring Data JPA kao framework za olaksanu perzistenciju podataka u bazi
- JUnit i Mockito za Unit testove

Autori: Rastko Mitrović i Ivan Ćukić
Mentor: Tatjana Stojanovic

![](/Users/mit1bg/Downloads/Konstrukcije softvera.drawio.png)