package mguilherme.br.com.piloto.vendor.Utilitarios;

import mguilherme.br.com.piloto.vendor.Config.Conf;

/**
 * Created by guilh on 28/02/2017.
 */

public class FabricarDataset {

    public String fabCabecalhoDataSet(){

        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("package "+ Conf.getConfig().PACKAGE_DATABASE+";\n");
        strBuilder.append("import android.content.ContentValues;\n");
        strBuilder.append("import android.content.Context;\n");
        strBuilder.append("import android.database.Cursor;\n");
        strBuilder.append("import android.database.SQLException;\n");
        strBuilder.append("import android.database.sqlite.SQLiteDatabase;\n");
        strBuilder.append("import android.text.TextUtils;\n");
        strBuilder.append("import java.util.ArrayList;\n");
        strBuilder.append("import java.util.List;\n");

        return strBuilder.toString();

    }

    public String fabConteudoInical(){
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("public class DatasetHelper {\n\n");
        strBuilder.append("private int contador;\n")
                  .append("private SQLiteDatabase database;\n")
                  .append("private DatabaseHelper dbHelper;\n")
                  .append("private String entidade;\n\n");

        strBuilder.append("public DatasetHelper(String entidade,Context contexto){\n\n")
                  .append("this.entidade = entidade;\n")
                  .append("dbHelper = new DatabaseHelper(contexto);\n").append("}\n\n");

        strBuilder.append("public DatasetHelper(Context contexto){dbHelper = new DatabaseHelper(contexto);}\n\n");
        strBuilder.append("public int getContador() {return contador;}\n\n");
        strBuilder.append("public void setContador(int contador) {this.contador = contador;}\n\n");
        strBuilder.append("public void open() throws SQLException {database = dbHelper.getWritableDatabase();}\n\n");
        strBuilder.append("public void close() {dbHelper.close();}\n\n");
        strBuilder.append("private int getParametroAlteracao(Object objeto){\n").append("int parametro=0;\n\n");


        return strBuilder.toString();
    }

    public String fabIfParametro(int contator,boolean hasNext, String nomeCapitalizado){
        StringBuilder strIfParametro = new StringBuilder();

        if(contator==1) {
            if(hasNext) {
                strIfParametro.append("if(objeto instanceof " + nomeCapitalizado + "){\n")
                        .append("     parametro = ((" + nomeCapitalizado + ") objeto).getId();")
                        .append("}\n");
            }else{
                strIfParametro.append("if(objeto instanceof " + nomeCapitalizado + "){\n")
                        .append("     parametro = ((" + nomeCapitalizado + ") objeto).getId();")
                        .append("}\n\n");
                strIfParametro.append(" return parametro;\n");
                strIfParametro.append("}\n\n");
            }
        }else{
            if(hasNext) {
                strIfParametro.append("else if(objeto instanceof " + nomeCapitalizado + "){\n")
                        .append("parametro = ((" + nomeCapitalizado + ") objeto).getId();")
                        .append("}\n");
            }else{
                strIfParametro.append("else if(objeto instanceof " + nomeCapitalizado + "){\n")
                        .append("parametro = ((" + nomeCapitalizado + ") objeto).getId();")
                        .append("}\n\n");
                strIfParametro.append(" return parametro;\n");
                strIfParametro.append("}\n\n");
            }
        }

        return strIfParametro.toString();

    }

    public String fabMetodoSalvar(){

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("public boolean salvar(Object objeto) {\n\n");
        strBuilder.append("     int parametroAlteracao = getParametroAlteracao(objeto);\n\n");
        strBuilder.append("     ContentValues values = getContentValues(objeto);\n\n");
        strBuilder.append("     if(parametroAlteracao!=0){\n");
        strBuilder.append("         if (database.update(entidade, values, \"id=?\", new String[]{String.valueOf(parametroAlteracao)}) == -1) {\n");
        strBuilder.append("             return false;\n")
                  .append("         }else{\n");
        strBuilder.append("             return true;\n")
                  .append("         }\n");
        strBuilder.append("     }else {\n");
        strBuilder.append("         if (database.insert(entidade, null, values) == -1) {\n");
        strBuilder.append("             return false;\n");
        strBuilder.append("         }else{\n");
        strBuilder.append("             return true;\n")
                  .append("         }\n");
        strBuilder.append("     }\n");
        strBuilder.append("}\n\n");


        return strBuilder.toString();
    }

