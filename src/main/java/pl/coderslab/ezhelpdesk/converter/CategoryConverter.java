package pl.coderslab.ezhelpdesk.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.ezhelpdesk.entity.Category;
import pl.coderslab.ezhelpdesk.repository.CategoryRepository;

public class CategoryConverter implements Converter<String, Category> {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category convert(String s) {
        return categoryRepository.findFirstById(Long.parseLong(s));
    }
}
