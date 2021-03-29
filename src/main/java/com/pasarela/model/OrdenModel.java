package com.pasarela.model;

import java.util.List;

public class OrdenModel {

    private String codigo;
    private UsuarioModel usuario;
    private List<CuotasModel> cuotas;

    public OrdenModel(String codigo, UsuarioModel usuario, List<CuotasModel> cuotas) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.cuotas = cuotas;
    }

    public String getCodigo() {
        return codigo;
    }
    public UsuarioModel getUsuario() {
        return usuario;
    }

}
