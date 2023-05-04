insert into empresasVendedoras values
	(default, 'Xiaomi', '01-05-2023'),
	(default, 'Apple', '10-07-2022'),
	(default, 'Samsung', '12-10-2021'),
	(default, 'Dyson', '03-01-2020'),
	(default, 'Lacoste', '04-15-2020'),
	(default, 'Balay', '02-23-2019'),
	(default, 'EA', '08-21-2018'),
	(default, 'Adidas', '10-09-2016'),
	(default, 'Nike', '01-20-2014'),
	(default, 'Cecotec', '07-18-2017'),
	(default, 'Amazon', '01-01-2000');

/*set datestyle to "ISO, MDY";*/

insert into almacenes values
	(default, 'Calle Portugal', 1, 'Madrid', 28001),
	(default, 'Calle Francia', 2, 'Madrid', 28003),
	(default, 'Calle Alemania', 3, 'Barcelona', 08004),
	(default, 'Calle Inglaterra', 4, 'Barcelona', 08002),
	(default, 'Calle Italia', 5, 'Sevilla', 41005);

insert into productos values
	(default, 1, 1, 'Redmi 9T', 'Descripcion Redmi 9T', 200.00, 100),
	(default, 1, 2, 'Redmi Note 12 Pro', 'Descripcion Redmi Note 12 Pro', 450.00, 50),
	(default, 1, 3, 'Redmi buds 3 lite', 'Descripcion Redmi buds 3 lite', 24.00, 200),
	(default, 1, 2, 'Redmi 12C', 'Descripcion Redmi 12C', 300.00, 40),
	(default, 1, 1, 'Redmi 11', 'Descripcion Redmi 11', 170.00, 143),
	(default, 2, 1, 'Iphone 11', 'Descripcion Iphone 11', 500.00, 64),
	(default, 2, 2, 'Mac Mini', 'Descripcion Mac Mini', 350.00, 100),
	(default, 2, 1, 'Ipad Pro', 'Descripcion Ipad Pro', 1000.00, 33),
	(default, 2, 3, 'Ruedas Pro', 'Descripcion Ruedas Pro', 400.00, 2000),
	(default, 3, 1, 'Galaxy S21', 'Descripcion Galaxy S21', 700.00, 100),
	(default, 3, 2, 'Galaxy S21 Ultra', 'Descripcion Galaxy S21 Ultra', 1000.00, 50),
	(default, 3, 3, 'Galaxy Buds Pro', 'Descripcion Galaxy Buds Pro', 200.00, 200),
	(default, 3, 2, 'Galaxy Watch 4', 'Descripcion Galaxy Watch 4', 300.00, 40),
	(default, 4, 1, 'V11 Absolute', 'Descripcion V11 Absolute', 500.00, 143),
	(default, 4, 2, 'V11 Animal', 'Descripcion V11 Animal', 400.00, 64),
	(default, 4, 3, 'V11 Slim', 'Descripcion V11 Slim', 300.00, 100),
	(default, 5, 1, 'Polo Classic', 'Descripcion Polo Classic', 100.00, 100),
	(default, 5, 2, 'Polo Sport', 'Descripcion Polo Sport', 150.00, 50),
	(default, 6, 1, 'Lavadora TF10001', 'Descripcion Lavadora TF10001', 500.00, 200),
	(default, 6, 2, 'Lavavajillas TF13902', 'Descripcion Lavavajillas TF13902', 600.00, 100),
	(default, 6, 3, 'Frigorifico TF20001', 'Descripcion Frigorifico TF20001', 700.00, 50),
	(default, 7, 1, 'Fifa 22', 'Descripcion Fifa 22', 60.00, 100),
	(default, 7, 2, 'Battlefield 2042', 'Descripcion Battlefield 2042', 70.00, 50),
	(default, 7, 3, 'Star Wars Jedi Fallen Order', 'Descripcion Star Wars Jedi Fallen Order', 50.00, 200),
	(default, 8, 1, 'Zapatillas Stan Smith', 'Descripcion Zapatillas Stan Smith', 100.00, 100),
	(default, 8, 2, 'Zapatillas Superstar', 'Descripcion Zapatillas Superstar', 150.00, 50),
	(default, 9, 1, 'Zapatillas Air Force 1', 'Descripcion Zapatillas Air Force 1', 100.00, 100),
	(default, 9, 2, 'Zapatillas Air Max 90', 'Descripcion Zapatillas Air Max 90', 150.00, 50),
	(default, 10, 1, 'Robot aspirador Conga 1090', 'Descripcion Robot aspirador Conga 1090', 200.00, 100),
	(default, 10, 2, 'Robot aspirador Conga 1090 Connected', 'Descripcion Robot aspirador Conga 1090 Connected', 250.00, 50),
	(default, 10, 3, 'Robot aspirador Conga 1090 Connected Force', 'Descripcion Robot aspirador Conga 1090 Connected Force', 300.00, 200),
	(default, 11, 1, 'Amazon Prime', 'El prime de toda la vida', 50.00, 1000);

