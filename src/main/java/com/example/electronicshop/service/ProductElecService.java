package com.example.electronicshop.service;

import com.example.electronicshop.communication.request.ProductElecRequest;
import com.example.electronicshop.communication.response.ProductElecListResponse;
import com.example.electronicshop.communication.response.ProductElecResponse;
import com.example.electronicshop.config.CloudinaryConfig;
import com.example.electronicshop.config.Constant;
import com.example.electronicshop.map.ProductElecMap;
import com.example.electronicshop.models.ResponseObject;
import com.example.electronicshop.models.enity.Product;
import com.example.electronicshop.models.enity.ProductElec;
import com.example.electronicshop.models.enity.ProductElecImage;
import com.example.electronicshop.notification.AppException;
import com.example.electronicshop.notification.NotFoundException;
import com.example.electronicshop.repository.BrandRepository;
import com.example.electronicshop.repository.CategoryRepository;
import com.example.electronicshop.repository.ProductElecRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Slf4j
public class ProductElecService {
    private final ProductElecRepository productElecRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductElecMap productElecMap;
    private final CloudinaryConfig cloudinary;

    public ResponseEntity<?> addProduct(ProductElecRequest req) {
        List<ProductElecImage> images = new ArrayList<>();
        if (req != null) {
            ProductElec product = productElecMap.toProduct(req);
            try {
                processUploadImage(req.getImages(), product);
                productElecRepository.save(product);
            } catch (Exception e) {
                throw new AppException(HttpStatus.CONFLICT.value(), "Product name already exists");
            }
            ProductElecResponse res = productElecMap.toProductRes(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject("true", "Add product successfully ", res)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("false", "Request is null", "")
        );
    }

    public List<ProductElecImage> processUploadImage (List<MultipartFile> images, ProductElec product) {
        if (images == null || images.isEmpty()) throw new AppException(HttpStatus.BAD_REQUEST.value(), "images is empty");
        for (int i = 0; i < images.size(); i++) {
            try {
                String url = cloudinary.uploadImage(images.get(i), null);
                if (i == 0) product.getImages().add(new ProductElecImage(UUID.randomUUID().toString(), url));
                else product.getImages().add(new ProductElecImage(UUID.randomUUID().toString(), url));
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new AppException(HttpStatus.EXPECTATION_FAILED.value(), "Error when upload images");
            }
            productElecRepository.save(product);
        }
        return product.getImages();
    }

    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<ProductElec> products;
        products = productElecRepository.findAllByState(Constant.ENABLE, pageable);
        List<ProductElecListResponse> resList = products.getContent().stream().map(productElecMap::toProductListRes).collect(Collectors.toList());
        ResponseEntity<?> resp = addPageableToRes(products, resList);
        if (resp != null) return resp;
        throw new NotFoundException("Can not found any product");
    }
    private ResponseEntity<?> addPageableToRes(Page<ProductElec> products, List<ProductElecListResponse> resList) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("list", resList);
        resp.put("totalQuantity", products.getTotalElements());
        resp.put("totalPage", products.getTotalPages());
        if (resList.size() >0 )
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("true", "Get all product success", resp));
        return null;
    }
    public ResponseEntity<?> findById(String id) {
        Optional<ProductElec> product = productElecRepository.findProductByIdAndState(id, Constant.ENABLE);
        if (product.isPresent()) {
            ProductElecResponse res = productElecMap.toProductRes(product.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("true", "Get product success", res));
        }
        throw new NotFoundException("Can not found any product with id: "+id);
    }
    public ResponseEntity<ResponseObject> deactivatedProduct(String id) {
        Optional<ProductElec> product = productElecRepository.findProductByIdAndState(id, Constant.ENABLE);
        if (product.isPresent()) {
            product.get().setState(Constant.DISABLE);
            productElecRepository.save(product.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("true", "Delete product successfully ", "")
            );
        } throw new NotFoundException("Can not found product with id: "+id);
    }
    @Transactional
    public ResponseEntity<ResponseObject> destroyProduct(String id) {
        Optional<ProductElec> product = productElecRepository.findProductByIdAndState(id, Constant.ENABLE);
        if (product.isPresent()) {
            try {
                productElecRepository.deleteById(product.get().getId());

            } catch (Exception e) {
                log.error(e.getMessage());
                throw new NotFoundException("Error when destroy product with id: "+id);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("true", "Destroy product successfully ", "")
            );
        } throw new NotFoundException("Can not found product with id: "+id);
    }
}
