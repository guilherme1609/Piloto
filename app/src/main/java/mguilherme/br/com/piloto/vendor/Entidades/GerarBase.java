package mguilherme.br.com.piloto.vendor.Entidades;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import mguilherme.br.com.piloto.vendor.Config.Conf;

public class GerarBase {

    public static void main(String[] args) throws JSONException {

        try {
            JSONParser parser = new JSONParser();

            JSONObject jsonMapaEntidades = (JSONObject) parser.parse(new FileReader(Conf.getConfig().LOCAL_MAPA_ENTIDADES));

            GerarClasses gerarClasses = new GerarClasses();
            gerarClasses.construirClasse(jsonMapaEntidades);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
