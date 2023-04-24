insert into empresasVendedoras values
	(default, 'Empresa 1', '01-05-2023'),
	(default, 'Empresa 2', '10-07-2022'),
	(default, 'Empresa 3', '12-10-2021'),
	(default, 'Empresa 4', '03-01-2020'),
	(default, 'Empresa 5', '07-06-2019');

insert into almacenes values
	(default, 'Calle 1', 1, 'Ciudad 1', 11111),
	(default, 'Calle 2', 2, 'Ciudad 2', 22222),
	(default, 'Calle 3', 3, 'Ciudad 3', 33333),
	(default, 'Calle 4', 4, 'Ciudad 4', 44444),
	(default, 'Calle 5', 5, 'Ciudad 5', 55555);

insert into productos values
	(default, 1, 1, 'Producto 1', 'Descripcion 1', 1.00, 10),
	(default, 2, 2, 'Producto 2', 'Descripcion 2', 2.00, 20),
	(default, 3, 3, 'Producto 3', 'Descripcion 3', 3.00, 30),
	(default, 4, 4, 'Producto 4', 'Descripcion 4', 4.00, 40),
	(default, 5, 5, 'Producto 5', 'Descripcion 5', 5.00, 50);

insert into clientes values
	(default, 'Cliente 1', 111111111, '01-01-2000', true, 'cliente1@pruebas.com', 'contrasena1'),
	(default, 'Cliente 2', 222222222, '02-02-2000', false, 'cliente2@pruebas.com', 'contrasena2'),
	(default, 'Cliente 3', 333333333, '03-03-2000', true, 'cliente3@pruebas.com', 'contrasena3'),
	(default, 'Cliente 4', 444444444, '04-04-2000', false, 'cliente4@pruebas.com', 'contrasena4'),
	(default, 'Cliente 5', 555555555, '05-05-2000', true, 'cliente5@pruebas.com', 'contrasena5');

insert into metodosPago values
	(1111111111111111, 1, true, true),
	(2222222222222222, 2, true, false),
	(3333333333333333, 3, true, true),
	(4444444444444444, 4, true, false),
	(5555555555555555, 5, true, true);

insert into direcciones values
	(default, 'Calle 1', 1, 'Ciudad 1', 11111, true),
	(default, 'Calle 2', 2, 'Ciudad 2', 22222, false),
	(default, 'Calle 3', 3, 'Ciudad 3', 33333, true),
	(default, 'Calle 4', 4, 'Ciudad 4', 44444, false),
	(default, 'Calle 5', 5, 'Ciudad 5', 55555, true);

insert into distribuidores values
	(default, 'Distribuidor 1', 111111111, 1.00),
	(default, 'Distribuidor 2', 222222222, 2.00),
	(default, 'Distribuidor 3', 333333333, 3.00),
	(default, 'Distribuidor 4', 444444444, 4.00),
	(default, 'Distribuidor 5', 555555555, 5.00);

insert into pedidos values
	(default, '01-01-2000', '01-01-2000', '01-01-2000', true, 1.00, 1111111111111111, 1, 1, 1),
	(default, '02-02-2000', '02-02-2000', '02-02-2000', true, 2.00, 2222222222222222, 2, 2, 2),
	(default, '03-03-2000', '03-03-2000', '03-03-2000', true, 3.00, 3333333333333333, 3, 3, 3),
	(default, '04-04-2000', '04-04-2000', '04-04-2000', true, 4.00, 4444444444444444, 4, 4, 4),
	(default, '05-05-2000', '05-05-2000', '05-05-2000', true, 5.00, 5555555555555555, 5, 5, 5);

insert into informar values
	(1, 1, default, 'Informe 1'),
	(2, 2, default, 'Informe 2'),
	(3, 3, default, 'Informe 3'),
	(4, 4, default, 'Informe 4'),
	(5, 5, default, 'Informe 5');

insert into valorar values
	(1, 1, default, 1, 'Valoracion 1', 1),
	(2, 2, default, 2, 'Valoracion 2', 2),
	(3, 3, default, 3, 'Valoracion 3', 3),
	(4, 4, default, 4, 'Valoracion 4', 4),
	(5, 5, default, 5, 'Valoracion 5', 5);

insert into contener values
	(1, 1, 1),
	(2, 2, 2),
	(3, 3, 3),
	(4, 4, 4),
	(5, 5, 5);

insert into residir values
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5);