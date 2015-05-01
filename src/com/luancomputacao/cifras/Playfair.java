package com.luancomputacao.cifras;

import java.util.*;

/**
 * Created by luan on 27/04/15.
 * <p/>
 * This class is to cipher a text with Playfair method
 */
public class Playfair {
    private String keyword;
    private String textoClaro;
    private char[][] matriz = new char[5][5];
    private HashMap<String, int[]> charMap = new HashMap<>();
    private char alfabeto[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private String strTextoPreparado = null;
    private Character x = 'x';
    private List<Character> lstTextoPreparado = new ArrayList<>();
    private List<Character> lstTextoCifrado = new ArrayList<>();
    private String textoCifrado;


    /*------------------------------------------------------------------------------------------------------------------
    | Cosntructor
    ------------------------------------------------------------------------------------------------------------------*/
    public Playfair(String keyword, String textoClaro) {
        this.setKeyword(keyword);
        this.textoClaro = textoClaro;
    }

    /*------------------------------------------------------------------------------------------------------------------
    | Getters e setters
    ------------------------------------------------------------------------------------------------------------------*/

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword.toLowerCase().replace("\\s+", "");
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

    private void setCharMap() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                charMap.put(String.valueOf(matriz[i][j]), new int[]{i, j});
            }
        }
    }

    public char[] getAlfabeto() {
        return alfabeto;
    }

    private void setStrTextoPreparado() {
        this.strTextoPreparado = this.lstTextoPreparado.toString().replaceAll("[\\n, \\[\\]]", "");
    }

    private void setStrTextoCifrado() {
        this.strTextoPreparado = this.lstTextoCifrado.toString().replaceAll("[\\n, \\[\\]]", "");
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

    public String getStrTextoCifrado() {
        this.setStrTextoCifrado();
        return this.strTextoPreparado;
    }

    public String getTextoCifrado() {
        this.textoCifrado = this.getStrTextoCifrado();
        return textoCifrado;
    }

    /*------------------------------------------------------------------------------------------------------------------
    | Not Default Getters
    ------------------------------------------------------------------------------------------------------------------*/
    private int getLinePlayfair(String letter) {
        return charMap.get(letter)[0];
    }

    private int getColPlayfair(String letter) {
        return charMap.get(letter)[1];
    }

    private String getLetterCharMap(int[] position) {
        return Arrays.toString(this.charMap.get(position));
    }



    /*------------------------------------------------------------------------------------------------------------------
    | Metodos
    ------------------------------------------------------------------------------------------------------------------*/

    private void setMatriz() {
        char[] arrKeyword = this.keyword.toCharArray();
        ArrayList<Character> lstKeyword = new ArrayList<>();

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
        strTemp = strTemp.replaceAll("j", "i");

        for (int i = 0; i < strTemp.length() - 1; i += 2) {
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

    public void cifrarTexto() {
        this.preparaTextoClaro();
        this.setMatriz();
        this.setCharMap();
        this.embaralhaCaracteres();
    }

    private void embaralhaCaracteres() {
        int temp;

        for (int i = 0; i < this.lstTextoPreparado.size(); i += 2) {

            String letra1 = this.lstTextoPreparado.get(i).toString();
            int line1 = getLinePlayfair(letra1);
            int col1 = getColPlayfair(letra1);

            String letra2 = this.lstTextoPreparado.get(i + 1).toString();
            int line2 = getLinePlayfair(letra2);
            int col2 = getColPlayfair(letra2);

            if (line1 == line2) {
                col1 = ++col1 % 5;
                col2 = ++col2 % 5;
            } else if (col1 == col2) {
                line1 = ++line1 % 5;
                line2 = ++line2 % 5;
            } else {
                temp = col1;
                col1 = col2;
                col2 = temp;
            }
            this.lstTextoCifrado.add(this.matriz[line1][col1]);
            this.lstTextoCifrado.add(this.matriz[line2][col2]);
        }
        this.textoCifrado = this.getStrTextoPreparado();
    }
}
