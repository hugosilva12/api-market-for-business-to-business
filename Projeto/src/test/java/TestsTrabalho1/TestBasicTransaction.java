package TestsTrabalho1;

import ModuleTransaction.Orgcom.BasicEntity;
import ModuleTransaction.Orgcom.BasicTransaction;
import ModuleTransaction.Orgcom.BasicTransactionLine;
import ModuleTransaction.Orgcom.District;
import ModuleTransaction.Orgcom.hashing.UnHashableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBasicTransaction {
    private BasicEntity senderEntity;
    private BasicEntity receiverEntity;
    private BasicTransaction basicTransaction;

    @BeforeEach
    void setup() {
        this.senderEntity = new BasicEntity("Sender", District.VIANA_CASTELO);
        this.receiverEntity = new BasicEntity("Receiver", District.VIANA_CASTELO);
        this.basicTransaction = new BasicTransaction(senderEntity, receiverEntity);
    }
    //Construtor BASIC TRANSACTION
    @Test
    public void testBasicTransactionBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> new BasicTransaction(null, null));
    }

    @Test
    public void testBasicTransactionECP01() {
        BasicTransaction basicTransaction1 = new BasicTransaction(this.senderEntity, this.receiverEntity);
        assertTrue(basicTransaction1 instanceof BasicTransaction);
    }


    //ADD TRANSACTION
    @Test
    public void testAddTransactionLineBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> basicTransaction.addTransactionLine(null));
    }

    @Test
    public void testAddTransactionLineECP01() {
        BasicTransaction basicTransaction1 = new BasicTransaction(this.senderEntity, this.receiverEntity);
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("linha1", 1, 2);
        assertTrue(basicTransaction1.addTransactionLine(basicTransactionLine), "\"\" should be true");
    }

    @Test
    public void testAddTransactionLineECP02() {
        BasicTransaction basicTransaction1 = new BasicTransaction(this.senderEntity, this.receiverEntity);
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("linha1", 1, 2);
        basicTransaction1.addTransactionLine(basicTransactionLine);
        assertFalse(basicTransaction1.addTransactionLine(basicTransactionLine), "\"\" should be false");
    }
    //REMOVE TRANSACTION
    @Test
    public void testRemoveTransactionLineBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> basicTransaction.removeTransactionLine(null));
    }

    @Test
    public void testRemoveTransactionLineBVA02() throws IllegalArgumentException {
        BasicTransaction basicTransaction1 = new BasicTransaction(this.senderEntity, this.receiverEntity);
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("linha1", 1, 2);
        assertFalse(basicTransaction1.removeTransactionLine(basicTransactionLine), "\"\" should be false");
    }

    @Test
    public void testRemoveTransactionLineECP01() {
        BasicTransaction basicTransaction1 = new BasicTransaction(this.senderEntity, this.receiverEntity);
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("linha1", 1, 2);
        basicTransaction1.addTransactionLine(basicTransactionLine);
        assertTrue(basicTransaction1.removeTransactionLine(basicTransactionLine), "\"\" should be true");
    }

    @Test
    public void testRemoveTransactionLineECP02() {
        BasicTransaction basicTransaction1 = new BasicTransaction(this.senderEntity, this.receiverEntity);
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("linha1", 1, 2);
        BasicTransactionLine basicTransactionLineRemover = new BasicTransactionLine("linha2", 1, 2);
        basicTransaction1.addTransactionLine(basicTransactionLine);
        assertFalse(basicTransaction1.removeTransactionLine(basicTransactionLineRemover), "\"\" should be false");
    }
   //GetTransaction
    @Test
    public void testGetTransactionLineBVA01() {
        assertThrows(UnHashableException.class, () -> basicTransaction.getTransactionLine(null));
    }

    @Test
    public void testGetTransactionLineECP01() {
        BasicTransaction basicTransaction1 = new BasicTransaction(this.senderEntity, this.receiverEntity);
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("linha1", 1, 2);
        basicTransaction1.addTransactionLine(basicTransactionLine);
        assertTrue(basicTransaction1.getTransactionLine(basicTransactionLine) instanceof BasicTransactionLine,"\"\" should be true");
    }

    @Test
    public void testGetTransactionLineECP02() {
        BasicTransaction basicTransaction1 = new BasicTransaction(this.senderEntity, this.receiverEntity);
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("linha1", 1, 2);
        assertNull(basicTransaction1.getTransactionLine(basicTransactionLine),"\"\" should be null");
    }

    @Test
    public void testGetTotalValueECP01() {
        Double expected= 6.0;
        BasicTransaction basicTransaction = new BasicTransaction(this.senderEntity, this.receiverEntity);
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("linha1", 1, 2.0);
        BasicTransactionLine basicTransactionLine2 = new BasicTransactionLine("linha2", 1, 2.0);
        BasicTransactionLine basicTransactionLine3 = new BasicTransactionLine("linha3", 1, 2.0);
        basicTransaction.addTransactionLine(basicTransactionLine);
        basicTransaction.addTransactionLine(basicTransactionLine2);
        basicTransaction.addTransactionLine(basicTransactionLine3);
        assertEquals(expected,basicTransaction.getTotalValue());
    }

    @Test
    public void testGetTransactionCountECP01() {
        BasicTransaction basicTransaction = new BasicTransaction(this.senderEntity, this.receiverEntity);
        BasicTransactionLine basicTransactionLine1 = new BasicTransactionLine("linha1", 1, 2.0);
        BasicTransactionLine basicTransactionLine2 = new BasicTransactionLine("linha2", 1, 2.0);
        BasicTransactionLine basicTransactionLine3 = new BasicTransactionLine("linha3", 1, 2.0);
        basicTransaction.addTransactionLine(basicTransactionLine1);
        basicTransaction.addTransactionLine(basicTransactionLine2);
        basicTransaction.addTransactionLine(basicTransactionLine3);
        int expected = 3;
        assertEquals(expected, basicTransaction.getTransactionCounter(), "should be 3");
    }

}
