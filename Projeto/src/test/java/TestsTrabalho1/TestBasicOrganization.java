package TestsTrabalho1;

import ModuleTransaction.Orgcom.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBasicOrganization {
    private BasicOrganization basicOrganization;
    private BasicEntity sender, receiver;


    @BeforeEach
    void setUp() {
        this.sender = new BasicEntity("Sender", District.AVEIRO);
        this.receiver = new BasicEntity("Receiver", District.AVEIRO);
        this.basicOrganization = new BasicOrganization();
    }

    @Test
    public void testAddTransactionBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> basicOrganization.addTransaction(null));
    }

    @Test
    public void testAddTransactionECP01() {
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        assertTrue(basicOrganization.addTransaction(basicTransaction), "\"\" should be true");
    }

    @Test
    public void testAddTransactionECP02() {
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        basicOrganization.addTransaction(basicTransaction);
        assertFalse(basicOrganization.addTransaction(basicTransaction), "\"\" should be false");
    }

    @Test
    public void testAddTransactionECP03() {
        sender.addTokens(1);
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        basicOrganization.addTransaction(basicTransaction);
        basicOrganization.registerTransactionsInLedger();
        assertTrue(basicOrganization.addTransaction(basicTransaction), "\"\" should be true");
    }

    @Test
    public void testAddTransactionECP04() {
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        basicOrganization.addTransaction(basicTransaction);
        basicOrganization.registerTransactionsInLedger();
        assertFalse(basicOrganization.addTransaction(basicTransaction), "\"\" should be false");
    }

    @Test
    public void testRemoveTransactionBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> basicOrganization.removeTransaction(null));
    }

    @Test
    public void testRemoveTransactionBVA02() {
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        assertFalse(basicOrganization.removeTransaction(basicTransaction), "\"\" should be false");
    }

    @Test
    public void testRemoveTransactionECP01() {
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        basicOrganization.addTransaction(basicTransaction);
        assertTrue(basicOrganization.removeTransaction(basicTransaction), "\"\" should be true");
    }

    @Test
    public void testRemoveTransactionECP02() {
        sender.addTokens(1);
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        basicOrganization.addTransaction(basicTransaction);
        basicOrganization.registerTransactionsInLedger();
        assertFalse(basicOrganization.removeTransaction(basicTransaction), "\"\" should be false");
    }

    @Test
    public void testGetTransactionBVA01() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> basicOrganization.getTransaction(null));
    }

    @Test
    public void testGetTransactionECP01() {
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        basicOrganization.addTransaction(basicTransaction);
        assertTrue(basicOrganization.getTransaction(basicTransaction) instanceof BasicTransaction, "\"\" should be true");
    }

    @Test
    public void testGetTransactionECP02() {
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        assertNull(basicOrganization.getTransaction(basicTransaction), "\"\" should be null");
    }

    //GET BLOCKSCOUNTS
    @Test
    public void testGetBlockCountECP01() {
        sender.addTokens(1); // Tokens validos
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver); // Transaçãp
        this.basicOrganization.addTransaction(basicTransaction); // add
        this.basicOrganization.registerTransactionsInLedger(); // register
        assertEquals(2, basicOrganization.getBlockCount(), "should be 2");
    }

    @Test
    public void testGetBlockCountECP02() {
        BasicTransaction basicTransaction = new BasicTransaction(sender, sender);
        basicOrganization.addTransaction(basicTransaction);
        basicOrganization.registerTransactionsInLedger();
        assertEquals(1, basicOrganization.getBlockCount(), "should be 1");
    }

    // Test REGISTER
    @Test
    public void testRegisterTransactionsInLedgerECP01() {
        sender.addTokens(1);
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver); // Transaçãp
        basicOrganization.addTransaction(basicTransaction); // add
        int expected = 1;
        assertEquals(expected, basicOrganization.registerTransactionsInLedger(), "should be 1");
    }

    @Test
    public void testRegisterTransactionsInLedgerECP02() {
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver); // Transaçãp
        basicOrganization.addTransaction(basicTransaction); // add
        int expected = 0;
        assertEquals(expected, basicOrganization.registerTransactionsInLedger());
    }

    //GET BLOCKS
    @Test
    public void testGetBlockBVA01() {
        assertTrue(basicOrganization.getBlock(0) instanceof Block, "should be True");
    }

    @Test
    public void testGetBlockBVA02() {
        assertThrows(IndexOutOfBoundsException.class, () -> basicOrganization.getBlock(basicOrganization.getBlockCount()));
    }

    @Test
    public void testGetBlockBVA03() throws IndexOutOfBoundsException {
        assertThrows(IndexOutOfBoundsException.class, () -> basicOrganization.getBlock(-1));
    }


    @Test
    public void testGetBlockECP01() {
        sender.addTokens(2);
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        basicOrganization.addTransaction(basicTransaction);
        basicOrganization.registerTransactionsInLedger();
        basicOrganization.addTransaction(basicTransaction);
        basicOrganization.registerTransactionsInLedger();
        assertTrue(basicOrganization.getBlock(1) instanceof BasicBlock, "should be True");
    }

    //VERIFICA SE API FAZ VERIFICAÇAO CORRETA BLOCOS INVALIDOS
    @Test
    public void testIsValidEdgerBVA01() {
        BasicTransactionLine basicTransactionLine = new BasicTransactionLine("transacao", 1, 0);
        BasicTransaction basicTransaction = new BasicTransaction(sender, receiver);
        basicTransaction.addTransactionLine(basicTransactionLine);
        sender.addTokens(1);
        //Adiciona e regista
        basicOrganization.addTransaction(basicTransaction);
        basicOrganization.registerTransactionsInLedger();
        basicTransaction.removeTransactionLine(basicTransactionLine);
        //assertTrue(basicOrganization.getBlock(0).wasTampered()); //Bloco Alterado
        assertFalse(basicOrganization.isValidLedger(), "\"\" should be false");
    }

    @Test
    public void testIsValidEdgerBVA02() {
        BasicTransaction basicTransaction = null;
        BasicTransactionLine basicTransactionLine = null;
        for (int i = 0; i < 2; i++) {
            basicTransactionLine = new BasicTransactionLine(String.valueOf(i), 1, 0);
            basicTransaction = new BasicTransaction(receiver, sender);
            basicTransaction.addTransactionLine(basicTransactionLine);
            receiver.addTokens(1);
            basicOrganization.addTransaction(basicTransaction);
            basicOrganization.registerTransactionsInLedger();
        }
        basicTransaction.removeTransactionLine(basicTransactionLine);
        assertTrue(basicOrganization.getLastBlock().wasTampered()); //Bloco Alterado
        assertFalse(basicOrganization.isValidLedger(), "\"\" should be false");
    }

    @Test
    public void testIsValidEdgerBVA03() {
        basicOrganization.registerTransactionsInLedger();
        assertTrue(basicOrganization.isValidLedger(), "\"\" should be true");
    }

    @Test
    public void testIsValidEdgerECP01() {
        for (int i = 0; i < 3; i++) { //Criar 5 blocos
            BasicTransactionLine basicTransactionLine = new BasicTransactionLine(String.valueOf(i), 1, 0);
            BasicTransaction basicTransaction = new BasicTransaction(receiver, sender);
            basicTransaction.addTransactionLine(basicTransactionLine);
            receiver.addTokens(1);
            basicOrganization.addTransaction(basicTransaction);
            basicOrganization.registerTransactionsInLedger();
            //Adulterar bloco numero 2
            if (i == 1) {
                basicTransaction.removeTransactionLine(basicTransactionLine);
            }
        }
        assertFalse(basicOrganization.isValidLedger(), "\"\" should be false");
    }



}

