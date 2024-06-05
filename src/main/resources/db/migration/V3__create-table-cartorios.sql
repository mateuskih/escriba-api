CREATE TABLE cartorios (
    id SERIAL NOT NULL,
    nome VARCHAR(150) NOT NULL UNIQUE,
    observacao VARCHAR(250),
    situacao_id VARCHAR(20),
    PRIMARY KEY (id),
    CONSTRAINT fk_situacao FOREIGN KEY (situacao_id) REFERENCES situacoes(id)
);