import com.luancomputacao.cifras.transposicao.TrasposicaoAlbini;
import org.junit.Test;

/**
 * Aplica
 */
public class TransposicaoTest {


    TrasposicaoAlbini transposicao;
    private String pathFiles = System.getProperty("user.dir") + "/test/files/";
    private String inputFileName = pathFiles + "smallinput.in";

    @Test
    public void deveCifrarArquivo() {
        transposicao = new TrasposicaoAlbini(inputFileName, "4312567");
    }


}
