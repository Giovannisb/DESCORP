package test;

import java.text.ParseException;
import org.junit.Test;
import tests.DESCORP.java.Teste;
import com.mycompany.descorp.Empregado;
import com.mycompany.descorp.Conta;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 *
 * @author David
 */
public class TesteConta extends Teste{
    @Test
    public void createConta() {
        Conta c = new Conta();
        c.setBanco("Santander");
        c.setConta("543543");
        c.setDigito(9);
        c.setAgencia("1236");
        Empregado empregado = em.find(Empregado.class, 1);
        c.setEmpregado(empregado);

        em.persist(c);
        em.flush();

        assertNotNull(c.getId());
    }
    
    @Test
    public void readConta() throws ParseException {
        Conta conta;
        conta = em.find(Conta.class, 1);

        assertEquals("ITAU", conta.getBanco());
     }
    
    @Test
    public void updateConta(){
        TypedQuery<Conta> q = em.createNamedQuery("Update.Conta", Conta.class);
        int id2update = 3;
        String newBanco = "BANCO SAFRA";
        String newConta = "94750";
        int newDigito = 0;
        String newAgencia = "6288";
        
        q.setParameter("banco", newBanco).setParameter("conta", newConta)
                .setParameter("digito", newDigito)
                .setParameter("agencia", newAgencia)
                .setParameter("id", id2update);
        int r = q.executeUpdate();
        assertEquals(1, r);

        Conta c = em.find(Conta.class, id2update);
        em.refresh(c);
        assertEquals(newBanco, c.getBanco());
    }
    
    @Test
    public void deleteConta(){
        
        int idtoremove = 4;
        TypedQuery<Conta> query = em.createQuery("SELECT e FROM Conta e WHERE e.id = :id", Conta.class);
        query.setParameter("id", idtoremove);
        
        query.getResultStream().forEach(em::remove);
        
        Conta c;
        c = em.find(Conta.class, idtoremove);
        
        assertNull(c);
    }
    
}