insert into clientes values
	(default, 'admin', 691669052, '01-21-1994', true, 'admin', '21232f297a57a5a743894a0e4a801fc3'), -- admin
	(default, 'Jose Perez Jimenez', 691669052, '01-21-1999', false, 'jpj@gmail.com', 'e2275670962d659c367c780875a2decc'), --jpj
	(default, 'Maria Garcia Lopez', 691669053, '04-19-2015', false, 'mgl@gmail.com', '738f144b1775be154d22e9640ce970ef'), --mgl
	(default, 'Juan Rodriguez Sanchez', 691669054, '07-17-2010', false, 'jrs@gmail.com', 'c5f463701e07238d500611d7ee42ea47'),--jrs
	(default, 'Ana Gonzalez Fernandez', 691669055, '10-15-2005', false, 'agf@gmail.com', '6d2698fa6a21e6a5b50b7c5d3d667742'), --agf
	(default, 'Carlos Martinez Perez', 691669056, '01-13-2000', false, 'cmp@gmail.com', '31b4e550aa5fd883246f9b9ceae82483'), --cmp
	(default, 'Laura Sanchez Rodriguez', 691669057, '04-11-2016', false, 'lsr@gmail.com', '971ea0ac060f20c995a5a3871d91deaf'), --lsr
	(default, 'Antonio Fernandez Gonzalez', 691669058, '07-09-2011', false, 'afg@gmail.com', 'ee676ed9ce5bd51b4452ddfbdf962ef7'), --afg
	(default, 'Sara Perez Martinez', 691669059, '10-07-2006', false, 'spm@gmail.com', '51762626b4f785729159fd35eea74deb'), --spm
	(default, 'Pedro Lopez Sanchez', 691669060, '01-05-2001', false, 'pls@gmail.com', 'd89a8633204d02f952c89b8245f2287e'), --pls
	(default, 'Carmen Rodriguez Fernandez', 691669061, '04-03-2017', false, 'crf@gmail.com', '62f29e71e972128920deb04f964ca04e'), --crf
	(default, 'Daniel Gonzalez Perez', 691669062, '07-01-2012', false, 'dgp@gmail.com', '7a77910cfd8c77ab1b0892a537ec6103'), --dgp
	(default, 'Paula Martinez Rodriguez', 691669063, '09-29-2007', false, 'pmr@gmail.com', '2baba74f3ab8c4f508ce57085c17cd62'), --pmr
	(default, 'Manuel Sanchez Gonzalez', 691669064, '12-27-2002', false, 'msg@gmail.com', '6e2baaf3b97dbeef01c0043275f9a0e7'), --msg
	(default, 'Lucia Fernandez Martinez', 691669065, '03-25-2018', false, 'lfm@gmail.com', '400ad14d9fc8101047cb994ec6dea0be'), --lfm
	(default, 'Marcos Perez Sanchez', 691669066, '06-23-2013', false, 'mps@gmail.com', '7c1dc91629afef3369fc77b119a2e24d'), --mps
	(default, 'Sofia Rodriguez Fernandez', 691669067, '09-21-2008', false, 'srf@gmail.com', '9fa1e546d34dd62a768567052aaa9f92'), --srf
	(default, 'Javier Gonzalez Perez', 691669068, '12-19-2003', false, 'jgp@gmail.com', 'ddc8f2b30c42909a8d151357a70bcc4b'), --jgp
	(default, 'Test', 111111111, '01-01-2000', false, 'test', '098f6bcd4621d373cade4e832627b4f6');

