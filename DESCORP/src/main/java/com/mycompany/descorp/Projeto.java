package com.mycompany.descorp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


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
    private int id;
        
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

//    @ManyToMany(mappedBy = "empregado", cascade = CascadeType.ALL)
//    private List<Empregado> empregados;

    public Projeto(int id,  String nome, String descricao){
        super();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Projeto( ){
        super();
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
}