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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import validadores.ValidaEstadoAnnotation;

/**

 *
 * @author Giovanni
 * @author David
 */

@Entity
@Table(name = "TB_ENDERECO")
@NamedQueries(
    {
        @NamedQuery(
            name = "Update.Endereco.cep",
            query = "UPDATE Endereco e SET e.cep = :cep WHERE e.id = :id"
        ),
        @NamedQuery(
            name = "Update.Endereco.estado",
            query = "UPDATE Endereco e SET e.estado = :estado WHERE e.id = :id"
        ),
        @NamedQuery(
            name = "Delete.Endereco",
            query = "DELETE FROM Endereco e WHERE e.id = :id "
        )
    }
)

public class Endereco implements Serializable {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 
    private int id;
        
    @Column(name = "cep", nullable = false)
    @NotBlank
    @Pattern(regexp = "[0-90]{2}.[0-9]{3}-[0-9]{3}", message = "{com.mycompany.descorp.Endereco.cep}")
    private String cep;

    @Column(name = "logradouro", nullable = false)
    @NotBlank
    private String logradouro;
    
    @Column(name = "numero", nullable = false)
    @NotNull
    private int numero;
    
    @Column(name = "complemento", nullable = false)
    @NotBlank
    private String complemento;
    
    @Column(name = "cidade", nullable = false)
    @NotBlank
    private String cidade;
    
    @Column(name = "estado", nullable = false)
    @ValidaEstadoAnnotation
    @NotBlank
    private String estado;
    
    public Endereco(int id,  String cep, String logradouro, int numero, String complemento, String cidade, String estado){
        super( );
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Endereco( ){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String log) {
        this.logradouro = log;
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int num) {
        this.numero = num;
    }
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String c) {
        this.complemento = c;
    }
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String ci) {
        this.cidade = ci;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String e) {
        this.estado = e;
    }
}
