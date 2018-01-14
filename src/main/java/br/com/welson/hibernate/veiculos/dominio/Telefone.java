package br.com.welson.hibernate.veiculos.dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telefone {

    private String prefixo;
    private String numero;
    private String ramal;

    public Telefone() {

    }

    public Telefone( String prefixo, String numero, String ramal) {
        this.prefixo = prefixo;
        this.numero = numero;
        this.ramal = ramal;
    }

    @Column(length = 3, nullable =  false)
    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    @Column(length = 20, nullable = false)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(length = 5)
    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }
}
