/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectequipos;

/**
 * The User class represents a user in the system.
 * It contains information such as name, username, password, favorite Liga, and country.
 */
public class User {
    /**
     * Constructs a new User object with the specified information.
     *
     * @param name the name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param favoriteLiga the favorite Liga of the user
     * @param pais the country of the user
     */
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

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the favorite Liga of the user.
     *
     * @return the favorite Liga of the user
     */
    public String getFavoriteLiga() {
        return favoriteLiga;
    }

    /**
     * Sets the favorite Liga of the user.
     *
     * @param favoriteLiga the favorite Liga of the user
     */
    public void setFavoriteLiga(String favoriteLiga) {
        this.favoriteLiga = favoriteLiga;
    }

    /**
     * Returns the country of the user.
     *
     * @return the country of the user
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the country of the user.
     *
     * @param pais the country of the user
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
}
