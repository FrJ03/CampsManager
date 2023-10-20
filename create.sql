CREATE TABLE IF NOT EXISTS asistentes{
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre TYNYTEXT,
    apellidos TYNYTEXT,
    especial BOOL,
    fechaNacimiento DATE
};

CREATE TABLE IF NOT EXISTS campamentos{
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    inicio DATE,
    fin DATE,
    nivel VARCHAR(11) CHECK(nivel='Adolescente' || nivel='Juvenil' || nivel='Infantil'),
    maxAsistentes INTEGER CHECK(maxAsistentes > 0)
};
CREATE TABLE IF NOT EXISTS inscripciones{
    idParticipante INTEGER,
    idCampamento INTEGER,
    fechaInscripcion DATE,
    precio REAL,
    tipo VARCHAR(8) CHECK(tipo='Completo' || tipo='Parcial'),
    CONSTRAINT PK_inscripciones PRIMARY KEY(idCampamento,idParticipante),
    CONSTRAINT FK_inscripciones_asistentes FOREIGN KEY (idParticipante) REFERENCES asistentes(id),
    CONSTRAINT FK_inscripciones_campamentos FOREIGN KEY (idCampamento) REFERENCES campamentos(id)
};
CREATE TABLE IF NOT EXISTS monitores{
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre TYNYTEXT,
    apellidos TYNYTEXT,
    especial BOOL,
};
CREATE TABLE IF NOT EXISTS campamentos-monitores{
    idCampamento INTEGER,
    idMonitor INTEGER,
    CONSTRAINT PK_campamentos-monitores PRIMARY KEY(idCampamento,idMonitor),
    CONSTRAINT FK_campamentos-monitores_monitores FOREIGN KEY (idMonitor) REFERENCES monitores(id),
    CONSTRAINT FK_campamentos-monitores_campamentos FOREIGN KEY (idCampamento) REFERENCES campamentos(id)
};
CREATE TABLE IF NOT EXISTS actividades{
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre TYNYTEXT,
    nivel VARCHAR(11) CHECK(nivel='Adolescente' || nivel='Juvenil' || nivel='Infantil'),
    turno VARCHAR(6) CHECK(turno='Mañana' || turno='Tarde'),
    maxParticipantes INTEGER CHECK(maxParticipantes > 0),
    maxMonitores INTEGER CHECK(maxMonitores > 0)
};
CREATE TABLE IF NOT EXISTS actividades-monitores{
    idActividad INTEGER,
    idMonitor INTEGER,
    CONSTRAINT PK_actividades-monitores PRIMARY KEY(idActividad,idMonitor),
    CONSTRAINT FK_actividades-monitores_monitores FOREIGN KEY (idMonitor) REFERENCES monitores(id),
    CONSTRAINT FK_actividades-monitores_actividades FOREIGN KEY (idActividad) REFERENCES actividades(id)
};