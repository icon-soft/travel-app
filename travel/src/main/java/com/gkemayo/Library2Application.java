package com.gkemayo;

import com.gkemayo.voy.agency.Agency;
import com.gkemayo.voy.agency.IAgencyService;
import com.gkemayo.voy.bus.Bus;
import com.gkemayo.voy.bus.IBusService;
import com.gkemayo.voy.customer.Customer;
import com.gkemayo.voy.customer.ICustomerService;
import com.gkemayo.voy.place.IPlaceService;
import com.gkemayo.voy.travel.ITravelService;
import com.gkemayo.voy.travel.Travel;
import com.gkemayo.voy.travel.TravelId;
import com.gkemayo.voy.travel.TravelStatus;
import com.gkemayo.voy.ville.IVilleService;
import com.gkemayo.voy.ville.Ville;
import java.time.LocalDate;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Library2Application {

    public static void main(String[] args) {
        SpringApplication.run(Library2Application.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gkemayo"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Library Spring Boot REST API Documentation")
                .description("REST APIs For Managing Books loans in a Library")
                .contact(new Contact("Georges Kemayo", "https://gkemayo.developpez.com/", "noreply.library.test@gmail.com"))
                .version("1.0")
                .build();

    }

    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


    @Autowired
    IVilleService villeService;
    @Autowired
    IAgencyService agencyService;
    @Autowired
    ICustomerService customerService;
    @Autowired
    IBusService busService;
    @Autowired
    ITravelService travelService;
    @Autowired
    IPlaceService placeService;

    @Bean
    public void data() {
        Ville ville1 = villeService.saveVille(new Ville(null, "Douala", null));
        Ville ville2 = villeService.saveVille(new Ville(null, "Yaoundé", null));
        Ville ville3 = villeService.saveVille(new Ville(null, "Foumban", null));

        agencyService.saveAgency(new Agency(null, "Buca kilomètre 5", LocalDate.now(), 5, ville1, null));
        agencyService.saveAgency(new Agency(null, "Buca Nvan", LocalDate.now(), 6, ville2, null));
        agencyService.saveAgency(new Agency(null, "Avenir du noun", LocalDate.now(), 7, ville3, null));
        agencyService.saveAgency(new Agency(null, "Butsys Bessengue", LocalDate.now(), 4, ville1, null));

        Customer c1 = customerService.saveCustomer(new Customer(null, "215054120", "FOKOU", "Ghyslain", "Analyst", "Bependa", "fokou@yahoo.fr", LocalDate.now(), null));
        Customer c2 = customerService.saveCustomer(new Customer(null, "8754452552", "NJOYA", "Arouna", "Administrator", "denver", "njoya@yahoo.fr", LocalDate.now(), null));
        Customer c3 = customerService.saveCustomer(new Customer(null, "84755452", "TALOM", "Joel", "Architect", "Cité sic", "talom@yahoo.fr", LocalDate.now(), null));

        Bus bus1 = busService.saveBus(new Bus(null, "LT 075 EM", "Madiba", null, null, null));
        Bus bus2 = busService.saveBus(new Bus(null, "LT 145 GE", "Mont Cameroun", null, null, null));
        Bus bus3 = busService.saveBus(new Bus(null, "CE 784 KL", "Martin luther", null, null, null));
        Bus bus4 = busService.saveBus(new Bus(null, "OU 065 FR", "King long", null, null, null));
        Bus bus5 = busService.saveBus(new Bus(null, "CE 874 TM", "Komodo", null, null, null));

        travelService.saveTravel(new Travel(null, 5, LocalDate.now(), LocalDate.now(), null));
        travelService.saveTravel(new Travel(null, 5, LocalDate.now(), LocalDate.now(), null));
        travelService.saveTravel(new Travel(null, 5, LocalDate.now(), LocalDate.now(), null));

    }
}
