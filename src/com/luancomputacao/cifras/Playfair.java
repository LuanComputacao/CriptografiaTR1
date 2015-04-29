package com.luancomputacao.cifras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luan on 27/04/15.
 *
 * This class is to cipher a text with Playfair method
 */
public class Playfair {
    private String keyword;
    private String textoClaro;
    private char[][] matriz = new char[5][5];
    private char alfabeto[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private String strTextoPreparado = null;
    private Character x = 'x';
    private List<Character> lstTextoPreparado = new ArrayList<Character>();


    public Playfair(String keyword, String textoClaro) {
        this.keyword = keyword.toLowerCase().replace("\\s+", "");
        this.textoClaro = textoClaro;
        this.montaMatriz();
        this.preparaTextoClaro();
        this.cifrarTexto();
    }

    /*------------------------------------------------------------------------------------------------------------------
    | Getters e setters
    ------------------------------------------------------------------------------------------------------------------*/

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTextoClaro() {
        return textoClaro;
    }

    public void setTextoClaro(String textoClaro) {
        this.textoClaro = textoClaro;
    }

    public char[][] getMatriz() {
        return this.matriz;
    }

    public char[] getAlfabeto() {
        return alfabeto;
    }

    private void setStrTextoPreparado() {
        this.strTextoPreparado = lstTextoPreparado.toString().replaceAll("[\\n, \\[\\]]", "");
    }

    public List<Character> getLstTextoPreparado() {
        return lstTextoPreparado;
    }

    public void setLstTextoPreparado(List<Character> lstTextoPreparado) {
        this.lstTextoPreparado = lstTextoPreparado;
    }

    public String getStrTextoPreparado() {
        this.setStrTextoPreparado();
        return this.strTextoPreparado;
    }

     /*------------------------------------------------------------------------------------------------------------------
    | Metodos
    ------------------------------------------------------------------------------------------------------------------*/

    private void montaMatriz() {
        char[] arrKeyword = this.keyword.toCharArray();
        ArrayList<Character> lstKeyword = new ArrayList<Character>();

        for (char anArrKeyword : arrKeyword) {
            if (!lstKeyword.contains(anArrKeyword)) {
                lstKeyword.add(anArrKeyword);
            }
        }

        for (char anAlfabeto : alfabeto) {
            if (!lstKeyword.contains(anAlfabeto)) {
                lstKeyword.add(anAlfabeto);
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.matriz[i][j] = lstKeyword.get(i * 5 + j);
            }
        }
    }

    private void preparaTextoClaro() {
        String strTemp = this.textoClaro.toLowerCase().replaceAll("\\s", "");

        for (int i = 0; i < strTemp.length()-1; i+=2) {
            if (strTemp.charAt(i) == strTemp.charAt(i + 1)) {
                lstTextoPreparado.add(strTemp.charAt(i));
                lstTextoPreparado.add(x);
                i--;
            } else {
                lstTextoPreparado.add(strTemp.charAt(i));
                lstTextoPreparado.add(strTemp.charAt(i + 1));
            }
        }
        this.strTextoPreparado = strTemp;
    }

    private void cifrarTexto() {
        List<char[]> lstMatriz = Arrays.asList(this.matriz);
        System.out.println(lstMatriz);
    }
}
