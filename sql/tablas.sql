create table empresasVendedoras (
	id serial primary key,
	nombre varchar(50) not null,
	fechaAsociacion date not null default now()
);

create table almacenes (
	id serial primary key,
	calle varchar(125) not null,
	numero integer not null,
	ciudad varchar(50) not null,
	codigoPostal numeric(5, 0) not null
);

create table productos (
	id serial not null primary key,
	idEmpresa serial not null,
	idAlmacen serial not null,
	nombre varchar(250) not null,
	descripcion text,
	precio numeric(10,2) not null,
	existencias integer not null default 0,
	constraint fk_empresa foreign key (idEmpresa) references empresasVendedoras(id) on delete cascade on update cascade,
	constraint fk_almacen foreign key (idAlmacen) references almacenes(id) on delete cascade on update cascade
);

create table clientes (
	id serial primary key,
	nombre varchar(100) not null,
	telefono numeric(9, 0) not null,
	fechaNacimiento date not null,
	prime boolean not null default false,
	email varchar(50) not null,
	contrasena varchar(50) not null
);

create table metodosPago (
	numeroTarjeta numeric(16, 0) primary key,
	idCliente serial not null,
	activa boolean not null default true,
	preferida boolean not null default false,
	constraint fk_cliente foreign key (idCliente) references clientes(id) on delete cascade on update cascade
);

create table direcciones (
	id serial primary key,
	calle varchar(125) not null,
	numero integer not null,
	ciudad varchar(50) not null,
	codigoPostal numeric(5, 0) not null,
	preferida boolean not null default false
);

create table distribuidores (
	id serial primary key,
	nombre varchar(50) not null,
	telefono numeric(9, 0) not null,
	costeEnvio numeric(10,2) not null
);

create table pedidos (
	id serial primary key,
	fechaPedido date not null default now(),
	fechaSalida date,
	fechaLlegada date,
	completado boolean not null default false,
	precioTotal numeric(10,2) not null,
	numTarjeta numeric(16, 0),
	idDireccion int,
	idCliente int,
	idDistribuidor int,
	constraint fk_numTarjeta foreign key (numTarjeta) references metodosPago(numeroTarjeta) on delete set null on update cascade,
	constraint fk_direccion foreign key (idDireccion) references direcciones(id) on delete set null on update cascade,
	constraint fk_cliente foreign key (idCliente) references clientes(id) on delete set null on update cascade,
	constraint fk_distribuidor foreign key (idDistribuidor) references distribuidores(id) on delete set null on update cascade
);

create table informar (
	idCliente int,
	idEmpresa serial not null,
	fecha timestamp not null default now(),
	descripcion text not null,
	primary key (fecha, idCliente, idEmpresa),
	constraint fk_cliente foreign key (idCliente) references clientes(id) on delete set null on update cascade,
	constraint fk_empresa foreign key (idEmpresa) references empresasVendedoras(id) on delete cascade on update cascade
);

create table valorar (
	idCliente int,
	idProducto serial not null,
	fecha timestamp not null default now(),
	nota numeric(2, 0) not null,
	comentario text,
	estadoLLegada numeric(2, 0),
	primary key (fecha, idCliente, idProducto),
	constraint fk_cliente foreign key (idCliente) references clientes(id) on delete set null on update cascade,
	constraint fk_producto foreign key (idProducto) references productos(id) on delete cascade on update cascade
);

create table contener (
	idPedido serial not null,
	idProducto serial not null,
	cantidad integer not null default 1,
	primary key (idPedido, idProducto),
	constraint fk_pedido foreign key (idPedido) references pedidos(id) on delete cascade on update cascade,
	constraint fk_producto foreign key (idProducto) references productos(id) on delete cascade on update cascade
);

create table residir (
	idCliente serial not null,
	idDireccion serial not null,
	primary key (idCliente, idDireccion),
	constraint fk_cliente foreign key (idCliente) references clientes(id) on delete cascade on update cascade,
	constraint fk_direccion foreign key (idDireccion) references direcciones(id) on delete cascade on update cascade
);