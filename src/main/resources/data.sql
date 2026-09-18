INSERT INTO categoria (nome) VALUES
                                 ('Eletrônicos'),
                                 ('Informática'),
                                 ('Roupas'),
                                 ('Livros'),
                                 ('Casa');

INSERT INTO cliente (nome, email, cpf) VALUES
                                           ('João Silva', 'joao@email.com', '12345678901'),
                                           ('Maria Souza', 'maria@email.com', '23456789012'),
                                           ('Carlos Santos', 'carlos@email.com', '34567890123'),
                                           ('Ana Oliveira', 'ana@email.com', '45678901234'),
                                           ('Pedro Lima', 'pedro@email.com', '56789012345');
/*
INSERT INTO users (email,senha,role,cliente_id) VALUES
                                                    ('joao@email.com', "123456","CLIENTE", 1),
                                                    ('maria@email.com',"123456", "CLIENTE", 2),
                                                    ('carlos@email.com',"123456", "CLIENTE", 3),
                                                    ('ana@email.com',"123456", "CLIENTE", 4),
                                                    ('pedro@email.com',"123456", "ADMIN", 5);

 */



INSERT INTO produto
(nome, preco, estoque, categoria_id, descricao, ativo)
VALUES
    ('Notebook', 3500.00, 10, 1, 'Notebook para trabalho e estudos', true),
    ('Mouse', 80.00, 50, 2, 'Mouse sem fio', true),
    ('Camiseta', 60.00, 30, 3, 'Camiseta de algodão', true),
    ('Livro Java', 120.00, 15, 4, 'Livro para aprendizado de Java', true),
    ('Mesa', 500.00, 8, 5, 'Mesa para escritório', true);

INSERT INTO pedido (cliente_id, status, data) VALUES
                                                  (1, 'PENDENTE', NOW()),
                                                  (2, 'PENDENTE', NOW()),
                                                  (3, 'PENDENTE', NOW()),
                                                  (4, 'PENDENTE', NOW()),
                                                  (5, 'PENDENTE', NOW());

INSERT INTO item_pedido
(quantidade, preco_unitario, produto_id, pedido_id)
VALUES
    (1, 3500.00, 1, 1),
    (2, 80.00, 2, 1),
    (1, 120.00, 4, 1),

    (3, 60.00, 3, 2),
    (1, 80.00, 2, 2),
    (1, 500.00, 5, 2),

    (2, 3500.00, 1, 3),
    (1, 60.00, 3, 3),
    (2, 120.00, 4, 3),
    (1, 80.00, 2, 3),

    (1, 500.00, 5, 4),
    (2, 60.00, 3, 4),
    (1, 3500.00, 1, 4),
    (3, 80.00, 2, 4),

    (2, 120.00, 4, 5),
    (1, 500.00, 5, 5),
    (4, 60.00, 3, 5),
    (1, 3500.00, 1, 5),
    (2, 80.00, 2, 5),
    (1, 120.00, 4, 5);