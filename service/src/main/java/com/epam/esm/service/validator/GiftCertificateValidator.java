package com.epam.esm.service.validator;

import com.epam.esm.model.dto.GiftCertificateDTO;

public final class GiftCertificateValidator {

    private GiftCertificateValidator() {
    }

    public static boolean validateForCreate(GiftCertificateDTO giftCertificateDTO) {
        if (!validateName(giftCertificateDTO.getName())) {
            return false;
        }
        if (!validateDesc(giftCertificateDTO.getDescription())) {
            return false;
        }
        if (!validatePrice(giftCertificateDTO.getPrice())) {
            return false;
        }
        return validateDuration(giftCertificateDTO.getDuration());
    }

    private static boolean validateDuration(int duration) {
        return duration > 0;
    }

    private static boolean validatePrice(int price) {
        return price > 0;
    }

    private static boolean validateName(String name) {
        final int MAX_NAME_LENGTH = 45;

        return name != null && name.length() < MAX_NAME_LENGTH;
    }

    private static boolean validateDesc(String desc) {
        final int MAX_DESC_LENGTH = 200;

        return desc != null && desc.length() < MAX_DESC_LENGTH;
    }

}
