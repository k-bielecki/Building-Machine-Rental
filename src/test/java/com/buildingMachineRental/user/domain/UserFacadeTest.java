package com.buildingMachineRental.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    private UserFacade userFacade;

    @BeforeEach
    void onInit() {
        userFacade = new UserFacadeConfiguration().inMemoryRepo();
    }

    @Nested
    class CRUDTest {

        @BeforeEach
        void onInit() {
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
                    .firstName("Jane")
                    .lastName("Smith")
                    .email("jane.smith@example.com")
                    .nickname("janeS456")
                    .password("password456")
                    .build();

            User user3 = User.builder()
                    .userId(3L)
                    .firstName("Alice")
                    .lastName("Johnson")
                    .email("alice.johnson@example.com")
                    .nickname("aliceJ789")
                    .password("password789")
                    .build();

            userFacade.addUser(user1);
            userFacade.addUser(user2);
            userFacade.addUser(user3);
        }

        @Test
        void shouldGetAllUsers() {
            //given
            //when
            List<User> users = userFacade.getAllUsers();
            //then
            users.forEach(System.out::println);
            assertThat(users).hasSize(3);
        }

        @Test
        void shouldGetSelectedUser() {
            //given
            //when
            User user = userFacade.getUser(1L);
            //then
            System.out.println(user);
            assertThat(user).isNotNull();
            assertThat(user.getUserId()).isEqualTo(1L);
        }

        @Test
        void shouldThrowExceptionWhenUserNotExist(){
            //given
            //when
            //then
            assertThatThrownBy(() -> userFacade.getUser(4L))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("ID doesn't exist");
        }

        @Test
        void shouldAddNewUser(){
            //given
            User user4 = User.builder()
                    .userId(4L)
                    .firstName("Michael")
                    .lastName("Brown")
                    .email("michael.brown@example.com")
                    .nickname("mikeB123")
                    .password("passwordABC")
                    .build();
            //when
            userFacade.addUser(user4);
            //then
            userFacade.getAllUsers().forEach(System.out::println);
            assertThat(userFacade.getAllUsers()).hasSize(4);
            assertThat(userFacade.getAllUsers()).extracting(User::getFirstName)
                    .contains("Michael");
        }

        @Test
        void shouldUpdateUser(){
            //given
            User updatedUser = User.builder()
                    .userId(1L)
                    .firstName("Mark")
                    .lastName("Doe")
                    .email("john.doe@example.com")
                    .nickname("johnny123")
                    .password("password123")
                    .build();
            //when
            userFacade.updateUser(1L, updatedUser);
            User result = userFacade.getUser(1L);
            //then
            System.out.println(result);
            assertThat(result.getFirstName()).isEqualTo("Mark");
        }

        @Test
        void shouldDeleteUser(){
            //given
            //when
            userFacade.deleteUser(1L);
            //then
            userFacade.getAllUsers().forEach(System.out::println);
            assertThat(userFacade.getAllUsers()).hasSize(2);
            assertThat(userFacade.getAllUsers()).extracting(User::getFirstName)
                    .doesNotContain("John");
        }
    }

}