package br.com.welson.hibernate.veiculos.beans;

import br.com.welson.hibernate.veiculos.dominio.Proprietario;
import br.com.welson.hibernate.veiculos.dominio.Veiculo;
import br.com.welson.hibernate.veiculos.dominio.VeiculoId;
import br.com.welson.hibernate.veiculos.dominio.VeiculoPersistence;
import org.omg.CORBA.LongHolder;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class VeiculoBean implements Serializable {

    private Veiculo veiculo = new Veiculo();

    private Proprietario proprietarioPesquisado;

    private List<Veiculo> veiculos;

    private Long idProprietario;

    public void init() {
        System.out.println("3");
        veiculos = VeiculoPersistence.listandoTodosVeiculos();
        System.out.println("4");
        veiculo.setProprietario(new Proprietario());
    }

    public  void gravar() {
        veiculo.setDataCadastro(new Date());
        VeiculoPersistence.persistir(veiculo);
        veiculo = new Veiculo();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravado com sucesso!", ""));
        init();
    }

    public void pesquisarPorId(){
        proprietarioPesquisado = VeiculoPersistence.buscarPorId(idProprietario);
        System.out.println("Pesquisando por id");
    }

    public void editar(Veiculo veiculo) {
        veiculo.setEditando(true);
    }

    public String salvarEdicao(Veiculo veiculo) {
        VeiculoPersistence.salvarEdicao(veiculo);
        return "index?faces-redirect=true";
    }

    public void excluir(Veiculo veiculo) {
        VeiculoPersistence.excluir(veiculo);
        veiculos.remove(veiculo);
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Long getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(Long idProprietario) {
        this.idProprietario = idProprietario;
    }

    public Proprietario getProprietarioPesquisado() {
        return proprietarioPesquisado;
    }

    public void setProprietarioPesquisado(Proprietario proprietarioPesquisado) {
        this.proprietarioPesquisado = proprietarioPesquisado;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}
