package TestsTrabalho1;

import ModuleTransaction.Orgcom.BasicTransactionLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBasicTransactionLine {
    //Construtor BASIC TRANSACTION
    @Test
    public void testBasicTransactionLineBVA01() {
        assertThrows(IllegalArgumentException.class, () -> new BasicTransactionLine("", 1,3));
    }

    @Test
    public void testBasicTransactionLineBVA02() {
        assertThrows(IllegalArgumentException.class, () -> new BasicTransactionLine("transacao", -1,3));
    }

    @Test
    public void testBasicTransactionLineBVA03() {
        assertThrows(IllegalArgumentException.class, () -> new BasicTransactionLine("transacao", 1,-0.1));
    }
    @Test
    public void testBasicTransactionLineBVA04() {
        assertThrows(IllegalArgumentException.class, () -> new BasicTransactionLine(null, 1,3));
    }
    @Test
    public void testBasicTransactionLineBVA05() {
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("transacao", 1,0);
        assertTrue(basicTransactionLine instanceof BasicTransactionLine,"\"\" should be true");
    }

    @Test
    public void testBasicTransactionLineBVA06() {
        assertThrows(IllegalArgumentException.class, () -> new BasicTransactionLine("transacao", 0,1));

    }

    @Test
    public void testBasicTransactionLineECP01() {
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("transacao", 4,1);
        assertTrue(basicTransactionLine instanceof BasicTransactionLine,"\"\" should be true");
    }

}
