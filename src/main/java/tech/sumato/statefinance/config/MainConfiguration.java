package tech.sumato.statefinance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.egov.tracer.config.TracerConfiguration;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Import({ TracerConfiguration.class })
public class MainConfiguration {

    @Value("${app.timezone}")
    private String timeZone;

    @PostConstruct
    public void initialize() {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    @Bean
    @Autowired
    public MappingJackson2HttpMessageConverter jacksonConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean
    public ServletRegistrationBean<HttpRequestHandlerServlet> reportViewerServlet() {
        ServletRegistrationBean<HttpRequestHandlerServlet> bean = new ServletRegistrationBean<>(
                new HttpRequestHandlerServlet(), "/reportViewer");
        bean.setName("reportViewer");
        bean.setLoadOnStartup(0);
        return bean;
    }

}