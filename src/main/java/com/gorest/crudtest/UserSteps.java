package com.gorest.crudtest;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {
    @Step("Creating user with name : {0}, gender:{1},staus:{2},email:{3}")
    public ValidatableResponse crateUserInfo(String name, String gender, String status, String email) {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        userPojo.setEmail(email);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .body(userPojo)
                .when()
                .post()
                .then();
    }

    @Step("Get user info by id:{0}")
    public HashMap<String, Object> getUserInfo(int userId) {
        HashMap<String, Object> userData = SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .header("Cookie","_gorest_session=F%2FGqXDGP%2F1p7nvEU2kH33sANL9PX9M8kuhJ0Zl9BQKtvC5tsdwl108SssuSQAhBmShqGfdCnl4FVimhchaGTo3CPCtyqMMMsSPDd1pNPjQQePQR7R1USabZ9yEWIJqAQMbzEDRZPCQOB093AEAM63XcB7mN4vJo41VQUa%2FIuNnxSSs5gHznenvPQjMHoTxMwdugkkXaD7F9dHvo5spGU8HlckbR9GLB0qrySPD83JOiKy2KtT3ouhqpw0i4cosFzZ2yXdHC%2FiRxA6bPh73SSIJqgA8B071w%3D--q9BpayTtRko8n6OU--IRy9CAPhXGV3VXnJiPibbw%3D%3D")
                .pathParam("userId", userId)
                .when()
                .get(EndPoints.USERS_BYID)
                .then()
                .extract().path("");
        return userData;

    }

    @Step("Update user details with name :{0},gender:{1},userid:{2},email:{3},status:{4}")
    public ValidatableResponse updateUser(String name,String gender, int userId, String email, String status){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("userId",userId)
                .body(userPojo)
                .when()
                .patch(EndPoints.USERS_BYID)
                .then();
    }
    @Step("Delete the user by id :{0}")
    public ValidatableResponse deleteUser(int userId){
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("userId",userId)
                .when()
                .delete(EndPoints.USERS_BYID)
                .then();
    }

    @Step("Getting All Users Data")
    public ValidatableResponse getAllUserData() {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .header("Cookie","_gorest_session=F%2FGqXDGP%2F1p7nvEU2kH33sANL9PX9M8kuhJ0Zl9BQKtvC5tsdwl108SssuSQAhBmShqGfdCnl4FVimhchaGTo3CPCtyqMMMsSPDd1pNPjQQePQR7R1USabZ9yEWIJqAQMbzEDRZPCQOB093AEAM63XcB7mN4vJo41VQUa%2FIuNnxSSs5gHznenvPQjMHoTxMwdugkkXaD7F9dHvo5spGU8HlckbR9GLB0qrySPD83JOiKy2KtT3ouhqpw0i4cosFzZ2yXdHC%2FiRxA6bPh73SSIJqgA8B071w%3D--q9BpayTtRko8n6OU--IRy9CAPhXGV3VXnJiPibbw%3D%3D")
                .when()
                .get()
                .then();

    }

}
