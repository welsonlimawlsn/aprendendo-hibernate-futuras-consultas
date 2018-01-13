package br.com.welson.hibernate.veiculos.dominio;

import br.com.welson.hibernate.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class VeiculoPersistence {

    public static void persistir(Veiculo veiculo) {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        manager.persist(veiculo.getProprietario());

        manager.persist(veiculo);

        transaction.commit();
        manager.close();
        //JpaUtil.close();
    }

    public static Proprietario buscarPorId(Long id) {
        EntityManager manager = JpaUtil.getEntityManager();
        //Veiculo veiculo = manager.getReference(Veiculo.class, 1L)
        Proprietario proprietario = manager.find(Proprietario.class, id);
        manager.close();
        //JpaUtil.close();
        return proprietario;
    }

    public static List<Veiculo> listandoTodosVeiculos() {
        EntityManager manager = JpaUtil.getEntityManager();
        List<Veiculo> veiculos = manager.createQuery("from Veiculo", Veiculo.class).getResultList();
        manager.close();
        //JpaUtil.close();
        //return new ArrayList<>();
        return veiculos;
    }

    public static void salvarEdicao(Veiculo veiculo) {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        //Veiculo veiculo1 = manager.find(Veiculo.class, veiculo.getCodigo());
//        veiculo1.setAnoFabricacao(veiculo.getAnoFabricacao());
//        veiculo1.setAnoModelo(veiculo.getAnoModelo());
//        veiculo1.setFabricante(veiculo.getFabricante());
//        veiculo1.setModelo(veiculo.getModelo());
//        veiculo1.setValor(veiculo.getValor());
        //Troucou


        veiculo = manager.merge(veiculo);

        transaction.commit();
        manager.close();
        //JpaUtil.close();
    }

    public static void excluir(Veiculo veiculo) {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Veiculo veiculo1 = manager.find(Veiculo.class, veiculo.getCodigo());

        manager.remove(veiculo1);

        transaction.commit();
        manager.close();
    }

    /*Obeservações

    boolean resultado = entityManager.contains(objeto); Verifica se o objeto está sendo gerenciado
    entityManager.detach(objeto); Serve para parar de gerenciar o objeto
    entityManager.flush(); Força a sicronização com o banco de dados antes do transaction.commit();
    objeto = entityManager.merge(objeto)*/

}
