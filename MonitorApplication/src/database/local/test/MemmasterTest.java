package database.local.test;

import database.local.Memmaster;
import database.local.MemmasterModel;

/**
 *
 * @author nateesun
 */
public class MemmasterTest {
    
    public static void main(String[] args) {
        Memmaster rd = new Memmaster();
        MemmasterModel model = rd.findByMemberCode("MB00022");
        System.out.println(model);
    }
}
