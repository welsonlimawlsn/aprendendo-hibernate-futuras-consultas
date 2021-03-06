package br.com.welson.hibernate.veiculos.dominio;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VeiculoId implements Serializable {

    private String placa;
    private String cidade;

    public VeiculoId() {

    }

    public VeiculoId(String placa, String cidade) {
        this.placa = placa;
        this.cidade = cidade;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VeiculoId veiculoId = (VeiculoId) o;

        if (placa != null ? !placa.equals(veiculoId.placa) : veiculoId.placa != null) return false;
        return cidade != null ? cidade.equals(veiculoId.cidade) : veiculoId.cidade == null;
    }

    @Override
    public int hashCode() {
        int result = placa != null ? placa.hashCode() : 0;
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        return result;
    }
}
