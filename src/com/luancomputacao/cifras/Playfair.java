package com.luancomputacao.cifras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luan on 27/04/15.
 */
public class Playfair {
    private String keyword;
    private String text;
    private char[][] matriz = new char[5][5];
    private char alfabeto[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


    public Playfair(String keyword, String text) {
        this.keyword = keyword.toLowerCase().replace("\\s+", "");
        this.text = text;
        fazMatriz();
    }

    public char[][] getMatriz() {
        return this.matriz;
    }

    private void fazMatriz() {
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
}
