package com.kiraw.LoginKiraw.pagos;
import com.culqi.*;
import com.culqi.Culqi;
import com.culqi.util.CurrencyCode;
import com.google.gson.Gson;
import com.kiraw.LoginKiraw.entity.Category;
import com.kiraw.LoginKiraw.entity.Comments;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.repository.IVentaDao;
import com.kiraw.LoginKiraw.repository.ProductoRepository;
import com.kiraw.LoginKiraw.service.IClientsService;
import com.kiraw.LoginKiraw.service.IListproviderService;
import com.kiraw.LoginKiraw.service.jpa.VentaService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class NuevaVentaController {
    @Autowired
    private VentaService ventaService;
    @Autowired
    private PagoService pagoService;
    public NuevaVentaController() {
        Culqi.public_key = "pk_test_8503ab3c9f83319a";
        Culqi.secret_key = "sk_test_c4d1f8e04cc8cfbc";
    }
    private static final Logger logger = LoggerFactory.getLogger(NuevaVentaController.class);
    @Secured({"ROLE_PROVIDER","ROLE_CLIENT"})
    @PostMapping(value = "/pagosculqi")
    @ResponseStatus(HttpStatus.CREATED)

    public Map<String, Object> makePayment(@RequestBody Venta venta) throws Exception{
       /* nombre_proveedor
        numero_proveedor
        direccion_proveedor
        nombre_cliente-
        apellido_cliente
        id_cliente
        id_proveedor
        cantidad_producto
        precio_unitario
*/

        Culqi culqi = new Culqi();

        Map<String, Object> antifraudDetails = new HashMap<>();
        antifraudDetails.put("phone_number",venta.getNumero_proveedor() );
        antifraudDetails.put("first_name", venta.getNombre_cliente());
        antifraudDetails.put("last_name", venta.getApellido_cliente());

        antifraudDetails.put("address", venta.getDireccion_proveedor());

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("order_id",venta.getId_proveedor());
        metadata.put("user_id", venta.getId_cliente());
        metadata.put("user_details", venta.getNombre_proveedor());

        Map<String, Object> requestVenta = new HashMap<>();

        requestVenta.put("amount", venta.getAmount());

        requestVenta.put("currency_code", CurrencyCode.PEN);
        requestVenta.put("description",venta.getDescripcion());

        requestVenta.put("email", venta.getEmail());
        requestVenta.put("source_id", venta.getToken());
        requestVenta.put("antifraud_details", antifraudDetails);
        requestVenta.put("metadata", metadata);

        /*requestVenta.put("amount", venta.getAmount());

        requestVenta.put("currency_code", CurrencyCode.PEN);
        requestVenta.put("description",venta.getDescripcion());

        requestVenta.put("email", venta.getEmail());
        requestVenta.put("source_id", venta.getToken())*/;

        logger.info( venta.getToken());
        ventaService.save(venta);

        return culqi.charge.create(requestVenta);


    }


    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})

    @GetMapping("/ventas/clients/{id}")
    public List<Venta> obtener(@PathVariable Integer id) {

        List<Venta> ventas = pagoService.obtener(id);

        return ventas;
    }

    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})

    @GetMapping("/ventas/provider/{id}")
    public List<Venta> obtener2(@PathVariable Integer id) {

        List<Venta> ventas = pagoService.obtener2(id);

        return ventas;
    }

   /* @Secured({"ROLE_PROVIDER","ROLE_CLIENT"})
    @GetMapping("/ventas/{id}")
    public List<Venta> ventas(@PathVariable Integer id){
        return ventaService.findVenta(id);

    }*/
}



/*



        Gson gson = new Gson();
        String body = gson.toJson(requestVenta);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseVenta;
        String test;
        JSONObject object;

        try {

            responseVenta = restTemplateNuevaVenta.exchange("https://api.culqi.com/v2/charges", HttpMethod.POST, entity, String.class);

            logger.info("Respuesta de la venta: " + responseVenta.toString());
            logger.info("Respuesta de la venta: " + responseVenta.toString());

            object = new JSONObject(responseVenta.getBody());
            OutComeData outComeData = null;

            if (object.get("object").equals("charge")) {
                test = object.get("outcome").toString();
                outComeData = new Gson().fromJson(test, OutComeData.class);
            } else {
                test = object.get("user_message").toString();
            }

            return ResponseEntity.ok(outComeData != null ? outComeData.getUserMessage() != null ? outComeData.getUserMessage() : test : test);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la venta. Por favor comuníquese con CULQI para más información.");
        }

    }*/
