package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author viorel
 */
public class Test {

    
     public static void main(String[] args) throws JSONException {
        Scanner input = new Scanner(System.in);
        System.out.println("Introdu IP adresa... (ex: 192.168.1.1)");
        String ip = input.nextLine();
        getIpSite(ip);
    }

    public static String getIpSite(String ip) throws JSONException {
        String output = getUrlContent("http://ip-api.com/json/" + ip);
        if (!output.isEmpty()) {
            JSONObject obj = new JSONObject(output);
            if (!obj.get("status").equals("fail")) {

                try {
                    System.out.println("");
                    System.out.println("=======  Rezultatul scanari  =======");
                    System.out.println("Organizatie: " + obj.getString("org"));
                    System.out.println("Country: " + obj.getString("country"));
                    System.out.println("Region: " + obj.getString("regionName"));
                    System.out.println("City: " + obj.getString("city"));
                    System.out.println("Cod zip: " + obj.getString("zip"));
                    System.out.println(" --------------------------------");
                    System.out.println("Zona: " + obj.getString("timezone"));
                    System.out.println("Country Code: " + obj.getString("countryCode"));
                    System.out.println("Latitudine: " + obj.getDouble("lat"));
                    System.out.println("Longitudine: " + obj.getDouble("lon"));
                    System.out.println("============ FINISH! ================");
                    System.out.println("");
                } catch (JSONException e) {
                    System.out.println("EROARE, nu exista adresa " + ip);
                }
            } else {
                System.out.println("************* EROARE ****************");
                System.out.println("Ai introdus o adresa IP ce nu exista: " + ip);
                System.out.println("************* ________ ****************");
            }
//                Thread.sleep(5000);
        }
        return " FINISH! ";
    }

    private static String getUrlContent(String linck) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(linck);
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + " \n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Eroare " + e.toString());
        }
        return content.toString();
    }

}
