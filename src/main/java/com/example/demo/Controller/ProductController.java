package com.example.demo.Controller;



import com.example.demo.dto.*;

//import com.example.demo.entities.Category;
//import com.example.demo.entities.Purchase;
//import com.example.demo.repository.CategoryRepository;
import com.example.demo.entities.Product;
import com.example.demo.entities.Vendor;
import com.example.demo.repository.ProductRepository;
//import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    VendorRepository vendorRepository;
    @Autowired
    ProductRepository productRepository;

//    @Autowired
//    PurchaseRepository purchaseRepository;
//    @Autowired
//    CategoryRepository categoryRepository;

//many to many
    // http://localhost:8080/api/product/addproduct
    @PostMapping("/addproduct")
    public ResponseEntity<VendorDTO> addProduct(@RequestBody VendorDTO vendorDTO) {


        Vendor vendor = new Vendor();

        vendor.setVendorId(vendorDTO.getVendorId());
        vendor.setVendorName(vendorDTO.getVendorName());

List<Vendor> vendors = new ArrayList<>();
vendors.add(vendor);

        List<ProductDTO> products = vendorDTO.getProducts();
        List<Product> newproductslist = new ArrayList<>();
        for (ProductDTO pd:products){

            Product product = new Product();
            product.setProductId(pd.getProductId());
            product.setProductName(pd.getProductName());
            product.setQuantity(pd.getQuantity());
            product.setVendors(vendors);
          //  productRepository.save(product);  either this or line number 65 (productRepository.saveAll(newproductslist)
            newproductslist.add(product);

        }

        productRepository.saveAll(newproductslist);
        vendor.setProducts(newproductslist);
        Vendor savedVendor = vendorRepository.save(vendor);


        VendorDTO   vendorDTOResponse = new VendorDTO();

        vendorDTOResponse.setVendorId(savedVendor.getVendorId());
        vendorDTOResponse.setVendorName(savedVendor.getVendorName());


        List<Product> savedVendorProducts = savedVendor.getProducts();


        List<ProductDTO> productDTOS = new ArrayList<>();


        for (Product  product: savedVendorProducts){

            ProductDTO  productDTO= new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setQuantity(product.getQuantity());
            productDTOS.add(productDTO);


        }
        vendorDTOResponse.setProducts(productDTOS);



        return new ResponseEntity<>(vendorDTOResponse, HttpStatus.CREATED);
    }

