package mguilherme.br.com.piloto.vendor.Entidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Iterator;

import mguilherme.br.com.piloto.vendor.Config.Conf;
import mguilherme.br.com.piloto.vendor.Utilitarios.FabricarBanco;
import mguilherme.br.com.piloto.vendor.Utilitarios.FabricarClasses;
import mguilherme.br.com.piloto.vendor.Utilitarios.FabricarDataset;
import mguilherme.br.com.piloto.vendor.Utilitarios.Util;

public class GerarClasses {

    private FabricarBanco fabBanco = new FabricarBanco();
    private FabricarClasses fabClasse = new FabricarClasses();
    private FabricarDataset fabDataset = new FabricarDataset();

    public void construirClasse(JSONObject jsonClasses){

        CriarArquivoClasse criarArquivoClasse = new CriarArquivoClasse();
        CriarArquivoBanco  criarArquivoBanco = new CriarArquivoBanco();
        CriarArquivoDataset criarArquivoDataset = new CriarArquivoDataset();

        JSONArray arrayEntidades = (JSONArray) jsonClasses.get("entidades"); //Carrega o json array com as entidades
        Iterator i = arrayEntidades.iterator();
        int contator=0;
        //Variaveis para montar estrutura do arquivo de banco
        StringBuilder strEstruturaBanco = new StringBuilder();
        StringBuilder strEstruturaTabelas = new StringBuilder();
        StringBuilder strExecutarVariavelTabela = new StringBuilder();
        StringBuilder strEstruturaDataset = new StringBuilder();

        //Variaveis para montar estrutura do arquivo dataset
        StringBuilder strImportsDataset = new StringBuilder();
        StringBuilder strMetodoGetParametro = new StringBuilder();
        StringBuilder strFabCaseContentValue = new StringBuilder();


        StringBuilder strFabMetodoCaseCursorToObject = new StringBuilder();


        while (i.hasNext()) {
            contator++;
            JSONObject entidade = (JSONObject) i.next();

            StringBuilder strEstruturaClasse = new StringBuilder();

            StringBuilder strVariaveis = new StringBuilder();
            StringBuilder strGetSet = new StringBuilder();
            StringBuilder strPropriedades = new StringBuilder();

            StringBuilder strImportEntidade = new StringBuilder();
            StringBuilder strFabVariaveisContentValue = new StringBuilder();
            StringBuilder strFabCarregarVariaveisNoObjeto = new StringBuilder();

            //Primeiro nivel do json
            String nome = (String)entidade.get("nome");
            String tabela = (String)entidade.get("tabela");
            String namespace = (String)entidade.get("namespace");

            String nomeCapitalizado = Util.capitalizar(nome);

            JSONArray arrayPropriedades = (JSONArray)entidade.get("propriedades");
            Iterator p = arrayPropriedades.iterator();

            //Montanto string do dataset
            strImportsDataset.append("import "+Conf.getConfig().PACKAGE+Util.normalizarNamespace(namespace)+"."+nomeCapitalizado+";\n");

            strMetodoGetParametro.append(fabDataset.fabIfParametro(contator,i.hasNext(),nomeCapitalizado));

            int posicao = 0;
            while (p.hasNext()){
                JSONObject propriedade = (JSONObject) p.next();

                String coluna = (String)propriedade.get("coluna");
                String atributo = (String)propriedade.get("atributo");
                boolean chavePrimaria = (boolean) propriedade.get("chavePrimaria");
                boolean chaveEstrangeira = (boolean) propriedade.get("chaveEstrangeira");
                String tipo = (String)propriedade.get("tipo");

                if(chaveEstrangeira){
                    strImportEntidade.append(fabClasse.fabImportEntidadeEstrangeira(atributo));
                }
                //Montar variaveis de classe
                strVariaveis.append(fabClasse.fabVariaveisClasse(chaveEstrangeira,coluna,tipo));
                //Montar propriedades da tabela
                strPropriedades.append(fabBanco.fabPropriedadesTabela(chavePrimaria,coluna,p.hasNext(),tipo));
                strGetSet.append(fabClasse.fabGetSet(coluna,tipo,chaveEstrangeira));
                //Montar variaveis dataset content value
                strFabVariaveisContentValue.append(fabDataset.fabVariaveisContentValue(nome,coluna,tipo,chaveEstrangeira));
                //Montar variaveis do objeto no dataset
                strFabCarregarVariaveisNoObjeto.append(fabDataset.fabCarregarVariaveisNoObjeto(nome,coluna,tipo,posicao,chaveEstrangeira));

                posicao++;
            }
            //Montar estrutura da entidade
            strEstruturaClasse.append(fabClasse.fabCabecalho(nome,namespace,strImportEntidade.toString())); //Cabecalho da classe
            strEstruturaClasse.append(strVariaveis);
            strEstruturaClasse.append(strGetSet).append("\n");
            strEstruturaClasse.append(fabClasse.fabMetodoSalvar(nome));
            strEstruturaClasse.append(fabClasse.fabMetodoExcluir(nome));
            strEstruturaClasse.append(fabClasse.fabMetodoRetornarUltimoId(nome));

            strEstruturaClasse.append("}");
            criarArquivoClasse.criarArquivoJava(strEstruturaClasse.toString(),nome,namespace);

            //Montar estrutura da tabela
            strEstruturaTabelas.append(fabBanco.fabVariavelTabelaBd(tabela));
            strEstruturaTabelas.append(strPropriedades);
            strExecutarVariavelTabela.append("database.execSQL(tab_"+tabela+");\n");

            //Montar estrutura cases dataset
            strFabCaseContentValue.append(fabDataset.fabCaseContentValue(contator,nome,strFabVariaveisContentValue.toString()));
            strFabMetodoCaseCursorToObject.append(fabDataset.fabMetodoCaseCursorToObject(contator,nome,strFabCarregarVariaveisNoObjeto.toString()));
        }

        strEstruturaBanco.append(fabBanco.fabCabecalhoBd());
        strEstruturaBanco.append(strEstruturaTabelas);
        strEstruturaBanco.append(fabBanco.fabMetodosCreateUpgradeBd(strExecutarVariavelTabela.toString()));
        strEstruturaBanco.append("}");
        //Chamar metodo para gravar arquivo do banco
        criarArquivoBanco.criarArquivoBdJava(strEstruturaBanco.toString());


        // MONTANDO ESTRUTURA DO ARQUIVO DatasetHelper
        strEstruturaDataset.append(fabDataset.fabCabecalhoDataSet());
        strEstruturaDataset.append(strImportsDataset);
        strEstruturaDataset.append(fabDataset.fabConteudoInical());
        strEstruturaDataset.append(strMetodoGetParametro);
        strEstruturaDataset.append(fabDataset.fabMetodoSalvar());
        strEstruturaDataset.append(fabDataset.fabMetodoExcluir());
        strEstruturaDataset.append(fabDataset.fabMetodoIdCadastro());
        strEstruturaDataset.append(fabDataset.fabMetodoExecutarConsulta());
        strEstruturaDataset.append(fabDataset.fabMetodoConsultaCustomizada());
        strEstruturaDataset.append(fabDataset.fabMetodoContentValues(strFabCaseContentValue.toString()));
        strEstruturaDataset.append(fabDataset.fabMetodoCursorToObject(strFabMetodoCaseCursorToObject.toString()));
        strEstruturaDataset.append("}");
        //Metodo para gravar arquivo de dataset
        criarArquivoDataset.criarArquivoDatasetJava(strEstruturaDataset.toString());

    }




}
