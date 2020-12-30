package iprestorer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IpResolver {

    private Logger log = LoggerFactory.getLogger(getClass());

    public List<String> restore(String token) {

        List<String> resultList = new ArrayList<>();

        search(token, resultList);

        return resultList;
    }

    private void search(String token, List<String> resultList) {
        //start 13:14
        int N = token.length();

        for (int A = 1; A <= 3; A++) {
            String partA = token.substring(0, A);
            String restA = token.substring(A);
            //log.info("A: " + partA+"." + restA);

            for(int B = 1; B <= 3 && B < restA.length(); B++){
                String partB = restA.substring(0, B);
                String restB = restA.substring(B);
                //log.info("\tB: " + partB+"." + restB);

                for(int C = 1; C <= 3 && C < restB.length(); C++){
                    String partC = restB.substring(0, C);
                    String restC = restB.substring(C);
                    //log.info("\t\tC: " + partC+"." + restC);

                    if(isSegment(partA)
                        && isSegment(partB)
                        && isSegment(partC)
                        && isSegment(restC))
                    {
                        String NEW_IP = String.format("%s.%s.%s.%s",partA, partB, partC, restC);
                        //log.info("NEW_IP: "  + NEW_IP);
                        //log.info("\t HIT:" + NEW_IP);
                        resultList.add(NEW_IP);
                    }

                }
            }
        }

        //end: 13:31
        //all tests working: 13:42 :-)
        //

    }





    protected boolean isSegment(String value) {
        boolean result = false;

        if(value == null || value.length() == 0){
            return false;
        }
        if (value.charAt(0) == '0') {
            return value.equals("0");
        }

        int iValue = Integer.parseInt(value);

        if (iValue >= 0 && iValue <= 255) {
            result = true;
        }
        return result;
    }


}
