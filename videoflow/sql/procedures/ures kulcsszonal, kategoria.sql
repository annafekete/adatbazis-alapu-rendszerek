CREATE OR REPLACE TRIGGER trg_set_kulcsszo
BEFORE INSERT OR UPDATE ON VIDEO
FOR EACH ROW
BEGIN
  IF :NEW.kulcsszo IS NULL OR TRIM(:NEW.kulcsszo) = '' THEN
    :NEW.kulcsszo := :NEW.kategoria;
  END IF;
END;