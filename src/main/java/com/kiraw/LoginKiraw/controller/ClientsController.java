package com.kiraw.LoginKiraw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.validation.Valid;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.repository.ClientRepository;
import com.kiraw.LoginKiraw.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import com.kiraw.LoginKiraw.entity.Clients;
import com.kiraw.LoginKiraw.service.IClientsService;
import com.kiraw.LoginKiraw.service.IUsersService;
import com.mysql.jdbc.Field;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class    ClientsController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private IClientsService clientsService;

    @Autowired
    private ClientRepository clientsRepository;

//	@PostMapping("/clientes")
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public Clients create(@Valid @RequestBody Clients cliente ) {
//		//Clients clients=new Clients();
//		
//		//cliente.setUsers(passwordEncoder.encode(cliente.getUsers().getPassword()));
//		return clientsService.save(cliente);		

//	}




    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/clients/{id}")
    public Clients show(@PathVariable int id) {

        return clientsService.findClientsById(id);

    }






    @PostMapping("/clientes")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Clients cliente, BindingResult result) {

        Clients clienteNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "el campo" + err.getField() + " " + err.getDefaultMessage()
                    ).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);


        }
        try {
            clienteNew = clientsService.save(cliente);

        } catch (DataAccessException e) {
            response.put("mensaje", "error usuario duplicado");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "el cliente ha sido registrado con exito");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @Secured({"ROLE_CLIENT","ROLE_PROVIDER"})
    @PostMapping("/clients/edit")
        public ResponseEntity<?> upload(@RequestParam(name = "imagen_perfil",required = false) MultipartFile imagen_perfil ,@RequestParam(name = "img_background",required = false) MultipartFile img_background,@RequestParam(name = "id") Integer id, @RequestParam(name = "address",required = false) String address , @RequestParam(name = "phone",required = false) String phone  , @RequestParam(name = "name",required = false) String name, @RequestParam(name = "surname",required = false) String surname, @RequestParam(name = "occupation",required = false) String occupation) {
        Map<String, Object> response = new HashMap<>();


        Clients clients = clientsService.findClientsById(id);


        if (imagen_perfil != null && !imagen_perfil.isEmpty() ){
            String nombrearchivo = UUID.randomUUID().toString() + "_" + imagen_perfil.getOriginalFilename();
            String nombrearchivo2 = UUID.randomUUID().toString() + "_" + img_background.getOriginalFilename();

            Path rutaarchivo = Paths.get("profile").resolve(nombrearchivo).toAbsolutePath();
            try {
                Files.copy(imagen_perfil.getInputStream(), rutaarchivo);


            } catch (IOException e) {
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            clients.setPhone(phone);
            clients.setAddress(address);
            clients.setSurname(surname);
            clients.setName(name);
            clients.setOccupation(occupation);
            clients.setImgProfile(nombrearchivo);
            clients.setImgBackground(nombrearchivo2);


            clientsRepository.crear(clients);


            response.put("mensaje", "datos actualizados");

        }
        else if(imagen_perfil == null  && img_background != null){

            String nombrearchivo2 = UUID.randomUUID().toString() + "_" + img_background.getOriginalFilename();
            Path rutaarchivo2 = Paths.get("fondo").resolve(nombrearchivo2).toAbsolutePath();

            try {
                Files.copy(img_background.getInputStream(), rutaarchivo2);


            } catch (IOException e) {
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            clients.setPhone(phone);

            clients.setImgBackground(nombrearchivo2);
            clients.setAddress(address);
            clients.setSurname(surname);
            clients.setName(name);
            clients.setOccupation(occupation);

            clientsRepository.crear(clients);


            response.put("mensaje", "datos actualizados");
        }
        else {
            clients.setPhone(phone);

            clients.setAddress(address);
            clients.setSurname(surname);
            clients.setName(name);
            clients.setOccupation(occupation);


            clientsRepository.crear(clients);

            response.put("mensaje", "datos actualizados correctamente");
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


}
