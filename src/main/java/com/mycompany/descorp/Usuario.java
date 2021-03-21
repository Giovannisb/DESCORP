package com.mycompany.descorp;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED) 
@DiscriminatorColumn(name = "DISC_USUARIO",
        discriminatorType = DiscriminatorType.STRING, length = 1)
@Access(AccessType.FIELD)
public abstract class Usuario implements Serializable{
    
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 
    protected int id;
    
    @Column(name = "name")
    @NotBlank
    @Size(min = 1, max = 30)
    @Pattern(regexp= "\\p{Upper}{1}\\p{Lower}+", message = "A primeira letra deve ser maiúscula")
    protected String name;
    
    @Column(name ="cpf", nullable =false, unique= true)
    @CPF
    protected String cpf;
    
    @Column(name = "email")
    @Email
    protected String email;
    
    
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @NotNull
    protected Endereco endereco;
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
