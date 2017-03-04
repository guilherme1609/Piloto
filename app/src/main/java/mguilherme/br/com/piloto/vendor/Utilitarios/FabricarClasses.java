package mguilherme.br.com.piloto.vendor.Utilitarios;

import mguilherme.br.com.piloto.vendor.Config.Conf;

/**
 * Created by guilh on 28/02/2017.
 */

public class FabricarClasses {

    public String fabCabecalho(String nomeClasse,String namespace,String strImportEntidade){
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("package "+ Conf.getConfig().PACKAGE+Util.normalizarNamespace(namespace)+";\n");
        strBuilder.append("import java.io.Serializable;\n");
        strBuilder.append("import "+Conf.getConfig().PACKAGE+".Database.DatasetHelper;\n");
        strBuilder.append("import android.content.Context;\n");
        strBuilder.append(strImportEntidade);

        strBuilder.append("public class "+ Util.capitalizar(nomeClasse)+" implements Serializable{\n");

        return strBuilder.toString();
    }

    public String fabImportEntidadeEstrangeira(String atrNamespace){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("import "+ Conf.getConfig().PACKAGE+Util.normalizarNamespace(atrNamespace)+";\n");
        return strBuilder.toString();
    }

    public String fabGetSet(String atributo,String tipo,boolean chaveEstrangeira){
        StringBuilder stringBuilder = new StringBuilder();
        if(chaveEstrangeira){
            stringBuilder.append("public "+Util.pegarEntidade(atributo)+" get"+Util.pegarEntidade(atributo)+"(){ return "+Util.descapitalizar(Util.pegarEntidade(atributo))+"; }\n");
            stringBuilder.append("public void set"+Util.pegarEntidade(atributo)+"("+Util.pegarEntidade(atributo)+" "+Util.descapitalizar(Util.pegarEntidade(atributo))+") { this."+Util.descapitalizar(Util.pegarEntidade(atributo))+" = "+Util.descapitalizar(Util.pegarEntidade(atributo))+";}\n");
        }else{
            if(tipo.startsWith("varchar")){
                stringBuilder.append("public String get"+Util.capitalizar(atributo)+"(){ return "+atributo+"; }\n");
                stringBuilder.append("public void set"+Util.capitalizar(atributo)+"(String "+atributo+") { this."+atributo+" = "+atributo+";}\n");
            }else{
                stringBuilder.append("public "+tipo+" get"+Util.capitalizar(atributo)+"(){ return "+atributo+"; }\n");
                stringBuilder.append("public void set"+Util.capitalizar(atributo)+"("+tipo+" "+atributo+") { this."+atributo+" = "+atributo+";}\n");
            }

        }
        return stringBuilder.toString();
    }

    public String fabVariaveisClasse(boolean chaveEstrangeira,String atributo,String tipo){

        StringBuilder strVariaveis = new StringBuilder();

        if(chaveEstrangeira){
            strVariaveis.append("private " + Util.pegarEntidade(atributo) + " " + Util.descapitalizar(Util.pegarEntidade(atributo)) + ";\n");
        }else {
            if(tipo.startsWith("varchar")){
                strVariaveis.append("private String " + atributo + ";\n");
            }else {
                strVariaveis.append("private " + tipo + " " + atributo + ";\n");
            }
        }

        return strVariaveis.toString();
    }

    public String fabMetodoSalvar(String entidade){
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("public boolean salvar(Context contexto) {\n" +
                "\n" +
                "        boolean salvar = false;\n" +
                "\n" +
                "        DatasetHelper datasetHelper = new DatasetHelper(\""+entidade+"\",contexto);\n" +
                "        datasetHelper.open();\n" +
                "        salvar = datasetHelper.salvar(this);\n" +
                "        datasetHelper.close();\n" +
                "\n" +
                "        return salvar;\n" +
                "    }\n\n");

        return strBuilder.toString();
    }

    public String fabMetodoExcluir(String entidade){
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("public boolean excluir(Context contexto) {\n" +
                "\n" +
                "        boolean exclusao = false;\n" +
                "        DatasetHelper datasetHelper = new DatasetHelper(\""+entidade+"\",contexto);\n" +
                "        datasetHelper.open();\n" +
                "        exclusao = datasetHelper.excluir(this);\n" +
                "        datasetHelper.close();\n" +
                "\n" +
                "        return exclusao;\n" +
                "\n" +
                "    }\n\n");

        return strBuilder.toString();
    }

    public String fabMetodoRetornarUltimoId(String entidade){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("public int retornarUltimoId(Context contexto) {\n" +
                "\n" +
                "        int id = 0;\n" +
                "        DatasetHelper datasetHelper = new DatasetHelper(\""+entidade+"\",contexto);\n" +
                "        datasetHelper.open();\n" +
                "        id = datasetHelper.retornarIdCadastro();\n" +
                "        datasetHelper.close();\n" +
                "\n" +
                "        return id;\n" +
                "\n" +
                "    }\n\n");

        return strBuilder.toString();
    }

}
