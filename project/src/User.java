/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectequipos;

/**
 *
 * @author Jorge
 */
public class User {
    private String name;
    private String username;
    private String password;
    private String favoriteLiga;
    private String pais;

    public User(String name, String username, String password, String favoriteLiga, String pais) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.favoriteLiga = favoriteLiga;
        this.pais = pais;
    }
}
