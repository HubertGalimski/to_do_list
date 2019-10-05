package io.github.HubertGalimski.lang;

public class LangMapper {
    static LangDTO langToDTO(Lang lang) {
        LangDTO langDTO = new LangDTO();
        langDTO.setId(lang.getId());
        langDTO.setCode(lang.getCode());
        return langDTO;
    }

    static Lang toLang(LangDTO langDTO) {
        Lang lang = new Lang();
        lang.setId(langDTO.getId());
        lang.setCode(langDTO.getCode());
        return lang;
    }
}
