package com.kiraw.LoginKiraw.pagos;

import com.culqi.Culqi;
import com.culqi.model.Customer;
import com.culqi.util.CurrencyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class CulqiPaymentGatewayClient {
    private static final Logger logger = LoggerFactory.getLogger(NuevaVentaController.class);


    @RequestMapping(value= "/charges", method = RequestMethod.POST, produces = "application/json")

    public Map<String, Object> makePayment( Map<String, Object> token) throws Exception {

        Culqi culqi = new Culqi();
        culqi.secret_key = "pk_test_8503ab3c9f83319a";
        culqi.public_key= "sk_test_c4d1f8e04cc8cfbc";



        Map<String, Object> requestVenta = new HashMap<>();

        requestVenta.put("amount",1000);
        requestVenta.put("currency_code", CurrencyCode.PEN);
        requestVenta.put("email","test@culqi.com");
        requestVenta.put("source_id",token);
        logger.info("Respuesta de la venta: " + "tkn_test_IrDN67Y254OuirHB");

        return culqi.charge.create(requestVenta);
    }


}
