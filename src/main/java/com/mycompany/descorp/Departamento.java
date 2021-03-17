
package com.mycompany.descorp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @NotBlank
    @Size(min = 2, max = 15)
    private String name;
    
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Empregado> empregados = new ArrayList<>();

    public List<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(List<Empregado> empregados) {
        this.empregados = empregados;
    }
   
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