    public String fabMetodoExcluir(){
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("public boolean excluir(Object objeto) {\n");
        strBuilder.append("     int parametroAlteracao = getParametroAlteracao(objeto);\n");
        strBuilder.append("     if (database.delete(entidade, \"id=?\", new String[]{String.valueOf(parametroAlteracao)}) == -1) {\n");
        strBuilder.append("         return false;\n");
        strBuilder.append("     } else {\n");
        strBuilder.append("         return true;\n");
        strBuilder.append("     }\n");
        strBuilder.append("}\n\n");

        return strBuilder.toString();
    }

    public String fabMetodoIdCadastro(){
        StringBuilder strMetodoIdCadastro = new StringBuilder();

        strMetodoIdCadastro.append("public int retornarIdCadastro() {\n");
        strMetodoIdCadastro.append("    int idRetorno=0;\n");
        strMetodoIdCadastro.append("    String sentenca = \"select max(id) from \"+entidade+\";\";\n");
        strMetodoIdCadastro.append("    Cursor cursor = database.rawQuery(sentenca, null);\n");
        strMetodoIdCadastro.append("    setContador(cursor.getCount());\n");
        strMetodoIdCadastro.append("    cursor.moveToFirst();\n");
        strMetodoIdCadastro.append("    while (!cursor.isAfterLast()) {\n");
        strMetodoIdCadastro.append("        idRetorno = cursor.getInt(0);\n");
        strMetodoIdCadastro.append("        cursor.moveToNext();\n")
                           .append("    }\n\n");
        strMetodoIdCadastro.append("    cursor.close();\n")
                           .append("    return idRetorno;")
                           .append("}\n\n");

        return strMetodoIdCadastro.toString();
    }

    public String fabMetodoExecutarConsulta(){

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("public List<Object> executarConsulta(String sentenca,String[] parametros,Object entidade) {\n");
        strBuilder.append("     List<Object> lsObjects = new ArrayList<Object>();\n\n");
        strBuilder.append("     if(parametros==null){\n");
        strBuilder.append("         parametros = new String[]{};\n")
                  .append("     }\n\n");
        strBuilder.append("     Cursor cursor = database.rawQuery(sentenca, parametros);\n");
        strBuilder.append("     setContador(cursor.getCount());\n");
        strBuilder.append("     cursor.moveToFirst();\n");
        strBuilder.append("     while (!cursor.isAfterLast()) {\n");
        strBuilder.append("         Object objeto = cursorToObject(cursor,entidade);\n");
        strBuilder.append("         lsObjects.add(objeto);\n");
        strBuilder.append("         cursor.moveToNext();\n")
                  .append("     }\n\n");
        strBuilder.append(" cursor.close();\n");
        strBuilder.append(" return lsObjects;\n");
        strBuilder.append("}\n\n");

        return strBuilder.toString();

    }

    public String fabMetodoConsultaCustomizada(){
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("public Cursor executarConsultaCustomizada(String sentenca,String[] parametros) {\n" +
                "\n" +
                "        if(parametros==null){\n" +
                "            parametros = new String[]{};\n" +
                "        }\n" +
                "\n" +
                "        Cursor cursor = database.rawQuery(sentenca, parametros);\n" +
                "\n" +
                "        return cursor;\n" +
                "    }");

        return strBuilder.toString();
    }

    public String fabMetodoContentValues(String strCases){

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("private ContentValues getContentValues(Object objeto) {\n");
        strBuilder.append("     ContentValues values = new ContentValues();\n\n");
        strBuilder.append(strCases);
        strBuilder.append("     return values;\n");
        strBuilder.append("}\n");

        return strBuilder.toString();
    }

