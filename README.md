
# Order Manager Service

Serviço para o controle de pedidos.




## Instalação

Para configurar o projeto deve-se executar os seguintes comandos:
    
```bash
  sudo kubectl apply -f 1-mongodb-configMap.yaml
  sudo kubectl apply -f 2-db-deployment.yaml
  sudo kubectl apply -f 3-app-deployment.yaml
```
## Para autenticar

Uma camada de segurança básica foi adicionada com usuário e senha:

**Usuário:** master
**Senha:*** master@@1525#Apk

Utilizar estes dados para autenticar as requisições.

Obs: Será implementado uma segurança mais aprimorada usando tokens no futuro.

## Obter endereço do serviço

Após executar os comandos de instalação e aguardar o kubernates iniciar os pods, deve-se executar o comando abaixo para obter a url para o serviço (URL_MINIKUBE):

```bash
    sudo minikube service springboot-crud-svc
```

## Produto Externo A : Envio de pedidos

Acessar via POST o endereço http://URL_MINIKUBE/orders/receive

Exemplo de body:
```javascript

"idCustomer":"50",
"products":[
   {
     "idProduto" : "4545",
      "unitPrice":12.0,
      "quantity":2,
      "totalPrice":24.0
   }
]
}

```

## Produto Externo B : Buscar pedidos processados

Acessar via GET o endereço http://URL_MINIKUBE/orders/finished

## Documentação da API

Para mais informações acesse:
 http://URL_MINIKUBE/swagger-ui/index.html
