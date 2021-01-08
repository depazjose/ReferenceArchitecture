INSERT INTO book_data
(id,
author,
available,
isbn,
name,
properties,
quantity,
start_sale_date,
status)
VALUES
(1,
'author',
false,
123456,
'CLEAN CODE',
'{properties: {}}',
100,
'2020-01-01 00:00:00',
'SCHEDULED');

INSERT INTO book_data
(id,
author,
available,
isbn,
name,
properties,
quantity,
start_sale_date,
status)
VALUES
(2,
'author',
false,
1234567890,
'CLEAN CODE',
'{properties: {}}',
100,
'2020-01-01 00:00:00',
'SCHEDULED');

INSERT INTO publishing_house_data
(id,
name,
address,
is_active)
VALUES
(1,
'Norma',
'Calle 13',
true);

INSERT INTO publishing_house_data
(id,
name,
address,
is_active)
VALUES
(2,
'Atalaya',
'Cra 1',
false);

INSERT INTO publishing_house_data
(id,
name,
address,
is_active)
VALUES
(3,
'Castilla',
'Diagonal sur',
true);