package test.validadores;

import com.mycompany.descorp.Empregado;
import javax.validation.ConstraintViolationException;

import com.mycompany.descorp.Projeto;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import tests.DESCORP.java.Teste;

public class ProjetoValidadorTeste extends Teste{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirProjetoInvalido() {

        Projeto p =null;
        
        
        try {
            p = new Projeto();
            p.setNome(""); //Nome inválido
            p.setDescricao(""); //descrição inválida.
            p.setEmpregados(new ArrayList<Empregado>()); //lista inválida;
            em.persist(p);
            em.flush();
            
        } catch (ConstraintViolationException e) {
             Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

            constraintViolations.forEach(violation -> {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class com.mycompany.descorp.Projeto.nome: não deve estar em branco"),
                                startsWith("class com.mycompany.descorp.Projeto.descricao: não deve estar em branco"),
                                startsWith("class com.mycompany.descorp.Projeto.empregados: tamanho deve ser entre 1 e 50")
                        )
                );
            });

            assertEquals(3, constraintViolations.size());
            assertNull(p.getId());
            throw e;
        }


    }
    @Test(expected = ConstraintViolationException.class)
    public void atualizarProjetoInvalido() {
        int idp = 5;
        TypedQuery<Projeto> query = em.createQuery("SELECT p FROM Projeto p WHERE p.id = :id", Projeto.class);
        query.setParameter("id", idp);
        Projeto p = query.getSingleResult();
        p.setNome(""); //nome inválido
        
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {    
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("não deve estar em branco", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    
    }
}
