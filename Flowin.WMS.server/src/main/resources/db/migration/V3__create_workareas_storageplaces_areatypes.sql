CREATE TABLE work_areas (
    work_area_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    work_area_name VARCHAR(50)
);

CREATE TABLE storage_places(
    storage_place_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY
);

CREATE TABLE area_types(
    area_type_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    area_type_name VARCHAR(50)
);