package com.mycompany.descorp;

import com.mycompany.descorp.Empregado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2021-03-16T23:42:26")
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile SingularAttribute<Departamento, String> name;
    public static volatile ListAttribute<Departamento, Empregado> empregados;
    public static volatile SingularAttribute<Departamento, Integer> id;

}