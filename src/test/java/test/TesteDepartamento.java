package test;

import tests.DESCORP.java.Teste;
import com.mycompany.descorp.Departamento;
import java.text.ParseException;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.Test;



/**
 *
 * @author Giovanni
 * @author David
 */
public class TesteDepartamento extends Teste {
   
    @Test
    public void createDepartamento() {
        Departamento aux = new Departamento();
        aux.setName("VENDAS");
        em.persist(aux);
        em.flush(); //força que a persistência realizada vá para o banco neste momento.

        assertNotNull(aux.getId());
    }
    
    @Test
    public void readDepartamento() throws ParseException {
        Departamento departamento;
        departamento = em.find(Departamento.class, 1);

        assertEquals("TI", departamento.getName());
        assertEquals(1, departamento.getId());
    }
    
    @Test 
    public void updateDepartamento(){
        TypedQuery<Departamento> q = em.createNamedQuery("Update.Name", Departamento.class);
        String newName = "Tecnologia da Informação";
        q.setParameter("name", newName).setParameter("id", 1);
        int r = q.executeUpdate();
        assertEquals(1, r);
        Departamento departamento = em.find(Departamento.class, 1);
        em.refresh(departamento);
        assertEquals(newName, departamento.getName());
    }

    @Test 
    public void deleteDepartamento(){
        TypedQuery<Departamento> q = em.createNamedQuery("Delete.Departamento", Departamento.class);
        q.setParameter("id", 6);
        int r = q.executeUpdate();
        assertEquals(1, r);
    }

}
