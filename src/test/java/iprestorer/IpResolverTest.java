package iprestorer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class IpResolverTest {


    private Logger log = LoggerFactory.getLogger(getClass());
    private IpResolver cut;


    @BeforeEach
    public void setup(){
        cut = new IpResolver();

    }


    @Test
    public void test_1921681781(){

        //WHEN
        List<String> resultIpAddresses = cut.restore("1921681781");

        //THEN

        for (String resultIpAddress : resultIpAddresses) {
            log.info(resultIpAddress);
        }
        Assertions.assertEquals(2, resultIpAddresses.size());

        Assertions.assertEquals("192.168.17.81", resultIpAddresses.get(0));
        Assertions.assertEquals("192.168.178.1", resultIpAddresses.get(1));


    }



    @Test
    public void test_127001(){
        List<String> list = cut.restore("127001");

        for (String ip : list) {
            log.info(ip);
        }

        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void test_11011(){

        /*
            11011
                =>
                1.1.0.11
                1.10.1.1
                11.0.1.1


         */

        String input = "11011";

        List<String> result = cut.restore(input);

        Assertions.assertEquals(3, result.size());

        for (String s : result) {
            log.info("result: " + s);
        }
        Assertions.assertTrue(result.contains("1.1.0.11"));
        Assertions.assertTrue(result.contains("11.0.1.1"));
        Assertions.assertTrue(result.contains("1.10.1.1"));


    }

    @Test
    public void testIsSegment_111(){
        Assertions.assertTrue(cut.isSegment("111"));
    }

    @Test
    public void testIsSegment_1(){

        Assertions.assertTrue(cut.isSegment("1"));
    }

    @Test
    public void testIsSegment_0(){

        Assertions.assertTrue(cut.isSegment("0"));
    }

    @Test
    public void testIsNotSegment_01(){

        Assertions.assertFalse(cut.isSegment("01"));
    }
}
