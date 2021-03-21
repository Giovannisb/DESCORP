package test.validadores;

import com.mycompany.descorp.Conta;
import com.mycompany.descorp.Empregado;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import tests.DESCORP.java.Teste;

/**
 *
 * @author David
 */
public class EmpregadoValidadorTeste extends Teste {
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirEmpregadoInvalido() {
        
        Empregado emp = null;  
        try {
            
            emp = new Empregado();
            
            
            emp.setName("david"); //nome inválido
            emp.setCargo(""); //cargo inválido;
            emp.setSalario(0.0); //salario invalido
            emp.setCpf("aslkj123"); // CPF invalido
            emp.setEmail("EM"); //email invalido
            emp.setContas(new ArrayList<Conta>());
            emp.setDepartamento(null); //dptop invalido
            emp.setEndereco(null); //endereco invalido

            em.persist(emp);
            em.flush();
            
        } catch (ConstraintViolationException e) {
             Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

            constraintViolations.forEach(violation -> {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class com.mycompany.descorp.Empregado.name: A primeira letra deve ser maiúscula"),
                                startsWith("class com.mycompany.descorp.Empregado.cargo: não deve estar em branco"),
                                startsWith("class com.mycompany.descorp.Empregado.salario: Salario não pode ser zerado"),
                                startsWith("class com.mycompany.descorp.Empregado.cpf: número do registro de contribuinte individual brasileiro (CPF) inválido"),
                                startsWith("class com.mycompany.descorp.Empregado.email: deve ser um endereço de e-mail bem formado"),
                                startsWith("class com.mycompany.descorp.Empregado.departamento: não deve ser nulo"),
                                startsWith("class com.mycompany.descorp.Empregado.endereco: não deve ser nulo")
                        )
                );
            });

            assertEquals(7, constraintViolations.size());
            assertNull(emp.getId());
            throw e;
        }
    
    
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarEmpregadoEmailInvalido() {
        
        int idp = 2;
        TypedQuery<Empregado> query = em.createQuery("SELECT e FROM Empregado e WHERE e.id = :id", Empregado.class);
        query.setParameter("id", idp);
        Empregado e = query.getSingleResult();
        e.setEmail("emailinvalido@"); //email inválido        
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {    
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("deve ser um endereço de e-mail bem formado", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
    
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarEmpregadoSalarioInvalido() {
        
        int idp = 5;
        TypedQuery<Empregado> query = em.createQuery("SELECT e FROM Empregado e WHERE e.id = :id", Empregado.class);
        query.setParameter("id", idp);
        Empregado e = query.getSingleResult();
        e.setSalario(0.0); //salário invalido inválido        
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {    
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("Salario não pode ser zerado", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
