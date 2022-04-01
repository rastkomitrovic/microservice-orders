create table klijent (id integer not null auto_increment, adresa varchar(255) not null, broj_telefona varchar(255) not null, email varchar(255) not null, ime varchar(255) not null, prezime varchar(255) not null, primary key (id));
create table narudzbenicaDTO (broj_narudzbenice integer not null auto_increment, datum_azuriranja datetime, datum_kreiranja datetime not null, ukupno double precision not null, primary key (broj_narudzbenice));
create table proizvodDTO (id integer not null auto_increment, jedinica varchar(255) not null, naziv varchar(255) not null, tip_proizvoda varchar(255) not null, primary key (id));
create table stavkanarudzbenice (broj_narudzbenice integer not null, rb integer not null, cena double precision not null, kolicina integer not null, ukupna_cena double precision not null, proizvod_id integer not null, primary key (broj_narudzbenice, rb));
alter table klijent add constraint UNIQUE_USER_BROJ_TELEFONA unique (broj_telefona);
alter table klijent add constraint UNIQUE_USER_EMAIL unique (email);
alter table stavkanarudzbenice add constraint FK_STAVKA_NARUDZBENICE_NARUDZBENICA foreign key (broj_narudzbenice) references narudzbenicaDTO (broj_narudzbenice);
alter table stavkanarudzbenice add constraint FK_STAVKA_NARUDZBENICE_PROIZVOD foreign key (proizvod_id) references proizvodDTO (id);
