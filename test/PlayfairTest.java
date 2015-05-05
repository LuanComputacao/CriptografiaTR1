import com.luancomputacao.cifras.Playfair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by luan on 28/04/15.
 * <p/>
 * Test base to Playfair class
 */
public class PlayfairTest {

    private Playfair playfair;
    private String keyworkd = "Monarchy";
    private String textoClaro = "Noãb ijbnín.";
    private String textoPreparado = "noabixibninx";
    private String textoCifrado = "anbisasiagaw";

    private char[][] matrizmodel = {
            {'m', 'o', 'n', 'a', 'r'},
            {'c', 'h', 'y', 'b', 'd'},
            {'e', 'f', 'g', 'i', 'k'},
            {'l', 'p', 'q', 's', 't'},
            {'u', 'v', 'w', 'x', 'z'}
    };
    private char[][] matrizMaked;
    private String pathFiles = "/var/workspace/Mestrado/Criptografia/tr1/test/files/";
    private String inputFileName = pathFiles + "smallinput.in";
    private String outputFileName = pathFiles + "smallout.out";
    private String testoutputFileName = "files/testsmallout.out";


    @Before
    public void inicializa() {
        this.playfair = new Playfair(keyworkd, textoClaro);
        this.playfair.cifrarTexto();
    }

    // First Element
    @Test
    public void RetornaMatriz() {
        this.matrizMaked = this.playfair.getMatriz();
//        this.imprimeMatrizMaked();
        //noinspection deprecation
        assertEquals(this.matrizmodel, this.matrizMaked);
    }

    public void imprimeMatrizMaked() {
        for (char[] aMatrizMaked : this.matrizMaked) {
            for (char anAMatrizMaked : aMatrizMaked) {
                System.out.print(anAMatrizMaked);
            }
            System.out.println("");
        }
    }

    @Test
    public void deveRetornarTextoPreparado1() {
        assertEquals(this.textoPreparado, this.playfair.getTextoPreparado());
    }

    @Test
    public void deveRetornarTextoCifrado() {
        assertEquals(this.textoCifrado, this.playfair.getTextoCifrado());
    }

    @Test
    public void deveCifrarNovoTexto() {
        assertEquals(this.textoCifrado, this.playfair.cifrarTexto(this.textoClaro));
    }

    @Test
    public void cifraUmArquivoDeTexto() {

        this.playfair.cifraArquivoDeTexto(inputFileName, outputFileName);
    }
}
