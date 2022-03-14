import ModuleTransaction.Orgcom.BasicEntity;
import ModuleTransaction.Orgcom.District;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBasicEntity {
    private final int VALOR_LIMITE = 2147483647;
    private BasicEntity basicEntity;

    @BeforeEach
    void setup() {
        this.basicEntity = new BasicEntity("Test", District.VIANA_CASTELO);
    }

    //TEST CONSTRUTORES
    @Test
    public void testBasicEntityBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new BasicEntity( "", null));
    }

    @Test
    public void testBasicEntityBVA02() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new BasicEntity( null, null));
    }

    @Test
    public void testBasicEntityECP01() {
        BasicEntity basicEntity1 = new BasicEntity("Entidade1", District.BEJA);
        assertTrue(basicEntity1 instanceof BasicEntity, "\"\" should be true");
    }

    ///ADD TOKENS
    @Test
    public void testAddTokenBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> basicEntity.addTokens(-1));
    }

    @Test
    public void testAddTokenBVA02() {
        int expected = this.basicEntity.getTokens();
        Assertions.assertEquals(expected, this.basicEntity.addTokens(0), "\"\" should be true");
    }

    @Test
    public void testAddTokenBVA03() {
        int expected = this.basicEntity.getTokens()+ VALOR_LIMITE;
        Assertions.assertEquals(expected, this.basicEntity.addTokens(VALOR_LIMITE), "\"\" should be true");
    }
    @Test
    public void testAddTokenECP01() {
        int expected = 3 + this.basicEntity.getTokens();
        Assertions.assertEquals(expected, this.basicEntity.addTokens(3), "\"\" should be true");
    }


}
