create table klijent (id integer not null auto_increment, adresa varchar(255) not null, broj_telefona varchar(255) not null, email varchar(255) not null, ime varchar(255) not null, prezime varchar(255) not null, primary key (id));
create table narudzbenica (broj_narudzbenice integer not null auto_increment, datum_azuriranja datetime, datum_kreiranja datetime not null, ukupno double precision not null, klijent_id integer not null, primary key (broj_narudzbenice));
create table proizvod (id integer not null auto_increment, jedinica varchar(255) not null, naziv varchar(255) not null, tip_proizvoda varchar(255) not null, primary key (id));
create table stavkanarudzbenice (broj_narudzbenice integer not null, rb integer not null, cena double precision not null, kolicina integer not null, ukupna_cena double precision not null, proizvod_id integer not null, primary key (broj_narudzbenice, rb));
alter table klijent add constraint KLIJENT_UNQIUE_BROJ_TELEFONA unique (broj_telefona);
alter table klijent add constraint KLIJENT_UNIQUE_EMAIL unique (email);
alter table narudzbenica add constraint FK_NARUDZBENICA_KLIJENT foreign key (klijent_id) references klijent (id);
alter table stavkanarudzbenice add constraint FK_STAVKANARUDZBENICE_NARUDZBENICA foreign key (broj_narudzbenice) references narudzbenica (broj_narudzbenice);
alter table stavkanarudzbenice add constraint FK_STAVKANARUDZBENICE_PROIZVOD foreign key (proizvod_id) references proizvod (id);

insert into klijent(ime, prezime, adresa, email, broj_telefona) values ('Ivan', 'Cukic', 'Brace Baruha 25', 'ivancukic@gmail.com', '+381631234567');
insert into klijent(ime,prezime,adresa,email,broj_telefona) values ('Rastko','Mitrovic','Cara Dusana 12','rastkomitrovic@gmail.com','+381632345612');

insert into proizvod(naziv,tip_proizvoda,jedinica) values ('Smoki','Hrana','GRAM');
insert into proizvod(naziv,tip_proizvoda,jedinica) values ('Keks','Hrana','GRAM');
insert into proizvod(naziv,tip_proizvoda,jedinica) values ('Mleko','Hrana','LITAR');
insert into proizvod(naziv,tip_proizvoda,jedinica) values ('Piletina','Hrana','KILOGRAM');
insert into proizvod(naziv,tip_proizvoda,jedinica) values ('Teletina','Hrana','KILOGRAM');
insert into proizvod(naziv,tip_proizvoda,jedinica) values ('Casa','Hrana','KOMAD');
insert into proizvod(naziv,tip_proizvoda,jedinica) values ('Pirinac','Hrana','GRAM');
insert into proizvod(naziv,tip_proizvoda,jedinica) values ('Voda','Hrana','LITAR');
insert into proizvod(naziv,tip_proizvoda,jedinica) values ('Koka Kola','Hrana','LITAR');
insert into proizvod(naziv,tip_proizvoda,jedinica) values ('Televizor','Hrana','KOMAD');
