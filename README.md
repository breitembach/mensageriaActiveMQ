## mensageriaActiveMQ

Sistema de Mensageria queues/Topic Java utilizando activeMQ

Arquitetura MAVEN

Authors: Lucas Breitembach & Erica Viana

#### Dependências

* JAVAFX 13

* JAVA 13 ou pode funcionar também >= 11

* ActiveMQ
```> activeMQ console```

* Maven

#### Maven
```mvn install```

```clean javafx:run -U```

#### Linux
 
instalar JDK:

```sudo apt-get install openjdk-<version>```

Mudar de JDK:

```
sudo update-alternatives --config java 
```

Rodar o projeto:

```mvn install```

``` mvn clean javafx:run ```

para criar o jar:

``` mvn compile package ```

```sudo chmod+x mensageriaAutoInst.jar```

``` java -jar shade/mensageriaAutoInst.jar ```

#### Windows

Rodar o projeto:

```mvn install```

``` mvn compile exec:java ```

para criar o jar:

``` mvn compile package ```

``` java -jar shade/mensageriaAutoInst.jar ```

Sem conexão com ActiveMQ
![alt text](./screenShots/Captura_sem_conexao_activeMQ.png)

entrando no chat
![alt text](./screenShots/entrando.png)

entrando com segundo usuário
![alt text](./screenShots/entrando_segundo_usuario.png)

enviando mengem chat
![alt text](./screenShots/enviando_mengem_chat.png)

enviando mengem privada por UID
![alt text](./screenShots/enviando_mensagem_privada.png)

Saindo chat
![alt text](./screenShots/saindo_chat.png)
