CREATE TABLE location_item_balances(
    location_id INTEGER NOT NULL REFERENCES locations(location_id),
    sku_id INTEGER NOT NULL REFERENCES item_balances(sku_id),
    reserved_count INTEGER NOT NULL check ( reserved_count >= 0 and reserved_count <= count ),
    count INTEGER NOT NULL check ( count >= 0 ),

    PRIMARY KEY (location_id, sku_id)
);