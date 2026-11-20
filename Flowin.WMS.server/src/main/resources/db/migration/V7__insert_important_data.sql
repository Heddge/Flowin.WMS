delete from work_areas
where 1 = 1;

delete from storage_places
    where 1= 1;

delete from area_types
    where 1= 1;

insert into area_types (area_type_name)
values ('staging area'), ('packing area');

insert into work_areas (work_area_name)
values ('zone electronics'), ('staging area'), ('packing area');

insert into storage_places (info)
values ('bin'), ('area'), ('area');