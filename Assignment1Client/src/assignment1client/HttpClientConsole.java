package assignment1client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author carl + frede + grace
 * @version 1.0
 * @since 05-29-2019
 */
public class HttpClientConsole {

    private final String USER_AGENT = "Mozilla/5.0";
    
    public void sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL: " + url);
        System.out.println("Response Code: " + responseCode);
        StringBuffer response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(String.format("%s\n", inputLine));
            }
        }
        System.out.println(response.toString());
    }

    public void sendPost(String url, String title, String description, String isbn, String author, String publisher) throws Exception {
        URL obj = new URL(url);
        String params = "title=" + title + "&description=" + description + "&isbn=" + isbn + "&author=" + author + "&publisher=" + publisher;
        byte[] postData = params.getBytes( StandardCharsets.UTF_8 );
         System.out.println(obj.toString());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
           con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(params);
            wr.flush();
        }
        con.connect();
        int response = con.getResponseCode();
        System.out.println(response);
    }

    
   public void sendPut(String url,String id,String title, String description, String isbn, String author,String publisher) throws IOException {
        URL obj = new URL(url);
        System.out.println(obj.toString());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestMethod("PUT");
        OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());

    out.write("id="+id+"&title=" + title + "&" + "description=" + description + "&" +
               "isbn=" + isbn + "&" + "author=" + author + "&" + "publisher=" + publisher);
                out.close();
            //System.out.println("Wrote to data output");
            //con.getInputStream();
            int responseCode = con.getResponseCode();
            if (responseCode == 404 || responseCode == 500)
                System.out.println("Key does not exist on server");
            System.out.println(responseCode);
    }

    public void sendDelete(String url) throws IOException{
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestMethod("DELETE");
        con.connect();
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);
    }
}