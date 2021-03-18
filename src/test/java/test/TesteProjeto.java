package test;

import java.text.ParseException;

import com.mycompany.descorp.Empregado;
import com.mycompany.descorp.Projeto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import tests.DESCORP.java.Teste;

/**
 *
 * @author David
 */
public class TesteProjeto extends Teste{
    
    @Test
    public void createProjeto() {

        Projeto p = new Projeto();
        Empregado emp1 = em.find(Empregado.class, 1);
        Empregado emp2 = em.find(Empregado.class, 2);
        Empregado emp3 = em.find(Empregado.class, 3);

        List<Empregado> empregadosProjeto = new ArrayList<>();
        empregadosProjeto.add(emp1);
        empregadosProjeto.add(emp2);
        empregadosProjeto.add(emp3);

        p.setEmpregados(empregadosProjeto);
        p.setNome("Pentest");
        p.setDescricao("Realizar testes de segurança no endpoint");
        em.persist(p);
        em.flush();
        assertNotNull(p.getId());
        assertEquals(p.getEmpregados().get(0).getName(), "Giovanni");

    }
    
    @Test
    public void readProjeto() throws ParseException { 

        Projeto p = em.find(Projeto.class, 1);
        assertEquals("Site", p.getNome());
        assertEquals(1, p.getId());

    }
    
    @Test
    public void updateNomeProjeto(){
        
        TypedQuery<Projeto> query = em.createNamedQuery("Update.Projeto.nome",Projeto.class);
        String newName = "Construção de análises";
        int idp = 5;
        query.setParameter("nome", newName).setParameter("id", idp);
        int r = query.executeUpdate();
        assertEquals(1, r);
        Projeto p = em.find(Projeto.class, idp);
        em.refresh(p);

        assertTrue(newName.equals(p.getNome()));
    }
    
    @Test
    public void deleteProjeto(){
        int idtoremove = 4;
        
        TypedQuery<Projeto> query = em.createQuery("SELECT e FROM Projeto e WHERE e.id = :id", Projeto.class);
        query.setParameter("id", idtoremove);
        
        query.getResultStream().forEach(em::remove);
        
        Projeto proj;
        proj = em.find(Projeto.class, idtoremove);
        
        assertNull(proj);
    }
}
