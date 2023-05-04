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

-- Transaccion para obtener los pedidos de un cliente, asi como los datos de las entidades relacionadas
begin;
	
	select * from pedidos as p 
	inner join distribuidores as d on d.id = p.iddistribuidor 
	inner join direcciones as dir on dir.id = p.iddireccion 
	inner join metodospago as m on m.numerotarjeta = p.numtarjeta 
	where p.idcliente = 1;
	
end;

-- Transaccion para obtener los productos a la venta, asi como los datos de las entidades relacionadas
begin;
	
	select * from productos as p 
	inner join empresasvendedoras as e on e.id = p.idempresa 
	inner join almacenes as a on a.id = p.idalmacen 
	where p.nombre like '%a%' and existencias != 0;
	
end;