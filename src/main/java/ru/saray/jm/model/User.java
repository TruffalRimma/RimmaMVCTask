package ru.saray.jm.model;

/*
Задание:
1. Написать CRUD-приложение. Использовать Spring MVC + Hibernate.
2. Должен быть класс User с произвольными полями (id, name и т.п.).
3. В приложении должна быть страница, на которую выводятся
 все юзеры с возможностью добавлять, удалять и изменять юзера.
4. Конфигурация Spring через JavaConfig и аннотации,
 по аналогии с предыдущими проектами. Без использования xml. Без Spring Boot.
5. Внесите изменения в конфигурацию для работы с базой данных.
Вместо SessionFactory должен использоваться EntityManager.
 */

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Field name should not be empty!")
    @Size(min = 2, max = 25, message = "Field name should be between 2 and 25 characters!")
    private String name;

    @Column(name = "nickname")
    @NotEmpty(message = "Field nickname should not be empty!")
    @Size(min = 2, max = 25, message = "Field name should be between 2 and 25 characters!")
    private String nickname;

    @Column(name = "email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    public User() {

    }

    public User(Long id, String name, String nickname, String email) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
