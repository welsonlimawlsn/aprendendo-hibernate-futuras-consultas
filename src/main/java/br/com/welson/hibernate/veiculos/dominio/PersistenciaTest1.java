package br.com.welson.hibernate.veiculos.dominio;

import br.com.welson.hibernate.JpaUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Named
@RequestScoped
public class PersistenciaTest1 implements Serializable {
    public void executa() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Proprietario proprietario = new Proprietario("Welson", "6198765435", "welsonlimawlsn@gmail.com");

        entityManager.persist(proprietario);

        Veiculo veiculo1 = new Veiculo();
        veiculo1.setFabricante("GM");
        veiculo1.setModelo("Celta");
        veiculo1.setAnoFabricacao(2006);
        veiculo1.setAnoModelo(2006);
        veiculo1.setValor(new BigDecimal(11_000));
        veiculo1.setTipoCombustivel(TipoCombustivel.GASOLINA);
        veiculo1.setDataCadastro(new Date());
        veiculo1.setProprietario(proprietario);

        entityManager.persist(veiculo1);

        Veiculo veiculo2 = new Veiculo();
        veiculo2.setFabricante("VM");
        veiculo2.setModelo("Gol");
        veiculo2.setAnoFabricacao(2010);
        veiculo2.setAnoModelo(2010);
        veiculo2.setValor(new BigDecimal(17200));
        veiculo2.setTipoCombustivel(TipoCombustivel.ALCOOL_GASOLINA);
        veiculo2.setDataCadastro(new Date());
        veiculo2.setProprietario(proprietario);

        entityManager.persist(veiculo2);

        entityTransaction.commit();
        entityManager.close();
        JpaUtil.close();
    }

    public void executa2(){
        EntityManager entityManager = JpaUtil.getEntityManager();

        Proprietario proprietario = entityManager.find(Proprietario.class, 1L);

        System.out.println("Proprietario: " + proprietario.getNome());

        for (Veiculo veiculo : proprietario.getVeiculos()) {
            System.out.println("Veiculo: " + veiculo.getFabricante() + " " + veiculo.getModelo());
        }

        entityManager.close();
        JpaUtil.close();
    }
}
