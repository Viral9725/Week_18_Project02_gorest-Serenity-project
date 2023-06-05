package com.gorest.crudtest;

import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class CategoriesCRUDTest extends TestBase {
    static String name = "Jason" + TestUtils.getRandomValue();
    static String email = "jason" + TestUtils.getRandomValue() + "@gmail.com";
    static String status = "Active";
    static String gender = "male";
    static Integer userId;

    @Steps
    UserSteps userSteps;

    @Title("This test will create new user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.crateUserInfo(name, gender, status, email).statusCode(201).log().all();
        userId = response.log().all().extract().path("id");

    }

    @Title("This test will verify user added successfully")
    @Test
    public void test002() {
        HashMap<String, Object> userMap = userSteps.getUserInfo(userId);
        Assert.assertThat(userMap, hasValue(name));
        System.out.println(userMap);
    }

    @Title("This test will update user details")
    @Test
    public void test003() {
        name = name + "_updated";
        email = email + "c";
        ValidatableResponse response = userSteps.updateUser(name, gender, userId, email, status).statusCode(200).log().all();
        HashMap<String, Object> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(name));
    }

    @Title("This test will Check email of new id")
    @Test
    public void test004() {
        ValidatableResponse response = userSteps.getAllUserData();
        List<?> emailfetch = response.log().all().extract().path("findAll{it.id==" + userId + "}.email");
        Assert.assertEquals(email, emailfetch.get(0));
    }
    @Title("This test will check size of records")
    @Test
    public void test005() {
        ValidatableResponse response = userSteps.getAllUserData().statusCode(200).log().all();

        int totalRecords = response.extract().path("records.size");
        Assert.assertEquals(20, totalRecords);
    }

    @Title("This test will Check name of new id")
    @Test
    public void test006() {
        ValidatableResponse response = userSteps.getAllUserData();
        List<?> namefetch = response.log().all().extract().path("findAll{it.id==" + userId + "}.name");
        Assert.assertEquals(name, namefetch.get(0));
    }

    @Title("This test will check all ID has status 'active' ")
    @Test
    public void test008() {
        ValidatableResponse response = userSteps.getAllUserData();
        List<String> statusList = response.log().all().extract().path("status");
        System.out.println(statusList);
        for (String data : statusList) {
            if (data.equalsIgnoreCase("active")) {
                Assert.assertEquals("active", data);
            }
        }
    }

    @Title("This test will check new id has gender 'male'")
    @Test
    public void test009() {
        ValidatableResponse response = userSteps.getAllUserData();
        List<?> genderfetch = response.log().all().extract().path("findAll{it.id==" + userId + "}.gender");
        Assert.assertEquals(gender, genderfetch.get(0));
    }

    @Title("This test will delete the user")
    @Test
    public void test010() {
        ValidatableResponse response = userSteps.deleteUser(userId).statusCode(204).log().all();
    }
}
