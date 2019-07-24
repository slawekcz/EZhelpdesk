package pl.coderslab.ezhelpdesk.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.ezhelpdesk.entity.Category;
import pl.coderslab.ezhelpdesk.repository.CategoryRepository;

@Component
public class CategoryFixture {
    @Autowired
    CategoryRepository categoryRepository;

    public void initData() {
//        Category category1 = new Category();
//        category1.setName("Technical issues");
//        Category category2 = new Category();
//        category2.setName("Feature requests");
//        Category category3 = new Category();
//        category3.setName("Bugs");
//
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//        categoryRepository.save(category3);
    }
}
