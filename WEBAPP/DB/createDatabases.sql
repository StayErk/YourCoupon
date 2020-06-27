drop database if exists yourcoupon;
create database yourcoupon;
use yourcoupon;

DROP user IF EXISTS 'youpon'@'%';
CREATE USER 'youpon'@'%' IDENTIFIED BY 'adminadmin';
GRANT ALL ON yourcoupon.* TO 'youpon'@'%';



create table Cliente(
                        nome            varchar(15)     not null,
                        cognome         varchar(15)     not null,
                        puntiViaggio    int             not null default 0,
                        email           varchar(35)     not null unique primary key,
                        password        binary(32)      not null,    /* SHA256 encripted */
                        admin           boolean         not null default FALSE,
                        immagine        varchar(200)
);


create table StrutturaAlberghiera(
                        id              char(36)        not null primary key,
                        nome            varchar(20)     not null,
                        indirizzo       varchar(50)     not null,
                        citta           varchar(20)     not null,
                        costoNotte      double          not null,
                        stelle          int             not null check (stelle >= 0 AND stelle <= 5),
                        immagine        varchar(100)    not null,
                        email           varchar(30)     not null,
                        numeroTelefono  varchar(9)      not null
);

create table Pacchetto(
                        id            char(36)        not null primary key,
                        costo         double          not null,
                        id_cliente    varchar(35)     not null,
                        id_struttura  char(36)        not null,
                        durata        int             not null,
                        predefinito   boolean         not null default FALSE,
                        persone       int             not null default '1',
                        foreign key (id_cliente)      references Cliente (email)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE,
                        foreign key (id_struttura)    references StrutturaAlberghiera (id)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
);


create table StruttureRistorative(
                        id              char(36)        not null primary key,
                        indirizzo       varchar(50)     not null,
                        citta           varchar(20)     not null,
                        nome            varchar(20)     not null,
                        costo           double          not null,
                        immagine        varchar(100)    not null,
                        numeroTelefono  varchar(9)      not null,
                        email           varchar(30)     not null
);

create table Pacchetto_Ristorante(
                        id_pacchetto    char(36)        not null,
                        id_ristorante   char(36)        not null,
                        primary key (id_pacchetto, id_ristorante),
                        foreign key (id_pacchetto) references Pacchetto (id)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE,
                        foreign key (id_ristorante) references StruttureRistorative (id)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
);


create table Luogo(
                        id              char(36)        not null primary key,
                        nome            varchar(25)     not null,
                        indirizzo       varchar(50)     not null,
                        citta           varchar(20)     not null,
                        descrizione     varchar(500)    not null,
                        immagine        varchar(100)    not null
);

create table VisitaGuidata(
                        id              char(36)        not null primary key,
                        id_luogo        char(36)     not null,
                        costo           double          not null,
                        partecipanti    int             not null,
                        foreign key (id_luogo) references Luogo (id)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE

);
create table Pacchetto_Visita(
                         id_pacchetto   char(36)        not null,
                         id_visita      char(36)        not null,
                         primary key (id_visita, id_pacchetto),
                         foreign key (id_pacchetto)     references Pacchetto (id)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE,
                         foreign key (id_visita)        references VisitaGuidata (id)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE
);


create table Carrello(
                        id_carrello      varchar(35)     not null primary key,
                        totale          double          not null,
                        foreign key     (id_carrello)    references Cliente(email)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
);

create table Carrello_Pacchetto(
                        id_carrello     varchar(35)     not null,
                        id_pacchetto    char(36)        not null,
                        primary key (id_pacchetto, id_carrello),
                        foreign key (id_carrello)       references Carrello(id_carrello)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE,
                        foreign key (id_pacchetto)      references Pacchetto(id)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
);

create table Fattura(
                        id              varchar(36)     not null primary key,
                        id_carrello     varchar(35)     not null,
                        totale          double          not null,
                        foreign key     (id_carrello)   references Carrello(id_carrello)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
);
