package com.project.sap.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.ui.Model;

public final class InputValidation {
    public static Model numberCheck(String name, String number, Model model){
        if(!NumberUtils.isCreatable(number)){
            model.addAttribute("error", name + " must be a number!");
        }
        return model;
    }

    public static Model lenghtCheck(String name, String input, Model model){
        if(input.length()<2){
            model.addAttribute("error", name + " must be at least 2 characters long!");
        }
        return model;
    }
}
