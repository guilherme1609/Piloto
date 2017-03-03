package mguilherme.br.com.piloto.vendor.Config;

/* Classe para definir qual o arquivo de configuracao
   caso tenha mais de um
*/
public class Conf {
    public static final ConfigGuilherme config = new ConfigGuilherme();
    public static final ConfigGuilherme getConfig(){return config;}
}
