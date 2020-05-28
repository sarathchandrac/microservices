package com.training.mobile.controller;

import com.training.mobile.dto.GetAllMobileRequest;
import com.training.mobile.dto.SaveMobileRequest;
import com.training.mobile.exception.ErrorDetails;
import com.training.mobile.exception.MobileNotFoundException;
import com.training.mobile.model.Mobile;
import com.training.mobile.service.MobileDaoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("mobiles")
public class MobileController {
    @Autowired
    private MobileDaoService mobileDaoService;

    @GetMapping
    @ApiResponse(description = "This method returns all mobile details ....")
    public List<Mobile> getAllMobiles(
    		GetAllMobileRequest inputRequest
    		){
        return mobileDaoService.getAllMobiles(inputRequest);
    }


    
    @GetMapping( value = "/{mobile-id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Mobile> getMobile(@PathVariable("mobile-id") int mobileId ){
        return  (mobileId <= 0)
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.ok().body(mobileDaoService.getMobileById(mobileId));
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMobile(@RequestBody @Valid SaveMobileRequest saveMobileRequest){
    	System.out.println("Mobile :" + saveMobileRequest);
        mobileDaoService.saveMobile(saveMobileRequest);
//        httpEntity.getHeaders().forEach((k,v) -> {
//            System.out.println("Key :" + k.toString());
//            System.out.println("Value :" + v.toString());
//        });
//        mobileDaoService.saveMobile(httpEntity.getBody());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMobile(@RequestBody Mobile mobile){
        mobileDaoService.updateMobile(mobile);
    }

    @DeleteMapping("/{mobileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMobileById(@PathVariable int mobileId){
        mobileDaoService.deleteMobile(mobileId);
    }
    

    /*
    @GetMapping( value = "/name/{mobile-name}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<List<Mobile>> getMobile(@PathVariable("mobile-name") String name ){
        return  (name.trim().isEmpty())
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.ok().body(mobileDaoService.getMobileByName(name));
    }

    @GetMapping( value = "/price/{mobile-price}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<List<Mobile>> getMobile(@PathVariable("mobile-price") Integer price ){
        return  (price <= 0)
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.ok().body(mobileDaoService.getMobileByPrice(price));
    }
    */

    /*
    * Handling Controller level exception handler
    @ExceptionHandler(value = MobileNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleMobileNotFoundException(MobileNotFoundException mfe){
        ErrorDetails error = new ErrorDetails();
        error.setErrorCode(mfe.getErrorCode());
        error.setErrorMessage(mfe.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

     */

}
