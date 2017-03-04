package mguilherme.br.com.piloto.vendor.Config;

/* Classe para definir qual o arquivo de configuracao
   caso tenha mais de um
*/
public class Conf {
    public static final ConfigPadrao config = new ConfigPadrao();
    public static final ConfigPadrao getConfig(){return config;}
}
