INSERT INTO Usuario (nome_completo, funcao, email, senha) VALUES ('Ana Clara Silveira Souza', 'ADMIN', 'ana.souza@example.com', 'Senha@12');
INSERT INTO Usuario (nome_completo, funcao, email, senha) VALUES ( 'Bruno Henrique Costa Lima', 'PROFISSIONAL', 'bruno.lima@example.com', 'Senha#13');
INSERT INTO Usuario (nome_completo, funcao, email, senha) VALUES ( 'Joana da Silva', 'VOLUNTARIO', 'joana.silva@email.com', 'Senha@1');
INSERT INTO Usuario (nome_completo, funcao, email, senha) VALUES ( 'João Pedro Diniz', 'PROFISSIONAL', 'jpdiniz@email.com', 'Senha@10' );

-- INSERT INTO Triagem (id_triagem, prioridade, estado_saude, classificacao, data_triagem, id_usuario) VALUES (1, 'ALTA', 'Grave, necessita de cirurgia imediata', 1, TO_DATE('2024-06-01', 'YYYY-MM-DD'), 1);
-- INSERT INTO Triagem (id_triagem, prioridade, estado_saude, classificacao, data_triagem, id_usuario) VALUES (2, 'MEDIA', 'Estável, mas com fratura no braço', 2, TO_DATE('2024-06-02', 'YYYY-MM-DD'), 2);
-- INSERT INTO Triagem (id_triagem, prioridade, estado_saude, classificacao, data_triagem, id_usuario) VALUES (3, 'BAIXA', 'Gripe leve', 1, TO_DATE('2024-06-01', 'YYYY-MM-DD'), 3);
-- INSERT INTO Triagem (id_triagem, prioridade, estado_saude, classificacao, data_triagem, id_usuario) VALUES (4, 'ALTA', 'Fratura exposta', 5, TO_DATE('2024-06-01', 'YYYY-MM-DD'), 4);

INSERT INTO Triagem (id_triagem, prioridade, estado_saude, classificacao, data_triagem, id_usuario) VALUES (1, 'ALTA', 'Grave, necessita de cirurgia imediata', 1, '2024-06-01', 1);
INSERT INTO Triagem (id_triagem, prioridade, estado_saude, classificacao, data_triagem, id_usuario) VALUES (2, 'MEDIA', 'Estável, mas com fratura no braço', 2, '2024-06-02', 2);
INSERT INTO Triagem (id_triagem, prioridade, estado_saude, classificacao, data_triagem, id_usuario) VALUES (3, 'BAIXA', 'Gripe leve', 1, '2024-06-01', 3);
INSERT INTO Triagem (id_triagem, prioridade, estado_saude, classificacao, data_triagem, id_usuario) VALUES (4, 'ALTA', 'Fratura exposta', 5, '2024-06-01', 4);

INSERT INTO Vitima (id_vitima, nome, idade, sexo, id_triagem) VALUES (1, 'Carlos Eduardo Santos', 45, 'M', 1);
INSERT INTO Vitima (id_vitima, nome, idade, sexo, id_triagem) VALUES (2, 'Diana Rodrigues Pereira', 28, 'F', 2);
INSERT INTO Vitima (id_vitima, nome, idade, sexo, id_triagem) VALUES (3,'Bruno Silva Almeida', 25, 'M', 3);
INSERT INTO Vitima (id_vitima, nome, idade, sexo, id_triagem) VALUES (4,'Juliana Alves Junior', 60, 'F', 4 );