CREATE TABLE sku_categories (
    sku_category_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sku_category_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE catalogue_skus (
    sku_code INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sku_name VARCHAR(255) NOT NULL,
    sku_category_id INTEGER NOT NULL references sku_categories(sku_category_id),
    volume INTEGER NOT NULL check ( volume > 0 ),
    weight INTEGER NOT NULL check ( weight > 0 ),
    height INTEGER NOT NULL check ( height > 0 ),
    width INTEGER NOT NULL check ( width > 0 ),
    length INTEGER NOT NULL check ( length > 0 )
);