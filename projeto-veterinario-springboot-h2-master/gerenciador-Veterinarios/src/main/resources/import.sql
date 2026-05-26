-- tb_veterinario (manter para testes id 1 e 2)
INSERT INTO tb_veterinario (nome, email, especialidade, salario) VALUES('Conceição Evaristo', 'conceicao@gmail.com', 'pequenos', 3500.0);
INSERT INTO tb_veterinario (nome, email, especialidade, salario) VALUES('Erica Queiroz Pinto', 'erica@gmail.com', 'grandes', 4500.0);

-- tb_funcionario
INSERT INTO tb_funcionario (nome, cargo, salario, em_ferias) VALUES ('Ana', 'Veterinario(a)', 10000.0, false);
INSERT INTO tb_funcionario (nome, cargo, salario, em_ferias) VALUES ('Bruno', 'Técnico em Veterinária', 4000.0, false);
INSERT INTO tb_funcionario (nome, cargo, salario, em_ferias) VALUES ('Carlos', 'Recepcionista', 2500.0, true);

-- tb_servico
INSERT INTO tb_servico (nome, valor, tempo_minutos, disponivel) VALUES ('Consulta', 150.0, 30, true);
INSERT INTO tb_servico (nome, valor, tempo_minutos, disponivel) VALUES ('Vacina', 80.0, 15, true);
INSERT INTO tb_servico (nome, valor, tempo_minutos, disponivel) VALUES ('Cirurgia', 500.0, 120, false);

-- tb_animal
INSERT INTO tb_animal (nome, especie, idade, internado) VALUES ('Rex', 'cachorro', 5, true);
INSERT INTO tb_animal (nome, especie, idade, internado) VALUES ('Mimi', 'gato', 3, false);
INSERT INTO tb_animal (nome, especie, idade, internado) VALUES ('Piu', 'pássaro', 2, false);

-- tb_produto
INSERT INTO tb_produto (descricao, preco, quantidade_estoque, ativo) VALUES ('Ração premium 15kg', 89.90, 50, true);
INSERT INTO tb_produto (descricao, preco, quantidade_estoque, ativo) VALUES ('Antipulgas spray', 45.50, 30, true);
INSERT INTO tb_produto (descricao, preco, quantidade_estoque, ativo) VALUES ('Coleira antiga', 25.00, 0, false);
