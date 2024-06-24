package ru.nikitinia.odm.bki.config.tls.context;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import ru.nikitinia.odm.bki.config.tls.config.KeyManagerConfig;
import ru.nikitinia.odm.bki.config.tls.config.TrustManagerConfig;
import ru.nikitinia.odm.bki.exception.tls.SSLContextException;
import ru.nikitinia.odm.bki.util.Constant;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SSLContextConfig {

    private final TrustManagerConfig trustManagerConfig;
    private final KeyManagerConfig keyManagerConfig;

    /**
     * Назначение ->
     * Контекст с готовыми сертификатами и подключениями для установки безопасного соединения;
     */
    public SSLContext getInstance(boolean isContext) {

        SSLContext context;

        try {
            context = SSLContext.getInstance(Constant.Security.GOST_TLS_PROTOCOL);
            context.init(
                    isContext ? keyManagerConfig.getGostKeyManagers() : null,
                    trustManagerConfig.getGostTrustManager(),
                    null);
        } catch (NoSuchAlgorithmException | KeyManagementException exception) {
            log.error(
                    "SSLContext. Ошибка при формировании SSL контекста: {}",
                    exception.getMessage()
            );
            throw new SSLContextException(exception.getMessage());
        }
        return context;
    }

}
