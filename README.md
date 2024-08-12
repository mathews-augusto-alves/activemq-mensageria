# Projeto de Mensageria

## Descrição do Projeto

O projeto de Mensageria é uma aplicação desenvolvida com o objetivo de processar ordens de compra e enviar notificações utilizando mensageria. Ele faz uso de diversas validações para garantir a integridade dos dados processados e utiliza padrões de design e princípios de programação para garantir a escalabilidade, flexibilidade e manutenção do código.

O projeto implementa uma abordagem baseada na Clean Architecture e segue princípios SOLID, assegurando que as responsabilidades estão bem definidas e que a aplicação é facilmente extensível e testável.

## Objetivo

O objetivo principal deste projeto é simular o processamento de ordens de compra usando mensageria, realizar validações diversas (como validação de e-mail e ID de ordem de forma escalável), e simular o envio de notificações a partir de leitura de mensagens em tópicos. A aplicação é configurada para garantir que todas as ordens sejam validadas antes de serem processadas, e as mensagens de notificação sejam enviadas de forma confiável.

O intuíto não é ter uma aplicação funcional com CRUD, envios reais de notificação e etc. É apenas fornecer uma estrutura minima para uma ideia escalável de aplicação.

## Stack Tecnológica

- **Java 17**: Linguagem de programação utilizada no desenvolvimento da aplicação.
- **Spring Boot 3.3.2**: Framework para construção de aplicações Java, responsável por gerenciar dependências, injeção de dependências e simplificação da configuração.
- **JPA (Java Persistence API)**: Para mapeamento objeto-relacional e persistência de dados.
- **ActiveMQ**: Broker de mensagens utilizado para mensageria.
- **Docker**: Para containerização da aplicação e seus serviços.
- **JUnit 5**: Framework de testes utilizado para testes unitários e de integração.
- **Lombok**: Biblioteca para redução de boilerplate no código Java.
- **Jakarta Servlet API**: Para manipulação de requisições HTTP.

## Padrões de Design Implementados

### 1. Strategy Pattern
O padrão Strategy é implementado nas validações, permitindo que diferentes estratégias de validação sejam aplicadas a uma ordem. Isso é visto na interface `IValidation` e suas implementações como `EmailValidation` e `OrderIdValidation`.

### 2. Composite Pattern
O `CompositeValidationHandler` utiliza o padrão Composite para tratar múltiplas validações de forma uniforme, permitindo a composição e execução de múltiplas validações em um único fluxo.

### 3. Factory Pattern
A classe `OrderValidationFactory` implementa o padrão Factory, criando instâncias do `CompositeValidationHandler` com validações específicas para o processamento de ordens.

### 4. Template Method Pattern
O `UseCaseHandler` implementa o padrão Template Method, definindo o esqueleto para o tratamento de execuções e capturas de exceções, permitindo que a lógica específica seja definida pelas implementações de `IExecution`.

### 5. Decorator Pattern
O padrão Decorator é empregado indiretamente pelo `CompositeValidationHandler`, que permite a adição de múltiplas validações de forma dinâmica, "decorando" a cadeia de validações.

### 6. Dependency Injection (DI)
O projeto utiliza o conceito de injeção de dependências, permitindo a inversão de controle e garantindo que as dependências sejam injetadas no momento da execução. Isso é facilitado pelo Spring Framework.

### 7. Exception Handling
O `UseCaseHandler` lida com exceções de forma centralizada, garantindo que erros sejam tratados de forma consistente e que o usuário receba feedback adequado.

### 8. Builder Pattern
O `ResponseError` utiliza o padrão Builder para construção de objetos complexos, permitindo uma criação fluente e flexível.

### 9. Single Responsibility Principle (SRP)
Cada classe no projeto possui uma responsabilidade bem definida, como validação, execução de casos de uso ou manipulação de requisições. Isso assegura que o código é modular e fácil de manter.

## Estrutura de Pastas

- **application**: Contém a lógica de negócios, incluindo validações específicas e casos de uso.
  - **common**: Inclui classes comuns como validações e fábricas.
- **domain**: Contém as entidades do domínio como `Order`, `CompositeResult`, e `ValidationResult`.
- **infrastructure**: Contém implementações de infraestrutura como adaptadores, interfaces e handlers.
- **test**: Contém os testes unitários e de integração da aplicação.

## Tecnologias e Ferramentas Utilizadas

- **Spring Boot**: Para simplificação da configuração e gerenciamento da aplicação.
- **ActiveMQ/RabbitMQ**: Para gerenciamento de filas e tópicos de mensagens.
- **JUnit 5**: Para testes unitários.
- **Lombok**: Para simplificação do código Java.
- **Docker**: Para containerização da aplicação.
- **Jakarta Servlet API**: Para manipulação de requisições HTTP.
- **Git**: Controle de versão do projeto.

## Como Executar

### Pré-requisitos

- **JDK 17**
- **Maven**
- **Docker** (para ActiveMQ/RabbitMQ)

### Passos

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/mathews-alves-2019 activemq-mensageria
    ```

2. **Run active-mq docker image**
    ```bash
    docker run -d --name activemq -p 61616:61616 -p 8161:8161 rmohr/activemq
    ```

3. **Maven install**
    ```bash
    mvn clean install
    ```
4. **Deploy application on container**
    ```bash
    mvn exec:exec@docker-build
    ```

5. **Test**
    ```bash
    curl --request POST \
      --url http://localhost:8080/api/v1/orders \
      --header 'Content-Type: application/json' \
      --cookie 'JSESSIONID=2773747226B6D22366137D46AD3DAA4B; JSESSIONID=AE98C018C9C2BCEED8420CDF24C80CF1' \
      --data '{
        "id": "12345",
        "email": "example@email.com",
        "content": "This is an example order content.",
        "amount": 100.50,
        "currency": "USD"
      }'
    ```
