package ru.nikitinia.odm.bki.config.tls.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ru.nikitinia.odm.bki.config.properties.Pfx;
import ru.nikitinia.odm.bki.exception.tls.PXFStoreException;
import ru.nikitinia.odm.bki.util.Constant;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Slf4j
@Configuration
@EnableConfigurationProperties(value = Pfx.class)
@RequiredArgsConstructor
public class PFXStore {

    private KeyStore keyStore;
    private final Pfx pfx;

    /**
     * Назначение ->
     * Хранилище pfx сертификата;
     */
    public KeyStore getKeyStore() {

        if (keyStore != null)
            return keyStore;
        try {
            byte[] certByteArray = new ClassPathResource("certs/cert.pfx").getContentAsByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(certByteArray);
            keyStore = KeyStore.getInstance(Constant.Security.PFX_STORE);
            keyStore.load(byteArrayInputStream, pfx.passwordPFX().toCharArray());

        } catch (
                KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException exception) {
            log.error(
                    "Store. Ошибка при подготовке хранилища ключей: {}",
                    exception.getMessage()
            );
            throw new PXFStoreException(exception.getMessage());
        }
        return keyStore;
    }

}