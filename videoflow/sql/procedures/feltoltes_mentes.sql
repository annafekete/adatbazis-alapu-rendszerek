create or replace PROCEDURE feltoltes_mentes(
    p_videocim IN VARCHAR2,
    p_kategoria IN VARCHAR2,
    p_kulcsszo IN VARCHAR2,
    p_leiras IN VARCHAR2,
    p_file_path IN VARCHAR2,
    p_email IN VARCHAR2
) AS
    v_videoid NUMBER;
BEGIN
    INSERT INTO video (videocim, kategoria, kulcsszo, leiras, file_path, megtekintes_szam)
    VALUES (p_videocim, p_kategoria, p_kulcsszo, p_leiras, p_file_path, 0)
    RETURNING videoid INTO v_videoid;

    INSERT INTO feltolti (videoid, email, feltoltes_ideje)
    VALUES (v_videoid, p_email, SYSDATE);
END;
/