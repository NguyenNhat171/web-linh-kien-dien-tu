package com.example.electronicshop.controller;

import com.example.electronicshop.communication.request.ProductElecRequest;
import com.example.electronicshop.models.ResponseObject;
import com.example.electronicshop.service.ProductElecService;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductElecController {
    private final ProductElecService productElecService;
    @PostMapping("/admin/manage/productelec/add")
    public ResponseEntity<?> addProduct( @ModelAttribute ProductElecRequest req) {
        return productElecService.addProduct(req);
    }
    @PutMapping("/admin/manage/productelec/update/{productId}")
    public ResponseEntity<?> updateProduct (@PathVariable("productId") String productId, @RequestBody ProductElecRequest req){
        return productElecService.updateProduct(productId,req);
    }


    @GetMapping(path = "/productelec/all")
    public ResponseEntity<?> findAll (@ParameterObject Pageable pageable){
        return productElecService.findAll(pageable);
    }
    @GetMapping(path = "/productelec/{productId}")
    public ResponseEntity<?> findProductById (@PathVariable("productId") String productId){
        return productElecService.findProductById(productId);
    }
    @DeleteMapping("/admin/manage/productelec/deactive/{productId}")
    public ResponseEntity<ResponseObject> deactiveProduct(@PathVariable("productId") String productId) {
        return productElecService.deactivatedProduct(productId);
    }
    @DeleteMapping("/admin/manage/productelec/delete/{productId}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable("productId") String productId) {
        return productElecService.destroyProduct(productId);
    }

}
