/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.mycompany.descorp.Departamento;
import tests.DESCORP.java.DbUnitUtil;
import com.mycompany.descorp.Empregado;
import static com.mycompany.descorp.Empregado_.departamento;
import java.text.ParseException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Giovanni
 */
public class TesteEmpregado extends Teste{
//    @Test
//    public void persistDepartamento() {
//        Empregado aux = new Empregado();
//        aux.setName("Ulisses");
//        aux.setCargo("DIRETOR");
//        aux.setSalario(15800.00);
//        
//        Departamento dpto = new Departamento();
//        dpto = assertEquals(1, dpto.getId());
//        aux.setDepartamento();
//        em.persist(aux);
//        em.flush(); //força que a persistência realizada vá para o banco neste momento.
//
//        assertNotNull(aux.getId());
//    }
    
    @Test
    public void consultDepartamento() throws ParseException {
        Empregado empregado;
        empregado = em.find(Empregado.class, 1);

        assertEquals("Giovanni", empregado.getName());
        assertEquals(1, empregado.getId());
    }
    
}
