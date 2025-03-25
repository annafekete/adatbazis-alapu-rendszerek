CREATE TABLE FELHASZNALO (
    email VARCHAR2(100) PRIMARY KEY,
    felhasznalonev VARCHAR2(100) UNIQUE NOT NULL,
    jelszo VARCHAR2(100) NOT NULL
);

CREATE TABLE VIDEO (
    videoid NUMBER GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 PRIMARY KEY,
    videocim VARCHAR2(255) NOT NULL,
    kategoria VARCHAR2(100),
    megtekintes_szam NUMBER DEFAULT 0,
    kulcsszo VARCHAR2(255),
    leiras VARCHAR2(1000)
);

CREATE TABLE HOZZASZOLAS (
    commentid NUMBER GENERATED ALWAYS AS IDENTITY START WITH 1 INCREMENT BY 1 PRIMARY KEY,
    videoid NUMBER,
    email VARCHAR2(100),
    tartalom VARCHAR2(1000),
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid) ON DELETE CASCADE,
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email) ON DELETE CASCADE --todo
);

CREATE TABLE LEJATSZASI_LISTA (
    playlistid NUMBER GENERATED ALWAYS AS IDENTITY START WITH 1 INCREMENT BY 1 PRIMARY KEY,
    email VARCHAR2(100),
    playlistnev VARCHAR2(255),
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email) ON DELETE CASCADE
);

CREATE TABLE FELTOLTI (
    videoid NUMBER,
    email VARCHAR2(100),
    feltoltes_ideje DATE DEFAULT SYSDATE,
    PRIMARY KEY (videoid, email),
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid) ON DELETE CASCADE, 
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email) ON DELETE CASCADE
);

CREATE TABLE KEDVELI (
    email VARCHAR2(100),
    videoid NUMBER,
    PRIMARY KEY (email, videoid),
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email) ON DELETE SET NULL,
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid) ON DELETE CASCADE
);

CREATE TABLE NEZ (
    viewid NUMBER GENERATED ALWAYS AS IDENTITY START WITH 1 INCREMENT BY 1 PRIMARY KEY,
    email VARCHAR2(100),
    videoid NUMBER,
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email) ON DELETE SET NULL,
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid) ON DELETE CASCADE
);

CREATE TABLE MEGIR (
    email VARCHAR2(100),
    commentid NUMBER,
    PRIMARY KEY (email, commentid),
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email) ON DELETE CASCADE,
    FOREIGN KEY (commentid) REFERENCES HOZZASZOLAS(commentid) ON DELETE CASCADE
);

CREATE TABLE TARTOZIK (
    commentid NUMBER,
    videoid NUMBER,
    PRIMARY KEY (commentid, videoid),
    FOREIGN KEY (commentid) REFERENCES HOZZASZOLAS(commentid) ON DELETE CASCADE,
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid) ON DELETE CASCADE
);

CREATE TABLE LETREHOZ (
    email VARCHAR2(100),
    playlistid NUMBER,
    PRIMARY KEY (email, playlistid),
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email) ON DELETE CASCADE,
    FOREIGN KEY (playlistid) REFERENCES LEJATSZASI_LISTA(playlistid) ON DELETE CASCADE
);

CREATE TABLE HOZZAAD (
    videoid NUMBER,
    playlistid NUMBER,
    PRIMARY KEY (videoid, playlistid),
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid) ON DELETE CASCADE,
    FOREIGN KEY (playlistid) REFERENCES LEJATSZASI_LISTA(playlistid) ON DELETE CASCADE 
);


-- rekordok
INSERT INTO FELHASZNALO (email, felhasznalonev, jelszo) VALUES ('anna@gmail.com', 'Anna', 'jelszo123');
INSERT INTO FELHASZNALO (email, felhasznalonev, jelszo) VALUES ('marina@gmail.com', 'Marina', 'Alma123');
INSERT INTO FELHASZNALO (email, felhasznalonev, jelszo) VALUES ('csaba@gmail.com', 'Csaba', 'Titok789');
INSERT INTO FELHASZNALO (email, felhasznalonev, jelszo) VALUES ('david@gmail.com', 'David', 'password123');
INSERT INTO FELHASZNALO (email, felhasznalonev, jelszo) VALUES ('erika@gmail.com', 'Erika', 'hello321');

