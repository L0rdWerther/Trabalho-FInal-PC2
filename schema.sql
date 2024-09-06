-- Tabela Cliente
CREATE TABLE Cliente (
                         codCliente SERIAL PRIMARY KEY,
                         nomeCliente VARCHAR(100) NOT NULL,
                         rgCliente VARCHAR(9) NOT NULL,
                         enderecoCliente VARCHAR(255) NOT NULL,
                         bairroCliente VARCHAR(100) NOT NULL,
                         cidadeCliente VARCHAR(100) NOT NULL,
                         estadoCliente VARCHAR(2) NOT NULL,
                         CEPCliente VARCHAR(10) NOT NULL,
                         nascimentoCliente DATE NOT NULL
);

-- Tabela Chale
CREATE TABLE Chale (
                       codChale SERIAL PRIMARY KEY,
                       localizacao VARCHAR(255) NOT NULL,
                       capacidade INT NOT NULL,
                       valorAltaEstacao DECIMAL(10, 2) NOT NULL,
                       valorBaixaEstacao DECIMAL(10, 2) NOT NULL
);

-- Tabela Hospedagem
CREATE TABLE Hospedagem (
                            codHospedagem SERIAL PRIMARY KEY,
                            codChale INT NOT NULL,
                            estado VARCHAR(255) NOT NULL,
                            dataInicio DATE NOT NULL,
                            dataFim DATE,
                            qtdPessoas INT NOT NULL,
                            desconto DECIMAL(5, 2),
                            valorFinal DECIMAL(10, 2),
                            codCliente INT,
                            FOREIGN KEY (codChale) REFERENCES Chale(codChale),
                            FOREIGN KEY (codCliente) REFERENCES Cliente(codCliente)
);