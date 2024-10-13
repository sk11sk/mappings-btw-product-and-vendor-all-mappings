//package com.example.demo.Controller;
//
//
//
//import com.example.demo.entities.Vendor;
//import com.example.demo.repository.VendorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/vendor")
//public class VendorController {
//
//
//    @Autowired
//    VendorRepository vendorRepository;
//   // http://localhost:8080/api/vendor/addVendor
//    @PostMapping("/addVendor")
//     public ResponseEntity <VendorDTO> addVendor(@RequestBody VendorDTO vendorDto) {
//
//         Vendor vendor = new Vendor();
//
//         vendor.setVendorName(vendorDto.getVendorName());
//
//        Vendor saveVendor = vendorRepository.save(vendor);
//
//        VendorDTO  dto = new VendorDTO();
//        dto.setVendorId(saveVendor.getVendorId());
//        dto.setVendorName(saveVendor.getVendorName());
//
//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
//     }
//
//
//}