INSERT INTO VIDEO (videocim, kategoria, megtekintes_szam, kulcsszo, leiras) VALUES ('Nyaralás', 'Vlog', 482, 'nyaralas25', '2025-ös nyaralás családdal.');
INSERT INTO VIDEO (videocim, kategoria, megtekintes_szam, kulcsszo, leiras) VALUES ('Főzés', 'Recept', 350, 'fozes', 'Finom vacsora készítése.');
INSERT INTO VIDEO (videocim, kategoria, megtekintes_szam, kulcsszo, leiras) VALUES ('Edzés', 'Sport', 220, 'edzes', 'Otthoni edzés gyakorlatok.');
INSERT INTO VIDEO (videocim, kategoria, megtekintes_szam, kulcsszo, leiras) VALUES ('Programozás', 'Oktatóvideó', 900, 'programozas', 'Bevezetés a Pythonba.');
INSERT INTO VIDEO (videocim, kategoria, megtekintes_szam, kulcsszo, leiras) VALUES ('Film Review', 'Review', 150, 'film', 'Legújabb filmek kritikái.');

INSERT INTO LEJATSZASI_LISTA (email, playlistnev) VALUES ('anna@gmail.com', 'Kedvencek');
INSERT INTO LEJATSZASI_LISTA (email, playlistnev) VALUES ('marina@gmail.com', 'Vlogok');
INSERT INTO LEJATSZASI_LISTA (email, playlistnev) VALUES ('csaba@gmail.com', 'Receptek');
INSERT INTO LEJATSZASI_LISTA (email, playlistnev) VALUES ('david@gmail.com', 'Filmek');
INSERT INTO LEJATSZASI_LISTA (email, playlistnev) VALUES ('erika@gmail.com', 'Edzés');

INSERT INTO FELTOLTI (videoid, email) VALUES (1, 'marina@gmail.com');
INSERT INTO FELTOLTI (videoid, email) VALUES (2, 'csaba@gmail.com');
INSERT INTO FELTOLTI (videoid, email) VALUES (3, 'erika@gmail.com');
INSERT INTO FELTOLTI (videoid, email) VALUES (4, 'david@gmail.com');
INSERT INTO FELTOLTI (videoid, email) VALUES (5, 'anna@gmail.com');

INSERT INTO KEDVELI (email, videoid) VALUES ('anna@gmail.com', 1);
INSERT INTO KEDVELI (email, videoid) VALUES ('marina@gmail.com', 2);
INSERT INTO KEDVELI (email, videoid) VALUES ('csaba@gmail.com', 3);
INSERT INTO KEDVELI (email, videoid) VALUES ('david@gmail.com', 4);
INSERT INTO KEDVELI (email, videoid) VALUES ('erika@gmail.com', 5);

INSERT INTO NEZ (email, videoid) VALUES ('anna@gmail.com', 1);
INSERT INTO NEZ (email, videoid) VALUES ('marina@gmail.com', 2);
INSERT INTO NEZ (email, videoid) VALUES ('csaba@gmail.com', 3);
INSERT INTO NEZ (email, videoid) VALUES ('david@gmail.com', 4);
INSERT INTO NEZ (email, videoid) VALUES ('erika@gmail.com', 5);

INSERT INTO HOZZAAD (videoid, playlistid) VALUES (1, 1);
INSERT INTO HOZZAAD (videoid, playlistid) VALUES (2, 2);
INSERT INTO HOZZAAD (videoid, playlistid) VALUES (3, 3);
INSERT INTO HOZZAAD (videoid, playlistid) VALUES (4, 4);
INSERT INTO HOZZAAD (videoid, playlistid) VALUES (5, 5);


SELECT * FROM FELHASZNALO;
SELECT * FROM VIDEO;
SELECT * FROM HOZZASZOLAS;
SELECT * FROM LEJATSZASI_LISTA;
SELECT * FROM FELTOLTI;
SELECT * FROM KEDVELI;
SELECT * FROM NEZ;
SELECT * FROM MEGIR;
SELECT * FROM TARTOZIK;
SELECT * FROM LETREHOZ;
SELECT * FROM HOZZAAD;