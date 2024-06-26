package com.buildingMachineRental.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class InvalidUserExceptionTest {

    private UserFacade userFacade;

    @BeforeEach
    void onInit() {
        userFacade = new UserFacadeConfiguration().inMemoryRepo();
    }

    @Nested
    class ValidateNewUser {

        @ParameterizedTest
        @ValueSource(strings = {
                "toolongfirstname1234567890",
                "John123",
                "john-",
                "JOHN"
        })
        void shouldThrowExceptionWhenFirstNameIsNotValid(String values) {
            //given
            User user = User.builder()
                    .userId(1L)
                    .firstName(values)
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .nickname("johnny123")
                    .password("password123")
                    .build();
            //when
            //then
            InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userFacade.addUser(user));
            System.out.println(exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "toolonglastname1234567890",
                "Doe123",
                "Doe-",
                "DOE"
        })
        void shouldThrowExceptionWhenLastNameIsNotValid(String values) {
            //given
            User user = User.builder()
                    .userId(1L)
                    .firstName("John")
                    .lastName(values)
                    .email("john.doe@example.com")
                    .nickname("johnny123")
                    .password("password123")
                    .build();
            //when
            //then
            InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userFacade.addUser(user));
            System.out.println(exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "invalid-email",
                "invalid-email@",
                "invalid-email@domain",
                "invalid!email@example.com",
                "123invalid-email@example.com",
                "invalid email@example.com",
                "invalid-email@example.com!",
                "invalid-email@examplecom"
        })
        void shouldThrowExceptionEmailIsNotValid(String values) {
            //given
            User user = User.builder()
                    .userId(1L)
                    .firstName("John")
                    .lastName("Doe")
                    .email(values)
                    .nickname("johnny123")
                    .password("password123")
                    .build();
            //when
            //then
            InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userFacade.addUser(user));
            System.out.println(exception.getMessage());
        }

        @Test
        void shouldThrowExceptionWhenEmailIsTaken() {
            //given
            User user1 = User.builder()
                    .userId(1L)
                    .firstName("John")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .nickname("johnny123")
                    .password("password123")
                    .build();
            User user2 = User.builder()
                    .userId(2L)
                    .firstName("Johnny")
                    .lastName("Doeee")
                    .email("john.doe@example.com")
                    .nickname("johnny1234")
                    .password("password123")
                    .build();
            //when
            userFacade.addUser(user1);
            //then
            InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userFacade.addUser(user2));
            System.out.println(exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "too_short",
                "toolongnickname1234567890",
                "invalid nickname!",
                "nickname_with_spaces",
                "nickname-with-dashes-",
                "_nickname",
                "nickname_",
                "nickname@domain"
        })
        void shouldThrowExceptionWhenNicknameIsNotValid(String values) {
            //given
            User user = User.builder()
                    .userId(1L)
                    .firstName("John")
                    .lastName("Doe")
                    .email(values)
                    .nickname("johnny123")
                    .password("password123")
                    .build();
            //when
            //then
            InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userFacade.addUser(user));
            System.out.println(exception.getMessage());
        }

        @Test
        void shouldThrowExceptionWhenNicknameIsTaken() {
            //given
            User user1 = User.builder()
                    .userId(1L)
                    .firstName("John")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .nickname("johnny123")
                    .password("password123")
                    .build();
            User user2 = User.builder()
                    .userId(2L)
                    .firstName("Johnny")
                    .lastName("Doeee")
                    .email("johnny.doe@example.com")
                    .nickname("johnny123")
                    .password("password123")
                    .build();
            //when
            userFacade.addUser(user1);
            //then
            InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userFacade.addUser(user2));
            System.out.println(exception.getMessage());
        }
    }

    @Nested
    class ValidateAddUser {

        @BeforeEach
        void onInit() {
            User user = User.builder()
                    .userId(1L)
                    .firstName("John")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .nickname("johnny123")
                    .password("password123")
                    .build();

            userFacade.addUser(user);
        }

        @Test
        void shouldThrowExceptionWhenIdIsNotValid() {
            //given
            User updatedUser = User.builder()
                    .userId(1L)
                    .firstName("Johnny")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .nickname("johnny123")
                    .password("password123")
                    .build();
            //when
            //then
            InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userFacade.updateUser(2L, updatedUser));
            System.out.println(exception.getMessage());
        }

        @Test
        void shouldThrowExceptionWhenEmailIsChanged() {
            //given
            User updatedUser = User.builder()
                    .userId(1L)
                    .firstName("Johnny")
                    .lastName("Doe")
                    .email("johnny.doe@example.com")
                    .nickname("johnny123")
                    .password("password123")
                    .build();
            //when
            //then
            InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userFacade.updateUser(1L, updatedUser));
            System.out.println(exception.getMessage());
        }

        @Test
        void shouldThrowExceptionWhenNickNameIsChanged() {
            //given
            User updatedUser = User.builder()
                    .userId(1L)
                    .firstName("Johnny")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .nickname("johnnyJohn123")
                    .password("password123")
                    .build();
            //when
            //then
            InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userFacade.updateUser(1L, updatedUser));
            System.out.println(exception.getMessage());
        }

    }

}