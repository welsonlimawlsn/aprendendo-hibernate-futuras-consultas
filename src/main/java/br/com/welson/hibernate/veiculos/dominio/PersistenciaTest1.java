package br.com.welson.hibernate.veiculos.dominio;

import br.com.welson.hibernate.JpaUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Entity;
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

    public void executa3() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Acessorio alarme = new Acessorio();
        alarme.setDescricao("Alarme");

        Acessorio arCondicionado = new Acessorio();
        arCondicionado.setDescricao("Ar condicionado");

        Acessorio bancosDeCouro = new Acessorio();
        bancosDeCouro.setDescricao("Bancos de couro");

        Acessorio direcaoHidraulica = new Acessorio();
        direcaoHidraulica.setDescricao("Direção hidráulica");

        entityManager.persist(alarme);
        entityManager.persist(arCondicionado);
        entityManager.persist(bancosDeCouro);
        entityManager.persist(direcaoHidraulica);

        Veiculo veiculo1 = new Veiculo();
        veiculo1.setFabricante("GM");
        veiculo1.setModelo("Celta");
        veiculo1.setAnoFabricacao(2006);
        veiculo1.setAnoModelo(2006);
        veiculo1.setValor(new BigDecimal(11_000));
        veiculo1.setTipoCombustivel(TipoCombustivel.GASOLINA);
        veiculo1.setDataCadastro(new Date());
        veiculo1.getAcessorios().add(alarme);
        veiculo1.getAcessorios().add(arCondicionado);

        entityManager.persist(veiculo1);

        Veiculo veiculo2 = new Veiculo();
        veiculo2.setFabricante("VM");
        veiculo2.setModelo("Gol");
        veiculo2.setAnoFabricacao(2010);
        veiculo2.setAnoModelo(2010);
        veiculo2.setValor(new BigDecimal(17200));
        veiculo2.setTipoCombustivel(TipoCombustivel.ALCOOL_GASOLINA);
        veiculo2.setDataCadastro(new Date());
        veiculo2.getAcessorios().add(arCondicionado);
        veiculo2.getAcessorios().add(direcaoHidraulica);

        entityManager.persist(veiculo2);

        tx.commit();
        entityManager.close();
        JpaUtil.close();
    }

    public void executa4() {
        EntityManager manager = JpaUtil.getEntityManager();

        Veiculo veiculo = manager.find(Veiculo.class, 6L);
        System.out.println("Veiculo: " + veiculo.getModelo());

        for (Acessorio acessorio : veiculo.getAcessorios()) {
            System.out.println(acessorio.getDescricao());
        }

        manager.close();
        JpaUtil.close();
    }

    public void executa5 () {
        EntityManager manager = JpaUtil.getEntityManager();
        Acessorio acessorio = manager.find(Acessorio.class, 2L);

        for (Veiculo veiculo : acessorio.getVeiculos()) {
            System.out.println(veiculo.getFabricante() + " " + veiculo.getModelo());
        }

        manager.close();
        JpaUtil.close();
    }

    public void executa6() {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Proprietario proprietario = new Proprietario();
        proprietario.setNome("Welson");
        proprietario.setEmail("welsonlimawlsn@gmail.com");
        //proprietario.getTelefone().add("(61) 9 9876-5432");
        //proprietario.getTelefone().add("(61) 9 8765-4321");

        manager.persist(proprietario);

        transaction.commit();
        manager.close();
        JpaUtil.close();
    }

    public void executa7 () {
        EntityManager manager = JpaUtil.getEntityManager();

        Proprietario proprietario = manager.find(Proprietario.class, 1L);

        System.out.println("Nome do proprietraio: " + proprietario.getNome());

        //for (String telefone : proprietario.getTelefone()) {
        //    System.out.println("Telefone: " + telefone);
        //}

        manager.close();
        JpaUtil.close();
    }

    public void executa8() {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Proprietario proprietario = new Proprietario();
        proprietario.setNome("Welson");
        proprietario.getTelefone().add(new Telefone("61", "1234-4567", "123"));
        proprietario.getTelefone().add(new Telefone("61", "7894-4561", null));

        manager.persist(proprietario);

        transaction.commit();
        manager.close();
        JpaUtil.close();
    }
}
