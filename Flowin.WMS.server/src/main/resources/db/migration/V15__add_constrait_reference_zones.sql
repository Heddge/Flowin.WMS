alter table zones
add constraint fk_zone_id foreign key (zone_id) references work_areas (work_area_id);
