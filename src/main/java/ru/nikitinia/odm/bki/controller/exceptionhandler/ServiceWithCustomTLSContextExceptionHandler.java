package ru.nikitinia.odm.bki.controller.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nikitinia.odm.bki.exception.BKIIncomeWebException;
import ru.nikitinia.odm.bki.exception.bki.BKIProcessingException;
import ru.nikitinia.odm.bki.exception.tls.*;
import ru.nikitinia.odm.bki.model.exception.ServiceModelError;
import ru.nikitinia.odm.bki.model.exception.ServiceModelException;
import ru.nikitinia.odm.bki.util.Constant;

import java.util.List;

import static ru.nikitinia.odm.bki.exception.util.IdErrorGenerator.generateErrorId;

@RestControllerAdvice
public class ServiceWithCustomTLSContextExceptionHandler {

    @ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
    @ExceptionHandler({
            KeyManagerException.class,
            CertException.class,
            SSLContextException.class,
            PXFStoreException.class,
            TrustManagerException.class,
            JcpKeyStoreException.class
    })
    public ResponseEntity<ServiceModelException> handleSslNetworkException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
                .body(ServiceModelException.builder()
                        .id(generateErrorId())
                        .source(Constant.SystemAndComponent.SSL_PROCESSING)
                        .errors(List.of(
                                ServiceModelError.builder()
                                        .message(exception.getMessage())
                                        .build()
                        ))
                        .build());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            BKIIncomeWebException.class
    })
    public ResponseEntity<ServiceModelException> handleEquifaxIncomeWebException(BKIIncomeWebException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ServiceModelException.builder()
                        .id(generateErrorId())
                        .source(Constant.SystemAndComponent.BKI_NAME)
                        .errors(List.of(
                                ServiceModelError.builder()
                                        .message(exception.getMessage())
                                        .build()
                        ))
                        .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BKIProcessingException.class
    })
    public ResponseEntity<ServiceModelException> handleEquifaxConnectException(BKIProcessingException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ServiceModelException.builder()
                        .id(exception.getServiceModelException().id())
                        .source(Constant.SystemAndComponent.BKI_NAME)
                        .errors(exception.getServiceModelException().errors())
                        .build()
                );
    }

}