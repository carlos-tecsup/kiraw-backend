package com.kiraw.LoginKiraw.controller;

import com.kiraw.LoginKiraw.entity.*;
import com.kiraw.LoginKiraw.service.jpa.ProveedorService;
import com.kiraw.LoginKiraw.service.jpa.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.kiraw.LoginKiraw.service.IProviderService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class ProvidersController {
    @Autowired
    private IProviderService providerService;
    @Autowired
    private ProveedorService proveedorService;



    @PostMapping("/proveedores")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Provider provider, BindingResult result) {

        Provider clienteNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "el campo " + err.getField() + " " + err.getDefaultMessage()
                    ).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);


        }
        try {
            clienteNew = providerService.save(provider);

        } catch (DataAccessException e) {
            response.put("mensaje", "error usuario duplicado");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "el cliente ha sido registrado con exito");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }













    //@Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @ResponseStatus(code = HttpStatus.OK)
        @GetMapping("/proveedores/{id}")
    public Provider show(@PathVariable int id) {

        return providerService.findProviderById(id);

    }


    @Secured({"ROLE_PROVIDER"})
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/providers/edit")
    public ResponseEntity<?> upload(@RequestParam(name = "imagen_perfil",required = false) MultipartFile imagen_perfil,@RequestParam(name = "imagen_fondo",required = false) MultipartFile imagen_fondo, @RequestParam(name = "id") Integer id, @RequestParam(name = "address",required = false) String  address,@RequestParam(name = "name",required = false) String name,@RequestParam(name = "city",required = false) String city,@RequestParam(name = "phone",required = false) String phone) {
        Map<String, Object> response = new HashMap<>();


        Provider provider = providerService.findProviderById(id);


        if (imagen_perfil != null && !imagen_perfil.isEmpty() ){
            String nombrearchivo = UUID.randomUUID().toString() + "_" + imagen_perfil.getOriginalFilename();
            String nombrearchivo2 = UUID.randomUUID().toString() + "_" + imagen_fondo.getOriginalFilename();
            Path rutaarchivo2 = Paths.get("fondo").resolve(nombrearchivo2).toAbsolutePath();

            Path rutaarchivo = Paths.get("profile").resolve(nombrearchivo).toAbsolutePath();
            try {
                Files.copy(imagen_perfil.getInputStream(), rutaarchivo);
                Files.copy(imagen_fondo.getInputStream(), rutaarchivo2);


            } catch (IOException e) {
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            provider.setImgProfile(nombrearchivo);
            provider.setImgBackground(nombrearchivo2);
            provider.setAddress(address);
            provider.setName(name);
            provider.setCity(city);
            provider.setPhone(phone);



            proveedorService.crear(provider);
            response.put("img_profile", provider.getImgProfile());

            response.put("mensaje", "datos acualizaddos correctamente");

        }
        else if(imagen_perfil == null  && imagen_fondo != null){

            String nombrearchivo2 = UUID.randomUUID().toString() + "_" + imagen_fondo.getOriginalFilename();
            Path rutaarchivo2 = Paths.get("fondo").resolve(nombrearchivo2).toAbsolutePath();

            try {
                Files.copy(imagen_fondo.getInputStream(), rutaarchivo2);


            } catch (IOException e) {
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            provider.setImgBackground(nombrearchivo2);

            provider.setAddress(address);
            provider.setName(name);
            provider.setCity(city);
            provider.setPhone(phone);



            proveedorService.crear(provider);
            response.put("img_background", provider.getImgBackground());

            response.put("mensaje", "datos acualizaddos correctamente");
        }
        else {
            provider.setAddress(address);
            provider.setName(name);
            provider.setCity(city);
            provider.setPhone(phone);



            proveedorService.crear(provider);

            response.put("mensaje", "datos acualizaddos correctamente");
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @Secured({"ROLE_PROVIDER"})
    @PostMapping("/providers/upload/imgbackground")
    public ResponseEntity<?> uploads(@RequestParam(name = "archivo") MultipartFile archivo, @RequestParam(name = "id") Integer id) {
        Map<String, Object> response = new HashMap<>();


        Provider provider = providerService.findProviderById(id);


        if (archivo != null && !archivo.isEmpty()) {
            String nombrearchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
            Path rutaarchivo = Paths.get("fondo").resolve(nombrearchivo).toAbsolutePath();
            try {


                Files.copy(archivo.getInputStream(), rutaarchivo);

            } catch (IOException e) {
                response.put("mensaje", "error al subir la imagen del cliente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            }
            provider.setImgBackground(nombrearchivo);
            proveedorService.crear2(provider);
            response.put("img_profile", provider.getImgBackground());

            response.put("mensaje", "Has subido correctamente la imagen");

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @GetMapping("/providers/img/fondo/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFotos(@PathVariable String nombreFoto) {
        Path rutaarchivo = Paths.get("fondo").resolve(nombreFoto).toAbsolutePath();


        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaarchivo.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (!recurso.exists() && !recurso.isReadable()) {
            rutaarchivo = Paths.get("src/main/resources/static/images").resolve("no-fondo.jpg").toAbsolutePath();
            try {
                recurso = new UrlResource(rutaarchivo.toUri());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }


    @GetMapping("/providers/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
        Path rutaarchivo = Paths.get("profile").resolve(nombreFoto).toAbsolutePath();


        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaarchivo.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (!recurso.exists() && !recurso.isReadable()) {
            rutaarchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
            try {
                recurso = new UrlResource(rutaarchivo.toUri());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


        }


        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }


}
