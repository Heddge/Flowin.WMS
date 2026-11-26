CREATE TABLE locations(
    location_id INTEGER NOT NULL PRIMARY KEY REFERENCES storage_places(storage_place_id),
    address VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE item_balances(
    sku_id INTEGER NOT NULL PRIMARY KEY REFERENCES catalogue_skus(sku_code),
    reserved_count INTEGER NOT NULL check ( reserved_count >= 0 and reserved_count <= count ),
    count INTEGER NOT NULL check ( count >= 0 )
);