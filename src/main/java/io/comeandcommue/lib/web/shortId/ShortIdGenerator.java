package io.comeandcommue.lib.web.shortId;

import java.io.Serializable;
import java.util.Base64;
import java.util.EnumSet;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

public class ShortIdGenerator implements BeforeExecutionGenerator {

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object owner,
                                 Object currentValue,
                                 EventType eventType) {
        // 이미 값이 있으면 그대로 사용
        if (currentValue != null) {
            return (Serializable) currentValue;
        }

        UUID uuid = UUID.randomUUID();
        byte[] bytes = uuidToBytes(uuid);

        // URL-safe Base64, padding 제거 → 앞 11자 사용
        String encoded = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);

        return encoded.substring(0, Math.min(11, encoded.length()));
    }

    private static byte[] uuidToBytes(UUID uuid) {
        byte[] bytes = new byte[16];
        long most = uuid.getMostSignificantBits();
        long least = uuid.getLeastSignificantBits();

        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((most >>> (8 * (7 - i))) & 0xFF);
        }
        for (int i = 0; i < 8; i++) {
            bytes[8 + i] = (byte) ((least >>> (8 * (7 - i))) & 0xFF);
        }
        return bytes;
    }
}
