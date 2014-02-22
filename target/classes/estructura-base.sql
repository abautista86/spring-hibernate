CREATE TABLE usuario (usuario_id VARCHAR(50) NULL,password VARCHAR(50) NULL ,nombre VARCHAR(50) NOT NULL,PRIMARY KEY (usuario_id));

INSERT INTO usuario(usuario_id, nombre, password) VALUES ('1', 'Juan Perez', '123');