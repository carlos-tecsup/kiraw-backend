package com.kiraw.LoginKiraw.controller;

import com.kiraw.LoginKiraw.entity.Category;
import com.kiraw.LoginKiraw.entity.Product;
import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Publication;
import com.kiraw.LoginKiraw.repository.ICategoryDao;
import com.kiraw.LoginKiraw.service.IProductService;
import com.kiraw.LoginKiraw.service.jpa.CategoriaService;
import com.kiraw.LoginKiraw.service.jpa.ProductService;
import com.kiraw.LoginKiraw.service.jpa.ProductoService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productoService2;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ProductoService service;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryDao categoryDao;
    //@Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
        @GetMapping("/products")
    public List<Product> productos() {
        logger.info("call productos");

        List<Product> productos =
                productoService2.findAll();

        return productos;
    }

    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @GetMapping("/productos/page/{page}")
    public Page<Product> index2(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        return productService.findAll(pageable);
    }

    // @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @GetMapping("/products/categoria/{id}")
    public List<Product> pr(Integer id ) {
        logger.info("call productos");

        List<Product> productos = productoService2.obtener2(id);

        return productos;
    }


    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @GetMapping("/productsbypriceasc")
    public List<Product> productos_id() {
        logger.info("call productos");

        List<Product> productos = productService.findAllByOrOrderByPriceAsc();

        return productos;
    }


    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @GetMapping("/productsbypricedesc")
    public List<Product> products() {

        List<Product> productos = productService.findProductByPriceDesc();

        return productos;
    }
    //@Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/products/{id}")
    public Product show(@PathVariable int id) {

        return productoService2.findById(id);

    }

    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/products/provider/{id}")
    public List<Product> showproduct(@PathVariable int id) {

        return productoService2.findProductByProviderId(id);

    }


    @Secured({"ROLE_PROVIDER"})
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Integer id) {
        productoService2.delete(id);
    }






    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/productos/provider/{id}/{id2}")
    public List<Product> findProductByProviderId(@PathVariable int id,@PathVariable int id2) {

        return productoService2.obtener2(id,id2);

    }





    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/products/filter/{term}")
    public List<Product> filtrar(@PathVariable String term) {

        return productService.findProductByName(term);

    }


    @Secured({"ROLE_PROVIDER"})
    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(
            @RequestParam(name = "product_description") String product_description,
            @RequestParam(name = "name") String name_product,
            @RequestParam(name = "archivo", required = false) MultipartFile archivo,
            @RequestParam(name = "price") Double price,
            @RequestParam(name = "stock") Integer stock,
            @RequestParam(name = "product_category") String product_category,
            @PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        Product product1 = productoService2.findById(id);

        Category category1=categoryDao.findCategoryByName(product_category);

        Category category2=categoryDao.findCategoryById(category1.getId());

        category1.setId(category2.getId());
        product1.setCategory(category1);

        product1.setName(name_product);
        product1.setPrice(price);
        product1.setStock(stock);
        product1.setDescription(product_description);



        Provider provider = new Provider();
        provider.setId(id);

        product1.setProvider(provider);


        if (archivo != null && !archivo.isEmpty()) {
            String nombrearchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
            Path rutaarchivo = Paths.get("products").resolve(nombrearchivo).toAbsolutePath();
            try {

                logger.info(rutaarchivo.toString());
                product1.setImage(nombrearchivo);

                Files.copy(archivo.getInputStream(), rutaarchivo);

            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            }


            product1.setImage(nombrearchivo);
            service.actualizar(product1);

            response.put("product_id", product1.getId());
            response.put("product_category", product1.getCategory().getName());
            response.put("product_description", product1.getDescription());
            response.put("product_price", product1.getPrice());
            response.put("product_stock", product1.getStock());
            response.put("date_created", product1.getDate_created());
            response.put("product_name", product1.getName());
            response.put("img_product", product1.getImage());
            response.put("provider_id", product1.getProvider().getId());
            response.put("mensaje", "Has subido correctamente el producto");

        } else {
            product1.setImage(product1.getImage());

            service.actualizar(product1);

            response.put("product_id", product1.getId());
            response.put("product_category", product1.getCategory().getName());
            response.put("product_description", product1.getDescription());
            response.put("product_price", product1.getPrice());
            response.put("product_stock", product1.getStock());
            response.put("date_created", product1.getDate_created());
            response.put("product_name", product1.getName());
            response.put("img_product", product1.getImage());

            response.put("provider_id", product1.getProvider().getId());
            response.put("mensaje", "Has subido correctamente el producto");
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured({"ROLE_PROVIDER", "ROLE_CLIENT"})
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/products/category/{id}")
    public List<Product> findProductByCategoryId(@PathVariable int id) {

        return productoService2.findByCategoryId(id);

    }

    @Secured({"ROLE_PROVIDER"})
    @PostMapping("/products/upload")
    public ResponseEntity<?> upload(
            @RequestParam(name = "product_description") String product_description,
            @RequestParam(name = "name") String name_product,
            @RequestParam(name = "archivo") MultipartFile archivo,
            @RequestParam(name = "price") Double price,
            @RequestParam(name = "stock") Integer stock,
            @RequestParam(name = "product_category") String product_category,
            @RequestParam(name = "provider_id") Integer provider_id) {
        Map<String, Object> response = new HashMap<>();
        logger.info("call crear(" + archivo + ", " + name_product + ", " + price + ", " + stock + ")" + product_category + ")" + archivo);

        Product product = new Product();




        product.setName(name_product);
        product.setPrice(price);
        product.setStock(stock);
        product.setDescription(product_description);


            Category category=categoryDao.findCategoryByName(product_category)   ;
        category.setName(product_category);


        product.setCategory(category);

        Provider provider = new Provider();
        provider.setId(provider_id);

        product.setProvider(provider);


        if (archivo != null && !archivo.isEmpty()) {
            String nombrearchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
            Path rutaarchivo = Paths.get("products").resolve(nombrearchivo).toAbsolutePath();
            try {

                logger.info(rutaarchivo.toString());
                product.setImage(nombrearchivo);

                Files.copy(archivo.getInputStream(), rutaarchivo);

            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            }


            product.setImage(nombrearchivo);
            productService.save(product);
            response.put("product_id", product.getId());
            response.put("product_description", product.getDescription());
            response.put("product_price", product.getPrice());
            response.put("product_stock", product.getStock());
            response.put("date_created", product.getDate_created());
            response.put("product_name", product.getName());

            response.put("provider_id", product.getProvider().getId());
            response.put("mensaje", "Has subido correctamente el producto");

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @GetMapping("/products/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
        Path rutaarchivo = Paths.get("products").resolve(nombreFoto).toAbsolutePath();
        logger.info(rutaarchivo.toString());

        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaarchivo.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (!recurso.exists() && !recurso.isReadable()) {
            rutaarchivo = Paths.get("products").resolve(nombreFoto).toAbsolutePath();
            throw new RuntimeException("Error no se pudo cargar la imagen:" + nombreFoto);

        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }


}
