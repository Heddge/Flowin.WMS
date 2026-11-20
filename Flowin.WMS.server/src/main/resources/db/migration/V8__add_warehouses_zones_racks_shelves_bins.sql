CREATE TABLE warehouses(
    wh_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    wh_name VARCHAR(100) NOT NULL UNIQUE
);

insert into warehouses (wh_name)
values ('Ozon 1');

CREATE TABLE zones(
    zone_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    zone_sku_category_id INTEGER NOT NULL REFERENCES sku_categories(sku_category_id),
    wh_id INTEGER NOT NULL REFERENCES warehouses(wh_id)
);

