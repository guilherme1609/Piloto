package mguilherme.br.com.piloto.vendor.Config;

import java.io.File;
import java.io.IOException;

/**
 *  Altere as variaveis para sua configuracao ou crie um novo arquivo de configuracao
 *  e depois instancie ele na classe >>Conf
 */
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
    public final String LOCAL_MAPA_ENTIDADES = LOCAL_PROJECT+"\\vendor\\Config\\MapaEntidades.json";

}
