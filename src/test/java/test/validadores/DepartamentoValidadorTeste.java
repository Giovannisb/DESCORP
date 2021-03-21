
package test.validadores;

import com.mycompany.descorp.Departamento;
import com.mycompany.descorp.Empregado;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import tests.DESCORP.java.Teste;

/**
 *
 * @author David
 */
public class DepartamentoValidadorTeste extends Teste{
    @Test(expected = ConstraintViolationException.class)
    public void persistirDepartamentoInvalido() {
        Departamento d =null;
        
        try {
            d = new Departamento();
            d.setName(""); //nome inválido
            d.setEmpregados(new ArrayList<Empregado>()); //um dpto não pode ter 0 empregados
            em.persist(d);
            em.flush();
            
        } catch (ConstraintViolationException e) {
             Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

            constraintViolations.forEach(violation -> {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class com.mycompany.descorp.Departamento.name: não deve estar em branco"),
                                startsWith("class com.mycompany.descorp.Departamento.empregados: tamanho deve ser entre 1 e 50")
                        )
                );
            });

            assertEquals(2, constraintViolations.size());
            assertEquals(d.getId(), 0);
            throw e;
        }


    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarDepartamentoNomeInvalido() {
        
        int idp = 2;
        TypedQuery<Departamento> query = em.createQuery("SELECT d FROM Departamento d WHERE d.id = :id", Departamento.class);
        query.setParameter("id", idp);
        Departamento d = query.getSingleResult();
        d.setName(""); //Nome inválido
        //maior que 15 caracteres
        
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
