package mguilherme.br.com.piloto.vendor.Entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import mguilherme.br.com.piloto.vendor.Config.Conf;
import mguilherme.br.com.piloto.vendor.Utilitarios.Util;

/**
 * Created by guilh on 27/02/2017.
 *
 * CLASSE RESPONSAVEL POR CRIAR OS ARQUIVOS JAVA DINAMICAMENTE
 */

public class CriarArquivoClasse {

    public void criarArquivoJava(String classe,String entidade,String namespace) {

        File diretorio = new File(Conf.getConfig().LOCAL_PROJECT, namespace);
        try{
            if (!diretorio.exists()) {
                diretorio.mkdirs();
                System.out.println("Diretorios criados!");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Ocorreu um erro verifique se o caminho do diretorio especificado está correto!");
        }

        File f = new File(diretorio, Util.capitalizar(entidade)+".java");

        try {
            f.createNewFile();
            System.out.println("Arquivo "+Util.capitalizar(entidade)+".java criado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro verifique se o caminho do diretorio especificado está correto!");
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.append(classe);

        pw.flush();
        pw.close();
    }

}
