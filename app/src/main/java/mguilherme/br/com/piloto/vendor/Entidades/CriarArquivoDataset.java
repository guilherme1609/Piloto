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

public class CriarArquivoDataset {

    public void criarArquivoDatasetJava(String estrutura){

        File diretorio = new File(Conf.getConfig().LOCAL_PROJECT,"\\Database\\");

        if(!diretorio.exists()) {
            diretorio.mkdirs();
            System.out.println("Diretorio do banco criado!");
        }

        File f = new File(diretorio, "DatasetHelper.java");

        try {
            f.createNewFile();
            System.out.println("Arquivo DatasetHelper.java criado!");
        } catch (IOException e) {
            e.printStackTrace();
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
