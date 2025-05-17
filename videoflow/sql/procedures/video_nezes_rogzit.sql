CREATE OR REPLACE PROCEDURE video_nezes_rogzit (
    p_email IN VARCHAR2,
    p_videoid IN NUMBER
) AS
    cnt NUMBER;
BEGIN
SELECT COUNT(*) INTO cnt
FROM NEZ
WHERE email = p_email AND videoid = p_videoid;

IF cnt = 0 THEN
        INSERT INTO NEZ (email, videoid)
        VALUES (p_email, p_videoid);
END IF;
END;
/
