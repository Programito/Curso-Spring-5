/* Populate tables */
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Paco', 'Jose', 'pacojose@gmail.com', '2017-08-28','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-28','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Laia', 'Martin', 'laia.martin@gmail.com', '2017-07-05','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Antonio', 'Gallardo', 'asdas@gmail.com', '2014-05-28','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Nacho', 'Pozo', 'nachopozo@gmail.com', '2016-02-17','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Lidia', 'Gimenez', 'lidiagimenez@gmail.com', '2012-05-16','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Neus', 'Cruz', 'neus30@gmail.com', '2004-05-28','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Joan', 'Gallo', 'gallo123@hotmail.com', '2003-01-07','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Elvira', 'Vilaseca', 'elviravilaseca@gmail.com', '2008-04-20','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Alan', 'Shearer', 'shearer31@gmail.com', '2016-04-12','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Michael', 'Bryant', 'michaelbryant@gmail.com', '2013-03-16','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Shaquille', 'Jordan', 'jordan23@gmail.com', '2011-02-03','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Noelia', 'Perez', 'noeliaperez21@gmail.com', '2011-03-03','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('David', 'Gutierrez', 'guti42@gmail.com', '2003-01-20','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Raul', 'Ruiz', 'asdfdsas@gmail.com', '2010-10-28','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Diana', 'Gil', 'gil2014@gmail.com', '2014-02-28','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Andres', 'Montes', 'pinchodemerluza@gmail.com', '2014-03-28','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Damiel', 'Iglesias', 'igleisasdamiel@gmail.com', '2010-05-15','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Akira', 'Nagai', 'nagai21@gmail.com', '2016-01-03','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Alicia', 'Santos', 'santos89@gmail.com', '2011-05-21','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Isabel', 'Gallardo', 'isagallardo@gmail.com', '2011-09-28','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Jacob', 'Madsen', 'lostmadsen@gmail.com', '2014-06-29','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Bertha', 'Holzner', 'berthaholzner@gmail.com', '2011-04-27','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Ambrosio', 'Rodriguez', 'ambrosio60@gmail.com', '2012-02-28','');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Carla', 'Bosh', 'carlabosh@gmail.com', '2014-05-16','');

/* Populate tabla productos */
INSERT INTO productos (nombre,precio,create_at) VALUES ("Samsung UE55MU6125 55 LED UltraHD 4K",549,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ("Acer Aspire GX-781 Intel Core i5-7400",699,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ("Silla de oficina Columbia",39.99,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ("LG 43UK6300PLB 43 LED UltraHD 4K",389,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ("Samsung Galaxy A6 Plus 3",249,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ("Samsung Galaxy J3 2017 Negro",129,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ("LG 49UK6300MLB 49 LED UltraHD 4K",448,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ("Benq TW533 3300 Lúmenes WXGA ANSI DLP 3D Blanco",339,NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id,create_at) VALUES ('Factura de equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,1);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (2,1,4);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,5);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,7);


INSERT INTO facturas (descripcion, observacion, cliente_id,create_at) VALUES ('Factura del departamento', null, 1, NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (3,2,6);
