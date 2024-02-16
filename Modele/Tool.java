/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modele;

/**
 *
 * @author naell
 */
public class Tool {
    
    public static int monRandom(int min, int max){
        return (int)(Math.random() * (max - min + 1)) + min;
    }
}