insert into metodosPago values
	(4000123456789010, 1, true, true),
	(1234567894325512, 2, false, true),
	(9876543219856741, 2, true, false),
	(1234554321984325, 3, true, true),
	(8765432198765497, 3, false, false),
	(1234567891234566, 4, true, true),
	(9876543219876354, 4, false, false),
	(1234554321987679, 5, true, true),
	(8765432198765123, 5, false, false),
	(1234567891232342, 6, true, true),
	(9876543219876544, 6, false, false),
	(1234554321987673, 7, true, true),
	(8765432198765413, 7, false, false),
	(1234567891237656, 8, true, true),
	(9876543219876532, 8, false, false),
	(1234554321987321, 9, true, true),
	(8765432198765543, 9, false, false),
	(1234567891231231, 10, true, true),
	(9876543219857899, 10, false, false),
	(1234554321987354, 11, true, true),
	(8765432198769878, 11, false, false),
	(1234567891234745, 12, true, true),
	(9876543219876479, 12, false, false),
	(1234554321983212, 13, true, true),
	(8765432198765342, 13, false, false),
	(1234567891234784, 14, true, true),
	(9876543219123141, 14, false, false),
	(1234554321987678, 15, true, true),
	(8765432198765432, 15, false, false),
	(1234567891234567, 16, true, true),
	(9876543219876543, 16, false, false);

insert into direcciones values
	(default, 1, 'Calle Portugal', 15, 'Madrid', 28001, true),
	(default, 2, 'Calle Francia', 45, 'Madrid', 28002, false),
	(default, 3, 'Calle Alemania', 8, 'Madrid', 28003, true),
	(default, 4, 'Calle Italia', 23, 'Madrid', 28002, true),
	(default, 5, 'Calle Inglaterra', 1, 'Madrid', 28001, false),
	(default, 6, 'Calle Holanda', 7, 'Madrid', 28003, true),
	(default, 7, 'Calle Mexico', 98, 'Madrid', 28001, true),
	(default, 8, 'Calle Rusia', 76, 'Barcelona', 08004, false),
	(default, 9, 'Calle China', 54, 'Barcelona', 08002, true),
	(default, 10, 'Calle Japon', 32, 'Barcelona', 08001, true),
	(default, 11, 'Calle Colombia', 10, 'Barcelona', 08004, false),
	(default, 12, 'Calle Taiwan', 4, 'Barcelona', 08002, true),
	(default, 13, 'Calle Grecia', 1, 'Sevilla', 41005, true),
	(default, 14, 'Calle Suiza', 2, 'Sevilla', 41003, false),
	(default, 15, 'Calle Albania', 3, 'Sevilla', 41001, true),
	(default, 16, 'Calle Argentina', 4, 'Sevilla', 41005, true);

insert into distribuidores values
	(default, 'Seur', 981572390, 10.00),
	(default, 'DHL', 986765643, 15.00),
	(default, 'MRW', 981234567, 12.00),
	(default, 'Correos', 981234567, 5.00),
	(default, 'UPS', 981234567, 20.00);

