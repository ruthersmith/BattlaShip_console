package Testing;

import com.company.BattleShip;
import org.junit.Test;

public class BattleShipTest {
    @Test
    public void testDeployShip(){
        BattleShip battleShip = new BattleShip();
        battleShip.deployShip('p',0,0,false);
        assert battleShip.getGrid()[0][0] == 'p';
    }

    @Test
    public void testValidateInput(){
        BattleShip battleShip = new BattleShip();
        assert battleShip.validateInput(1,2);
    }

    @Test
    public void testValidInputAvoidCollision(){
        BattleShip battleShip = new BattleShip();
        battleShip.deployShip('a',1,2,false);
        assert !battleShip.validateInput(1,2);
    }
}
