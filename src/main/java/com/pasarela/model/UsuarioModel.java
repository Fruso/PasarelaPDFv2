package com.pasarela.model;

public class UsuarioModel {

    private String fecha;
    private String hora;
    private String motivo;
    private String totalPagado;
    private String formaPago;
    private String codigoAutorizacion;

    public UsuarioModel(String fecha, String hora, String motivo, String totalPagado, String formaPago, String codigoAutorizacion) {
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.totalPagado = totalPagado;
        this.formaPago = formaPago;
        this.codigoAutorizacion = codigoAutorizacion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getTotalPagado() {
        return totalPagado;
    }

    public String geFormaPago() {
        return formaPago;
    }

    public String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }
}
