CREATE TABLE IF NOT EXISTS asistente (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre TINYTEXT,
    apellidos TINYTEXT,
    especial BOOL,
    fechaNacimiento DATE
);

CREATE TABLE IF NOT EXISTS campamento (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    inicio DATE,
    fin DATE,
    nivel VARCHAR(11) CHECK(nivel='Adolescente' || nivel='Juvenil' || nivel='Infantil'),
    maxAsistentes INTEGER CHECK(maxAsistentes > 0)
);
CREATE TABLE IF NOT EXISTS inscripcion(
    idParticipante INTEGER,
    idCampamento INTEGER,
    fechaInscripcion DATE,
    precio REAL,
    tipo VARCHAR(8) CHECK(tipo='Completo' || tipo='Parcial'),
    CONSTRAINT PK_inscripcion PRIMARY KEY(idCampamento,idParticipante),
    CONSTRAINT FK_inscripcion_asistente FOREIGN KEY (idParticipante) REFERENCES asistente(id),
    CONSTRAINT FK_inscripcion_campamento FOREIGN KEY (idCampamento) REFERENCES campamento(id)
);
CREATE TABLE IF NOT EXISTS monitor(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre TINYTEXT,
    apellidos TINYTEXT,
    especial BOOL
);
CREATE TABLE IF NOT EXISTS campamento_monitor(
    idCampamento INTEGER,
    idMonitor INTEGER,
    CONSTRAINT PK_campamento_monitor PRIMARY KEY(idCampamento,idMonitor),
    CONSTRAINT FK_campamento_monitor_monitor FOREIGN KEY (idMonitor) REFERENCES monitor(id),
    CONSTRAINT FK_campamento_monitor_campamento FOREIGN KEY (idCampamento) REFERENCES campamento(id)
);
CREATE TABLE IF NOT EXISTS actividad(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre TINYTEXT,
    nivel VARCHAR(11) CHECK(nivel='Adolescente' || nivel='Juvenil' || nivel='Infantil'),
    turno VARCHAR(6) CHECK(turno='Mañana' || turno='Tarde'),
    maxParticipantes INTEGER CHECK(maxParticipantes > 0),
    maxMonitores INTEGER CHECK(maxMonitores > 0)
);
CREATE TABLE IF NOT EXISTS actividad_monitor(
    idActividad INTEGER,
    idMonitor INTEGER,
    CONSTRAINT PK_actividad_monitor PRIMARY KEY(idActividad,idMonitor),
    CONSTRAINT FK_actividad_monitor_monitor FOREIGN KEY (idMonitor) REFERENCES monitor(id),
    CONSTRAINT FK_actividad_monitor_actividad FOREIGN KEY (idActividad) REFERENCES actividad(id)
);