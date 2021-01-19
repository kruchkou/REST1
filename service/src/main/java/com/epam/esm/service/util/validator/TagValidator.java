package com.epam.esm.service.util.validator;

import com.epam.esm.service.model.dto.TagDTO;

public final class TagValidator {

    private TagValidator() {
    }

    public static boolean validateForCreate(TagDTO tagDTO) {
        return validateName(tagDTO.getName());
    }

    private static boolean validateName(String name) {
        final int MAX_NAME_LENGTH = 45;

        return name != null && name.length() < MAX_NAME_LENGTH;
    }

}
