import com.luancomputacao.cifras.Playfair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by luan on 28/04/15.
 *
 * Test base to Playfair class
 */
public class PlayfairTest {

    private Playfair playfair;
    private String keyworkd = "Monarchy";
    private String textoClaro = "Luan nnb";
    private String textoPreparado = "luannxnb";
    private Playfair playfair2;
    private String textoClaro2 = "balloon";
    private String textoPreparado2 = "balxloon";
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
        this.playfair2 = new Playfair(keyworkd, textoClaro2);
    }

    @Test
    public void RetornaMatriz() {
        this.matrizMaked = this.playfair.getMatriz();
//        this.imprimeMatrizMaked();
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
    public void deveRetornarTextoPreparado2() {
        assertEquals(this.textoPreparado2, this.playfair2.getStrTextoPreparado());
    }
}
