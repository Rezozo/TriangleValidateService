import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIntegrationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void AllValid_success() throws Exception {
        ResponseEntity<Void> response = restTemplate.getForEntity("/triangle/validateAll", Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void OneValid_success() throws Exception {
        ResponseEntity<Void> response = restTemplate.getForEntity("/triangle/validate?a=3&b=5&c=5", Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void OneNotValid_success() throws Exception {
        ResponseEntity<Void> response = restTemplate.getForEntity("/triangle/validate?a=3&b=5&c=5", Void.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
