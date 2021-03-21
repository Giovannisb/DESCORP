package test;

import tests.DESCORP.java.Teste;
import com.mycompany.descorp.Departamento;
import com.mycompany.descorp.Empregado;
import com.mycompany.descorp.Endereco;
import java.text.ParseException;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Giovanni
 * @author David
 */
public class TesteEmpregado extends Teste{
    @Test
    public void createEmpregado() {
        Empregado aux = new Empregado();
        Endereco end = em.find(Endereco.class, 3L);
        aux.setEndereco(end);
        aux.setName("Ulisses");
        aux.setCargo("DIRETOR");
        aux.setCpf("653.496.510-05");
        aux.setEmail("email@email.com");
        aux.setSalario(15800.00);
        
        Departamento dpto = em.find(Departamento.class, 1L);
        aux.setDepartamento(dpto);
        em.persist(aux);
        em.flush(); //força que a persistência realizada vá para o banco neste momento.
        System.out.println("=====> "+aux.getName());
        assertNotNull(aux.getId());
        assertEquals(aux.getDepartamento().getName(), "TI");
    }
    
    @Test
    public void readDepartamento() throws ParseException {
        Empregado empregado;
        empregado = em.find(Empregado.class, 1L);

        assertEquals("Giovanni", empregado.getName());
        assertTrue(1L == empregado.getId());
    }
    
    @Test
    public void updateEmpregado(){
        TypedQuery<Empregado> query = em.createNamedQuery("Update.Empregado",
                Empregado.class);
        double sal = 4000.00;
        query.setParameter("salario", sal).setParameter("id", 1);
        int r = query.executeUpdate();
        assertEquals(1, r);
        Empregado empregado = em.find(Empregado.class, 1L);
        em.refresh(empregado);

        assertTrue(sal == empregado.getSalario());
    }
    
    @Test
    public void deleteEmpregado(){
        
        Long idtoremove = 4L;
        TypedQuery<Empregado> query = em.createQuery("SELECT e FROM Empregado e WHERE e.id = :id", Empregado.class);
        query.setParameter("id", idtoremove);
        
        query.getResultStream().forEach(em::remove);
        
        Empregado empregado;
        empregado = em.find(Empregado.class, idtoremove);
        
        assertNull(empregado);
    }
}