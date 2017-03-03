package mguilherme.br.com.piloto.vendor.Utilitarios;

import mguilherme.br.com.piloto.vendor.Config.Conf;

/**
 * Metodos para fabricar o arquivo da base de dados
 */
public class FabricarBanco {

    public String fabCabecalhoBd(){

        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("package "+ Conf.getConfig().PACKAGE_DATABASE+";\n");
        strBuilder.append("import android.content.Context;\n");
        strBuilder.append("import android.database.sqlite.SQLiteDatabase;\n");
        strBuilder.append("import android.database.sqlite.SQLiteOpenHelper;\n");
        strBuilder.append("import "+Conf.getConfig().PACKAGE+".vendor.Config.Conf;\n");

        strBuilder.append("public class DatabaseHelper extends SQLiteOpenHelper {\n\n");

        strBuilder.append("public DatabaseHelper(Context context) {\n");
        strBuilder.append("     super(context, Conf.getConfig().DATABASE_NAME, null, Conf.getConfig().DATABASE_VERSION);\n");
        strBuilder.append("}\n\n");

        return strBuilder.toString();
    }

    public String fabVariavelTabelaBd(String tabela){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("private static final String tab_"+tabela+"=\"CREATE TABLE IF NOT EXISTS "+tabela+" (");
        return strBuilder.toString();
    }

    public String fabMetodosCreateUpgradeBd(String strVariaveis){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("@Override\n");
        strBuilder.append("public void onCreate(SQLiteDatabase database) {\n");
        strBuilder.append(strVariaveis);
        strBuilder.append("}\n\n");

        strBuilder.append("@Override\n");
        strBuilder.append("public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {\n");
        strBuilder.append("  /* ATENÇÃO! É RECOMENDADO ESCREVER OS METODOS DE UPGRADE EM OUTRO ARQUIVO \nPOIS CASO SEJA GERADO AS ENTIDADES NOVAMENTE, AS ALTERAÇÕES FEITAS NESTE ARQUIVO SERÃO PERDIDAS*/\n\n");
        strBuilder.append("     if (newVersion > oldVersion) {\n");
        strBuilder.append("         for (int i = oldVersion; i < newVersion; ++i) {\n");
        strBuilder.append("             int nextVersion = i + 1;\n");
        strBuilder.append("                 switch (nextVersion) {\n");
        strBuilder.append("                     case 3: /*chamar metodo para fazer upgrade*/ break;\n");
        strBuilder.append("                 }\n");
        strBuilder.append("         }\n");
        strBuilder.append("     }\n");
        strBuilder.append("}\n\n");

        return strBuilder.toString();
    }

    public String fabPropriedadesTabela(boolean chavePrimaria,String coluna,boolean hasNext,String tipo){
        StringBuilder strPropriedades = new StringBuilder();

        if(chavePrimaria){
            strPropriedades.append(coluna+" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        }else{
            if(hasNext) {
                strPropriedades.append(coluna + " " + tipo + ", ");
            }else{
                strPropriedades.append(coluna + " " + tipo + ");\";\n\n");
            }
        }

        return strPropriedades.toString();
    }
}
