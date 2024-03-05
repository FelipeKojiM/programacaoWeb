package org.example;
import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        try{
            System.out.println("Endereco: "+ getViaCep("85813090"));
        }catch (Exception e){
            System.out.println(e);
        }

    }

    private static String getViaCep(String cep) throws Exception{
        URL url = new URL("Http://viacep.com.br/ws/"+cep.replace("-", "").replace("-", "")+"/xml/");

        BufferedReader in = new BufferedReader (new InputStreamReader(url.openStream()));

        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null) result += inputLine;

        in.close();
        return result;

        Cep objCep = new Cep();
        objCep = Cep.unmarshalFromString(result);

        return objCep.toString();
    }
}