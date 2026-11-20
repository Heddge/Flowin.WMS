ALTER TABLE zones
    ALTER COLUMN zone_id DROP IDENTITY;

WITH new_id AS (
    SELECT nextval('storage_work_area_id_seq') AS id
),
     new_work_area AS (
INSERT INTO work_areas (work_area_id, work_area_name)
SELECT id, 'zone electronics'
FROM new_id
         RETURNING work_area_id
),
new_storage_place AS (
INSERT INTO storage_places (storage_place_id, info)
SELECT work_area_id, 'zone electronics'
FROM new_work_area
    RETURNING storage_place_id
    )
INSERT INTO zones (zone_id, zone_sku_category_id, wh_id)
SELECT storage_place_id, 1, 1
FROM new_storage_place;