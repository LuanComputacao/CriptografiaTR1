package com.luancomputacao.cifras.playfair;

import com.luancomputacao.Utils.TextUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
    private List<Character> lstTextoPreparado = new ArrayList<>();
    private List<Character> lstTextoCifrado = new ArrayList<>();
    private String strTextoCifrado;

    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private long numOfChars = 0;


    /*------------------------------------------------------------------------------------------------------------------
    | Cosntructor
    ------------------------------------------------------------------------------------------------------------------*/
    public Playfair(String keyword, String textoClaro) {
        this.setKeyword(keyword);
        this.setTextoClaro(textoClaro);
    }

    /*------------------------------------------------------------------------------------------------------------------
    | Getters e setters
    ------------------------------------------------------------------------------------------------------------------*/

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword.toLowerCase().replace("\\s+", "");
        this.setMatriz();
    }

    public String getTextoClaro() {
        return textoClaro;
    }

    void setTextoClaro(String textoClaro) {
        this.textoClaro = textoClaro;
    }

    private void setTextoPreparado() {

        String strTemp = new TextUtils().clearText(this.getTextoClaro());

        //une j e i (Regra de playfair)
        strTemp = strTemp.replaceAll("j", "i");

        if ((strTemp.length() % 2) == 0) {
            strTemp += "x";
        }
        this.separeteDoubleChar(strTemp);
    }

    public char[][] getMatriz() {
        return this.matriz;
    }

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
        this.setCharMap();
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
        this.strTextoCifrado = this.lstTextoCifrado.toString().replaceAll("[\\n, \\[\\]]", "");
    }

    public String getTextoPreparado() {
        this.setStrTextoPreparado();
        return this.strTextoPreparado;
    }

    public String getTextoCifrado() {
        this.setStrTextoCifrado();
        return this.strTextoCifrado;
    }

    public List<Character> getLstTextoCifrado() {
        return this.lstTextoCifrado;
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

    /*------------------------------------------------------------------------------------------------------------------
    | Metodos
    ------------------------------------------------------------------------------------------------------------------*/

    public void cifrarTexto() {
        this.setTextoPreparado();
        this.embaralhaStringTexto();
    }

    private void resetLsts() {
        this.lstTextoCifrado = new ArrayList<>();
        this.lstTextoPreparado = new ArrayList<>();
    }

    public String cifrarTexto(String textoClaro) {
        this.resetLsts();
        this.setTextoClaro(textoClaro);
        this.setTextoPreparado();
        this.embaralhaStringTexto();
        return this.getTextoCifrado();
    }

    private void embaralhaStringTexto() {
        String cipher;
        for (int i = 0; i < this.lstTextoPreparado.size(); i += 2) {
            cipher = this.trocaCaracteres(this.lstTextoPreparado.get(i).toString(), this.lstTextoPreparado.get(i + 1).toString());
            this.lstTextoCifrado.add(cipher.charAt(0));
            this.lstTextoCifrado.add(cipher.charAt(1));
        }
    }

    private void separeteDoubleChar(String strTemp) {
        for (int i = 0; i < strTemp.length() - 1; i += 2) {
            if (strTemp.charAt(i) == strTemp.charAt(i + 1)) {
                this.lstTextoPreparado.add(strTemp.charAt(i));
                this.lstTextoPreparado.add('x');
                i--;
            } else {
                this.lstTextoPreparado.add(strTemp.charAt(i));
                this.lstTextoPreparado.add(strTemp.charAt(i + 1));
            }
        }
    }

    private String trocaCaracteres(String letra1, String letra2) {
        int temp;
        int line1 = getLinePlayfair(letra1);
        int col1 = getColPlayfair(letra1);

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
        return this.matriz[line1][col1] + "" + this.matriz[line2][col2];

    }

    public void cifraArquivoDeTexto(String inputFileName, String outputFileName) {
        String outTempFile = System.getProperty("java.io.tmpdir") + "/tmpPlayFairout.out";

        Path path = Paths.get(inputFileName);
        Path pathWriteTemp = Paths.get(outTempFile);
        Path pathWriteCipher = Paths.get(outputFileName);
        String text;
        try {

            //@Todo Create "public String clearTextOfFile(filePath){}" returning the path of temporary file with prefixing tmp in the name of input file
            BufferedWriter writer = Files.newBufferedWriter(pathWriteTemp, ENCODING);
            Scanner scanner = new Scanner(path, ENCODING.name());

            //Faz a leitura das linhas, limpa o texto e grava em arquivo temporário
            while (scanner.hasNextLine()) {
                text = new TextUtils().clearText(scanner.nextLine());
                writer.write(text);
            }
            writer.close();

            //Faz a leitura do arquivo temporário para separar os caracters iguais
            FileInputStream fileInputStream = new FileInputStream(pathWriteTemp.toString());
            int r;
            char[] digraph = new char[2];
            digraph[0] = ' ';
            char tempC;

            BufferedWriter writeCipher = Files.newBufferedWriter(pathWriteCipher, ENCODING);
            while ((r = fileInputStream.read()) != -1) {
                char c = (char) r;
                if ((numOfChars % 2 == 0) && (digraph[0] == ' ')) {
                    digraph[0] = c;
                } else {
                    digraph[1] = c;
                    if (digraph[0] == digraph[1]) {
                        tempC = digraph[1];
                        digraph[1] = 'x';
                        writeCipher.write(trocaCaracteres(String.valueOf(digraph[0]), String.valueOf(digraph[1])));
                        digraph[0] = tempC;
                        numOfChars++;
                    } else {
                        writeCipher.write(trocaCaracteres(String.valueOf(digraph[0]), String.valueOf(digraph[1])));
                        digraph[0] = ' ';
                    }
                }
                numOfChars++;
            }

            fileInputStream.close();
            if (!(numOfChars % 2 == 0)) {
                writeCipher.write(trocaCaracteres(String.valueOf(digraph[0]), "x"));
            }
            writeCipher.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
