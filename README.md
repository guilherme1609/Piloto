# Framework Android
  Um framework simples porém muito útil para criar projetos android com muita rapidez, não precisa se preocupar em criar as classes e o banco de dados deixe isso para o framework e se concentre no que importa!

## Como utilizar

> Estrutura

  Para utilizar o framework crie ou edite o arquivo json disponivel em **../vendor/Config/MapaEntidades.json** para mapear suas entidades como no exemplo:
  
  ```
  {
  "entidades": [
    {
      "nome": "pessoa",
      "tabela": "pessoa",
      "namespace":"\\Gerenciador\\Dominio",
      "propriedades": [
        {
          "coluna": "id",
          "atributo": "id",
          "chavePrimaria": true,
          "chaveEstrangeira": false,
          "tipo": "int"
        },
        {
          "coluna": "email",
          "atributo": "email",
          "chavePrimaria": false,
          "chaveEstrangeira": false,
          "tipo": "varchar(75)"
        },
        {
          "coluna": "nome",
          "atributo": "nome",
          "chavePrimaria": false,
          "chaveEstrangeira": false,
          "tipo": "varchar(75)"
        },
        {
          "coluna": "telefone",
          "atributo": "telefone",
          "chavePrimaria": false,
          "chaveEstrangeira": false,
          "tipo": "varchar(15)"
        },
        {
          "coluna": "cidade",
          "atributo": "\\Gerenciador\\Dominio\\Cidade",
          "chavePrimaria": false,
          "chaveEstrangeira": true,
          "tipo": "int"
        }
      ]
    }
    ]
  }  
  ```
> Configuração

  Altere o arquivo **../vendor/Config/ConfigPadrao** conforme o necessário. Exemplo:
  
  ```
  public class ConfigPadrao {

    //Defina o nome do banco para criacao dinamica
    public final String DATABASE_NAME = "piloto";
    //Defina a versao do banco
    public final int DATABASE_VERSION=2;

    //Caminho relativo do seu pacote pode ser localizado no arquivo manifest
    public final String PACKAGE = "mguilherme.br.com.piloto";
    //Caminho do diretorio onde sera criado o arquivo da base
    public final String PACKAGE_DATABASE = PACKAGE+".Database";
    /**
     * Caminho local do projeto para criacao de arquivos dinamicamente
     * deve ser definido o caminho completo ate a raiz do projeto
     */
    public final String LOCAL_PROJECT = "C:\\Users\\guilh\\AndroidStudioProjects\\Piloto\\app\\src\\main\\java\\mguilherme\\br\\com\\piloto";
    /**
     * Caminho do arquivo json de definição das entidades
     * deve ser especificado o caminho completo onde o arquivo foi salvo
     * o arquivo pode ser salvo no local de sua escolha dentro do projeto
     */
    public final String LOCAL_MAPA_ENTIDADES = "C:\\Users\\guilh\\AndroidStudioProjects\\Piloto\\app\\src\\main\\java\\mguilherme\\br\\com\\piloto\\vendor\\Config\\MapaEntidades.json";

}
```
> Execução

  Depois de mapeado e configurado o projeto, basta executar o arquivo **../vendor/Entidades/GerarBase** no seu IDE, ele irá criar as pastas mapeadas na propriedade namespace do arquivo de mapeamento em seguida ira criar as classes e a base de dados.
  
  
