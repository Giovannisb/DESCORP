package com.mycompany.descorp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "TB_PROJETO")
@NamedQueries(
    {
        @NamedQuery(
            name = "Update.Projeto.nome",
            query = "UPDATE Projeto p SET p.nome = :nome WHERE p.id = :id"
        ),
        
        @NamedQuery(
            name = "Update.Projeto.descricao",
            query = "UPDATE Projeto p SET p.descricao = :descricao WHERE p.id = :id"
        ),
        
        @NamedQuery(
            name = "Delete.Projeto",
            query = "DELETE FROM Projeto p WHERE p.id = :id "
        )
    }
)
public class Projeto implements Serializable{

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 
    private Long id;
        
    @Column(name = "nome", nullable = false, unique = true)
    @NotBlank
    private String nome;

    @Column(name = "descricao", nullable = false)
    @NotBlank
    private String descricao;

    @ManyToMany(mappedBy="projetos")
    @Size(min = 1, max = 50)
    private List<Empregado> empregados = new ArrayList<Empregado>();

    public Projeto(Long id,  String nome, String descricao){
        super();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Projeto( ){
        super();
    }

    public Long getId(){
        return this.id;
    }
    public void setId(Long newId){
        this.id = newId;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
        public List<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(List<Empregado> empregados) {
        this.empregados = empregados;
    }
    
       @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Projeto)) {
            return false;
        }
        Projeto other = (Projeto) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.mycompany.descorp.Projeto[ id=" + id + " ]";
    }
}