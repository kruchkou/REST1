package com.epam.esm.service.validator;

import com.epam.esm.dto.GiftCertificateDTO;

public class GiftCertificateValidator {

    public boolean validateForCreate(GiftCertificateDTO giftCertificateDTO) {
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

    private boolean validateDuration(int duration) {
        return duration > 0;
    }

    private boolean validatePrice(int price) {
        return price > 0;
    }

    private boolean validateName(String name) {
        final int MAX_NAME_LENGTH = 45;

        return name != null && name.length() < MAX_NAME_LENGTH;
    }

    private boolean validateDesc(String desc) {
        final int MAX_DESC_LENGTH = 200;

        return desc != null && desc.length() < MAX_DESC_LENGTH;
    }

}
