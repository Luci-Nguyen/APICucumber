package commons;

import io.restassured.response.Response;
import org.junit.Test;

public class TestCase extends BaseMethod {
    Response response;

    int id = 132;

    @Test
    public void test01_getBill() {
        getURI(Endpoints.BASE_URI);
        setHeader();
        setParam("id", id);
        response = get(Endpoints.GET_BILL);
        response.prettyPrint();
        verifyStatusCode(response, 200);

    }

}
