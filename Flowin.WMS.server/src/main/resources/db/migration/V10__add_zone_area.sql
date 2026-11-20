delete from work_areas
where 1 = 1;

delete from storage_places
where 1 = 1;

CREATE SEQUENCE storage_work_area_id_seq
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

ALTER TABLE work_areas
    ALTER COLUMN work_area_id DROP IDENTITY;

ALTER TABLE storage_places
    ALTER COLUMN storage_place_id DROP IDENTITY;

ALTER TABLE work_areas
    ALTER COLUMN work_area_id
        SET DEFAULT nextval('storage_work_area_id_seq');

ALTER TABLE storage_places
    ALTER COLUMN storage_place_id
        SET DEFAULT nextval('storage_work_area_id_seq');

WITH new_id AS (
    SELECT nextval('storage_work_area_id_seq') AS id
),
     new_work_area AS (
INSERT INTO work_areas (work_area_id, work_area_name)
SELECT id, 'staging area'
FROM new_id
         RETURNING work_area_id
)
INSERT INTO storage_places (storage_place_id, info)
SELECT work_area_id, 'area'
FROM new_work_area;