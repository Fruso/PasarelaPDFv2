package com.pasarela.service;

import com.pasarela.model.UsuarioModel;
import com.pasarela.model.CuotasModel;
import com.pasarela.model.OrdenModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenService {

    public OrdenModel getOrderByCode(final String codigo) {
        return order(codigo);
    }

    private OrdenModel order(String codigo) {
        return new OrdenModel(codigo, detalle(), null);
    }

    private UsuarioModel detalle() {
        return new UsuarioModel("21/03/2021",
                "17:39",
                "Pago Crédito",
                "$220.091",
                "Webpay",
                "123456789");
    }

    /*
    private List<CuotasModel> cuotas() {
        return new ArrayList<CuotasModel>() {
            {
                add(new CuotasModel("Crédito Social - Cuota 1 de 12 36.036COM102910187-7", 1, 73003d));
                add(new CuotasModel("Crédito Social - Cuota 2 de 12 36.036COM102910187-7", 1, 73150d));
                add(new CuotasModel("Crédito Social - Cuota 3 de 12 36.036COM102910187-7", 1, 73210d));
            }
        };
    }
    */
}
