# Gestão do curso de treinamento

Programa desenvolvido em linguagem Java para a gestão de um curso de treinamento. Esse ocorre em 2 etapas e com dois intervalos de café. Assim, o programa é responsável pelo cadastro e consulta de pessoas participantes, salas de treinamento e espaços para intervalos. 

## Instalação
Para execução do código, é necessário a instalação do [JDK 15.0.2](https://www.oracle.com/br/java/technologies/javase-jdk15-downloads.html) e, para execução dos testes unitários, é necessário instalar o Maven.

### Instalação do Maven
A instalação do Maven é realizada conforme descrita [na documentação oficial](https://maven.apache.org/install.html).

### Como executar código
A execução do código no Prompt de Comando (CMD) ocorre a partir dos seguintes passos:

1. Abrir o diretório dos arquivos no CMD
2. Instalação da dependência do [JUnit 5](https://junit.org/junit5/) com o comando:
   ```
   mvn install
   ```   
3. Compilar o código, digitando:
   ```
   mvn compile
   ```
4. Executar com o comando:
   ```
   mvn exec:java
   ```

### Como executar testes unitários
Os testes unitários são executados no CMD, após a compilação do código, através do comando:
```
mvn test
```


## Documentação do sistema
* O código foi desenvolvido com auxílio do IntelliJ IDEA Community. Entretanto, como a execução dos testes unitários necessita da biblioteca JUnit 5, encontrei a alternativa de uso da ferramenta Maven para permitir a execução do código e dos testes fora do IntelliJ; 


* A estruturação do projeto seguiu o padrão do Maven;  


* A interface do usuário foi desenvolvida para o console;
  

* Abaixo é listado as classes presentes no código e suas funcionalidades:
  
    * `Main`, responsável pela interface com o usuário;
    * `Curso`, realiza o cadastro de cada pessoa, sala e espaço de café; reconhece e limita a lotação máxima das salas; distribui as pessoas nas vagas disponíveis nas salas; redistribui metade das pessoas entre as salas para a segunda etapa do treinamento; e distribui as pessoas entre os espaços de café disponíveis. 
    * `Pessoa`, registra o nome e sobrenome de cada pessoa cadastrada;
    * `Sala`, registra o nome e lotação de cada sala cadastrada;
    * `EspacoCafe`, registra o nome e lotação de cada espaço de café cadastrado.


* Os testes unitários foram feitos com base na classe `Curso`, garantindo que:
    * A diferença entre a quantidade de pessoas nas salas é no máximo igual a 1 pessoa;
    * Metade das pessoas trocam de sala entre as duas etapas do treinamento;
    * A mesma pessoa está locada simultaneamente em apenas uma sala;
    * A mesma pessoa está locada simultaneamente em apenas um espaço de café.
    




