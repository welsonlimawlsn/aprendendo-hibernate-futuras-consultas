package br.com.welson.hibernate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Persistence;
import java.io.Serializable;

@Named
@RequestScoped
public class CriaTabelas implements Serializable {
    public void criarTabelas(){
        Persistence.createEntityManagerFactory("default");
    }
}
