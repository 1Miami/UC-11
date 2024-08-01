-- Criação do banco de dados
CREATE DATABASE sosrs;

-- Seleciona o banco de dados
USE sosrs;

-- Criação da tabela `doencas`
CREATE TABLE doencas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    sintomas TEXT,
    tratamento VARCHAR(255),
    informacoes TEXT,
    picada BOOLEAN
);

SELECT * FROM doencas;
