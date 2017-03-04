package mguilherme.br.com.piloto.vendor.Entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import mguilherme.br.com.piloto.vendor.Config.Conf;
/**
 * Created by guilh on 27/02/2017.
 *
 * CLASSE RESPONSAVEL POR CRIAR OS ARQUIVOS JAVA DINAMICAMENTE
 */

public class CriarArquivoBanco {

    public void criarArquivoBdJava(String estrutura){

        File diretorio = new File(Conf.getConfig().LOCAL_PROJECT,"\\Database\\");

        try {
            if (!diretorio.exists()) {
                diretorio.mkdirs();
                System.out.println("Diretorio do banco criado!");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Ocorreu um erro verifique se o caminho do diretorio especificado está correto!");
        }
        File f = new File(diretorio, "DatabaseHelper.java");

        try {
            f.createNewFile();
            System.out.println("Arquivo DatabaseHelper.java criado!");
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
        pw.append(estrutura);

        pw.flush();
        pw.close();
    }

}