    public String fabCaseContentValue(int contador,String entidade,String strVariaveis){
        StringBuilder strBuilder = new StringBuilder();

        if(contador==1) {
            strBuilder.append("if(objeto instanceof " + Util.capitalizar(entidade) + "){\n");
            strBuilder.append("     " + Util.capitalizar(entidade) + " " + entidade + "=(" + Util.capitalizar(entidade) + ")objeto;\n");
            strBuilder.append(strVariaveis);
            strBuilder.append("}\n");
        }else{
            strBuilder.append("else if(objeto instanceof " + Util.capitalizar(entidade) + "){\n");
            strBuilder.append("     " + Util.capitalizar(entidade) + " " + entidade + "=(" + Util.capitalizar(entidade) + ")objeto;\n");
            strBuilder.append(strVariaveis);
            strBuilder.append("}\n");
        }

        return strBuilder.toString();
    }

    public String fabVariaveisContentValue(String entidade,String coluna,String tipo,boolean chaveEstrangeira){

        StringBuilder strBuilder = new StringBuilder();

        if(chaveEstrangeira){
            strBuilder.append("     if(" + entidade + ".get" + Util.capitalizar(coluna) + "()!=null){\n");
            strBuilder.append("         values.put(\"" + coluna + "\", ((" + Util.capitalizar(entidade) + ") objeto).get" + Util.capitalizar(coluna) + "().getId());\n");
            strBuilder.append("     }\n");
        }else {
            if (tipo.startsWith("varchar")) {
                strBuilder.append("     if(!TextUtils.isEmpty(" + entidade + ".get" + Util.capitalizar(coluna) + "())){\n");
                strBuilder.append("         values.put(\"" + coluna + "\", ((" + Util.capitalizar(entidade) + ") objeto).get" + Util.capitalizar(coluna) + "());\n");
                strBuilder.append("     }\n");
            } else{
                strBuilder.append("     if(" + entidade + ".get" + Util.capitalizar(coluna) + "()>0){\n");
                strBuilder.append("         values.put(\"" + coluna + "\", ((" + Util.capitalizar(entidade) + ") objeto).get" + Util.capitalizar(coluna) + "());\n");
                strBuilder.append("     }\n");
            }
        }

        return strBuilder.toString();
    }

    public String fabMetodoCursorToObject(String strCases){

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("private Object cursorToObject(Cursor cursor,Object classe) {\n");
        strBuilder.append("     Object objeto = new Object();\n\n");
        strBuilder.append(strCases);
        strBuilder.append("     return objeto;\n");
        strBuilder.append("}\n");

        return strBuilder.toString();
    }

    public String fabMetodoCaseCursorToObject(int contador,String entidade,String strVariaveis){
        StringBuilder strBuilder = new StringBuilder();
        if(contador==1) {
            strBuilder.append("if(classe instanceof " + Util.capitalizar(entidade) + "){\n");
            strBuilder.append("     " + Util.capitalizar(entidade) + " " + entidade + "=new "+Util.capitalizar(entidade)+"();\n");
            strBuilder.append(strVariaveis);
            strBuilder.append(" objeto="+entidade+";");
            strBuilder.append("}\n");
        }else{
            strBuilder.append("else if(classe instanceof " + Util.capitalizar(entidade) + "){\n");
            strBuilder.append("     " + Util.capitalizar(entidade) + " " + entidade + "=new "+Util.capitalizar(entidade)+"();\n");
            strBuilder.append(strVariaveis);
            strBuilder.append(" objeto="+entidade+";");
            strBuilder.append("}\n");
        }
        return strBuilder.toString();
    }

    public String fabCarregarVariaveisNoObjeto(String entidade,String coluna,String tipo,int posicao,boolean chaveEstrangeira){

        StringBuilder strBuilder = new StringBuilder();

        if(chaveEstrangeira){
            strBuilder.append("     " + entidade + ".get" + Util.capitalizar(coluna) + "().setId" + "(cursor.getInt" + "(" + posicao + "));\n");
        }else {
            if (tipo.startsWith("varchar")) {
                strBuilder.append("     " + entidade + ".set" + Util.capitalizar(coluna) + "(cursor.getString" + "(" + posicao + "));\n");
            }else{
                strBuilder.append("     " + entidade + ".set" + Util.capitalizar(coluna) + "(cursor.get" + Util.capitalizar(tipo) + "(" + posicao + "));\n");
            }
        }
        return strBuilder.toString();
    }


}
