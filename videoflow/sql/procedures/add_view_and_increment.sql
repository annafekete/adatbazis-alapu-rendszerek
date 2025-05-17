create or replace PROCEDURE add_view_and_increment (
    p_email    IN VARCHAR2,
    p_videoid  IN NUMBER
)
AS
BEGIN
    -- Néző rögzítése
INSERT INTO nez (email, videoid) VALUES (p_email, p_videoid);

-- Megtekintésszám növelése
UPDATE video SET megtekintes_szam = megtekintes_szam + 1 WHERE videoid = p_videoid;
END;
