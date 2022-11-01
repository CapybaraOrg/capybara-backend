package org.capybara.capybarabackend.account.service.cryptoservice;

public interface CryptoService {

    byte[] encryptSymmetric(String plaintext);

    String decryptSymmetric(byte[] ciphertext);

}
