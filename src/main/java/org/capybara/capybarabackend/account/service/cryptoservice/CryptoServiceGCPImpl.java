package org.capybara.capybarabackend.account.service.cryptoservice;

import com.google.cloud.kms.v1.CryptoKeyName;
import com.google.cloud.kms.v1.DecryptResponse;
import com.google.cloud.kms.v1.EncryptResponse;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @see <a href="https://cloud.google.com/kms/docs/encrypt-decrypt#kms-encrypt-symmetric-java">Encrypting and decrypting data with a symmetric key</a>
 */
@Service
@Profile("cloud")
class CryptoServiceGCPImpl implements CryptoService {

    private final KeyManagementServiceClient keyManagementServiceClient;

    private final String cloudGoogleProjectId;

    private final String cloudGoogleRegion;

    private final String cloudGoogleCryptoKeyRingId;

    private final String cloudGoogleCryptoKeyId;

    @Autowired
    CryptoServiceGCPImpl(KeyManagementServiceClient keyManagementServiceClient,
                         @Value("${cloud.google.project-id}") String cloudGoogleProjectId,
                         @Value("${cloud.google.region}") String cloudGoogleRegion,
                         @Value("${cloud.google.crypto.key-ring-id}") String cloudGoogleCryptoKeyRingId,
                         @Value("${cloud.google.crypto.key-id}") String cloudGoogleCryptoKeyId) {
        this.keyManagementServiceClient = keyManagementServiceClient;
        this.cloudGoogleProjectId = cloudGoogleProjectId;
        this.cloudGoogleRegion = cloudGoogleRegion;
        this.cloudGoogleCryptoKeyRingId = cloudGoogleCryptoKeyRingId;
        this.cloudGoogleCryptoKeyId = cloudGoogleCryptoKeyId;
    }

    @Override
    public byte[] encryptSymmetric(String plaintext) {
        // Build the key version name from the project, location, key ring and key
        CryptoKeyName keyName = CryptoKeyName.of(cloudGoogleProjectId, cloudGoogleRegion, cloudGoogleCryptoKeyRingId, cloudGoogleCryptoKeyId);

        // Encrypt the plaintext.
        EncryptResponse response = keyManagementServiceClient.encrypt(keyName, ByteString.copyFromUtf8(plaintext));

        return response.getCiphertext().toByteArray();
    }

    @Override
    public String decryptSymmetric(byte[] ciphertext) {
        // Build the key version name from the project, location, key ring and key
        CryptoKeyName keyName = CryptoKeyName.of(cloudGoogleProjectId, cloudGoogleRegion, cloudGoogleCryptoKeyRingId, cloudGoogleCryptoKeyId);

        // Decrypt the response.
        DecryptResponse response = keyManagementServiceClient.decrypt(keyName, ByteString.copyFrom(ciphertext));

        return response.getPlaintext().toStringUtf8();
    }

}
