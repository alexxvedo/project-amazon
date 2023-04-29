-- Transacion para insertar una nueva direccion de envio y asociarla a un cliente
-- Se comprueba que nos exista la misma direccion en la tabla direcciones y si no existe se inserta
-- Una vez insertada la direccion se inserta en la tabla residir la relacion entre el cliente y la direccion

do $$

	declare
		direccion_id int;

	begin

		select id into direccion_id from direcciones where calle = 'Vista alegre' and numero = 15 and ciudad = 'Santiago de Compostela' and codigopostal = 15705;

		if not found then

			insert into direcciones values (default, 'Vista alegre', 15, 'Santiago de Compostela', 15705, true);

			direccion_id := currval('direcciones_id_seq');

		end if;

		insert into residir values (1, direccion_id);

	end

$$

-- Transacion para crear un nuevo pedido y asociarlo a un cliente asi como registrar los productos que contiene
-- Comprobamos que exista el producto y que haya suficiente cantidad para poder realizar el pedido

do $$

	declare
		pedido_id int;
		cantidad int;

	begin

		select existencias into cantidad from productos where id = 1 and existencias >= 1;

		if not found then

			raise exception 'No existe el producto o no hay suficiente cantidad';

		end if;

		insert into pedidos values (default, '01-01-2023', NULL, NULL, false, 500.00, 4000123456789010, 1, 1, 1);

		pedido_id := currval('pedidos_id_seq');

		insert into contener values (pedido_id, 6, 1);
		update productos set existencias = existencias - 1 where id = 1;

	end

$$

-- Transacion para actualizar la valoracion de un producto por parte de un cliente cuando ya ha sido valorado si no se crea una nueva valoracion
-- Nos aseguramos de que el producto ha sido comprado por el cliente

do $$

	declare
		total float;
		valoracion numeric(2, 0);

	begin

		select p.preciototal into total from pedidos as p right join contener as c on p.id = c.idPedido where p.idcliente = 1 and c.idProducto = 6 and p.completado = true;

		if not found then

			raise exception 'No existe el producto o no ha sido comprado por el cliente';

		end if;

		select nota into valoracion from valorar where idCliente = 1 and idProducto = 1;

		if found then

			update valorar set nota = 5, comentario = 'Muy buen producto', estadoLLegada = 1 where idCliente = 1 and idProducto = 1;

		else

			insert into valorar values (1, 1, default, 5, 'Muy buen producto', 1);

		end if;

	end

$$

-- Tranasacion para realizar un pedido solo cuando el medio de pago seleccionado este activo

do $$

	declare
		cliente int;

	begin

		select idcliente into cliente from metodosPago where numerotarjeta = 4000123456789010 and activa = true;

		if not found then

			raise exception 'El medio de pago seleccionado no esta activo';

		end if;

		insert into pedidos values (default, '01-01-2020', NULL, NULL, false, 500.00, 4000123456789010, 1, 1, 1);

	end

$$

-- Transacion para eliminar un almacen y todos los productos que contiene moverlos a otro almacen

do $$

	declare
		nombreCiudad1 text;
		nombreCiudad2 text;

	begin

		select ciudad into nombreCiudad1 from almacenes where id = 1;

		if not found then

			raise exception 'No existe el almacen a eliminar';

		end if;

		select ciudad into nombreCiudad2 from almacenes where id = 2;

		if not found then

			raise exception 'No existe el almacen donde mover los productos';

		end if;

		update productos set idAlmacen = 2 where idAlmacen = 1;

		delete from almacenes where id = 1;

	end

$$

-- Transacion para que cuando se elimine un distribuidor se asignen sus pedidos a otro distribuidor

do $$

	declare
		telefonoDist1 numeric(9, 0);
		telefonoDist2 numeric(9, 0);

	begin

		select telefono into telefonoDist1 from distribuidores where id = 1;

		if not found then

			raise exception 'No existe el distribuidor a eliminar';

		end if;

		select telefono into telefonoDist2 from distribuidores where id = 2;

		if not found then

			raise exception 'No existe el distribuidor al que asignar los pedidos';

		end if;

		update pedidos set idDistribuidor = 2 where idDistribuidor = 1;

		delete from distribuidores where id = 1;

	end

$$