//get  by product id
    // get by vendor id  = same as add product response ?? yes  same
    //fetch all products
    //fetch all   vendors




    //http://localhost:8080/api/product/getVendor/401
    @GetMapping("/getVendor/{id}")
    public ResponseEntity<VendorDTO> getVendor(@PathVariable Long id) {
        Vendor savedVendor = vendorRepository.findById(id).get();


        VendorDTO   vendorDTOResponse = new VendorDTO();

        vendorDTOResponse.setVendorId(savedVendor.getVendorId());
        vendorDTOResponse.setVendorName(savedVendor.getVendorName());


        List<Product> savedVendorProducts = savedVendor.getProducts(); // to get the products  associated to particular vendor


        List<ProductDTO> productDTOS = new ArrayList<>();


        for (Product  product: savedVendorProducts){

            ProductDTO  productDTO= new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setQuantity(product.getQuantity());
            productDTOS.add(productDTO);


        }
        vendorDTOResponse.setProducts(productDTOS);



        return new ResponseEntity<>(vendorDTOResponse, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/product/getAllVendors
    @GetMapping("/getAllVendors")

    public ResponseEntity<List<VendorDTO>> getAllVendors() {

        List<VendorDTO> vendorDTOS = new ArrayList<>();


        List<Vendor>  vendors = vendorRepository.findAll();

for (Vendor  savedVendor : vendors ){

    VendorDTO   vendorDTO = new VendorDTO();

    vendorDTO.setVendorId(savedVendor.getVendorId());
    vendorDTO.setVendorName(savedVendor.getVendorName());


    List<Product> savedVendorProducts = savedVendor.getProducts();  // to get the products  associated to particular vendor


    List<ProductDTO> productDTOS = new ArrayList<>();


    for (Product  product: savedVendorProducts){

        ProductDTO  productDTO= new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setQuantity(product.getQuantity());
        productDTOS.add(productDTO);


       }
    vendorDTO.setProducts(productDTOS);

    vendorDTOS.add(vendorDTO);


}

        return new ResponseEntity<>(vendorDTOS, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/product/getProduct/7
        @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {


            ProductResponseDto  productResponseDto= new ProductResponseDto();

            Product product = productRepository.findById(id).get();
            productResponseDto.setProductId(product.getProductId());
            productResponseDto.setProductName(product.getProductName());
            productResponseDto.setQuantity(product.getQuantity());

            List<VendorResponseDto> vendorResponseDtos = new ArrayList<>();
            List<Vendor> productVendors = product.getVendors();

            for (Vendor v: productVendors){
                VendorResponseDto   vendorResponseDto = new VendorResponseDto();
                vendorResponseDto.setVendorId(v.getVendorId());
                vendorResponseDto.setVendorName(v.getVendorName());

                vendorResponseDtos.add(vendorResponseDto);

            }


            productResponseDto.setVendors(vendorResponseDtos);

            return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }




// just  fetch by id (ProductResponseDto ) gets converted into   fetch all (List<ProductResponseDto>)  thats it
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        List<Product> products = productRepository.findAll();

        for (Product product : products){

            ProductResponseDto  productResponseDto= new ProductResponseDto();


            productResponseDto.setProductId(product.getProductId());
            productResponseDto.setProductName(product.getProductName());
            productResponseDto.setQuantity(product.getQuantity());

            List<VendorResponseDto> vendorResponseDtos = new ArrayList<>();
            List<Vendor> productVendors = product.getVendors();

            for (Vendor v: productVendors){
                VendorResponseDto   vendorResponseDto = new VendorResponseDto();
                vendorResponseDto.setVendorId(v.getVendorId());
                vendorResponseDto.setVendorName(v.getVendorName());

                vendorResponseDtos.add(vendorResponseDto);

            }


            productResponseDto.setVendors(vendorResponseDtos);

            productResponseDtos.add(productResponseDto);
        }





        return new ResponseEntity<>(productResponseDtos, HttpStatus.CREATED);
    }
}


//one to many /many to one

    // http://localhost:8080/api/product/addproduct
//    @PostMapping("/addproduct")
//    public ResponseEntity<VendorDTO> addProduct(@RequestBody VendorDTO vendorDTO) {
//
//        Vendor vendor  = new Vendor();
//        vendor.setVendorName(vendorDTO.getVendorName());
//
//        List<ProductDTO> productDTOList = vendorDTO.getProducts();
//
//        List<Product> products = new ArrayList<>();
//
//        for (ProductDTO p : productDTOList){
//
//            Product product = new Product();
//
//            product.setProductName(p.getProductName());
//
//            product.setQuantity(p.getQuantity());
//
//            product.setVendor(vendor); // set parent.set (child object)
//
//            products.add(product);
//
//        }
//
//        vendor.setProducts(products); //child Object.set (parentList)
//
//
//        Vendor savedVendor = vendorRepository.save(vendor);  // save child
//
//
//        productRepository.saveAll(products);  //save parent
//
//
//
//        VendorDTO vendorDTOResponse = new VendorDTO();
//
//        vendorDTOResponse.setVendorId(savedVendor.getVendorId());
//        vendorDTOResponse.setVendorName(savedVendor.getVendorName());
//
//
//
//        List<ProductDTO> productDTOResponseList = new ArrayList<>();
//
//        List<Product> products1 = savedVendor.getProducts();
//
//        for (Product  savedVendorProduct  : products1){
//
//            ProductDTO productDTOResponse = new ProductDTO();
//
//            productDTOResponse.setProductId(savedVendorProduct.getProductId());
//            productDTOResponse.setProductName(savedVendorProduct.getProductName());
//            productDTOResponse.setQuantity(savedVendorProduct.getQuantity());
//
//            productDTOResponseList.add(productDTOResponse);
//
//
//        }
//        vendorDTOResponse.setProducts(productDTOResponseList);
//
//
//        return new ResponseEntity<>(vendorDTOResponse, HttpStatus.CREATED);
//    }
//
//
//
//
////http://localhost:8080/api/product/7
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductResponseDto> getProductDetails(@PathVariable Long id) {
//
//
//
//
//
//
//        ProductResponseDto productResponseDto = new ProductResponseDto();
//
//        Product product = productRepository.findById(id).get();
//
//        productResponseDto.setProductId(product.getProductId());
//        productResponseDto.setProductName(product.getProductName());
//        productResponseDto.setQuantity(product.getQuantity());
//
//        Vendor vendor = product.getVendor();
//
//        VendorResponseDto  vendorResponseDto = new VendorResponseDto();
//        vendorResponseDto.setVendorId(vendor.getVendorId());
//        vendorResponseDto.setVendorName(vendor.getVendorName());
//
//        productResponseDto.setVendors(vendorResponseDto);
//
//
//        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
//
//
//
//    }
//
//
//    //http://localhost:8080/api/product/getAllProductDetails
//    @GetMapping("/getAllProductDetails")
//    public ResponseEntity<List<ProductResponseDto>> getAllProductDetails() {
//
//
//
//
//
//
//       List<ProductResponseDto> productResponseDtos = new ArrayList<>();
//
//
//
//        List<Product> all = productRepository.findAll();
//
//
//for (Product product :all){
//
//    ProductResponseDto productResponseDto = new ProductResponseDto();
//
//    productResponseDto.setProductId(product.getProductId());
//    productResponseDto.setProductName(product.getProductName());
//    productResponseDto.setQuantity(product.getQuantity());
//
//    Vendor vendor = product.getVendor();
//
//    VendorResponseDto  vendorsResponseDto = new VendorResponseDto();
//
//    vendorsResponseDto.setVendorId(vendor.getVendorId());
//    vendorsResponseDto.setVendorName(vendor.getVendorName());
//
//    productResponseDto.setVendors(vendorsResponseDto);
//
//    productResponseDtos.add(productResponseDto);
//
//}
//
//
//
//
//        return new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
//
//
//
//    }
//
//
//    //http://localhost:8080/api/product/VendorProducts/7
//@GetMapping("/VendorProducts/{id}")
//public ResponseEntity<VendorProductsDto> VendorProducts(@PathVariable Long id) {
//
//
//
//
//
//
//
//    VendorProductsDto vendorProductsDto = new VendorProductsDto();
//
//
//    Vendor vendor1 = vendorRepository.findById(id).get();
//
//    vendorProductsDto.setVendorId(vendor1.getVendorId());
//    vendorProductsDto.setVendorName(vendor1.getVendorName());
//
//    List<Product> products = vendor1.getProducts();
//    List<ProductDTO>  productList = new ArrayList<>();
//
//for (Product  p : products){
//    ProductDTO productDTO = new ProductDTO();
//    productDTO.setProductId(p.getProductId());
//    productDTO.setProductName(p.getProductName());
//    productDTO.setQuantity(p.getQuantity());
//    productList.add(productDTO);
//
//}
//    vendorProductsDto.setProductList(productList);
//
//    return new ResponseEntity<>(vendorProductsDto, HttpStatus.OK);
//
//
//
//}
//
//
//
//    //http://localhost:8080/api/product/allVendorProducts
//    @GetMapping("/allVendorProducts")
//    public ResponseEntity<List<VendorProductsDto>>  allVendorProducts() {
//
//        List<Vendor> vendors = vendorRepository.findAll(); // Fetch all vendors
//
//        List<VendorProductsDto> vendorProductsDtos = new ArrayList<>();
//
//        for (Vendor vendor : vendors) {
//            VendorProductsDto vendorProductsDto = new VendorProductsDto();
//            vendorProductsDto.setVendorId(vendor.getVendorId());
//            vendorProductsDto.setVendorName(vendor.getVendorName());
//
//            List<Product> products = vendor.getProducts();
//            List<ProductDTO> productList = new ArrayList<>();
//
//            for (Product product : products) {
//                ProductDTO productDTO = new ProductDTO();
//                productDTO.setProductId(product.getProductId());
//                productDTO.setProductName(product.getProductName());
//                productDTO.setQuantity(product.getQuantity());
//                productList.add(productDTO);
//            }
//
//            vendorProductsDto.setProductList(productList);
//            vendorProductsDtos.add(vendorProductsDto);
//        }
//
//        return new ResponseEntity<>(vendorProductsDtos, HttpStatus.OK);
//
//
//
//    }
//
//}





// one  to one

//        ProductDTO productDTO = vendorDTO.getProduct();
//        Product product = new Product();
//        product.setProductName(productDTO.getProductName());
//        product.setQuantity(productDTO.getQuantity());
//
//        productRepository.save(product);
//
//
//        Vendor vendor  = new Vendor();
//        vendor.setVendorName(vendorDTO.getVendorName());
//
//
//       vendor.setProduct(product);
//       product.setVendor(vendor);
//
//
//        Vendor savedVendor = vendorRepository.save(vendor);
//
//
//        VendorDTO vendorDTOResponse = new VendorDTO();
//
//        vendorDTOResponse.setVendorId(savedVendor.getVendorId());
//        vendorDTOResponse.setVendorName(savedVendor.getVendorName());
//
//
//        Product savedVendorProduct = savedVendor.getProduct();
//
//        ProductDTO productDTOResponse = new ProductDTO();
//
//        productDTOResponse.setProductId(savedVendorProduct.getProductId());
//        productDTOResponse.setProductName(savedVendorProduct.getProductName());
//        productDTOResponse.setQuantity(savedVendorProduct.getQuantity());
//
//        vendorDTOResponse.setProduct(productDTOResponse);







//older code


//
//
//        Long categoryId = productRequestDTO.getCategoryId();
//
//        Category category = categoryRepository.findById(categoryId).get();
//
//
//        Optional<Product> byProductName = productRepository.findByProductName(productRequestDTO.getProductName());
//
//        if (byProductName.isPresent()){
//
//
//            Product existingProduct = byProductName.get();
//
//            long quantity = existingProduct.getQuantity();
//
//            quantity = quantity+productRequestDTO.getQuantity();
//
//            existingProduct.setQuantity(quantity);
//
//
//
//            productRepository.save(existingProduct);//save cause quantity needs to be updated
//
//
//
//
//
//
//
//            Long vendorId = productRequestDTO.getVendorId();
//
//            Vendor savedVendor = vendorRepository.findById(vendorId).get();
//
//
//
//             savedVendor.getProducts().add(existingProduct);
//
//
//
//
//
//
//            vendorRepository.save(savedVendor);
//
//
//
//            Purchase purchase = new Purchase();
//
//            purchase.setProductId(existingProduct.getProductId());
//            purchase.setPurchasedQuantity(productRequestDTO.getQuantity());
//            purchase.setVendor(savedVendor);
//
//            purchaseRepository.save(purchase);
//
//
//
//
//
//        }  else {
//
//            Product product = new Product();
//            product.setProductName(productRequestDTO.getProductName());
//            product.setQuantity(productRequestDTO.getQuantity());
//
//            product.setCategory(category);
//
//            Product saveProduct = productRepository.save(product);
//
//
//
//            Long vendorId = productRequestDTO.getVendorId();
//
//            Vendor savedVendor = vendorRepository.findById(vendorId).get();
//
//
//
//            savedVendor.getProducts().add(product);
//
//            vendorRepository.save(savedVendor);
//
//
//
//            Purchase purchase = new Purchase();
//
//            purchase.setProductId(saveProduct.getProductId());
//            purchase.setPurchasedQuantity(productRequestDTO.getQuantity());
//            purchase.setVendor(savedVendor);
//
//            purchaseRepository.save(purchase);
//
//        }
//
//


//        Long categoryId = productRequestDTO.getCategoryId();
//
//        Category category = categoryRepository.findById(categoryId).get();
//
//
//
//
//
//
//        Product product = new Product();
//        product.setProductName(productRequestDTO.getProductName());
//        product.setQuantity(productRequestDTO.getQuantity());
//
//        product.setCategory(category);
//
//        productRepository.save(product);
//
//        List<Product> productList = new ArrayList<>();
//
//        productList.add(product);
//
//        Long vendorId = productRequestDTO.getVendorId();
//
//        Vendor savedVendor = vendorRepository.findById(vendorId).get();
//
//        savedVendor.setProducts(productList);
//
//        vendorRepository.save(savedVendor);
//


        // older code
//
//        Long categoryId = productRequestDTO.getCategoryId();
//
//
//        Category category = categoryRepository.findById(categoryId).get();
//
//
//        List<Product> productList = productRequestDTO.getProductList();
//
//
//        List<Product> alreadyPresentProductList = new ArrayList<>();
//
//        for (Product product : productList) {
//
//
//            Optional<Product> byProductName = productRepository.findByProductName(product.getProductName());
//
//            if (byProductName.isPresent()) {
//
//                int index = productList.indexOf(product);
//                System.out.println(index);
//
//                Product productPresent = byProductName.get();
//
//                System.out.println(product.getProductName());
//                alreadyPresentProductList.add(productPresent);
//
//                productList.remove(index);
//                System.out.println(productList);
//
//
//            } else {
//
//                product.setCategory(category);
//
//                productRepository.save(product);
//
//            }
//
//        }

//        Long vendorId = productRequestDTO.getVendorId();
//
//        Vendor savedVendor = vendorRepository.findById(vendorId).get();
//
//        savedVendor.setProducts(productList);
//
//        vendorRepository.save(savedVendor);
//
//        if (!alreadyPresentProductList.isEmpty()) {
//
//            savedVendor.setProducts(alreadyPresentProductList);
//            vendorRepository.save(savedVendor);
//
//        }
//

//
//        Long categoryId = productRequestDTO.getCategoryId();
//
//
//        Category category = categoryRepository.findById(categoryId).get();
//
//
//        List<Product> productList = productRequestDTO.getProductList();
//
//        for (Product product:productList){
//
//
//
//            product.setCategory(category);
//
//            productRepository.save(product);
//
//
//        }
//
//        Long vendorId = productRequestDTO.getVendorId();
//
//        Vendor savedVendor = vendorRepository.findById(vendorId).get();
//
//
//
//
//
//        savedVendor.setProducts(productList);
//
//        vendorRepository.save(savedVendor);
//
//
//




    //http://localhost:8080/api/product/1
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductResponseDto> getProductDetails(@PathVariable Long id) {
//
//        Product product = productRepository.findById(id).get();
//
//
//        ProductResponseDto productResponseDto = new ProductResponseDto();
//
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setProductId(product.getProductId());
//        productDTO.setProductName(product.getProductName());
//
//        productResponseDto.setProductDTO(productDTO);
//
//        Category category = product.getCategory();
//        CategoryDto categoryDto = new CategoryDto();
//
//        categoryDto.setCategoryId(category.getCategoryId());
//        categoryDto.setCategoryName(category.getCategoryName());
//        productResponseDto.setCategoryDTO(categoryDto);
//
//        List<Vendor> vendors = product.getVendors();
//
//        List<VendorDTO> vendorDTOList = new ArrayList<>();
//        for (Vendor vendor : vendors) {
//            VendorDTO vendorDTO = new VendorDTO();
//            vendorDTO.setVendorId(vendor.getVendorId());
//            vendorDTO.setVendorName(vendor.getVendorName());
//            vendorDTOList.add(vendorDTO);
//
//        }
//        productResponseDto.setVendorDTOList(vendorDTOList);
//
//
//        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
//
//    }
//
//
//    @GetMapping("/products") // Update the mapping as per your API design
//    public ResponseEntity<List<ProductResponseDto>> getAllProductDetails() {
//
//        List<Product> products = productRepository.findAll(); // Retrieve all products
//        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
//
//        for (Product product : products) {
//            ProductResponseDto productResponseDto = new ProductResponseDto();
//
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setProductId(product.getProductId());
//            productDTO.setProductName(product.getProductName());
//            productResponseDto.setProductDTO(productDTO);
//
//            // Set category details
//            Category category = product.getCategory();
//            CategoryDto categoryDto = new CategoryDto();
//            categoryDto.setCategoryId(category.getCategoryId());
//            categoryDto.setCategoryName(category.getCategoryName());
//            productResponseDto.setCategoryDTO(categoryDto);
//
//            // Set vendor details
//            List<Vendor> vendors = product.getVendors();
//            List<VendorDTO> vendorDTOList = new ArrayList<>();
//            for (Vendor vendor : vendors) {
//                VendorDTO vendorDTO = new VendorDTO();
//                vendorDTO.setVendorId(vendor.getVendorId());
//                vendorDTO.setVendorName(vendor.getVendorName());
//                vendorDTOList.add(vendorDTO);
//            }
//            productResponseDto.setVendorDTOList(vendorDTOList);
//
//            productResponseDtoList.add(productResponseDto); // Add the product response to the list
//        }
//
//        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK); // Return the list of product responses
//    }
//
//    @GetMapping("/quantity/{productName}")
//public ResponseEntity<?> getQuantity(@PathVariable String productName){
//
//
//
//        Long totalquantity = productRepository.getTotalQuantityByProductName(productName);
//
//
//
//
//
//        return new ResponseEntity<>(totalquantity, HttpStatus.OK); // Return the list of product response
//    }
//}
