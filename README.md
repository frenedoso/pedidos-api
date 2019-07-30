# Pedidos API Rest

API rest de cadastro de Pedidos. 

## Heroku 

[Link da aplicação](https://pedidos-api-rest.herokuapp.com)

[Documentação Swagger UI](https://pedidos-api-rest.herokuapp.com/swagger-ui.html)

## Resources 

#### Lista de produtos

GET /api/produtos

```
[
    {
        "id": 1,
        "multiplo": null,
        "preco": 550000.00,
        "nome": "Millenium Falcon"
    }
]
```

#### Lista de Clientes

GET /api/clientes

```
[
    {
        "id": 1,
        "nome": "Darth Vader"
    }
]
```

#### Lista de Pedidos

GET /api/pedidos


#### Itens de Pedido

GET /api/pedidos/1/itens


#### Inserção de Pedidos

POST /api/pedidos

```
{
	"cliente": {
		"id": 1
	}
}
```

#### Inserção de Itens de Pedido

POST /api/pedidos

```
{
    "produto": {
        "id": "7"
    },
    "quantidade": 10,
    "precoUnitario": 1350.01
}
```

