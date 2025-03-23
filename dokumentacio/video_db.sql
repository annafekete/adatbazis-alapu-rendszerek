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

INSERT INTO FELHASZNALO (email, felhasznalonev, jelszo) VALUES ('marina@gmail.com', 'Aniram', 'Alma123');
INSERT INTO FELHASZNALO (email, felhasznalonev, jelszo) VALUES ('anna@gmail.com', 'Anna', 'jelszo123');
INSERT INTO VIDEO (videocim, kategoria, megtekintes_szam, kulcsszo, leiras) VALUES ('Nyaralás', 'vlog', 482, 'nyaralas25', '2025-ös nyaralás családdal.');
INSERT INTO HOZZASZOLAS (videoid, email, tartalom) VALUES (1, 'anna@gmail.com', 'Csodás videó!');
INSERT INTO LEJATSZASI_LISTA (email, playlistnev) VALUES ('anna@gmail.com', 'Kedvencek');
INSERT INTO FELTOLTI (videoid, email, feltoltes_ideje) VALUES (1, 'marina@gmail.com', SYSDATE);
INSERT INTO KEDVELI (email, videoid) VALUES ('anna@gmail.com', 1);
INSERT INTO NEZ (email, videoid) VALUES ('anna@gmail.com', 1);
INSERT INTO MEGIR (email, commentid) VALUES ('marina@gmail.com', 3);
INSERT INTO TARTOZIK (commentid, videoid) VALUES (3, 1);
INSERT INTO LETREHOZ (email, playlistid) VALUES ('anna@gmail.com', 1);
INSERT INTO HOZZAAD (videoid, playlistid) VALUES (1, 1);


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
