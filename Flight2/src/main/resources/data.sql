
insert into location(id, latitude, longitude, city) values (1, 35.0457, -85.3096, 'Chattanooga'), (2, 35.9606, -83.9207, 'Knoxville'), (3, 36.1627, -86.7816, 'Nashville'), (4, 35.1495, -90.0490, 'Memphis') ON CONFLICT DO NOTHING
