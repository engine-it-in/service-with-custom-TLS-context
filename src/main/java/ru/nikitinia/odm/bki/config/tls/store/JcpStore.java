package ru.nikitinia.odm.bki.config.tls.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import ru.CryptoPro.JCP.JCP;
import ru.nikitinia.odm.bki.exception.tls.JcpKeyStoreException;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.UUID;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JcpStore {

    private final CertStore certStore;

    /**
     * Назначение ->
     * Хранилище гост сертификатов в хранилище на основе JCP;
     */
    public KeyStore prepareKeyStoreWithJcpCert() {

        KeyStore keyStore;

        try {
            keyStore = KeyStore.getInstance(JCP.CERT_STORE_NAME);
        } catch (KeyStoreException exception) {
            log.error(
                    "JcpKeyStore. Ошибка при подготовке JCP хранилища сертификатов: {}",
                    exception.getMessage()
            );
            throw new JcpKeyStoreException(exception.getMessage());
        }

        try {
            keyStore.load(null, null);
        } catch (IOException | NoSuchAlgorithmException | CertificateException exception) {
            log.error(
                    "JcpKeyStore. Ошибка при инициализации JCP хранилища сертификатов: {}",
                    exception.getMessage()
            );
            throw new JcpKeyStoreException(exception.getMessage());
        }

        try {
            for (X509Certificate cert : certStore.getCertsFromCacerts()) {
                keyStore.setCertificateEntry(UUID.randomUUID().toString(), cert);
            }
        } catch (KeyStoreException exception) {
            log.error(
                    "JcpKeyStore. Ошибка при добавлении гост сертификатов в хранилище: {}",
                    exception.getMessage()
            );
            throw new JcpKeyStoreException(exception.getMessage());
        }
        return keyStore;
    }
}