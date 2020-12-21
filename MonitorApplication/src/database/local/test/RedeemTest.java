package database.local.test;

import database.local.Redeem;
import database.local.RedeemModel;

/**
 *
 * @author nateesun
 */
public class RedeemTest {
    
    public static void main(String[] args) {
        Redeem rd = new Redeem();
        RedeemModel model = rd.findById("02fc1ec0-dbe9-49af-aec6-6bd3cdda5ffd");
        System.out.println(model);
    }
}
