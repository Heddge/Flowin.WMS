CREATE TABLE racks(
    rack_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    zone_id INTEGER NOT NULL REFERENCES zones(zone_id)
);

CREATE TABLE shelves(
    shelf_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    rack_id INTEGER NOT NULL REFERENCES racks(rack_id)
);

CREATE TABLE bins(
    bin_id INTEGER PRIMARY KEY references storage_places(storage_place_id),
    shelf_id INTEGER NOT NULL REFERENCES shelves(shelf_id),
    last_used_at TIMESTAMP NOT NULL,
    occupied_volume INTEGER NOT NULL CHECK ( occupied_volume >= 0 and occupied_volume <= max_volume),
    max_volume INTEGER NOT NULL CHECK ( max_volume > 0 ),
    occupied_weight INTEGER NOT NULL CHECK (occupied_weight >= 0 and occupied_weight <= max_weight),
    max_weight INTEGER NOT NULL CHECK (max_weight > 0 ),
    occupied_count INTEGER NOT NULL CHECK (occupied_count >= 0 and occupied_count <= max_count),
    max_count INTEGER NOT NULL CHECK (max_count > 0)
);

CREATE TABLE areas(
    area_id INTEGER PRIMARY KEY references storage_places(storage_place_id)  references work_areas(work_area_id),
    wh_id INTEGER NOT NULL REFERENCES warehouses(wh_id),
    area_type_id INTEGER NOT NULL REFERENCES area_types(area_type_id)
);