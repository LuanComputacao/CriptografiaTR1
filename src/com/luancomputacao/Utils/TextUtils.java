package com.luancomputacao.Utils;

import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by dev8 on 06/05/15.
 */
public class TextUtils {
    public String clearText(String sClearText) {
        sClearText = sClearText.toLowerCase();
        //Espaços em branco
        sClearText = sClearText.replaceAll("\\s", "");
        //Converte caracteres especiais
        sClearText = sClearText.replaceAll("[àÀáÁâÂãÃäÄÅå]", "a");
        sClearText = sClearText.replaceAll("[èÈéÉêÊëË]", "e");
        sClearText = sClearText.replaceAll("[ìÌíÍîÎïÏ]", "i");
        sClearText = sClearText.replaceAll("[òÒóÓôÔõÕöÖ]", "o");
        sClearText = sClearText.replaceAll("[ùÙÚúûÛüÜ]", "u");
        sClearText = sClearText.replaceAll("[çÇ]", "c");
        sClearText = sClearText.replaceAll("[ñÑ]", "n");
        sClearText = sClearText.replaceAll("[ýÝÿŸ]", "y");
        sClearText = sClearText.replaceAll("[ßØøÆæœ]", "x");
        //retira tudo que não é caractere
        sClearText = sClearText.replaceAll("\\W", "");

        return sClearText;
    }

    public String clearTextOfFile(String filePath) {
        Path pathIn = Paths.get(filePath);
        String outTempFile = System.getProperty("java.io.tmpdir") + "/tmp" + pathIn.getFileName().toString();
        System.out.print(outTempFile);
        Path pathWriteTemp = Paths.get(outTempFile);

        return outTempFile;
    }
}
