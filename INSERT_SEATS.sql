CREATE OR REPLACE PROCEDURE INSERT_SEATS(mid_ IN NUMBER, tid_ IN NUMBER,SHOWID IN NUMBER)
IS
    c CHAR(1);
BEGIN
        FOR j IN 0..9 LOOP
            c := CHR(65 + j);
            FOR k IN 1..10 LOOP
                INSERT INTO all_details(mid, tid, sid, bool, show_id)
                VALUES (mid_, tid_, c || TO_CHAR(k), 0, SHOWID);
            END LOOP;
        END LOOP;
END;
/