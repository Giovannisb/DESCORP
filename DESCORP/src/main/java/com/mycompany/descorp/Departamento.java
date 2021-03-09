
package com.mycompany.descorp;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Giovanni
 * @author David
 */

@Entity
@Table(name = "TB_DEPARTAMENTO")
@NamedQueries(
    {
        @NamedQuery(
            name = "Update.Name",
            query= "UPDATE Departamento d SET d.name = :name WHERE d.id = :id"
        ),
        
        @NamedQuery(
            name = "Delete.Departamento",
            query= "DELETE FROM Departamento d WHERE d.id = :id "
        )
    }
)
public class Departamento implements Serializable {
    @Id 
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int id;
    
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
   
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName( ){
        return name;
    }
    
    public void setName( String name ){
        this.name = name;
    }

}
