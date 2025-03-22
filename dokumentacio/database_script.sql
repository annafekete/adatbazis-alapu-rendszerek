
CREATE TABLE FELHASZNALO (
    email VARCHAR2(100) PRIMARY KEY,
    felhasznalonev VARCHAR2(100) NOT NULL,
    jelszo VARCHAR2(100) NOT NULL
);

CREATE TABLE VIDEO (
    videoid NUMBER PRIMARY KEY,
    videocim VARCHAR2(255) NOT NULL,
    kategoria VARCHAR2(100),
    megtekintes_szam NUMBER DEFAULT 0,
    kulcsszo VARCHAR2(255),
    leiras VARCHAR2(1000)
);

CREATE TABLE HOZZASZOLAS (
    commentid NUMBER PRIMARY KEY,
    videoid NUMBER,
    email VARCHAR2(100),
    tartalom VARCHAR2(1000),
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid),
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email)
);

CREATE TABLE LEJATSZASI_LISTA (
    playlistid NUMBER PRIMARY KEY,
    email VARCHAR2(100),
    playlistnev VARCHAR2(255),
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email)
);

CREATE TABLE FELTOLTI (
    videoid NUMBER,
    email VARCHAR2(100),
    feltoltes_ideje DATE DEFAULT SYSDATE,
    PRIMARY KEY (videoid, email),
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid),
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email)
);

CREATE TABLE KEDVELI (
    email VARCHAR2(100),
    videoid NUMBER,
    PRIMARY KEY (email, videoid),
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email),
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid)
);

CREATE TABLE NEZ (
    viewid NUMBER PRIMARY KEY,
    email VARCHAR2(100),
    videoid NUMBER,
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email),
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid)
);

CREATE TABLE MEGIR (
    email VARCHAR2(100),
    commentid NUMBER,
    PRIMARY KEY (email, commentid),
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email),
    FOREIGN KEY (commentid) REFERENCES HOZZASZOLAS(commentid)
);

CREATE TABLE TARTOZIK (
    commentid NUMBER,
    videoid NUMBER,
    PRIMARY KEY (commentid, videoid),
    FOREIGN KEY (commentid) REFERENCES HOZZASZOLAS(commentid),
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid)
);

CREATE TABLE LETREHOZ (
    email VARCHAR2(100),
    playlistid NUMBER,
    PRIMARY KEY (email, playlistid),
    FOREIGN KEY (email) REFERENCES FELHASZNALO(email),
    FOREIGN KEY (playlistid) REFERENCES LEJATSZASI_LISTA(playlistid)
);

CREATE TABLE HOZZAAD (
    videoid NUMBER,
    playlistid NUMBER,
    PRIMARY KEY (videoid, playlistid),
    FOREIGN KEY (videoid) REFERENCES VIDEO(videoid),
    FOREIGN KEY (playlistid) REFERENCES LEJATSZASI_LISTA(playlistid)
);

INSERT INTO FELHASZNALO (email, felhasznalonev, jelszo) VALUES ('marina@gmail.com', 'Aniram', 'Alma123');
INSERT INTO VIDEO (videoid, videocim, kategoria, megtekintes_szam, kulcsszo, leiras) VALUES (1, 'Nyaralás', 'vlog', 482, 'nyaralas25', '2025-ös nyaralás családdal.');
INSERT INTO HOZZASZOLAS (commentid, videoid, email, tartalom) VALUES (1, 1, 'anna@gmail.com', 'Csodás videó!');
INSERT INTO LEJATSZASI_LISTA (playlistid, email, playlistnev) VALUES (1, 'csaba@gmail.com', 'Kedvencek');
INSERT INTO FELTOLTI (videoid, email, feltoltes_ideje) VALUES (1, 'marina@gmail.com', SYSDATE);
INSERT INTO KEDVELI (email, videoid) VALUES ('anna@gmail.com', 1);
INSERT INTO NEZ (viewid, email, videoid) VALUES (1, 'csaba@gmail.com', 1);
INSERT INTO MEGIR (email, commentid) VALUES ('marina@gmail.com', 1);
INSERT INTO TARTOZIK (commentid, videoid) VALUES (1, 1);
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
