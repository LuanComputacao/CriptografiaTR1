import com.luancomputacao.cifras.Playfair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by luan on 28/04/15.
 */
public class PlayfairTest {

    private Playfair playfair;

    @Before
    public void inicializa() {
        this.playfair= new Playfair("Monarchy", "Luan");
    }

    @Test
    public void RetornaMatriz(){

        char[][] matrizmodel = {
                {'m', 'o', 'n', 'a', 'r'},
                {'c', 'h', 'y', 'b', 'd'},
                {'e', 'f', 'g', 'i', 'k'},
                {'l', 'p', 'q', 's', 't'},
                {'u', 'v', 'w', 'x', 'z'}
        };

        assertEquals(matrizmodel, this.playfair.getMatriz());
    }

}
