DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT EXISTS customer(
	email VARCHAR(64) PRIMARY KEY,
	password VARCHAR(32) NOT NULL,
	rol VARCHAR(8) CHECK (rol='Admin' || rol='Client')
);
DROP TABLE IF EXISTS admin;
CREATE TABLE IF NOT EXISTS admin(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	emailCustomer VARCHAR(64),
	nombre TINYTEXT,
	apellidos TINYTEXT,
	CONSTRAINT FK_admin_customer FOREIGN KEY (emailCustomer) REFERENCES customer(email)
);
DROP TABLE IF EXISTS asistente;
CREATE TABLE IF NOT EXISTS asistente (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    emailCustomer VARCHAR(64),
    nombre TINYTEXT,
    apellidos TINYTEXT,
    especial BOOL,
    fechaNacimiento DATE,
    CONSTRAINT FK_asistente_customer FOREIGN KEY (emailCustomer) REFERENCES customer(email)
);
DROP TABLE IF EXISTS monitor;
CREATE TABLE IF NOT EXISTS monitor(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre TINYTEXT,
    apellidos TINYTEXT,
    especial BOOL
);
DROP TABLE IF EXISTS campamento;
CREATE TABLE IF NOT EXISTS campamento (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    responsable INTEGER,
    responsableEspecial INTEGER,
    inicio DATE,
    fin DATE,
    nivel VARCHAR(11) CHECK(nivel='Adolescente' || nivel='Juvenil' || nivel='Infantil'),
    maxAsistentes INTEGER CHECK(maxAsistentes > 0),
    CONSTRAINT FK_responsable_monitor FOREIGN KEY (responsable) REFERENCES monitor(id),
    CONSTRAINT FK_responsable_especial_monitor FOREIGN KEY (responsableEspecial) REFERENCES monitor(id)
);
DROP TABLE IF EXISTS inscripcion;
CREATE TABLE IF NOT EXISTS inscripcion(
    idParticipante INTEGER,
    idCampamento INTEGER,
    fechaInscripcion DATE,
    precio REAL,
    tipo VARCHAR(8) CHECK(tipo='Completo' || tipo='Parcial'),
    temporalidad VARCHAR(10) CHECK (temporalidad='Temprana' || temporalidad='Tardia'),
    CONSTRAINT PK_inscripcion PRIMARY KEY(idCampamento,idParticipante),
    CONSTRAINT FK_inscripcion_asistente FOREIGN KEY (idParticipante) REFERENCES asistente(id),
    CONSTRAINT FK_inscripcion_campamento FOREIGN KEY (idCampamento) REFERENCES campamento(id)
);
DROP TABLE IF EXISTS actividad;
CREATE TABLE IF NOT EXISTS actividad(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre TINYTEXT,
    nivel VARCHAR(11) CHECK(nivel='Adolescente' || nivel='Juvenil' || nivel='Infantil'),
    turno VARCHAR(16) CHECK(turno='Morning' || turno='Afternoon'),
    maxParticipantes INTEGER CHECK(maxParticipantes > 0),
    maxMonitores INTEGER CHECK(maxMonitores > 0)
);
DROP TABLE IF EXISTS campamento_actividad;
CREATE TABLE IF NOT EXISTS campamento_actividad(
    idActividad INTEGER,
    idCampamento INTEGER,
    CONSTRAINT PK_campamento_actividad PRIMARY KEY(idCampamento,idActividad),
    CONSTRAINT FK_campamento_actividad_campamento FOREIGN KEY (idCampamento) REFERENCES campamento(id),
    CONSTRAINT FK_campamento_actividad_actividad FOREIGN KEY (idActividad) REFERENCES actividad(id)
);
DROP TABLE IF EXISTS actividad_monitor;
CREATE TABLE IF NOT EXISTS actividad_monitor(
    idActividad INTEGER,
    idMonitor INTEGER,
    CONSTRAINT PK_actividad_monitor PRIMARY KEY(idActividad,idMonitor),
    CONSTRAINT FK_actividad_monitor_monitor FOREIGN KEY (idMonitor) REFERENCES monitor(id),
    CONSTRAINT FK_actividad_monitor_actividad FOREIGN KEY (idActividad) REFERENCES actividad(id)
);