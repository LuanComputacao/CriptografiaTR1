import com.luancomputacao.cifras.Playfair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by luan on 28/04/15.
 */
public class PlayfairTest {

    private Playfair playfair;
    private String keyworkd = "Monarchy";
    private String textoClaro = "Luan nnb";
    private String textoPreparado = "luannxnb";
    char[][] matrizmodel = {
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
    }

    @Test
    public void RetornaMatriz() {
        this.matrizMaked = this.playfair.getMatriz();
//        this.imprimeMatrizMaked();
        assertEquals(this.matrizmodel, this.matrizMaked);
    }

    public void imprimeMatrizMaked() {
        for (char[] aMatrizMaked : this.matrizMaked) {
            for (int j = 0; j < aMatrizMaked.length; j++) {
                System.out.print(aMatrizMaked[j]);
            }
            System.out.println("");
        }
    }

    @Test
    public void deveRetornarTextoPreparado(){
        assertEquals(this.textoPreparado, this.playfair.getStrTextoPreparado());
    }
}
