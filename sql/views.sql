-- Vista que nos permite saber el numero de productos que tiene cada empresa vendedora en venta
create or replace view empresasProductos as
	select e.*, count(p.*) from empresasVendedoras as e
	right join productos as p on p.idEmpresa = e.id;
	group by e.id

-- Vista que nos permite saber el numero de productos que tiene cada almacen
create or replace view productosAlmacen as
	select a.*, sum(p.existencias) from almacenes as a
	right join productos as p on p.idalmacen = a.id 
	group by a.id

-- Vista que nos permite saber la cantidad de metodos de pago que tiene cada cliente
create or replace view clientesMetodosPago as
	select c.*, count(m.*) from clientes as c
	right join metodosPago as m on m.idCliente = c.id
	group by c.id

-- Vista que nos permite saber el dinero que ha gastado cada cliente
create or replace view clientesDinero as
	select c.*, sum(p.preciototal) from clientes as c
	right join pedidos as p on p.idcliente = c.id
	group by c.id

-- Vista que nos permite saber el dinero que se gasta de media cada cliente en cada pedido
create or replace view clientesDineroMedio as
	select c.*, avg(p.preciototal) from clientes as c
	right join pedidos as p on p.idcliente = c.id
	group by c.id

-- Vista que nos permite saber el numero de pedidos que hacen los clientes con y sin prime
create or replace view clientesPedidos as
	select c.*, count(p.*), avg(p.preciototal) from clientes as c
	right join pedidos as p on p.idcliente = c.id
	group by c.prime

-- Vista que nos permite saber el tiempo en dias que tarda de media cada distribuidor en enviar los pedidos
create or replace view distribuidoresTiempoEnvio as
	select d.*, avg(p.fechallegada - p.fechasalida) from distribuidores as d
	inner join pedidos as p on p.iddistribuidor = d.id
	group by d.id

-- Vista que nos permite saber los productos que mas se venden
create or replace view productosMasVendidos as
	select p.*, sum(c.cantidad) as suma from contener as c
	inner join productos as p on p.id = c.idproducto 
	group by p.id 
	order by suma desc

-- Vista que nos permite saber los productos con mejores valoraciones
create or replace view productosValoraciones as
	select p.*, avg(v.nota) as nota from valorar as v
	inner join productos as p on p.id = v.idproducto
	group by p.id
	order by nota desc

-- Vista que nos permite ver los distribuidores que segun sus precios
create or replace view distribuidoresPrecio as
	select d.* from distribuidores as d
	order by d.costeenvio desc

-- Vista que nos permite ver los distribuidores segun el numero de envios que han hecho
create or replace view distribuidoresPedidos as
	select d.*, count(p.*) from distribuidores as d
	right join pedidos as p on p.iddistribuidor = d.id
	group by d.id

-- Vista que nos permite ver los distribuidores y el numero de pedidos completados y no completados
create or replace view distribuidoresPedidosEstado as
	select 
		d.*,
		(select count(*) from pedidos as p where p.iddistribuidor = d.id and p.completado = true) as completado,
		(select count(*) from pedidos as p where p.iddistribuidor = d.id and p.completado = false) as nocompletado
	from distribuidores as d
	group by d.id