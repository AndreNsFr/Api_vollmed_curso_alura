CREATE TABLE consultas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    paciente_id BIGINT,
    medico_id BIGINT,
    data_hora_consulta DATETIME,
    UNIQUE (paciente_id, medico_id, data_hora_consulta),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (medico_id) REFERENCES medicos(id)
);
