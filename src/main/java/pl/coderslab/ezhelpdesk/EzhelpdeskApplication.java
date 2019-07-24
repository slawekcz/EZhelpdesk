package pl.coderslab.ezhelpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import pl.coderslab.ezhelpdesk.fixture.CategoryFixture;
import pl.coderslab.ezhelpdesk.fixture.TicketFixture;
import pl.coderslab.ezhelpdesk.fixture.UserFixture;

@SpringBootApplication
public class EzhelpdeskApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(EzhelpdeskApplication.class, args);
        ctx.getBean(CategoryFixture.class).initData();
        ctx.getBean(UserFixture.class).initData();
        ctx.getBean(TicketFixture.class).initData();


    }

}
