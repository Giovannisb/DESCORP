package test;


import java.text.ParseException;
import org.junit.Test;
import tests.DESCORP.java.Teste;
import com.mycompany.descorp.Endereco;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author David
 */
public class TesteEndereco extends Teste{
    @Test
    public void createEndereco() {
        Endereco end = new Endereco();
        end.setLogradouro("Av. Bernado Vieira de Melo");
       
        end.setNumero(2399);
        end.setComplemento("apto 1103");
        end.setCep("54.400-000");
        end.setCidade("Jaboatão");
        end.setEstado("PE");

        em.persist(end);
        em.flush();

        assertNotNull(end.getId());
    }
    
    @Test
    public void readEndereco() throws ParseException {
        Endereco endereco;
        endereco = em.find(Endereco.class, 1L);

        assertTrue("Paulista".equals(endereco.getCidade()));
     }
    
    @Test
    public void updateCepEndereco(){
        TypedQuery<Endereco> q = em.createNamedQuery("Update.Endereco.cep", Endereco.class);
        String newCep = "54.410-280";
        Long id2Update = 3L;
        q.setParameter("cep", newCep).setParameter("id", id2Update);

        int r = q.executeUpdate();
        assertTrue(1 == r);

        Endereco endereco = em.find(Endereco.class, id2Update);
        em.refresh(endereco);
        assertEquals(newCep, endereco.getCep());
    }

    @Test
    public void updateEstadoEndereco(){
        TypedQuery<Endereco> q = em.createNamedQuery("Update.Endereco.estado", Endereco.class);
        String newEstado = "PB";
        Long id2Update = 3L;
        q.setParameter("estado", newEstado).setParameter("id", id2Update);

        int r = q.executeUpdate();
        assertTrue(1== r);

        Endereco endereco = em.find(Endereco.class, id2Update);
        em.refresh(endereco);
        assertEquals(newEstado, endereco.getEstado());
    }
    
    
}
