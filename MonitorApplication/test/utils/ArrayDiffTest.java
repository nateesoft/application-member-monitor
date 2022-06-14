package utils;

import api.connect.model.MemberModel;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author nateesun
 */
@DisplayName("ArrayDiffTest")
public class ArrayDiffTest {

    private final ArrayDiffMember arrayDiffMember = new ArrayDiffMember();
    private final ArrayDiffRedeem arrayDiffRedeem = new ArrayDiffRedeem();

    @Test
    @DisplayName("testMemberFoundStatus_With_Empty_MemberLocal")
    public void testMemberFoundStatus_With_Empty_MemberLocal() {
        MemberModel memberServer = null;
        MemberModel[] memberLocalList = new MemberModel[0];
        String expectResult = arrayDiffMember.foundStatus(memberServer, memberLocalList);

        String realResult = "save";
        assertTrue(expectResult.equals(realResult));
    }

    @Test
    @DisplayName("testMemberFoundStatus_With_MemberServer_NullCode")
    public void testMemberFoundStatus_With_MemberServer_NullCode() {
        MemberModel memberServer = new MemberModel();
        memberServer.setCode(null);

        MemberModel[] memberLocalList = new MemberModel[1];
        String expectResult = arrayDiffMember.foundStatus(memberServer, memberLocalList);

        String realResult = "";
        assertTrue(expectResult.equals(realResult));
    }

    @Test
    @DisplayName("testMemberFoundStatus_With_MemberLocal_NullCode")
    public void testMemberFoundStatus_With_MemberLocal_NullCode() {
        MemberModel memberServer = new MemberModel();
        memberServer.setCode("001");

        MemberModel memberLocal = new MemberModel();
        memberLocal.setCode(null);
        MemberModel[] memberLocalList = new MemberModel[]{memberLocal};
        String expectResult = arrayDiffMember.foundStatus(memberServer, memberLocalList);

        String realResult = "";
        assertTrue(expectResult.equals(realResult));
    }

    @Test
    @DisplayName("testMemberFoundStatus_WithNullTotalScore")
    public void testMemberFoundStatus_WithNullTotalScore() {
        MemberModel memberServer = new MemberModel();
        memberServer.setCode("001");

        MemberModel memberLocal = new MemberModel();
        memberLocal.setCode("001");
        memberLocal.setTotal_score(null);
        MemberModel[] memberLocalList = new MemberModel[]{memberLocal};
        String expectResult = arrayDiffMember.foundStatus(memberServer, memberLocalList);

        String realResult = "";
        assertTrue(expectResult.equals(realResult));
    }

    @Test
    @DisplayName("testMemberFoundStatus_WithNullM1AndMoreThan1ArrB")
    public void testMemberFoundStatus_WithNullM1AndMoreThan1ArrB() {
        MemberModel memberServer = null;
        MemberModel[] memberLocalList = new MemberModel[1];
        String expectResult = arrayDiffMember.foundStatus(memberServer, memberLocalList);

        String realResult = "";
        assertTrue(expectResult.equals(realResult));
    }

    @Test
    @DisplayName("testMemberFoundStatus_NotSaveAndNotUpdate")
    public void testMemberFoundStatus_NotSaveAndNotUpdate() {
        MemberModel memberServer = new MemberModel();
        memberServer.setCode("m0001");
        memberServer.setTotal_score(10f);
        memberServer.setTotal_purchase(100f);

        MemberModel a1 = new MemberModel();
        a1.setCode("m0001");
        a1.setTotal_score(10f);
        a1.setTotal_purchase(100f);

        MemberModel[] memberLocalList = new MemberModel[]{a1};
        String expectResult = arrayDiffMember.foundStatus(memberServer, memberLocalList);

        String realResult = "";
        assertTrue(expectResult.equals(realResult));
    }

    @Test
    @DisplayName("testFoundStatus_CanSave")
    public void testFoundStatus_CanSave() {
        MemberModel memberServer = new MemberModel();
        memberServer.setCode("m0001");

        MemberModel memberLocal = new MemberModel();
        memberLocal.setCode("m0002");

        MemberModel[] memberLocalList = new MemberModel[]{memberLocal};
        String expectResult = arrayDiffMember.foundStatus(memberServer, memberLocalList);

        String realResult = "save";
        assertTrue(expectResult.equals(realResult));
    }

    @Test
    @DisplayName("testFoundStatus_CanUpdate")
    public void testFoundStatus_CanUpdate() {
        MemberModel memberServer = new MemberModel();
        memberServer.setCode("m0001");
        memberServer.setTotal_score(10f);
        memberServer.setTotal_purchase(100f);

        MemberModel memberLocal = new MemberModel();
        memberLocal.setCode("m0001");
        memberLocal.setTotal_score(20f);
        memberLocal.setTotal_purchase(1000f);

        MemberModel[] memberLocalList = new MemberModel[]{memberLocal};
        String expectResult = arrayDiffMember.foundStatus(memberServer, memberLocalList);

        String realResult = "update";
        assertTrue(expectResult.equals(realResult));
    }

    @Test
    @DisplayName("diffInsertUpdateTest")
    public void diffInsertUpdateTest() {
        MemberModel[] memberServerList = new MemberModel[0];
        MemberModel[] memberLocalList = new MemberModel[0];
        MemberModel[] expectResult = arrayDiffMember.diffInsertUpdate(memberServerList, memberLocalList);

        MemberModel[] realResult = new MemberModel[0];
        assertTrue(Arrays.equals(expectResult, realResult));
    }
}
