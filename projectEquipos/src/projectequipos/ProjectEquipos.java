/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectequipos;

import java.util.HashMap;
import java.util.Scanner;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

/**
 *
 * @author Jorge
 */
public class ProjectEquipos {

    /**
     * @param args the command line arguments
     */
    
    private User user;
    Scanner scanner = new Scanner(System.in);
    int resp = 0;
    Connection conexion = new Connection();

    int respLogin =0;
    public static void main(String[] args) {
        // TODO code application logic here
        ProjectEquipos project = new ProjectEquipos();
        project.iniciar();
        
    }
    
    
    public void iniciar(){
        user = new User("","","","","");
        menu();
    }
    public void menu(){
        if(user.getUsername().equals("")){
            while(respLogin!=3){
                System.out.println("QUE DESEAS HACER?");
                System.out.println("1. INGRESAR SESIÓN");
                System.out.println("2. REGISTRARSE EN EL SISTEMA");
                System.out.println("3. SALIR DEL PROGRAMA");
                respLogin = scanner.nextInt();
                switch(respLogin){
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        System.out.println("GRACIAS POR UTILIZAR ESTE PROGRAMA");
                        break;
                    default:
                        System.out.println("RECUERDA INGRESAR UNA OPCIÓN VALIDA");
                }
            }
        }else{
            while(resp!=5){
                System.out.println("BIENVENIDO AL SISTEMA DE RECOMENDACIONES PARA SEGUIR A TUS EQUIPOS DE FUTBOL FAVORITOS");
                System.out.println("1. Ver los Equipos de tu liga");
                System.out.println("2. Ver los Equipos más populares de tu liga");
                System.out.println("3. Ver los equipos segun tu país");
                System.out.println("4. filtrado por contenido");
                System.out.println("5. SALIR SESIÓN");
                resp = scanner.nextInt();
                switch(resp){
                    case 1:
                        equiposLiga();
                        break;
                    case 2:
                        equiposPopulares();
                        break;
                    case 3:
                        equiposByPais();
                        break;
                    case 4:
                        filtradoPorContenido();
                    case 5:
                        logout();
                        break;
                    default:
                        System.out.println("RECUERDA INGRESAR UNA OPCIÓN VALIDA");
                }
            }
        }
        
    }
    public void register(){
        System.out.print("INGRESA TU NOMBRE: ");
        String name = scanner.next();
        
        System.out.print("INGRESA TU USUARIO: ");
        String username = scanner.next();
        
        System.out.print("INGRESA TU PASSWORD: ");
        String password = scanner.next();
        
        System.out.print("INGRESA TU LIGA FAVORITA: ");
        String favoriteLiga = scanner.next();
        
        System.out.print("INGRESA TU PAIS: ");
        String pais = scanner.next();
        
        HashMap<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("name", name);         
            parameters.put("username", username);
            parameters.put("password", password);
            parameters.put("favoriteLiga", favoriteLiga);
            parameters.put("pais", pais);
        
        try{
                Session session = conexion.getDriver().session();
                session.run("CREATE (a:User {name:$name, username:$username, password:$password, favoriteLiga:$favoriteLiga,pais:$pais}) RETURN a",
                        parameters);
                System.out.println("USUARIO CREADO EXITOSAMENTE");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void equiposLiga(){
        
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("liga", user.getFavoriteLiga());         
        try{
            Session session = conexion.getDriver().session();
            Result run = session.run("MATCH (equipo:Equipos)-[:JUEGA_EN]->(liga:Liga) WHERE liga.`Liga en la que compite` = $liga RETURN equipo.`Nombre del equipo`", parameters);
            System.out.println("LIGA: "+user.getFavoriteLiga());
            while (run.hasNext()) {
                Record record = run.next();
                String name = record.get("equipo.`Nombre del equipo`").asString();
                System.out.println("EQUIPO: "+name);
            }

        }catch(Exception e){
            e.printStackTrace();
        }  
    }
    
    public void equiposPopulares(){
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("liga", user.getFavoriteLiga());         
        try{
            Session session = conexion.getDriver().session();
            Result run = session.run("MATCH (equipo:Equipos)-[:JUEGA_EN]->(liga:Liga {`Liga en la que compite`:$liga}) MATCH (equipo)-[:NIVEL_POPULARIDAD]->(pop:Popularidad) RETURN equipo.`Nombre del equipo` ORDER BY pop.`Nivel de popularidad` DESC LIMIT 5", parameters);
            System.out.println("LIGA: "+user.getFavoriteLiga());
            System.out.println("LOS EQUIPOS MÁS POPULARES SON: ");
            while (run.hasNext()) {
                Record record = run.next();
                String name = record.get("equipo.`Nombre del equipo`").asString();
                System.out.println("EQUIPO: "+name);
            }

        }catch(Exception e){
            e.printStackTrace();
        }  
    }
    
    
    
    public void equiposByPais(){
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("pais", user.getPais());         
        try{
            Session session = conexion.getDriver().session();
            Result run = session.run("MATCH (pais:Pais {nombre: $pais}) MATCH (equipo:Equipos)-[:PERTENECE_A]->(pais) RETURN equipo.`Nombre del equipo`", parameters);
            System.out.println("EQUIPOS SEGUN TU PAÍS");
            while (run.hasNext()) {
                Record record = run.next();
                String name = record.get("equipo.`Nombre del equipo`").asString();
                System.out.println("EQUIPO: "+name);
            }

        }catch(Exception e){
            e.printStackTrace();
        }  
    }
    
    public void login(){
        System.out.print("INGRESA TU USUARIO: ");
        String username = scanner.next();
        
        System.out.print("INGRESA TU PASSWORD: ");
        String password = scanner.next();
        
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("username", username);         
        parameters.put("password", password);
        
        try{
            Session session = conexion.getDriver().session();        
            Result run = session.run("MATCH (u:User) where u.username=$username and u.password= $password RETURN u.name, u.username, u.password, u.favoriteLiga, u.pais",parameters);
            
            while ( run.hasNext() ){
                Record record = run.next();
                String username1 = record.get("u.username1").asString();
                String name = record.get("u.name").asString();
                String password1 = record.get("u.password1").asString();
                String favoriteLiga = record.get("u.favoriteLiga").asString();
                String pais = record.get("u.pais").asString();
                
                
                user = new User(name,username1,password1,favoriteLiga,pais);
                System.out.println("HAS INGRESADO SESIÓN EXITOSAMENTE");
            }
            
            if(user.getUsername().equals("")){
                System.out.println("USUARIO O CONTRASEÑA INCORRECTA");
            }
            menu();
        }catch(Exception e){
                e.printStackTrace();
        }
        
    }
    
    public void logout(){
        user = new User("","","","","");
        System.out.println("HAS SALIDO DE LA SESIÓN EXITOSAMENTE");
        menu();
    }
    
    
    public void filtradoPorContenido(){
        String text="";
        
        System.out.print("INGRESA EL TEXTO QUE DESEAS BUSCAR: ");
        text = scanner.next();
        
         HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("text", text);         
        try{
            Session session = conexion.getDriver().session();
            Result run = session.run(" MATCH (equipo:Equipos) WHERE toLower(equipo.`Nombre del equipo`) CONTAINS toLower($text) RETURN equipo.`Nombre del equipo`", parameters);
            System.out.println("EQUIPOS SEGUN TU CONTENIDO A BUSCAR");
            while (run.hasNext()) {
                Record record = run.next();
                String name = record.get("equipo.`Nombre del equipo`").asString();
                System.out.println("EQUIPO: "+name);
            }

        }catch(Exception e){
            e.printStackTrace();
        }  
    }
}
