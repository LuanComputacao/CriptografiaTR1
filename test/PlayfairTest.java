import com.luancomputacao.cifras.Playfair;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by luan on 28/04/15.
 *
 * Test base to Playfair class
 */
public class PlayfairTest {

    private Playfair playfair;
    private String keyworkd = "Monarchy";
    private String textoClaro = "Noab ijbni";
    private String textoPreparado = "noabixibni";
    private String textoCifrado = "anbisasiag";

    private char[][] matrizmodel = {
            {'m', 'o', 'n', 'a', 'r'},
            {'c', 'h', 'y', 'b', 'd'},
            {'e', 'f', 'g', 'i', 'k'},
            {'l', 'p', 'q', 's', 't'},
            {'u', 'v', 'w', 'x', 'z'}
    };
    private char[][] matrizMaked;


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
        assertEquals(this.textoPreparado, this.playfair.getStrTextoPreparado());
    }

    @Test
    public void deveRetornarTextoCifrado() {
        assertEquals(this.textoCifrado, this.playfair.getTextoCifrado());
    }
}
