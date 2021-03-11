package com.mycompany.descorp;

import com.mycompany.descorp.Empregado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2021-03-09T08:11:43")
@StaticMetamodel(Projeto.class)
public class Projeto_ { 

    public static volatile SingularAttribute<Projeto, String> nome;
    public static volatile ListAttribute<Projeto, Empregado> empregados;
    public static volatile SingularAttribute<Projeto, Integer> id;
    public static volatile SingularAttribute<Projeto, String> descricao;

}