INSERT INTO clientes (id, nombre, apellido, forma_pago, creado_en, editado_en) VALUES (1, 'Juan', 'Perez', 'paypal', '2023-11-22 12:47:02.000000', NULL),(2, 'Arkaitz', 'Artaraz', 'paypal', NULL, NULL),(3,'Cristian','Guerrero', 'credito', NULL,NULL),(4, 'Arantza','Lorenzo', 'debito', NULL, NULL);
INSERT INTO alumnos (id, nombre, apellido) VALUES (1, 'Johana', 'Doe');
INSERT INTO alumnos (id, nombre, apellido) VALUES (2, 'Pepe', 'Gon');
INSERT INTO cursos (id, titulo, profesor) VALUES (1, 'Curso Spring', 'Andres');
INSERT INTO cursos (id, titulo, profesor) VALUES (2, 'Curso Java EE 9', 'Andres');
INSERT INTO direcciones(calle, numero) VALUES ('vaticano', 123);
INSERT INTO direcciones(calle, numero) VALUES ('colon', 456);
INSERT INTO tbl_clientes_direcciones(id_cliente, id_direccion) VALUES (1,1);
INSERT INTO tbl_clientes_direcciones(id_cliente, id_direccion) VALUES (1,2);
INSERT INTO clientes_detalles (prime, puntos_acumulados, cliente_detalle_id) VALUES (1, 8000, 1);
INSERT INTO tbl_alumnos_cursos (alumno_id, curso_id) VALUES (1,1);
INSERT INTO tbl_alumnos_cursos (alumno_id, curso_id) VALUES (1,2);
INSERT INTO facturas (descripcion, total, id_cliente) VALUES ('oficina', 4000, 1);
INSERT INTO facturas (descripcion, total, id_cliente) VALUES ('casa', 2000, 1);
INSERT INTO facturas (descripcion, total, id_cliente) VALUES ('deporte', 3000, 1);
INSERT INTO facturas (descripcion, total, id_cliente) VALUES ('computacion', 7000, 2);