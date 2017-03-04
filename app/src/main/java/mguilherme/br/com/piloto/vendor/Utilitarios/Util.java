package mguilherme.br.com.piloto.vendor.Utilitarios;

/**
 * Created by guilh on 26/02/2017.
 */

public class Util {
    /**
     *
     * @return Primeira letra maiuscula
     */
    public static String capitalizar(String palavra){
        palavra = palavra.substring(0,1).toUpperCase().concat(palavra.substring(1));
        return palavra;
    }

    public static String descapitalizar(String palavra){
        palavra = palavra.substring(0,1).toLowerCase().concat(palavra.substring(1));
        return palavra;
    }

    public static String normalizarNamespace(String namespace){
        namespace = namespace.replace("\\",".");
        return namespace;
    }

    public static String pegarEntidade(String entidade){

        String strEntidade = entidade;
        int posIni = strEntidade.lastIndexOf("\\");

        return strEntidade.substring(posIni).replace("\\","");
    }

}