insert into pedidos values
	(default, '01-01-2020', NULL, NULL, false, 500.00, 4000123456789010, 1, 1, 1),
	(default, '02-02-2020', NULL, NULL, false, 500.00, 1234567894325512, 2, 2, 2),
	(default, '03-03-2020', NULL, NULL, false, 500.00, 1234554321984325, 3, 3, 1),
	(default, '04-04-2020', NULL, NULL, false, 500.00, 1234567891234566, 4, 4, 3),
	(default, '05-05-2020', NULL, NULL, false, 500.00, 1234554321987679, 5, 5, 2),
	(default, '06-06-2020', NULL, NULL, false, 500.00, 1234567891232342, 6, 6, 1),
	(default, '07-07-2020', NULL, NULL, false, 500.00, 1234554321987673, 7, 7, 1),
	(default, '08-08-2020', NULL, NULL, false, 500.00, 9876543219876532, 8, 8, 4),
	(default, '09-09-2020', NULL, NULL, false, 500.00, 1234554321987321, 9, 9, 2),
	(default, '01-10-2020', NULL, NULL, false, 500.00, 1234567891231231, 10, 10, 3),
	(default, '02-11-2020', NULL, NULL, false, 500.00, 1234554321987354, 11, 11, 3),
	(default, '03-12-2020', NULL, NULL, false, 500.00, 1234567891234745, 12, 12, 2),
	(default, '04-13-2020', NULL, NULL, false, 500.00, 8765432198765342, 13, 13, 4),
	(default, '05-14-2020', NULL, NULL, false, 500.00, 9876543219123141, 14, 14, 1),
	(default, '06-15-2020', NULL, NULL, false, 500.00, 8765432198765432, 15, 15, 2),
	(default, '07-16-2020', NULL, NULL, false, 500.00, 9876543219876543, 16, 16, 1);

insert into informar values
	(1, 1, default, 'Muy buen servicio, volvere a pedir'),
	(2, 2, default, 'Increible, me ha encantado'),
	(3, 3, default, 'Mal atencion al cliente, no se lo recomiendo a nadie'),
	(4, 4, default, 'Normal'),
	(5, 5, default, 'Muy mala empresa, no deberia volver a vender'),
	(1, 6, default, 'Muy buen servicio, volvere a pedir'),
	(5, 7, default, 'Increible, me ha encantado'),
	(3, 8, default, 'Mal atencion al cliente, no se lo recomiendo a nadie'),
	(8, 9, default, 'Normal'),
	(3, 10, default, 'Muy mala empresa, no deberia volver a vender'),
	(5, 1, default, 'Muy buen servicio, volvere a pedir'),
	(2, 4, default, 'Increible, me ha encantado'),
	(8, 3, default, 'Mal atencion al cliente, no se lo recomiendo a nadie'),
	(1, 4, default, 'Normal'),
	(5, 2, default, 'Muy mala empresa, no deberia volver a vender');

insert into valorar values
	(1, 1, default, 5, 'Buen Iphone 11', 10),
	(2, 1, default, 3, 'No vale la pena si tienes el 10', 8),
	(3, 1, default, 8, 'Muy buena compra', 3),
	(4, 1, default, 5, 'Normal, nada del otro mundo', 8),
	(5, 1, default, 9, 'Increible, lo han vuelto a hacer', 9),
	(6, 1, default, 6, 'Buena compra, no me arrepiento', 6),
	(7, 1, default, 8, 'Lo recomiendo si te gusta Apple', 7),
	(8, 1, default, 0, 'Mejor android', 0),
	(9, 1, default, 9, 'Lo mejor de lo mejor', 9),
	(10, 1, default, 1, 'Malo y caro', 8);

insert into contener values
	(1, 6, 1),
	(2, 6, 1),
	(3, 6, 1),
	(4, 6, 1),
	(5, 6, 1),
	(6, 6, 1),
	(7, 6, 1),
	(8, 6, 1),
	(9, 6, 1),
	(10, 6, 1),
	(11, 6, 1),
	(12, 6, 1),
	(13, 6, 1),
	(14, 6, 1),
	(15, 6, 1),
	(16, 6, 1);
