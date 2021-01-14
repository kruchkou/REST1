package com.epam.esm.service.validator;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;

public class TagValidator {

    public boolean validateForCreate(TagDTO tagDTO) {
        return validateName(tagDTO.getName());
    }

    private boolean validateName(String name) {
        final int MAX_NAME_LENGTH = 45;

        return name != null && name.length() < MAX_NAME_LENGTH;
    }

}
