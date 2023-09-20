# Descrição

O projeto "Agendamento de Transações" é uma aplicação web desenvolvida em Angular para agendar e gerenciar transações financeiras. A aplicação permite aos usuários agendar transações já calculando as taxas de acordo com os tipos de operações e visualizar o histórico de transações agendadas.

# Tecnologias Utilizadas

Frontend: Angular

Backend: Java 11, Spring, Lombok

Banco de Dados: H2

API: /transaction

Endpoint GET: Retorna todas as transações agendadas.
Endpoint POST: Permite agendar uma nova transação.

# Detalhes

## Backend

O projeto segue um padrão de Controller > Service > Repository 
Foi utilizado um Enum para as Operações existentes e um handler para cada tipo de operação, Uma camada facade para fornecer uma interface unificada para separar as regras de taxação de acordo com as Operações, facilidando a manutenção nas operações existentes e simplificando a adição de novos tipos de transação.


## Frontend

O projeto frontend é bem simples mas contém formatação dos campos de data e dos campos do valor, taxa e valor total e validação dos campos de entrada, a interface contem um formulario para agendar uma nova transação e listar todas as transações cadastradas

# Instalação
Importe a aplicação backend na sua IDE de preferencia
installe as depencias do maven e inicialize a aplicação

Para o frontend
Instale as dependências: npm install

Uso
Inicie a aplicação frontend: ng serve

Acesse a aplicação no seu navegador: http://localhost:4200
Para usar a aplicação, siga as instruções fornecidas na interface.