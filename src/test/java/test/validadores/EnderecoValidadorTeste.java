package test.validadores;

import com.mycompany.descorp.Departamento;
import com.mycompany.descorp.Empregado;
import com.mycompany.descorp.Endereco;
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
public class EnderecoValidadorTeste extends Teste{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirEnderecoInvalido() {
  
         Endereco ed = null;
        
        try {
            ed = new Endereco();
            ed.setCep("1283"); //cep invalido
            ed.setLogradouro(""); //logr invalido
            ed.setNumero(12);
            ed.setCidade(""); //cidade invalida
            ed.setComplemento(""); //complemento invalido
            ed.setEstado("GF"); //estado invalido;
            
            em.persist(ed);
            em.flush();
            
        } catch (ConstraintViolationException e) {
             Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

            constraintViolations.forEach(violation -> {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class com.mycompany.descorp.Endereco.logradouro: não deve estar em branco"),
                                startsWith("class com.mycompany.descorp.Endereco.cidade: não deve estar em branco"),
                                startsWith("class com.mycompany.descorp.Endereco.complemento: não deve estar em branco"),
                                startsWith("class com.mycompany.descorp.Endereco.estado: Estado brasileiro inválido"),
                                startsWith("class com.mycompany.descorp.Endereco.cep: CEP inválido")
                        )
                );
            });

            assertEquals(5, constraintViolations.size());
            assertNull(ed.getId());
            throw e;
        }
    
    
    
    }
    @Test(expected = ConstraintViolationException.class)
    public void atualizarEnderecoEstadoInvalido() {
        
        int idp = 1;
        TypedQuery<Endereco> query = em.createQuery("SELECT ed FROM Endereco ed WHERE ed.id = :id", Endereco.class);
        query.setParameter("id", idp);
        Endereco ed = query.getSingleResult();
        ed.setEstado("MEUESTADOERRADO"); //Estado inválido
        
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {    
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("Estado brasileiro inválido", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }

    
    
    }
    @Test(expected = ConstraintViolationException.class)
    public void atualizarEnderecoCEPInvalido() {
        
        int idp = 3;
        TypedQuery<Endereco> query = em.createQuery("SELECT ed FROM Endereco ed WHERE ed.id = :id", Endereco.class);
        query.setParameter("id", idp);
        Endereco ed = query.getSingleResult();
        ed.setCep("cepe.123-12"); //cep inválido
        
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {    
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("CEP inválido", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
