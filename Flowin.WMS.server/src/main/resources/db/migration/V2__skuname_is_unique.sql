ALTER TABLE catalogue_skus
    ADD CONSTRAINT catalogue_skus_sku_name UNIQUE (sku_name);