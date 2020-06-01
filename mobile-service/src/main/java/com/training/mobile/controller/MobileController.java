package com.training.mobile.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.mobile.dto.GetAllMobileRequest;
import com.training.mobile.dto.MobileDto;
import com.training.mobile.dto.Response;
import com.training.mobile.dto.SaveMobileRequest;
import com.training.mobile.service.MobileDaoService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("mobiles")
public class MobileController {
    @Autowired
    private MobileDaoService mobileDaoService;

    @GetMapping
    @ApiResponse(description = "This method returns all mobile details ....")
    public Response getAllMobiles(
    		GetAllMobileRequest inputRequest
    		){
        return Response.builder().response( mobileDaoService.getAllMobiles(inputRequest)).build();
    }


    
    @GetMapping( "{mobile-id}" )
    public Response getMobile(@PathVariable("mobile-id") @Valid @Min(value=1, message="{Min.SaveMobileRequest.id}" ) int mobileId ){
    	return Response.builder().response( mobileDaoService.getMobileById(mobileId)).build();
    	/*
        return  (mobileId <= 0)
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.ok().body(mobileDaoService.getMobileById(mobileId));
        */
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Response saveMobile(@RequestBody @Valid SaveMobileRequest saveMobileRequest){
    	System.out.println("Mobile :" + saveMobileRequest);
        mobileDaoService.saveMobile(saveMobileRequest);
        return Response.builder().response( "Mobile Saved successfully :: " + saveMobileRequest).build();
        
//        httpEntity.getHeaders().forEach((k,v) -> {
//            System.out.println("Key :" + k.toString());
//            System.out.println("Value :" + v.toString());
//        });
//        mobileDaoService.saveMobile(httpEntity.getBody());
    }

    @PutMapping
    public Response updateMobile(@RequestBody MobileDto mobile){
        return Response.builder().response(mobileDaoService.updateMobile(mobile)).build(); 
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
