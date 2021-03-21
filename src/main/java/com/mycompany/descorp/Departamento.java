
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
    private Long id;
    
    @Column(name = "name", nullable = false, unique = true)
    @NotBlank
    private String name;
    
    @Size(min= 1, max=50)
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Empregado> empregados = new ArrayList<>();

    public List<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(List<Empregado> empregados) {
        this.empregados = empregados;
    }
   
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getName( ){
        return name;
    }
    
    public void setName( String name ){
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.mycompany.descorp.Departamento[ id=" + id + " ]";
    }

}
