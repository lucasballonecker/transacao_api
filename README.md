
# Transação API

Este projeto é uma API REST para gerenciar transações e calcular estatísticas das transações realizadas nos últimos 60 segundos. A API foi desenvolvida com Java e Spring Boot.




## Variáveis de Ambiente

Para rodar esta aplicação, você precisa de:

Java: JDK 24 ou superior.
Maven: Versão 3.9.9 ou superior.
Git: Para clonar o repositório.
Docker (opcional): Caso queira rodar a aplicação em um container.




##  Como Configurar o Projeto

1. Clone o Repositório

2. Compile o Projeto

```bash
 mvn clean install
```

3. Execute o Projeto

```bash
mvn spring-boot:run
```
4. Como Rodar em um Container (Opcional)

4.1. Crie a Imagem Docker
Certifique-se de que o Docker está instalado e execute:

```bash
docker build -t transacoes_api 
```

4.2. Execute o Container

```bash
docker run -p 8080:8080 transacoes_api
```

## Documentação da API

#### Receber Transações

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `Double` | **Obrigatório**. O valor da transação 
| `dataHora` | `OffsetDateTime` | **Obrigatório**. O horário que a transação ocorreu

#### Limpar Transações

```http
  DELETE /transacao
```

#### Calcular Estatísticas

```http
  GET /estatistica
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `intervaloBusca` | `Integer` | **Não Obrigatório**. O padrão default é 60s  |


