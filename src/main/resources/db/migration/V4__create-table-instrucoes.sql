CREATE TABLE instrucoes
(

    id            BIGINT      NOT NULL AUTO_INCREMENT,
    aluno_id      BIGINT      NOT NULL,
    instrutor_id  BIGINT      NOT NULL,
    data          DATETIME    NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_instrucao_aluno_id FOREIGN KEY (aluno_id) REFERENCES alunos (id),
    CONSTRAINT fk_instrucao_instrutor_id FOREIGN KEY (instrutor_id) REFERENCES instrutores (id)
);