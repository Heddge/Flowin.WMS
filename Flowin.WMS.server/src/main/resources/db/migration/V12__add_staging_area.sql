WITH new_id AS (
    SELECT nextval('storage_work_area_id_seq') AS id
),
     new_work_area AS (
INSERT INTO work_areas (work_area_id, work_area_name)
SELECT id, 'staging area'
FROM new_id
         RETURNING work_area_id
),
new_storage_place AS (
INSERT INTO storage_places (storage_place_id, info)
SELECT work_area_id, 'staging area'
FROM new_work_area
    RETURNING storage_place_id
    ),
    staging_type AS (
SELECT area_type_id
FROM area_types
WHERE area_type_name = 'staging area'
    )
INSERT INTO areas (area_id, wh_id, area_type_id)
SELECT new_storage_place.storage_place_id, 1, staging_type.area_type_id
FROM new_storage_place
         CROSS JOIN staging_type;