CREATE TABLE cartorio_atribuicao (
    cartorio_id INT NOT NULL,
    atribuicao_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (cartorio_id, atribuicao_id),
    CONSTRAINT fk_cartorio FOREIGN KEY (cartorio_id) REFERENCES cartorios(id),
    CONSTRAINT fk_atribuicao FOREIGN KEY (atribuicao_id) REFERENCES atribuicoes(id)
);