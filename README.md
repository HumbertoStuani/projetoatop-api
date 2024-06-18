# Projeto Ativo Operante - API

Projeto Ativo Operante é uma aplicação web desenvolvida com o objetivo de aprofundar os conhecimentos do Framework Java Spring Boot e praticar as habilidades de programação.

## Descrição

Esta é a API do Projeto Ativo Operante, um portal de envio de denúncias que podem acompanhar imagens anexadas para avaliação e acompanhamento do feedback da denúncia. Administradores visualizam as denúncias recebidas e adicionam a elas o feedback, além de configurar o sistema com os tipos de problema e órgãos responsáveis disponíveis.

## Instalação

Para executar a aplicação localmente, siga os passos abaixo:

1. Clone o repositório:
    ```sh
    git clone https://github.com/HumbertoStuani/projetoatop-api.git
    ```

2. Navegue até o diretório do projeto:
    ```sh
    cd projetoatop-api
    ```

3. Configure as variáveis de ambiente necessárias para a conexão com o MongoDB Atlas e outros serviços:
    ```sh
    cp .env.example .env
    ```

4. Construa e execute a aplicação usando Maven:
    ```sh
    ./mvnw spring-boot:run
    ```

## Uso

A aplicação expõe uma API RESTful que pode ser utilizada para registrar e gerenciar denúncias. Abaixo alguns dos endpoints disponíveis:

- `POST api/cidadao/denuncias` - Registra uma nova denúncia.
- `GET api/cidadao/denuncias` - Lista todas as denúncias em registro do cidadão.
- `GET api/admin/denuncias/{id}` - Lista todas as denúncias em registro.
- `DELETE api/cidadao/denuncias/{id}` - Remove uma denúncia.


## Tecnologias

O Projeto Ativo Operante foi desenvolvido utilizando as seguintes tecnologias:

- **Java Spring Boot**: Para a construção da API RESTful.
- **JPA (Java Persistence API)**: Para o mapeamento objeto-relacional.
- **MongoDB Atlas**: Como banco de dados NoSQL hospedado em nuvem para armazenamento de dados.
- **Google Cloud**: Para hospedagem da aplicação, utilizando o serviço Cloud Run para execução de Containers Docker.

## Contribuição

Contribuições são sempre bem-vindas! Se você tem alguma sugestão ou quer melhorar algo, sinta-se à vontade para fazer um fork do repositório e submeter suas mudanças via Pull Request.

1. Faça um fork do projeto
2. Crie uma branch para sua nova feature (`git checkout -b feature/novaFeature`)
3. Faça commit das suas mudanças (`git commit -m 'Adiciona alguma novaFeature'`)
4. Faça push para a branch (`git push origin feature/novaFeature`)
5. Abra um Pull Request

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE.md para detalhes.
