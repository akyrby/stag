/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pro1;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author matej
 */
public class Main7Test {
    @Test
    public void test01()
    {
        assertEquals(
            "31.3.2014,31.3.2015,31.3.2016,30.4.2024,31.12.2024,2.2.2025,31.3.2025,20.4.2025,10.5.2025,30.5.2025,31.5.2025",
            Main7.specializationDeadlines(2025)
        );
    }
}
