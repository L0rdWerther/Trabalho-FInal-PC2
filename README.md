# Projeto de Gerenciamento de Hospedagem

Este projeto é uma aplicação Java para gerenciar clientes, chalés e hospedagens. Ele utiliza um banco de dados PostgreSQL para armazenar as informações.

## Estrutura do Projeto

- `src/MainApp.java`: Classe principal que inicializa a aplicação.
- `src/ClienteForm.java`: Formulário para cadastro de clientes.
- `src/ChaleForm.java`: Formulário para cadastro de chalés.
- `src/HospedagemForm.java`: Formulário para cadastro de hospedagens.
- `src/ClienteTable.java`: Tabela para exibir os clientes cadastrados.
- `src/ChaleTable.java`: Tabela para exibir os chalés cadastrados.
- `src/HospedagemTable.java`: Tabela para exibir as hospedagens cadastradas.
- `src/Cliente.java`: Classe modelo para clientes.
- `src/Chale.java`: Classe modelo para chalés.
- `src/Hospedagem.java`: Classe modelo para hospedagens.
- `src/DatabaseUtil.java`: Classe utilitária para conexão com o banco de dados.
- `schema.sql`: Script SQL para criação das tabelas no banco de dados.

## Requisitos

- Java 8 ou superior
- PostgreSQL
- IDE Java

## Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL.
2. Execute o script `schema.sql` para criar as tabelas necessárias.

## Configuração do Projeto

1. Clone o repositório:
    ```sh
    git clone https://github.com/L0rdWerther/gerenciamento-hospedagem.git
    ```
2. Abra o projeto na sua IDE Java.
3. Configure as credenciais do banco de dados no arquivo `src/DatabaseUtil.java`:
    ```java
    private static final String URL = "jdbc:postgresql://localhost:5432/seu_banco_de_dados";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";
    ```

## Executando a Aplicação

1. Compile e execute a classe `src/MainApp.java`.
2. A interface gráfica será exibida com opções para gerenciar clientes, chalés e hospedagens.

## Funcionalidades

- **Clientes**: Cadastro, visualização e atualização de clientes.
- **Chalés**: Cadastro, visualização e atualização de chalés.
- **Hospedagens**: Cadastro, visualização e atualização de hospedagens.

## Estrutura das Tabelas

### Cliente
```sql
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
```

### Chale
```sql
CREATE TABLE Chale (
    codChale SERIAL PRIMARY KEY,
    localizacao VARCHAR(255) NOT NULL,
    capacidade INT NOT NULL,
    valorAltaEstacao DECIMAL(10, 2) NOT NULL,
    valorBaixaEstacao DECIMAL(10, 2) NOT NULL
);
```

### Hospedagem
```sql
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
```