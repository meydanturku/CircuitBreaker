package com.turku.core.controller;

import com.turku.core.service.MernisService;
import com.turku.core.utils.ServiceInvoker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;


/**
 * @noinspection rawtypes, RedundantSuppression
 */
@RestController
@RequestMapping("/api/istemciServis")
public class Controller {

    private final MernisService mernisService;
    private final ServiceInvoker serviceInvoker;

    public Controller(MernisService mernisService, ServiceInvoker serviceInvoker) {
        this.mernisService = mernisService;
        this.serviceInvoker = serviceInvoker;
    }

    @PostMapping("/mernis")
    public  ResponseEntity<Object> receiveMernis(
            @RequestHeader(value = "X-Auth-Token") String authorization,
            @RequestHeader(value = "clientGibintraIP") String clientIp,
            @RequestBody Object requestBody) {

        return mernisService.callExternalService(requestBody, authorization, clientIp);

    }

    @GetMapping("/hello")
    public String hello() {
        return serviceInvoker.invokeWithCircuitBreaker(mernisService, fallbackMethod);
    }

    Function<Throwable, String> fallbackMethod = throwable -> "Bir hata oluştu, hata mesajı: " + throwable.getMessage();
}

