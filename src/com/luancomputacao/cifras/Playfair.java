package com.luancomputacao.cifras;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luan on 27/04/15.
 */
public class Playfair {
    private String keyword;
    private String textoClaro;
    private char[][] matriz = new char[5][5];
    private char alfabeto[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private String strTextoPreparado;
    private Character x = 'x';
    private List<Character> lstTextoPreparado = new ArrayList<Character>();


    public Playfair(String keyword, String textoClaro) {
        this.keyword = keyword.toLowerCase().replace("\\s+", "");
        this.textoClaro = textoClaro;
        montaMatriz();
        this.preparaTextoClaro();
    }

    public char[][] getMatriz() {
        return this.matriz;
    }

    public String getStrTextoPreparado() {
        this.strTextoPreparado = lstTextoPreparado.toString().replaceAll("[\\n, \\[\\]]", "");
        return this.strTextoPreparado;
    }

    private void montaMatriz() {
        char[] arrKeyword = this.keyword.toCharArray();
        ArrayList<Character> lstKeyword = new ArrayList<Character>();
        for (int count = 0; count < arrKeyword.length; count++) {
            if (!lstKeyword.contains(arrKeyword[count])) {
                lstKeyword.add(arrKeyword[count]);
            }
        }

        for (int count = 0; count < alfabeto.length; count++) {
            if (!lstKeyword.contains(alfabeto[count])) {
                lstKeyword.add(alfabeto[count]);
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
        System.out.println();
        this.strTextoPreparado = strTemp.toString();
    }
}
