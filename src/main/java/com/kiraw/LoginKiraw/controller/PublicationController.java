package com.kiraw.LoginKiraw.controller;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.websocket.ClientEndpoint;

import com.kiraw.LoginKiraw.entity.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kiraw.LoginKiraw.service.IPublicationService;
import com.kiraw.LoginKiraw.service.jpa.PublicationService;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class PublicationController {
    private static final Logger logger = LoggerFactory.getLogger(PublicationController.class);
    //private static final String FILEPATH = "D:/Cursos/Spring5/workspace/LoginKiraw/uploads";

    @Autowired
    private IPublicationService publicationService;
    @Autowired
    private PublicationService publicationservicio;

    @GetMapping("/publicaciones")
    public List<Publication> index() {
        List<Publication> publications = publicationService.findByOrderByIdDesc();


        return publications;
    }

    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})

    @GetMapping("/publicaciones/page/{page}")
    public Page<Publication> index2(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        return publicationService.findAll(pageable);
    }


    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @PostMapping("/publicaciones")
    @ResponseStatus(HttpStatus.CREATED)
    public Publication create(@RequestBody Publication publication) {
        return publicationService.save(publication);
    }

    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/publicaciones/{id}")
    public Publication show(@PathVariable int id) {

        return publicationService.findPublicationById(id);

    }

    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/publicaciones/provider/{id}/{pageable}")
    public Page<Publication> show2(@PathVariable int id,@PathVariable Integer pageable) {
        Pageable pageable2 = PageRequest.of(pageable, 10, Sort.by("id").descending());

        return publicationservicio.findAll2(id,pageable2);

    }

    @Secured({"ROLE_PROVIDER"})
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/publication/{id}")
    public void delete(@PathVariable Integer id) {
        publicationService.delete(id);
    }

    @Secured({"ROLE_PROVIDER"})
    @PutMapping("/publication/{id}")
    public ResponseEntity<?> update(


            @RequestParam(name = "archivo", required = false) MultipartFile archivo,
            @RequestParam(name = "title_description") String title_description,
            @RequestParam(name = "title_publication") String title_publication,

            @PathVariable int id
    ) {
        Map<String, Object> response = new HashMap<>();
        logger.info("call crear(" + archivo + ", " + archivo + ", " + title_description + ", " + title_publication + ")" + ")" + archivo);
        Publication publication = publicationService.findPublicationById(id);


        publication.setTitle_description(title_description);
        publication.setTitle_publication(title_publication);

        if (archivo != null && !archivo.isEmpty()) {
            String nombrearchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
            Path rutaarchivo = Paths.get("uploads").resolve(nombrearchivo).toAbsolutePath();
            try {

                logger.info(rutaarchivo.toString());
                publication.setImage_publication(nombrearchivo);

                Files.copy(archivo.getInputStream(), rutaarchivo);

            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            }


            publication.setImage_publication(nombrearchivo);
            publicationService.save(publication);

            response.put("publication_id", publication.getId());
            response.put("publication_tittle", publication.getTitle_description());
            response.put("publication_description", publication.getTitle_publication());
            response.put("publication_image", publication.getImage_publication());

            response.put("mensaje", "Has subido correctamente el producto");

        } else {
            publicationService.save(publication);

            response.put("publication_id", publication.getId());
            response.put("publication_tittle", publication.getTitle_description());
            response.put("publication_description", publication.getTitle_publication());
            response.put("publication_image", publication.getImage_publication());

            response.put("mensaje", "Has subido correctamente el producto");
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }



	/*@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/publicaciones/upload")
	public ResponseMessage crear(@RequestParam(name="archivo") MultipartFile archivo,
								 @RequestParam(name="title_description") String title_description,
								 @RequestParam(name="title_publication")String title_publication,
								 @RequestParam(name="provider_id") Integer providerId) {
		logger.info("call crear(" + archivo + ", " + title_description + ", " + title_publication + ", " + providerId + ")");
		Publication publication=new Publication();

		publication.setTitle_description(title_description);
		publication.setTitle_publication(title_publication);

		Provider provider = new Provider();
		provider.setId(providerId);
		publication.setProvider(provider);
	
		if (archivo != null && !archivo.isEmpty()) {
			try {
				
				publication.setImage_publication(archivo.getOriginalFilename());
				
				Files.copy(archivo.getInputStream(), Paths.get(FILEPATH).resolve(archivo.getOriginalFilename()));
				
			}catch(IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		publicationservicio.crear(publication);
		
		return ResponseMessage.success("Registro completo");
	}
*/

    @Secured({"ROLE_PROVIDER"})
    @PostMapping("/publicaciones/upload")
    public ResponseEntity<?> upload(@RequestParam(name = "archivo") MultipartFile archivo,
                                    @RequestParam(name = "title_description") String title_description,
                                    @RequestParam(name = "title_publication") String title_publication,
                                    @RequestParam(name = "provider_id") Integer providerId) {
        Map<String, Object> response = new HashMap<>();
        logger.info("call crear(" + archivo + ", " + title_description + ", " + title_publication + ", " + providerId + ")");

        Publication publication = new Publication();


        publication.setTitle_description(title_description);
        publication.setTitle_publication(title_publication);

        Provider provider = new Provider();
        provider.setId(providerId);
        publication.setProvider(provider);


        if (archivo != null && !archivo.isEmpty()) {

            try {

                String nombrearchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();

                Path rutaarchivo = Paths.get("uploads").resolve(nombrearchivo).toAbsolutePath();
                logger.info(rutaarchivo.toString());
                publication.setImage_publication(nombrearchivo);

                Files.copy(archivo.getInputStream(), rutaarchivo);

            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            }


            publicationService.save(publication);
            response.put("title_publication", publication.getTitle_publication());
            response.put("description_publicacion", publication.getTitle_description());
            response.put("image_publication", publication.getImage_publication());
            response.put("id_provider", publication.getProvider().getId());
            response.put(("fecha"), publication.getCreation_date());
            response.put("mensaje", "Has subido correctamente la imagen");

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @GetMapping("/publicaciones/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
        Path rutaarchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        logger.info(rutaarchivo.toString());

        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaarchivo.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (!recurso.exists() && !recurso.isReadable()) {
            rutaarchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
            throw new RuntimeException("Error no se pudo cargar la imagen:" + nombreFoto);

        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }


}
