drop database if exists yourcoupon;
create database yourcoupon;
use yourcoupon;

DROP user IF EXISTS 'adminYoupon'@'localhost';
CREATE USER 'adminYoupon'@'localhost' IDENTIFIED BY 'adminadmin';
GRANT ALL ON yourcoupon.* TO 'adminYoupon'@'localhost';



create table Cliente(
                        nome            varchar(15)     not null,
                        cognome         varchar(15)     not null,
                        puntiViaggio    int             not null default 0,
                        email           varchar(30)     not null unique primary key,
                        password        binary(64)        not null,    /* SHA256 encripted */
                        immagine        varchar(100)
);


create table StrutturaAlberghiera(
                        id              char(36)        not null primary key,
                        indirizzo       varchar(20)     not null,
                        costoNotte      double          not null,
                        stelle          int             not null check (stelle >= 0 AND stelle <= 5),
                        immagine        varchar(100)    not null,
                        email           varchar(30)     not null,
                        numeroTelefono  varchar(9)      not null
);

create table Pacchetto(
                        id            char(36)        not null primary key,
                        costo         double          not null,
                        id_cliente    varchar(30),
                        id_struttura  char(36)        not null,
                        durata        int             not null,
                        predefinito   boolean         not null default FALSE,
                        foreign key (id_cliente)      references Cliente (email),
                        foreign key (id_struttura)    references StrutturaAlberghiera (id)
);


create table StruttureRistorative(
                        id              char(36)        not null primary key,
                        indirizzo       varchar(15)     not null,
                        nome            varchar(15)     not null,
                        costo           double          not null,
                        immagine        varchar(100)    not null,
                        numeroTelefono  varchar(9)      not null,
                        email           varchar(30)     not null
);

create table Pacchetto_Ristorante(
                        id_pacchetto    char(36)        not null,
                        id_ristorante   char(36)        not null,
                        primary key (id_pacchetto, id_ristorante),
                        foreign key (id_pacchetto) references Pacchetto (id),
                        foreign key (id_ristorante) references StruttureRistorative (id)
);


create table Luogo(
                        id              char(36)        not null primary key,
                        nome            varchar(15)     not null,
                        indirizzo       varchar(20)     not null,
                        descrizione     varchar(500)    not null,
                        immagine        varchar(100)    not null
);

create table VisitaGuidata(
                        id              char(36)        not null primary key,
                        id_luogo        varchar(25)     not null,
                        costo           double          not null,
                        partecipanti    int             not null,
                        foreign key (id_luogo) references Luogo (id)

);
create table Pacchetto_Visita(
                         id_pacchetto   char(36)        not null,
                         id_visita      char(36)        not null,
                         primary key (id_visita, id_pacchetto),
                         foreign key (id_pacchetto)     references Pacchetto (id),
                         foreign key (id_visita)        references VisitaGuidata (id)
);


create table Carrello(
                        id_cliente      varchar(30)     not null primary key,
                        totale          double          not null,
                        foreign key     (id_cliente)    references Cliente(email)
);

create table Carrello_Pacchetto(
                        id_carrello     varchar(30)     not null,
                        id_pacchetto    char(36)        not null,
                        primary key (id_pacchetto, id_carrello),
                        foreign key (id_carrello)       references Carrello(id_cliente),
                        foreign key (id_pacchetto)      references Pacchetto(id)
);

create table Fattura(
                        id              char(36)        not null primary key,
                        id_carrello     varchar(30)     not null,
                        totale          double          not null,
                        foreign key     (id_carrello)   references Carrello(id_cliente)
);