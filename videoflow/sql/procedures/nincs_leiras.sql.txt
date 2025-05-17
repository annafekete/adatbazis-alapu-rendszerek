CREATE OR REPLACE TRIGGER set_default_description
BEFORE INSERT ON VIDEO
FOR EACH ROW
BEGIN
  IF :NEW.leiras IS NULL OR TRIM(:NEW.leiras) = '' THEN
    :NEW.leiras := 'Nincs leírás megadva.';
  END IF;
END;