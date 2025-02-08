package com.smshub.org.core.helpers.sms;

import com.smshub.org.core.helpers.sms.enums.SMSProviderType;
import com.smshub.org.core.helpers.sms.impl.OrangeSMSProvider;
import com.smshub.org.core.helpers.sms.interfaces.SMSProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Component
public class SMSFactory {

    private final OrangeSMSProvider orangeSMSProvider;

    public SMSFactory(OrangeSMSProvider orangeSMSProvider) {
        this.orangeSMSProvider = orangeSMSProvider;
    }

    public SMSProvider createSMSProvider(SMSProviderType type) {
        if (Objects.requireNonNull(type) == SMSProviderType.ORANGE) {
            return orangeSMSProvider;
        }
        throw new IllegalArgumentException("Unsupported SMS provider type: " + type);
    }
}
