import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class ClaseMain {
    private static final String APIPASS = "50b0449c210d4374837a86ad4d6042dc"; 
    private static final String IDIOMA = "es";
    private static final String Ciudad = "Madrid";
    private static final String Pais = "ES";

    private static final String BASE_URL = "https://api.weatherbit.io/v2.0/current";

    public static void main(String[] args) throws JSONException {
        InterfazAWT aplicacion = new InterfazAWT();
        obtenerDatos(aplicacion);
    }

    private static void obtenerDatos(InterfazAWT aplicacion) throws JSONException {
        double temperatura = 0;
        String ciudad = "", pais = "", hora = "", descripcion = "";

        try {
            String jsonResponse = getWeatherData();
            JSONObject jsonObject = new JSONObject(jsonResponse);

            JSONObject weatherData = jsonObject.getJSONArray("data").getJSONObject(0);
            temperatura = weatherData.getDouble("temp");
            ciudad = weatherData.getString("city_name");
            pais = weatherData.getString("country_code");
            hora = weatherData.getString("ob_time");
            descripcion = weatherData.getJSONObject("weather").getString("description");


        } catch (IOException e) {
            e.printStackTrace();
        }

        aplicacion.datos(descripcion, temperatura, ciudad, pais, hora);
    }

    private static String getWeatherData() throws IOException {
        String apiUrl = BASE_URL + "?key=" + APIPASS + "&lang=" + IDIOMA + "&city=" + Ciudad + "&country=" + Pais;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        return response.toString();
    }
}
